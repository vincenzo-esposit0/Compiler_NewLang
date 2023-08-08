package nodes;

import visitor.MyVisitor;

import java.util.ArrayList;

public class IdListNode {

    private IdNode id;
    private ArrayList<IdListNode> idListNode;

    public IdListNode(IdNode id, ArrayList<IdListNode> idListNode) {
        this.id = id;
        this.idListNode = idListNode;
    }

    public IdNode getId() {
        return id;
    }

    public ArrayList<IdListNode> getIdList() {
        return idListNode;
    }

    public void accept(MyVisitor visitor) {
        visitor.visit(this);
    }
}
