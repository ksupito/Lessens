import com.oracle.tools.packager.Log;

import java.io.*;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Task1 {
    public static void main(String[] args) {
        Threads thread = new Threads(".//src//files//doc1.txt");
        thread.start();
        thread.returnValue();
    }

    public static class Threads extends Thread {
        private String path;
        private ArrayList<Long> listOfLong = new ArrayList<>();

        public Threads(String path) {
            this.path = path;
        }

        private ArrayList<Long> readFile() {
            listOfLong = new ArrayList<>();
            try (FileInputStream file = new FileInputStream(this.path);
                 BufferedReader reader = new BufferedReader(new InputStreamReader(file))) {
                String stringLineTemp;
                while ((stringLineTemp = reader.readLine()) != null) {
                    if (stringLineTemp.length() > 10 || stringLineTemp.length() == 0) {
                        try {
                            throw new LengthIsNotValidException(String.format("Length of '%s' is incorrect,the length have to be  less than 10 and greater than 0", stringLineTemp));
                        } catch (LengthIsNotValidException e) {
                            e.printStackTrace(System.out);
                        }
                    } else {
                        try {
                            if (isDigit(stringLineTemp) == true) {
                                try {
                                    listOfLong.add(Long.parseLong(stringLineTemp));
                                } catch (NumberFormatException e) {
                                    e.getMessage();
                                }
                            }
                        } catch (StringIsNotPositiveAndInteger e) {
                            e.printStackTrace(System.out);
                        }
                    }
                }
            } catch (IOException e) {
            }
            return listOfLong;
        }

        private boolean isDigit(String stringLine) throws StringIsNotPositiveAndInteger {
            Pattern pattern = Pattern.compile("^\\d+$");
            Matcher matcher = pattern.matcher(stringLine);
            if (matcher.find() == true) {
                return true;
            } else {
                throw new StringIsNotPositiveAndInteger(String.format("String '%s' is not positive and integer", stringLine));
            }
        }

        private double returnValue() {
            double value;
            long count = 0;
            ArrayList<Long> tempListOfLong = new ArrayList<>(readFile());
            for (int i = 0; i < tempListOfLong.size(); i++) {
                count = count + tempListOfLong.get(i);
            }
            value = count / (double) tempListOfLong.size();
            System.out.println(value);
            return value;
        }
    }


}
