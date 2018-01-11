import java.util.*;

public class Students {
    public static void main(String[] args) {
        Students student = new Students();
        double[] listOfRaiting = new double[]{2.7, 3.4, 10.0, 2.7, 5.6, 43.5, 43.5};
        System.out.println(student.calculateMinumalSum(listOfRaiting));
    }

    private Map<Double, Integer> groupStudentsByRating(double[] listOfRaiting) {
        Map<Double, Integer> studentsGrouppedByRating = new HashMap<>();
        for (int i = 0; i < listOfRaiting.length; i++) {
            if (studentsGrouppedByRating.containsKey(listOfRaiting[i])) {
                studentsGrouppedByRating.put(listOfRaiting[i], studentsGrouppedByRating.get(listOfRaiting[i]) + 1);
            } else {
                studentsGrouppedByRating.put(listOfRaiting[i], 1);
            }
        }
        return studentsGrouppedByRating;
    }

    private int calculateMinumalSum(double[] list) {
        Map<Double, Integer> studentsGrouppedByRating = groupStudentsByRating(list);
        int sume = 0;
        int count = 1;

        List<Map.Entry<Double, Integer>> entries = new LinkedList<>(studentsGrouppedByRating.entrySet());
        Collections.sort(entries, new Comparator<Map.Entry<Double, Integer>>() {
            @Override
            public int compare(Map.Entry<Double, Integer> o1, Map.Entry<Double, Integer> o2) {
                return o1.getKey().compareTo(o2.getKey());
            }
        });
        for (Map.Entry<Double, Integer> pair : entries) {
            int value = pair.getValue();
            sume = sume + count * value;
            count++;
        }
        return sume;
    }
}
