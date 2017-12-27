import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.logging.Logger;


public class File {
    private ArrayList<String> listOfString;

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


}



