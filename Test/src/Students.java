import java.util.*;

public class Students {
    public static void main(String[] args) {
        Students student = new Students();
        System.out.println(student.calculateMinumalSum(new String[]{"50", "39", "49", "25", "69", "44", "98", "65"}));
    }

    private Queue<Integer> stringToInteger(String[] stringListOfRaiting) {
        Queue<Integer> queueOfStudents = new LinkedList<>();
        for (int i = 0; i < stringListOfRaiting.length; i++) {
            try {
                queueOfStudents.add(Integer.parseInt(stringListOfRaiting[i]));
            } catch (NumberFormatException e) {
                System.out.println("Format the value is incorrect");
            }
        }
        return queueOfStudents;
    }

    private int calculateMinumalSum(String[] stringListOfRaiting) {
        Queue<Integer> queueOfStudents = stringToInteger(stringListOfRaiting);
        int sum = 0;
        int money = 1;
        int firstStudent = queueOfStudents.remove();
        while (!queueOfStudents.isEmpty()) {
            int nextStudent = queueOfStudents.remove();
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
        return sum;
    }
}
