package nodes;

public class ExprNode extends StatNode{

    private String typeExpr;

    private ConstNode constant;

    private IdNode id;

    private FunCallNode funCall;

    private ExprNode expr1;

    private ExprNode expr2;

    public ExprNode(String name, String typeExpr, ConstNode constant, IdNode id, FunCallNode funCall, ExprNode expr1, ExprNode expr2) {
        super(name);
        this.typeExpr = typeExpr;
        this.constant = constant;
        this.id = id;
        this.funCall = funCall;
        this.expr1 = expr1;
        this.expr2 = expr2;
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

}
