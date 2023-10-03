package nodes;

public class IfStatNode extends StatNode{

    private ExprNode expr;
    private BodyNode body;
    private ElseNode elseStat;

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

}
