package nodes;

import visitor.MyVisitor;

import java.util.ArrayList;

public class BodyNode {

    private ArrayList<VarDeclListNode> varDeclList;
    private ArrayList<StatListNode> statList;

    public BodyNode(ArrayList<VarDeclListNode> varDeclList, ArrayList<StatListNode> statList) {
        this.varDeclList = varDeclList;
        this.statList = statList;
    }

    public ArrayList<VarDeclListNode> getVarDeclList() {
        return varDeclList;
    }

    public ArrayList<StatListNode> getStatList() {
        return statList;
    }

    public void accept(MyVisitor visitor) {
        visitor.visit(this);
    }

}
