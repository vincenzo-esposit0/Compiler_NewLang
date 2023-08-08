package nodes;

import visitor.MyVisitor;

public class WhileStatNode extends StatNode{

    private ExprNode expr;
    private BodyNode body;

    public WhileStatNode(ExprNode expr, BodyNode body) {
        this.expr = expr;
        this.body = body;
    }

    public ExprNode getExpr() {
        return expr;
    }

    public BodyNode getBody() {
        return body;
    }

    public void accept(MyVisitor visitor) {
        visitor.visit(this);
    }

}
