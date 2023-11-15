package nodes;

import table.SymbolTable;

public class IfStatNode extends StatNode{

    private ExprNode expr;
    private BodyNode body;
    private ElseNode elseStat;

    private SymbolTable symbolTable;

    private SymbolTable elseSymbolTable;

    public IfStatNode(String name, ExprNode expr, BodyNode body, ElseNode elseStat) {
        super(name);
        this.expr = expr;
        this.body = body;
        this.elseStat = elseStat;
    }

    public ExprNode getExpr() {
        return expr;
    }

    public BodyNode getBody() {
        return body;
    }

    public ElseNode getElseStat() {
        return elseStat;
    }

    public void setExpr(ExprNode expr) {
        this.expr = expr;
    }

    public void setBody(BodyNode body) {
        this.body = body;
    }

    public void setElseStat(ElseNode elseStat) {
        this.elseStat = elseStat;
    }

    public SymbolTable getSymbolTable() {
        return symbolTable;
    }

    public void setSymbolTable(SymbolTable symbolTable) {
        this.symbolTable = symbolTable;
    }

    public SymbolTable getElseSymbolTable() {
        return elseSymbolTable;
    }

    public void setElseSymbolTable(SymbolTable elseSymbolTable) {
        this.elseSymbolTable = elseSymbolTable;
    }


}
