package nodes;

import java.util.ArrayList;

public class FunCallNode extends StatNode{

    private IdNode id;
    private ArrayList<ExprNode> exprList;

    public FunCallNode(String name, IdNode id, ArrayList<ExprNode> exprList) {
        super(name);
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
