package nodes;

import visitor.MyVisitor;

import java.util.ArrayList;

public class IdInitObblListNode {

    private IdNode id;
    private ConstNode constant;
    public ArrayList<IdInitObblListNode> idInitObblList;

    public IdInitObblListNode(ArrayList<IdInitObblListNode> idInitObblList, IdNode id, ConstNode constant) {
        this.idInitObblList = idInitObblList;
        this.id = id;
        this.constant = constant;
    }

    public ArrayList<IdInitObblListNode> getIdInitObblList() {
        return idInitObblList;
    }

    public IdNode getId() {
        return id;
    }

    public ConstNode getConstant() {
        return constant;
    }

    public void accept(MyVisitor visitor) {
        visitor.visit(this);
    }

}
