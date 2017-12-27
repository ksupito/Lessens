import java.io.IOException;
import java.util.ArrayList;

public class MainClass {
    public static void main(String[] args) {
        ArrayList<String> listOfString = new ArrayList<>();
        ArrayList<Long> listOfLong = new ArrayList<>();
        long sum;
        double value;
        File file = new File();
        CheckerOfString checker = new CheckerOfString();
        try {
            listOfString = new ArrayList<>(file.readFile(".//src//files//doc1.txt"));
        } catch (IOException e) {
        }
        for (int i = 0; i < listOfString.size(); i++) {
            try {
                if (checker.isDigit(checker.checkLenght(listOfString.get(i))) == true) ;
                listOfLong.add(Long.parseLong(listOfString.get(i)));
            } catch (LengthIsNotValidException e) {
            } catch (StringIsNotPositiveAndInteger e) {
            }
        }
        sum = checker.sumDigit(listOfLong);
        value = checker.countAverageValue(sum, listOfLong.size());
        System.out.println(value);
    }
}
