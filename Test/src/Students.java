import java.util.*;

public class Students {
    public static void main(String[] args) {
        Students student = new Students();
        System.out.println(student.calculateMinumalSum(args));
    }

    private double[] stringToDouble(String[] stringListOfRaiting) {
        double[] listOfRaiting = new double[stringListOfRaiting.length];
        for (int i = 0; i < stringListOfRaiting.length; i++) {
            try {
                listOfRaiting[i] = Double.parseDouble(stringListOfRaiting[i]);
            } catch (NumberFormatException e) {
                System.out.println("Format the value is incorrect");
                throw new IllegalArgumentException("Input data is not valid");
            }
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

    private Map<Double, Integer> sortStudentsByRating(Map<Double, Integer> map) {
        Map<Double, Integer> sortedMap = new LinkedHashMap<>();
        List<Map.Entry<Double, Integer>> entries = new LinkedList<>(map.entrySet());
        Collections.sort(entries, new Comparator<Map.Entry<Double, Integer>>() {
            @Override
            public int compare(Map.Entry<Double, Integer> o1, Map.Entry<Double, Integer> o2) {
                return o1.getKey().compareTo(o2.getKey());
            }
        });
        for (Map.Entry<Double, Integer> pair : entries) {
            sortedMap.put(pair.getKey(), pair.getValue());
        }
        return sortedMap;
    }

    private int calculateMinumalSum(String[] list) {
        Map<Double, Integer> studentsGrouppedByRating;
        int sum = 0;
        int count = 1;
        try {
            studentsGrouppedByRating = sortStudentsByRating(groupStudentsByRating(stringToDouble(list)));
            for (Map.Entry<Double, Integer> pair : studentsGrouppedByRating.entrySet()) {
                int value = pair.getValue();
                sum = sum + count * value;
                count++;
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        return sum;
    }
}
