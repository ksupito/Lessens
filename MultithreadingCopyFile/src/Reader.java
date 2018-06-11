import org.apache.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.concurrent.BlockingQueue;

public class Reader implements Runnable {
    private static final Logger log = Logger.getLogger(Reader.class.getSimpleName());
    private BlockingQueue<byte[]> queue;
    private int countOfArray = 3;
    private byte[] buffer;
    private String name = Reader.class.getName();
    private ConsoleReader consoleReader;

    public Reader(BlockingQueue<byte[]> queue, ConsoleReader consoleReader) {
        this.queue = queue;
        this.consoleReader = consoleReader;
    }

    public void run() {
        while (true) {
            try {
                readFile(consoleReader.fromConsole(name));
                break;
            } catch (IOException | PathOfFileIsIncorrect e) {
                log.error(e.getMessage());
            }
        }
    }

    private void readFile(String path) throws IOException {
        try (FileInputStream inputStream = new FileInputStream(path)) {//e://1.txt
            try {
                while (inputStream.available() > 0) {
                    buffer = new byte[countOfArray];
                    inputStream.read(buffer);
                    queue.put(buffer);
                }
                queue.add(new byte[0]);
            } catch (InterruptedException e) {
                log.error(e.getMessage());
            }
        }
    }
}
