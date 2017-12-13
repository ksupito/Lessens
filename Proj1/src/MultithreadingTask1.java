import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;

/**/


public class MultithreadingTask1 implements Runnable{
    static String OnePath;
    static double SumMediumValuesFromAllFiles;
    static double FinelValue = 0;
    static String path = "E://Ksupito1//Projects//Proj1//src//files";
    public static void main(String[] args) throws Exception{
        File dir = new File(path);
        File[] list = dir.listFiles();
        Thread threads;

        for(int i = 0; i <list.length; i++){
            threads =  new Thread(new MultithreadingTask1());
            OnePath = list[i].toString();
            threads.start();
            Thread.sleep(50);
        }

        FinelValue = SumMediumValuesFromAllFiles/list.length;
        System.out.println(FinelValue);

    }
    public double MediumValueFromFile(){
        double value = 0;
        int intForChar = 0;
        int sum = 0;
        ArrayList<Integer> ListOfInteger = new ArrayList<>();
        ArrayList<Character> ListOfChar = new ArrayList<>();

        try{FileInputStream file = new FileInputStream (OnePath);

            while (file.available() > 0){
                int s = file.read();
                ListOfChar.add((char)s);
            }
            file.close();

            for(int i = 0; i < ListOfChar.size(); i++){
                if(ListOfChar.get(i) =='\r'){
                    ListOfInteger.add(intForChar);
                    intForChar = 0;
                    i++;
                }
                else if(i == ListOfChar.size()-1){  intForChar = intForChar *10 + Character.getNumericValue(ListOfChar.get(i));
                    ListOfInteger.add(intForChar);}

                else {
                    intForChar = intForChar *10 + Character.getNumericValue(ListOfChar.get(i));
                }
            }

            for (int i = 0 ; i < ListOfInteger.size(); i ++){
                sum = ListOfInteger.get(i) + sum;
            }
            value = sum /ListOfInteger.size();
        }
        catch (Exception e){}
        return value;}


    public void run() {
        double FinelSum = MediumValueFromFile();
        SumMediumValuesFromAllFiles = SumMediumValuesFromAllFiles + FinelSum;

    }


}