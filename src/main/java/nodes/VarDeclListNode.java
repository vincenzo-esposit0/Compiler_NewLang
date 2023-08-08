package nodes;

import visitor.MyVisitor;

import java.util.ArrayList;

public class VarDeclListNode {

    private VarDeclNode varDecl;
    private ArrayList<VarDeclListNode> varDeclList;

    public VarDeclListNode() {

    }

    public VarDeclListNode(VarDeclNode varDecl, ArrayList<VarDeclListNode> varDeclList) {
        this.varDecl = varDecl;
        this.varDeclList = varDeclList;
    }

    public VarDeclNode getVarDecl() {
        return varDecl;
    }

    public ArrayList<VarDeclListNode> getVarDeclList() {
        return varDeclList;
    }

    public void accept(MyVisitor visitor) {
        visitor.visit(this);
    }

}
