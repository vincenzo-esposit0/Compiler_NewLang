package nodes;

import visitor.MyVisitor;

import java.util.ArrayList;

public class ParDeclNode {

    public String type;
    public ArrayList<IdNode> idListNode;
    public Boolean out;  //se è OUT implica che è un puntatore e viene settato a 1 altrimenti 0

    public ParDeclNode(String type, ArrayList<IdNode> idListNode, Boolean out) {
        this.type = type;
        this.idListNode = idListNode;
        this.out = out;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ArrayList<IdNode> getIdListNode() {
        return idListNode;
    }

    public void setIdListNode(ArrayList<IdNode> idListNode) {
        this.idListNode = idListNode;
    }

    public Boolean getOut() {
        return out;
    }

    public void setOut(Boolean out) {
        this.out = out;
    }

    public void accept(MyVisitor visitor) {
        visitor.visit(this);
    }
}
