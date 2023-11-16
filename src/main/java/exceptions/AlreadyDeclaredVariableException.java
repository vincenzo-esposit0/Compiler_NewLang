package exceptions;

public class AlreadyDeclaredVariableException extends RuntimeException{
    public AlreadyDeclaredVariableException(String message){
        super(message);
    }
}