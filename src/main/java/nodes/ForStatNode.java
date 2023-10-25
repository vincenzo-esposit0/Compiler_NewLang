package nodes;

import table.SymbolTable;

public class ForStatNode extends StatNode{

    private IdNode id;
    private ConstNode intConst1;
    private ConstNode intConst2;
    private BodyNode body;

    private SymbolTable symbolTable;

    public ForStatNode(String name, IdNode id, ConstNode intConst1, ConstNode intConst2, BodyNode body) {
        super(name);
        this.id = id;
        this.intConst1 = intConst1;
        this.intConst2 = intConst2;
        this.body = body;
    }

    public IdNode getId() {
        return id;
    }

    public ConstNode getIntConst1() {
        return intConst1;
    }

    public ConstNode getIntConst2() {
        return intConst2;
    }

    public BodyNode getBody() {
        return body;
    }

    public void setId(IdNode id) {
        this.id = id;
    }

    public void setIntConst1(ConstNode intConst1) {
        this.intConst1 = intConst1;
    }

    public void setIntConst2(ConstNode intConst2) {
        this.intConst2 = intConst2;
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
