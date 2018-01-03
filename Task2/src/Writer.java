import org.apache.log4j.Logger;

import java.io.*;
import java.util.concurrent.BlockingQueue;

public class Writer implements Runnable {
    private static final Logger log = Logger.getLogger(Writer.class.getSimpleName());
    private BlockingQueue<byte[]> queue;
    private String name = Writer.class.getName();
    private ConsoleReader consoleReader;

    public Writer(BlockingQueue<byte[]> queue, ConsoleReader consoleReader) {
        this.queue = queue;
        this.consoleReader = consoleReader;
    }

    public void run() {
        while (true) {
            try {
                writeFile(createFile(consoleReader.fromConsole(name)));
                break;
            } catch (IOException | PathOfFileIsIncorrect e) {
                log.error(e.getMessage());
            }
        }
    }

    private void writeFile(File file) throws IOException {
        try (OutputStream outputStream = new FileOutputStream(file)) //c://Users//Prest//2.txt
        {
            for (; ; ) {
                try {
                    byte[] buffer = queue.take();
                    if (buffer.length == 0) {
                        break;
                    }
                    outputStream.write(buffer);
                } catch (InterruptedException e) {
                    log.error(e.getMessage());
                }
            }
        }
    }

    private File createFile(String path) throws IOException {
        File file = new File(path);
        if (!file.exists()) {
            file.createNewFile();
        }
        return file;
    }
}