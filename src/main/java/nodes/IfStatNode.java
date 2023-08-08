package nodes;

import visitor.MyVisitor;

public class IfStatNode extends StatNode{

    private ExprNode expr;
    private BodyNode body;
    private ElseNode elseStat;

    public IfStatNode(ExprNode expr, BodyNode body, ElseNode elseStat) {
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

    public void accept(MyVisitor visitor) {
        visitor.visit(this);
    }
}
