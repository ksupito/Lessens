import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class File {
    private ArrayList<String> listOfString;
    private ArrayList<Long> listOfLong = new ArrayList<>();
    IsCorrectDigit checker = new IsCorrectDigit();
    EvaluateValue evaluator = new EvaluateValue();


    public ArrayList<String> readFile(String path) throws IOException {
        listOfString = new ArrayList<>();

        try (FileInputStream file = new FileInputStream(path);
             BufferedReader reader = new BufferedReader(new InputStreamReader(file))) {
            String stringLineTemp;
            while ((stringLineTemp = reader.readLine()) != null) {
                listOfString.add(stringLineTemp);
            }
            return listOfString;
        }
    }

    public ArrayList<Long> checkString() {
        ArrayList<String> listOfStringTemp = new ArrayList<>(listOfString);
        for (int i = 0; i < listOfStringTemp.size(); i++) {
            try {
                if (checker.isDigit(checker.checkLenght(listOfStringTemp.get(i))) == true) ;
                try {
                    listOfLong.add(Long.parseLong(listOfStringTemp.get(i)));
                } catch (NumberFormatException e) {
                }
            } catch (LengthIsNotValidException e) {
            } catch (StringIsNotPositiveAndInteger e) {
            }
        }
        return listOfLong;
    }

    public double countValue() {
        long sumOfValues = evaluator.sumDigit(listOfLong);
        double averageValue = evaluator.countAverageValue(sumOfValues, listOfLong.size());
        return averageValue;
    }


}



