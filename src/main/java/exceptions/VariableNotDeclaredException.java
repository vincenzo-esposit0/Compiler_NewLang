package exceptions;

public class VariableNotDeclaredException extends RuntimeException {
    public VariableNotDeclaredException(String nomeID) {
        super("Variabile o funzione " + nomeID + " non dichiarata!");
    }
}
