package nodes;

public class ExprNode extends StatNode{

    private String modeExpr;
    /*
    Nel caso delle operazioni indica quale op deve assumere. Es: AddOp, MulOp
    Nel caso di una costante indica la constante da assumere. Es: integer_const
     */

    public ExprNode(String name, String modeExpr) {
        super(name);
        this.modeExpr = modeExpr;
    }

    public String getModeExpr() {
        return modeExpr;
    }

    public void setModeExpr(String modeExpr) {
        this.modeExpr = modeExpr;
    }
}
