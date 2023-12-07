package table;

import java.util.ArrayList;

public class SymbolRecord {

    private String symbolName;
    private String kind;    //definisce il tipo VAR o FUN
    private int typeVar;    //valore intero restituito dal TypeChecker
    private ParInitialize parInitialize;
    private int returnTypeFun;
    private boolean pointer = false;

    //Costruttore per variabili
    public SymbolRecord(String symbolName, String kind, int typeVar) {
        initialize(symbolName, kind, typeVar);
    }

    //Costruttore per variabili puntatori
    public SymbolRecord(String symbolName, String kind, int typeVar, boolean pointer) {
        initialize(symbolName, kind, typeVar);
        this.pointer = pointer;
    }

    //Costruttore per funzioni
    public SymbolRecord(String symbolName, String kind, ParInitialize parInitialize, int returnTypeFun) {
        initialize(symbolName, kind, parInitialize, returnTypeFun);
    }

    private void initialize(String symbolName, String kind, int typeVar) {
        this.symbolName = symbolName;
        this.kind = kind;
        this.typeVar = typeVar;
    }

    private void initialize(String symbolName, String kind, ParInitialize parInitialize, int returnTypeFun) {
        this.symbolName = symbolName;
        this.kind = kind;
        this.parInitialize = parInitialize;
        this.returnTypeFun = returnTypeFun;
    }

    public String getKind() {
        return kind;
    }

    public int getTypeVar() {
        return typeVar;
    }

    public int getReturnTypeFun() {
        return returnTypeFun;
    }

    public boolean isPointer() {
        return pointer;
    }

    public ParInitialize getParInitialize() {
        return parInitialize;
    }

    @Override
    public String toString() {
        return "SymbolRecord{" +
                "symbolName='" + symbolName + '\'' +
                ", kind='" + kind + '\'' +
                ", typeVar=" + typeVar +
                ", parInitialize=" + parInitialize +
                ", returnTypeFun=" + returnTypeFun +
                ", pointer=" + pointer +
                '}'+ "\n";
    }
}
