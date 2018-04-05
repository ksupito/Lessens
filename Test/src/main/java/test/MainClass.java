package test;

public class MainClass {
    public static void main(String[] args) {
        // ApplicationContext context = new AnnotationConfigApplicationContext(ConfigClass.class);
        //  HelloWorld helloWorldBean = context.getBean("helloWorld", HelloWorld.class);
        //	helloWorldBean.sayHello();}
        new ConfigClass().write();
    }
}
