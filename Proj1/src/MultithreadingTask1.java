import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;




public class MultithreadingTask1  {
    static String OnePath;
    static double SumMediumValuesFromAllFiles;
    static double FinelValue = 0;
    static String path = ".//src//files";
    static double FinelSum;

    public static void main(String[] args) {
        File directory = new File(path);
        File[] list = directory.listFiles();

        for (int i = 0; i < list.length; i++) {
          
            Thread threads = new Thread(new RunClass());
            OnePath = list[i].toString();
            threads.start();
            FinelSum = FinelSumMethod();
            SumMediumValuesFromAllFiles = SumMediumValuesFromAllFiles + FinelSum;
        }

        FinelValue = SumMediumValuesFromAllFiles / list.length;
        System.out.println(FinelValue);

    }
    public static synchronized double FinelSumMethod(){
        double value = 0;
        ArrayList<Character> ListOfChar = new ArrayList<>();
        try{
            FileInputStream file  = new FileInputStream(OnePath);

            while (file.available() > 0) {
            int s = file.read();
            ListOfChar.add((char)s);
        }
            file.close();

            ArrayList<Integer> ListOfInteger = new ArrayList<>(ArrayKsu(ListOfChar));
            value = Value(ListOfInteger);

        }catch (IOException e){}
     return value;
    }

    public static  ArrayList<Integer> ArrayKsu(ArrayList<Character> Char){
        ArrayList<Character> ListOfChar = new ArrayList<>(Char);
        ArrayList<Integer> ListOfInteger = new ArrayList<>();
        int intForChar = 0;

        for (int i = 0; i < ListOfChar.size(); i++) {
            if (ListOfChar.get(i) == '\r') {
                ListOfInteger.add(intForChar);
                intForChar = 0;
                i++;
            } else if (i == ListOfChar.size() - 1) {
                intForChar = intForChar * 10 + Character.getNumericValue(ListOfChar.get(i));
                ListOfInteger.add(intForChar);
            } else {
                intForChar = intForChar * 10 + Character.getNumericValue(ListOfChar.get(i));
            }
        }
        return  ListOfInteger;

    }


    public static  double Value(ArrayList<Integer> h){
        double value;
        int sum = 0;
        ArrayList<Integer> ListOfInteger = new ArrayList<>(h);

        for (int i = 0; i < ListOfInteger.size(); i++) {
            sum = ListOfInteger.get(i) + sum;
        }

        value = sum / ListOfInteger.size();
        return value;
    }

}
 class RunClass implements Runnable{
    public void run() {
    }


 }