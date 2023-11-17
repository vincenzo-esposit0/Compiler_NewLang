package exceptions;

public class IncompatibleNumberParamException extends RuntimeException {
    public IncompatibleNumberParamException(String m) {
        super(m);
    }
}