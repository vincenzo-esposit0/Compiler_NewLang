package table;

import java.util.HashMap;

public class SymbolTable extends HashMap<String, SymbolRecord> {

    private String scopeName;
    private String functionName;

    public SymbolTable(String scopeName){
        initialize(scopeName, null);
    }

    public SymbolTable(String scopeName, String functionName){
        initialize(scopeName, functionName);
    }

    private void initialize(String scopeName, String functionName) {
        this.scopeName = scopeName;
        this.functionName = functionName;
    }

    public String getFunctionName() {
        return functionName;
    }

    @Override
    public String toString() {
        return "SymbolTable{" +
                "scopeName='" + scopeName + '\'' +
                ", functionName='" + functionName + '\'' + super.values().toString() +
                '}';
    }
}
