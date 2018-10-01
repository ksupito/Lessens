package com.vizorgames.interview.ioc;

import com.vizorgames.interview.exception.BindingNotFoundException;
import com.vizorgames.interview.exception.ConstructorAmbiguityException;
import com.vizorgames.interview.exception.NoSuitableConstructorException;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Parameter;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.inject.Inject;

public class InjectorImpl implements Injector {
    private Map<Class, Class> singleton = new ConcurrentHashMap<>();
    private Map<Class, Class> prototype = new ConcurrentHashMap<>();
    private Map<Class, Object> createdSingletons = new ConcurrentHashMap<>();

    @Override
    public <T> Provider<T> getProvider(Class<T> type) {
        if (singleton.containsKey(type) || prototype.containsKey(type)) {
            prepareInstance(type, true);
            return new Provider<T>() {
                @Override
                public T getInstance() {
                    return prepareInstance(type, false);
                }
            };
        }
        return null;
    }

    private <T> T prepareInstance(Class<T> type, Boolean isCheck) {
        try {
            return createInstance(type, isCheck);
        } catch (InstantiationException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    private <T> T createInstance(Class<T> type, Boolean isCheck) throws IllegalAccessException, InstantiationException, InvocationTargetException {
        T newInstance;
        Constructor<T> constructor = getConstructor(findValue(type));
        synchronized (this) {
            if (singleton.containsKey(type)) {
                if (createdSingletons.containsKey(type)) {
                    return (T) createdSingletons.get(type);
                } else {
                    newInstance = createNewInstance(constructor, isCheck);
                    if (newInstance != null) {
                        createdSingletons.put(type, newInstance);
                    }
                    return newInstance;
                }
            } else {
                return createNewInstance(constructor, isCheck);
            }
        }
    }

    private <T> T createNewInstance(Constructor<T> constructor, Boolean isCheck) throws IllegalAccessException, InvocationTargetException, InstantiationException {
        if (constructor.getParameters().length == 0) {
            if (isCheck) return null;
            return constructor.newInstance();
        } else {
            List<Object> list = checkParams(constructor, isCheck);
            if (isCheck) return null;
            return constructor.newInstance(list.toArray());
        }
    }

    private <T> List<Object> checkParams(Constructor<T> constructor, Boolean isCheck) throws IllegalAccessException, InvocationTargetException, InstantiationException {
        List<Object> listObjects = new ArrayList<>();
        Parameter[] parameters = constructor.getParameters();
        for (Parameter parameter : parameters) {
            if (prototype.containsKey(parameter.getType())) {
                listObjects.add(createInstance(parameter.getType(), isCheck));
            } else if (singleton.containsKey(parameter.getType())) {
                listObjects.add(createInstance(parameter.getType(), isCheck));
            } else throw new BindingNotFoundException();
        }
        return listObjects;
    }

    private Constructor checkDefaultConstructor(Class cls) {
        Constructor[] listConstructors = cls.getConstructors();
        if (listConstructors.length > 1) {
            throw new NoSuitableConstructorException();
        }
        if (listConstructors[0].getParameters().length > 0)
            throw new NoSuitableConstructorException();
        else return listConstructors[0];
    }

    private <T> Class findValue(Class<T> type) {
        if (singleton.containsKey(type)) {
            return singleton.get(type);
        } else return prototype.get(type);
    }

    private <T> Constructor<T> getConstructor(Class cls) {
        List<Constructor> constructors = Stream
                .of(cls.getConstructors())
                .filter(c -> c.isAnnotationPresent(Inject.class))
                .collect(Collectors.toList());
        if (constructors.size() > 1)
            throw new ConstructorAmbiguityException();
        if (constructors.size() == 1) {
            return constructors.get(0);
        } else return checkDefaultConstructor(cls);
    }

    @Override
    public <T> void bind(Class<T> base, Class<? extends T> impl) {
        prototype.put(base, impl);
    }

    @Override
    public <T> void bindSingleton(Class<T> base, Class<? extends T> impl) {
        singleton.put(base, impl);
    }
}
