package nodes;

import visitor.MyVisitor;

import java.util.ArrayList;

public class ReadStatNode extends StatNode{

    private ArrayList<IdListNode> idList;
    private ConstNode stringConst;

    public ReadStatNode(ArrayList<IdListNode> idList, ConstNode stringConst) {
        this.idList = idList;
        this.stringConst = stringConst;
    }

    public ArrayList<IdListNode> getIdList() {
        return idList;
    }

    public ConstNode getStringConst() {
        return stringConst;
    }

    public void accept(MyVisitor visitor) {
        visitor.visit(this);
    }

}
