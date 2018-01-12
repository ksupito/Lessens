import java.text.DecimalFormat;
import java.util.*;

public class Students {
    public static void main(String[] args) {
        Students student = new Students();
        System.out.println(student.calculateMinumalSum(new String[]{"50", "39.23234234", "49.33055", "25", "69", "44", "98", "65"}));
    }

    private Queue<Double> convert(String[] stringListOfRaiting) {
        Queue<Double> queueOfStudents = new LinkedList<>();

        for (int i = 0; i < stringListOfRaiting.length; i++) {
            try {
                if (Double.parseDouble(stringListOfRaiting[i]) < 0) {
                    throw new IllegalArgumentException("Input data is not valid");
                }
                queueOfStudents.add(Math.round(Double.parseDouble(stringListOfRaiting[i]) * 100) / 100.00);
            } catch (NumberFormatException e) {
                System.out.println("Format the value is incorrect");
                throw new IllegalArgumentException("Input data is not valid");
            }
        }
        return queueOfStudents;
    }

    private int calculateMinumalSum(String[] stringListOfRaiting) {
        Queue<Double> queueOfStudents = convert(stringListOfRaiting);
        int sum = 0;
        int money = 1;
        if (queueOfStudents.size() == 1) {
            sum = money;
        } else {
            double firstStudent = queueOfStudents.remove();
            while (!queueOfStudents.isEmpty()) {
                double nextStudent = queueOfStudents.remove();
                if (firstStudent > nextStudent) {
                    sum = sum + money + 1;
                }
                if (firstStudent <= nextStudent) {
                    sum = sum + money;
                }
                firstStudent = nextStudent;
                if (queueOfStudents.size() == 0) {
                    sum = sum + money;
                }
            }
        }
        return sum;
    }
}
