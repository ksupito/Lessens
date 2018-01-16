import java.util.*;

public class Students {
    public static void main(String[] args) {
        Students student = new Students();
        System.out.println(student.calculateMinumalSum(new String[]{"1", "4", "3"}));
    }

    private ArrayList<Double> convert(String[] stringListOfRaiting) {
        ArrayList<Double> queueOfStudents = new ArrayList<>();

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
        ArrayList<Double> queueOfStudents = convert(stringListOfRaiting);
        ArrayList<Integer> moneysOfStudents = new ArrayList<>();
        int sum = 0;
        for (int i = 0; i < queueOfStudents.size(); i++) {
            moneysOfStudents.add(1);
        }
        for (int i = 0; i < queueOfStudents.size(); i++) {
            boolean comparisonDone = false;
            if (queueOfStudents.size() == 1) {
                break;
            }
            if (queueOfStudents.get(0) > queueOfStudents.get(1) && moneysOfStudents.get(0) <= moneysOfStudents.get(1)) {
                moneysOfStudents.set(0, moneysOfStudents.get(0) + 1);
            }
            for (int j = 1; j < queueOfStudents.size(); j++) {
                if (j == queueOfStudents.size() - 1) {
                    break;
                }
                double left = queueOfStudents.get(j - 1);
                double current = queueOfStudents.get(j);
                double right = queueOfStudents.get(j + 1);
                int leftMoney = moneysOfStudents.get(j - 1);
                int currentMoney = moneysOfStudents.get(j);
                int rightMoney = moneysOfStudents.get(j + 1);

                if ((current > left && currentMoney <= leftMoney) || (current > right && currentMoney <= rightMoney)) {
                    moneysOfStudents.set(j, moneysOfStudents.get(j) + 1);
                    comparisonDone = true;
                } else continue;
            }

            if (queueOfStudents.get(queueOfStudents.size() - 1) > queueOfStudents.get(queueOfStudents.size() - 2)
                    && moneysOfStudents.get(moneysOfStudents.size() - 1) <= moneysOfStudents.get(moneysOfStudents.size() - 2)) {
                moneysOfStudents.set(queueOfStudents.size() - 1, moneysOfStudents.get(queueOfStudents.size() - 1) + 1);
            }
            if (comparisonDone == false) {
                break;
            }
        }

        for (int i = 0; i < moneysOfStudents.size(); i++) {
            sum = sum + moneysOfStudents.get(i);
        }
        return sum;
    }
}
