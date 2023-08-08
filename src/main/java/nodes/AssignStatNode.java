package nodes;

import visitor.MyVisitor;

import java.util.ArrayList;

public class AssignStatNode extends StatNode{

    private ArrayList<IdListNode> idList;
    private ArrayList<ExprListNode> exprList;

    public AssignStatNode(ArrayList<IdListNode> idList, ArrayList<ExprListNode> exprList) {
        this.idList = idList;
        this.exprList = exprList;
    }

    public ArrayList<IdListNode> getIdList() {
        return idList;
    }

    public ArrayList<ExprListNode> getExprList() {
        return exprList;
    }

    public void accept(MyVisitor visitor) {
        visitor.visit(this);
    }
}
