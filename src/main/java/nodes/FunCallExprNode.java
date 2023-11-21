package nodes;

public class FunCallExprNode extends ExprNode{

    FunCallNode funCall;

    public FunCallExprNode(String name, String modeExpr, FunCallNode funCall) {
        super(name, modeExpr);
        this.funCall = funCall;
    }

    public FunCallNode getFunCall() {
        return funCall;
    }

}
