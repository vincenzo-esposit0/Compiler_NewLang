package visitor;

import esercitazione5.sym;

public class MyTypeChecker {

    public static int getInferenceType(String type) {
        switch (type) {
            case "INTEGER":
            case "INTEGER_CONST":
                return sym.INTEGER;
            case "REAL":
            case "REAL_CONST":
                return sym.REAL;
            case "TRUE":
            case "FALSE":
            case "BOOL":
                return sym.BOOL;
            case "STRING":
            case "STRING_CONST":
                return sym.STRING;
            case "CHAR":
            case "CHAR_CONST":
                return sym.CHAR;
            case "VOID":
                return sym.VOID;
            default:
                return sym.error;
        }

    }
}
