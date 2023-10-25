package nodes;

import table.SymbolTable;

public class ElseNode extends StatNode{

    private BodyNode body;

    private SymbolTable symbolTable;

    public ElseNode(String name, BodyNode body) {
        super(name);
        this.body = body;
    }

    public BodyNode getBody() {
        return body;
    }

    public void setBody(BodyNode body) {
        this.body = body;
    }

    public SymbolTable getSymbolTable() {
        return symbolTable;
    }

    public void setSymbolTable(SymbolTable symbolTable) {
        this.symbolTable = symbolTable;
    }
}
