public class LengthIsNotValidException extends Exception {
    public LengthIsNotValidException() {
    }

    public LengthIsNotValidException(String message) {
        super(message);
    }

    public LengthIsNotValidException(String message, Throwable cause) {
        super(message, cause);
    }

    public LengthIsNotValidException(Throwable cause) {
        super(cause);
    }

    public LengthIsNotValidException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
