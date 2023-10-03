package nodes;

import java.util.ArrayList;

public class WriteStatNode extends StatNode{

    private ArrayList<ExprNode> exprList;

    private String typeWrite;

    public WriteStatNode(String name, ArrayList<ExprNode> exprList, String typeWrite) {
        super(name);
        this.exprList = exprList;
        this.typeWrite = typeWrite;
    }

    public ArrayList<ExprNode> getExprList() {
        return exprList;
    }

    public String getTypeWrite() {
        return typeWrite;
    }

}
