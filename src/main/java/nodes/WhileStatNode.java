package nodes;

import visitor.MyVisitor;

public class WhileStatNode extends StatNode{

    private String nome;
    private ExprNode expr;
    private BodyNode body;

    public WhileStatNode(String nome, ExprNode expr, BodyNode body) {
        this.nome = nome;
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
