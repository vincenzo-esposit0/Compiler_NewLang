package nodes;


import table.SymbolTable;

public class WhileStatNode extends StatNode{

    private ExprNode expr;
    private BodyNode body;

    private SymbolTable symbolTable;

    public WhileStatNode(String name, ExprNode expr, BodyNode body) {
        super(name);
        this.expr = expr;
        this.body = body;
    }

    public ExprNode getExpr() {
        return expr;
    }

    public BodyNode getBody() {
        return body;
    }

    public void setExpr(ExprNode expr) {
        this.expr = expr;
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
