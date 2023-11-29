package nodes;

public class ReturnStatNode extends StatNode{

    ExprNode expr;

    public ReturnStatNode(String name) {
        super(name);
    }

    public ReturnStatNode(String name, ExprNode expr) {
        super(name);
        this.expr = expr;
    }

    public ExprNode getExpr() {
        return expr;
    }

}
