import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.Phaser;

public class MainClass {

    public static void main(String[] args) {
        org.apache.log4j.PropertyConfigurator.configure("src/resources/log4j.properties");

        String onePath;
        String path = ".//src//files";
        File directory = new File(path);
        File[] listOfFiles = directory.listFiles();
        Phaser phaser = new Phaser(listOfFiles.length + 1);
        ArrayList<Processor> listOfThreads = new ArrayList<>();
        double sumOfValue = 0;
        double averageValue;

        for (int i = 0; i < listOfFiles.length; i++) {
            onePath = listOfFiles[i].toString();
            Processor processor = new Processor(phaser, onePath, 0);
            processor.start();
            listOfThreads.add(processor);
        }

        phaser.arriveAndAwaitAdvance();

        for (int i = 0; i < listOfThreads.size(); i++) {
            sumOfValue = sumOfValue + listOfThreads.get(i).valueOfThread;
        }
        averageValue = sumOfValue / (double) listOfThreads.size();
        System.out.println(averageValue);


    }
}
