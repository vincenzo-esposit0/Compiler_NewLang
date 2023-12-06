package exceptions;

public class MissingReturnException extends RuntimeException {
    public MissingReturnException(String m) {
        super(m);
    }
}
