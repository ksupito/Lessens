import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IsCorrectDigit {

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
}
