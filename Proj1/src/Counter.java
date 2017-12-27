import java.util.ArrayList;

public class Counter {
    private double value;

    public long sumDigit(ArrayList<Long> listOfLong) {

        long count = 0;
        ArrayList<Long> tempListOfLong = new ArrayList<>(listOfLong);
        for (int i = 0; i < tempListOfLong.size(); i++) {
            count = count + tempListOfLong.get(i);
        }
        return count;
    }

    public double countAverageValue(long value, int count) {
        this.value = value / (double) count;
        return this.value;
    }
}
