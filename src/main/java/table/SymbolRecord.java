package table;

import java.util.ArrayList;

public class SymbolRecord {

    private String symbolName;
    private String kind;    //definisce il tipo VAR o FUN
    private int typeVar;    //valore intero restituito dal TypeChecker
    private ArrayList<Integer> paramsTypeList;
    private ArrayList<Boolean> paramsOutList;
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
    public SymbolRecord(String symbolName, String kind, ArrayList<Integer> paramsTypeList, ArrayList<Boolean> paramsOutList, int returnTypeFun) {
        initialize(symbolName, kind, paramsTypeList, paramsOutList, returnTypeFun);
    }

    private void initialize(String symbolName, String kind, int typeVar) {
        this.symbolName = symbolName;
        this.kind = kind;
        this.typeVar = typeVar;
    }

    private void initialize(String symbolName, String kind, ArrayList<Integer> paramsTypeList, ArrayList<Boolean> paramsOutList, int returnTypeFun) {
        this.symbolName = symbolName;
        this.kind = kind;
        this.paramsTypeList = paramsTypeList;
        this.paramsOutList = paramsOutList;
        this.returnTypeFun = returnTypeFun;
    }

    public String getSymbolName() {
        return symbolName;
    }

    public void setSymbolName(String symbolName) {
        this.symbolName = symbolName;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public int getTypeVar() {
        return typeVar;
    }

    public void setTypeVar(int typeVar) {
        this.typeVar = typeVar;
    }

    public ArrayList<Integer> getParamsTypeList() {
        return paramsTypeList;
    }

    public void setParamsTypeList(ArrayList<Integer> paramsTypeList) {
        this.paramsTypeList = paramsTypeList;
    }

    public ArrayList<Boolean> getParamsOutList() {
        return paramsOutList;
    }

    public void setParamsOutList(ArrayList<Boolean> paramsOutList) {
        this.paramsOutList = paramsOutList;
    }

    public int getReturnTypeFun() {
        return returnTypeFun;
    }

    public void setReturnTypeFun(int returnTypeFun) {
        this.returnTypeFun = returnTypeFun;
    }

    public boolean isPointer() {
        return pointer;
    }

    public void setPointer(boolean pointer) {
        this.pointer = pointer;
    }
}
