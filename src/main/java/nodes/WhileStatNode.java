package nodes;


public class WhileStatNode extends StatNode{

    private ExprNode expr;
    private BodyNode body;

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

}
