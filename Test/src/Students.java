import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class Students {
    private static final int N = 5;

    public static void main(String[] args) {
        double[] listOfRaiting;
        try {
            listOfRaiting = randomRating();
            System.out.println(result(listOfRaiting));
        } catch (NegativeArraySizeException e) {
            System.out.println("N is negative");
        }
    }

    private static double[] randomRating() throws NegativeArraySizeException {
        double[] listOfRaitingTemp = new double[N];
        double value;
        for (int i = 0; i < N; i++) {
            value = (Math.random() * 10);
            listOfRaitingTemp[i] = value;
        }
        return listOfRaitingTemp;
    }

    private static Map<Double, Integer> evaluateEqualRaiting(double[] listOfRaiting) {
        double[] listTemp = listOfRaiting;
        Map<Double, Integer> mapTemp = new TreeMap<>();
        for (int i = 0; i < listTemp.length; i++) {
            if (mapTemp.containsKey(listTemp[i])) {
                mapTemp.put(listTemp[i], mapTemp.get(listTemp[i]) + 1);
            } else {
                mapTemp.put(listTemp[i], 1);
            }
        }
        return mapTemp;
    }

    private static int result(double[] list) {
        Map<Double, Integer> map = evaluateEqualRaiting(list);
        int sume = 0;
        int count = 1;
        for (HashMap.Entry<Double, Integer> pair : map.entrySet()) {
            int value = pair.getValue();
            sume = sume + count * value;
            count++;
        }
        return sume;
    }

}
