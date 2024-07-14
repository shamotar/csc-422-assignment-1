package exceptions;

public class RecordDoesNotExistError extends Exception {
    public RecordDoesNotExistError(String message) {
        super(message);
    }
}
