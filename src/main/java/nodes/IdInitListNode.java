package nodes;

import visitor.MyVisitor;

public class IdInitListNode {

    private String id;
    private ExprNode expr;

    public IdInitListNode(String id, ExprNode expr) {
        this.id = id;
        this.expr = expr;
    }

    public String getId() {
        return id;
    }

    public ExprNode getExpr() {
        return expr;
    }

    public void accept(MyVisitor visitor) {
        visitor.visit(this);
    }

}
