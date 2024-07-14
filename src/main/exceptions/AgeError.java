package exceptions;

public class AgeError extends Exception {
    public AgeError(String message) {
        super("AgeError: " + message);
    }

}
