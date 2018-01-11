import java.util.*;

public class Students {
    public static void main(String[] args) {
        Students student = new Students();
        System.out.println(student.calculateMinumalSum(args));
    }

    private double[] stringToDouble(String[] stringListOfRaiting){
        double[] listOfRaiting = new double[stringListOfRaiting.length];
        for(int i = 0; i < stringListOfRaiting.length; i++){
            listOfRaiting[i] = Double.parseDouble(stringListOfRaiting[i]);
        }
        return listOfRaiting;
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

    private Map<Double, Integer> sortStudentsByRating(Map<Double, Integer> map){
        List<Map.Entry<Double, Integer>> entries = new LinkedList<>(map.entrySet());
        Collections.sort(entries, new Comparator<Map.Entry<Double, Integer>>() {
            @Override
            public int compare(Map.Entry<Double, Integer> o1, Map.Entry<Double, Integer> o2) {
                return o1.getKey().compareTo(o2.getKey());
            }
        });
        Map<Double, Integer> sortedMap = new LinkedHashMap<>();
        for (Map.Entry<Double, Integer> pair : entries) {
            sortedMap.put(pair.getKey(), pair.getValue());
        }
        return sortedMap;
    }

    private int calculateMinumalSum(String[] list) {
        Map<Double, Integer> studentsGrouppedByRating = sortStudentsByRating(groupStudentsByRating(stringToDouble(list)));
        int sum = 0;
        int count = 1;

        for (Map.Entry<Double, Integer> pair : studentsGrouppedByRating.entrySet()) {
            int value = pair.getValue();
            sum = sum + count * value;
            count++;
        }
        return sum;
    }
}
