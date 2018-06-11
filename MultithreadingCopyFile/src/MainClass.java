import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;


public class MainClass {

    public static void main(String[] args) {
        org.apache.log4j.PropertyConfigurator.configure("src/resources/log4j.properties");
        ConsoleReader consoleReader = new ConsoleReader();
        BlockingQueue<byte[]> queue = new ArrayBlockingQueue<>(10);
        Reader reader = new Reader(queue, consoleReader);
        Writer writer = new Writer(queue, consoleReader);
        new Thread(reader).start();
        new Thread(writer).start();
    }
}
