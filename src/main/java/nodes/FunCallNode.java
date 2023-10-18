package nodes;

import java.util.ArrayList;

public class FunCallNode extends ExprNode{

    private IdNode id;
    private ArrayList<ExprNode> exprList;

    public FunCallNode(String name, String modeExpr, IdNode id, ArrayList<ExprNode> exprList) {
        super(name, modeExpr);
        this.id = id;
        this.exprList = exprList;
    }

    public IdNode getId() {
        return id;
    }

    public ArrayList<ExprNode> getExprList() {
        return exprList;
    }

}
