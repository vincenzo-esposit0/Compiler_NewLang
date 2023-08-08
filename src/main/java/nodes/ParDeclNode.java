package nodes;

import visitor.MyVisitor;

import java.util.ArrayList;

public class ParDeclNode {

    public TypeNode type;
    public ArrayList<IdListNode> idListNode;
    public Boolean out;  //se è OUT implica che è un puntatore e viene settato a 1 altrimenti 0

    public ParDeclNode(TypeNode type, ArrayList<IdListNode> idListNode, Boolean out) {
        this.type = type;
        this.idListNode = idListNode;
        this.out = out;
    }

    public TypeNode getType() {
        return type;
    }

    public ArrayList<IdListNode> getIdListNode() {
        return idListNode;
    }

    public Boolean getOut() {
        return out;
    }

    public void accept(MyVisitor visitor) {
        visitor.visit(this);
    }
}
