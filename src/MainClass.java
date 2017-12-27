
import java.io.IOException;
import java.util.ArrayList;

public class MainClass {


    public static void main(String[] args) {
        ArrayList<Long> listOfLong = new ArrayList<>();
        long sum;
        double value;
        Processor processor = new Processor();
        Validator validator = new Validator();
        try {
            listOfLong = new ArrayList<>(processor.readFile(".//src//files//doc1.txt"));
        } catch (IOException e) {

        }

        sum = validator.sumDigit(listOfLong);
        value = validator.countAverageValue(sum, listOfLong.size());
        System.out.println(value);
    }
}
