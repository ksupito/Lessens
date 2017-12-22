import java.io.*;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Task1 {
    public static void main(String[] args) {
        new Threads(".//src//files//doc1.txt");
    }

    public static class Threads extends Thread {
        private String path;
        private ArrayList<Long> listOfLong = new ArrayList<>();
        private FileInputStream file;
        private BufferedReader reader;

        public Threads(String path) {
            this.path = path;
            start();
            returnValue();
        }

        private ArrayList<Long> readFile() {
            listOfLong = new ArrayList<>();
            try {
                file = new FileInputStream(this.path);
                reader = new BufferedReader(new InputStreamReader(file));
                String stringLineTemp;
                while ((stringLineTemp = reader.readLine()) != null) {
                    if (stringLineTemp.length() > 10 || stringLineTemp.length() == 0) {
                        try {
                            throw new LengthIsNotValidException();
                        } catch (LengthIsNotValidException e) {}
                    } else {
                        if (checkWithRegExp(stringLineTemp).find() == true) {
                            try {
                                listOfLong.add(Long.parseLong(stringLineTemp));
                            }catch (NumberFormatException e){}
                        }
                        else {
                            try {
                                throw new Exception();
                            } catch (Exception e) {}
                        }
                    }
                }
            } catch (IOException e) {
            } finally {
                try {
                    if (file != null)
                        file.close();
                } catch (IOException e) {}
            }
            return listOfLong;
        }

        private Matcher checkWithRegExp(String temp) {
            Pattern pattern = Pattern.compile("^\\d+$");
            Matcher matcher = pattern.matcher(temp);
            return matcher;
        }

        private double returnValue() {
            ArrayList<Long> tempListOfLong = new ArrayList<>(readFile());
            long count = 0;
            double value;
            for (int i = 0; i < tempListOfLong.size(); i++) {
                count = count + tempListOfLong.get(i);
            }
            value = count / (double) tempListOfLong.size();
            System.out.println(value);
            return value;
        }
    }


}
