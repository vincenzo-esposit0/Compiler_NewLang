package exceptions;

public class IncompatibleParamException extends RuntimeException {
    public IncompatibleParamException(String m) {
        super(m);
    }
}