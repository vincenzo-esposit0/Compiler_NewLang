package nodes;

import visitor.MyVisitor;

import java.util.ArrayList;

public class FunCallNode extends StatNode{

    private IdNode id;
    private ArrayList<ExprListNode> exprList;

    public FunCallNode(IdNode id, ArrayList<ExprListNode> exprList) {
        this.id = id;
        this.exprList = exprList;
    }

    public IdNode getId() {
        return id;
    }

    public ArrayList<ExprListNode> getExprList() {
        return exprList;
    }

    public void accept(MyVisitor visitor) {
        visitor.visit(this);
    }
}
