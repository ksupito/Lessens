import org.apache.log4j.Logger;
import java.io.IOException;
import java.util.ArrayList;

public class MainClass {
    private static final Logger log = Logger.getLogger(Processor.class.getSimpleName());

    public static void main(String[] args) {
        org.apache.log4j.PropertyConfigurator.configure("src/resources/log4j.properties");
        ArrayList<Long> listOfLong = new ArrayList<>();
        long sum;
        double value;
        Processor processor = new Processor();
        Validator validator = new Validator();

        try {
            listOfLong = new ArrayList<>(processor.readFile(".//src//files//doc1.txt"));
        } catch (IOException e) {
            log.error(e.getMessage());
        }

        sum = validator.sumDigit(listOfLong);
        value = validator.countAverageValue(sum, listOfLong.size());
        System.out.println(value);
    }
}
