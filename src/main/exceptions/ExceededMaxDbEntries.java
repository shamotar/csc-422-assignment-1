package exceptions;

public class ExceededMaxDbEntries extends Exception {
    public ExceededMaxDbEntries(String message) {
        super("ExceededMaxDbEntries: " + message);
    }
}
