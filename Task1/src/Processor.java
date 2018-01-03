import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.concurrent.Phaser;

public class Processor extends Thread {
    private ArrayList<Long> listOfLong = new ArrayList<>();
    private static final Logger log = Logger.getLogger(Processor.class.getSimpleName());
    private Phaser phaser;
    private String pathToFile;
    private long sum;
    double valueOfThread;
    Validator validator = new Validator();
    private ArrayList<Long> listOfLongForOneThread  = new ArrayList<>();

    public Processor(Phaser phaser, String pathToFile, double valueOfThread) {
        this.phaser = phaser;
        this.pathToFile = pathToFile;
        this.valueOfThread = valueOfThread;
    }

    @Override
    public void run() {
        try {
            listOfLongForOneThread.addAll(readFile());
        } catch (IOException e) {
            log.error(e.getMessage());
        }
        sum = validator.sumDigit(listOfLongForOneThread);
        valueOfThread = validator.countAverageValue(sum, listOfLongForOneThread.size());

        phaser.arrive();
    }

    private synchronized ArrayList<Long> readFile() throws IOException {
        try (FileInputStream file = new FileInputStream(pathToFile);
             BufferedReader reader = new BufferedReader(new InputStreamReader(file))) {
            String stringLineTemp;
            while ((stringLineTemp = reader.readLine()) != null) {
                try {
                    if (validator.isDigit(validator.checkLenght(stringLineTemp)) == true) ;
                    listOfLong.add(Long.parseLong(stringLineTemp));
                } catch (LengthIsNotValidException | StringIsNotPositiveAndInteger e) {
                    log.error(e.getMessage());
                }
            }
         return listOfLong;
        }
    }
}



