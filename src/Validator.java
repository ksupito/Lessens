import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {
    private double value;

    public boolean isDigit(String stringLine) throws StringIsNotPositiveAndInteger {
        Pattern pattern = Pattern.compile("^\\d+$");
        Matcher matcher = pattern.matcher(stringLine);
        if (matcher.find() == true) {
            return true;
        } else {
            throw new StringIsNotPositiveAndInteger(String.format("String '%s' is not positive and integer", stringLine));
        }
    }

    public String checkLenght(String stringLine) throws LengthIsNotValidException {
        if (stringLine.length() > 10 || stringLine.length() < 0) {
            throw new LengthIsNotValidException(String.format("Length of '%s' is incorrect,the length have to be  less than 10 and greater than 0", stringLine));
        }
        return stringLine;
    }

    public synchronized long sumDigit(ArrayList<Long> listOfLong) {
        long count = 0;
        ArrayList<Long> tempListOfLong = new ArrayList<>(listOfLong);
        for (int i = 0; i < tempListOfLong.size(); i++) {
            count = count + tempListOfLong.get(i);
        }
        return count;
    }

    public synchronized double countAverageValue(long longValue, int count) {
        value = longValue / (double) count;
        return value;
    }
}
