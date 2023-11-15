package exceptions;

public class IncompatibleTypeException extends RuntimeException {
    public IncompatibleTypeException(String m) {
        super(m);
    }
}