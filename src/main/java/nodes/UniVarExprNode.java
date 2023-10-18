package nodes;

public class UniVarExprNode extends ExprNode{

    private ExprNode exprNode;

    public UniVarExprNode(String name, String modeExpr, ExprNode exprNode) {
        super(name, modeExpr);
        this.exprNode = exprNode;
    }

    public ExprNode getExprNode() {
        return exprNode;
    }

    public void setExprNode(ExprNode exprNode) {
        this.exprNode = exprNode;
    }
}
