package test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "test")
public class ConfigClass {
    HelloWorld helloWorld;

    @Autowired
    public void setMyClass(HelloWorld myClass) {
        this.helloWorld = myClass;
    }

    public void write() {
        helloWorld.sayHello();
    }
}
