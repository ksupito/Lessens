import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.Phaser;


public class ThreadsWithPhaser {

    public static void main(String[] args){
        String onePath;
        String path = ".//src//files";
        File directory = new File(path);
        File[] listOfFiles = directory.listFiles();
        Phaser phaser = new Phaser(listOfFiles.length + 1);
        ArrayList<Threads> listOfThreads = new ArrayList<>();
        double  sumOfValueOfThreads = 0;
        double finelAverageValue;

        for(int i = 0; i < listOfFiles.length; i++){
            onePath = listOfFiles[i].toString();
            listOfThreads.add(new Threads(phaser, 0, onePath));
        }

        phaser.arriveAndAwaitAdvance();
        for(int i = 0; i < listOfThreads.size(); i++){
            sumOfValueOfThreads = sumOfValueOfThreads + listOfThreads.get(i).value;
        }
        finelAverageValue = sumOfValueOfThreads/listOfFiles.length;
        System.out.println(finelAverageValue);
    }

    public static class Threads extends Thread{
        private Phaser phaser;
        private double value;
        private String pathToFile;
        private ArrayList<Integer> listOfInteger;
        private ArrayList<Character> listOfChar;
        private FileInputStream file;
        private int variableForAdding = 0;
        private int sumOfValues;

        public Threads(Phaser phaser, int value, String path){
            this.phaser = phaser;
            this.value = value;
            this.pathToFile = path;
            start();
            getAverage();
        }

        @Override
        public void run(){
           phaser.arrive();
        }

        private void getAverage(){
            listOfInteger = new ArrayList<>(convertCharToInt(readFile()));
            value = evaluateAverage(listOfInteger);
        }

        private ArrayList<Character> readFile(){
            listOfChar = new ArrayList<>();
            try{
                    file  = new FileInputStream(pathToFile);
                    while (file.available() > 0){
                        int n = file.read();
                        listOfChar.add((char)n);
                    }
            }catch (IOException e){}
            finally {
                try {
                    if(file!=null)
                        file.close();

                }catch (IOException e){}
            }
            return listOfChar;
        }

        private ArrayList<Integer> convertCharToInt(ArrayList<Character> character){
            this.listOfChar = new ArrayList<>(character);
            listOfInteger = new ArrayList<>();
            for(int i = 0; i < listOfChar.size(); i++){
                if(listOfChar.get(i) == '\r'){
                    listOfInteger.add(variableForAdding);
                    variableForAdding = 0;
                    i++;
                } else if(i == listOfChar.size() - 1){
                    variableForAdding = variableForAdding * 10 + Character.getNumericValue(listOfChar.get(i));
                    listOfInteger.add(variableForAdding);
                } else{
                    variableForAdding = variableForAdding * 10 + Character.getNumericValue(listOfChar.get(i));
                }
            }
            return listOfInteger;
        }

        private double evaluateAverage(ArrayList<Integer> integer){
            this.listOfInteger = new ArrayList<>(integer);
            for(int i = 0; i < listOfInteger.size(); i++){
                sumOfValues = listOfInteger.get(i) + sumOfValues;
            }
            value = sumOfValues/listOfInteger.size();
            return value;
        }
    }
}
