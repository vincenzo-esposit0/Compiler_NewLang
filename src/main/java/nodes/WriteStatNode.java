package nodes;

import visitor.MyVisitor;

import java.util.ArrayList;

public class WriteStatNode extends StatNode{

    private ArrayList<ExprListNode> exprList;
    private String writeType;

    public WriteStatNode(ArrayList<ExprListNode> exprList, String writeType) {
        this.exprList = exprList;
        this.writeType = writeType;
    }

    public ArrayList<ExprListNode> getExprList() {
        return exprList;
    }

    public String getWriteType() {
        return writeType;
    }

    public void accept(MyVisitor visitor) {
        visitor.visit(this);
    }

}
