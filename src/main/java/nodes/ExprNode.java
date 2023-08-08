package nodes;

import visitor.MyVisitor;

public class ExprNode {

    private String typeExpr;
    private ConstNode constant;
    private IdNode id;
    private FunCallNode funCall;
    private ExprNode expr1;
    private ExprNode expr2;

    public ExprNode(String typeExpr, ConstNode constant) {
        this.typeExpr = typeExpr;
        this.constant = constant;
    }

    public ExprNode(String typeExpr, IdNode id) {
        this.typeExpr = typeExpr;
        this.id = id;
    }

    public ExprNode(String typeExpr, FunCallNode funCall) {
        this.typeExpr = typeExpr;
        this.funCall = funCall;
    }

    public ExprNode(String typeExpr, ExprNode expr1, ExprNode expr2) {
        this.typeExpr = typeExpr;
        this.expr1 = expr1;
        this.expr2 = expr2;
    }

    public ExprNode(String typeExpr, ExprNode expr1) {
        this.typeExpr = typeExpr;
        this.expr1 = expr1;
    }

    public String getTypeExpr() {
        return typeExpr;
    }

    public ConstNode getConstant() {
        return constant;
    }

    public IdNode getId() {
        return id;
    }

    public FunCallNode getFunCall() {
        return funCall;
    }

    public ExprNode getExpr1() {
        return expr1;
    }

    public ExprNode getExpr2() {
        return expr2;
    }

    public void accept(MyVisitor visitor) {
        visitor.visit(this);
    }
}
