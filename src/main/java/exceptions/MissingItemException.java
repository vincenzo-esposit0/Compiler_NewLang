package exceptions;

public class MissingItemException extends RuntimeException {
    public MissingItemException(String m) {
        super(m);
    }
}
