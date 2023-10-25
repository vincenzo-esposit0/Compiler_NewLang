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

    public String getScopeName() {
        return scopeName;
    }

    public void setScopeName(String scopeName) {
        this.scopeName = scopeName;
    }

    public String getFunctionName() {
        return functionName;
    }

    public void setFunctionName(String functionName) {
        this.functionName = functionName;
    }
}
