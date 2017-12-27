import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class Processor {
    private ArrayList<Long> listOfLong = new ArrayList<>();


    public ArrayList<Long> readFile(String path) throws IOException {
        Validator validator = new Validator();

        try (FileInputStream file = new FileInputStream(path);
             BufferedReader reader = new BufferedReader(new InputStreamReader(file))) {
            String stringLineTemp;
            while ((stringLineTemp = reader.readLine()) != null) {
                try {
                    if (validator.isDigit(validator.checkLenght(stringLineTemp)) == true) ;
                    listOfLong.add(Long.parseLong(stringLineTemp));
                } catch (LengthIsNotValidException e) {

                } catch (StringIsNotPositiveAndInteger e) {

                }
            }
            return listOfLong;
        }
    }
}



