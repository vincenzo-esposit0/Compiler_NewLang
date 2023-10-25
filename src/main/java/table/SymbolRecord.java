package table;

import java.util.ArrayList;

public class SymbolRecord {

    private String symbolName;
    private String typeVarOrFun;    //definisce il tipo VAR o FUN
    private int typeVar;    //valore intero restituito dal TypeChecker
    private ArrayList<Integer> paramsTypeList;
    private ArrayList<Boolean> paramsOutList;
    private int returnTypeFun;
    private boolean pointer = false;

    //Costruttore per variabili
    public SymbolRecord(String symbolName, String typeVarOrFun, int typeVar) {
        initialize(symbolName, typeVarOrFun, typeVar);
    }

    //Costruttore per variabili puntatori
    public SymbolRecord(String symbolName, String typeVarOrFun, int typeVar, boolean pointer) {
        initialize(symbolName, typeVarOrFun, typeVar);
        this.pointer = pointer;
    }

    //Costruttore per funzioni
    public SymbolRecord(String symbolName, String typeVarOrFun, ArrayList<Integer> paramsTypeList, ArrayList<Boolean> paramsOutList, int returnTypeFun) {
        initialize(symbolName, typeVarOrFun, paramsTypeList, paramsOutList, returnTypeFun);
    }

    private void initialize(String symbolName, String typeVarOrFun, int typeVar) {
        this.symbolName = symbolName;
        this.typeVarOrFun = typeVarOrFun;
        this.typeVar = typeVar;
    }

    private void initialize(String symbolName, String typeVarOrFun, ArrayList<Integer> paramsTypeList, ArrayList<Boolean> paramsOutList, int returnTypeFun) {
        this.symbolName = symbolName;
        this.typeVarOrFun = typeVarOrFun;
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

    public String getTypeVarOrFun() {
        return typeVarOrFun;
    }

    public void setTypeVarOrFun(String typeVarOrFun) {
        this.typeVarOrFun = typeVarOrFun;
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
