package nodes;

import java.util.ArrayList;

public class AssignStatNode extends StatNode{

    private ArrayList<IdInitNode> idList;
    private ArrayList<ExprNode> exprList;

    public AssignStatNode(String name, ArrayList<IdInitNode> idList, ArrayList<ExprNode> exprList) {
        super(name);
        this.idList = idList;
        this.exprList = exprList;
    }

    public ArrayList<IdInitNode> getIdList() {
        return idList;
    }

    public ArrayList<ExprNode> getExprList() {
        return exprList;
    }

}
