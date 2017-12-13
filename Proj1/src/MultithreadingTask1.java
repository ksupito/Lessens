import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;


public class MultithreadingTask1 implements Runnable{
    static String OnePath;
    static double SumMediumValuesFromAllFiles;
    static double FinelValue = 0;
    static String path = "E://Ksupito//Projects//Proj1//src//files";
    public static void main(String[] args) throws Exception{
        File dir = new File(path);
        File[] list = dir.listFiles();
        Thread threads;

        for(int i = 0; i <1; i++){
            threads =  new Thread(new MultithreadingTask1());
            OnePath = list[i].toString();
            threads.start();
            Thread.sleep(1000);
        }

        FinelValue = SumMediumValuesFromAllFiles/list.length;
        //System.out.println(FinelValue);



    }
    public double MediumValueFromFile(){
        double value = 0;

        try{FileInputStream ins = new FileInputStream (OnePath);
            long sum = 0;
            String sd = "";
            ArrayList<Integer> h = new ArrayList<>();
            ArrayList<Character> hdd = new ArrayList<>();
            while (ins.available() > 0){
                int s = ins.read();
               // int d = Character.getNumericValue((char)s);

                //sd = sd + Character.toString((char)s);
                //System.out.print(sd);
                //h.add(d);
               hdd.add((char)s);

            }
            hdd.add('\r');
            hdd.add('\n');
            ins.close();
            int r = 0;
            char[] n = new char[10];
            String v = "";
            ArrayList<String> qweqwe = new ArrayList<>();
            for(int i = 0; i < hdd.size(); i++){
                if(hdd.get(i) =='\r'){
                   // System.out.println(v);
                    qweqwe.add(v);

                    v = "";
                    r++;
                    i++;

                }
                else {

                    v = v + Character.toString(hdd.get(i));

                }



            }
            ArrayList<Integer> cc = new ArrayList<>();
            int a = Integer.parseInt(qweqwe.get(0));
            //cc.add(a);
            String dss= "12";
            //System.out.println(a);
            for(int i = 0; i <qweqwe.size(); i++){
                int dg = Integer.parseInt(qweqwe.get(i));
                cc.add(dg);

                System.out.println(cc.get(i));

            }

            

            /*for (int i = 0 ; i < asddd.size(); i ++){
                sum = Integer.parseInt(asddd.get(i)) + sum;

            }
            value = sum /h.size();*/

        }
        catch (Exception e){}
        return value;}


    public void run() {
        double Sum = MediumValueFromFile();
        SumMediumValuesFromAllFiles = SumMediumValuesFromAllFiles + Sum;

    }


}