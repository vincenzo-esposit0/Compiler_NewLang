package nodes;

import visitor.MyVisitor;

import java.util.ArrayList;

public class DeclListNode {
    private VarDeclNode varDecl;
    private FunDeclNode funDecl;
    private ArrayList<DeclListNode> declList;

    public DeclListNode(VarDeclNode varDecl, ArrayList<DeclListNode> declList) {
        this.varDecl = varDecl;
        this.declList = declList;
    }

    public DeclListNode(FunDeclNode funDecl, ArrayList<DeclListNode> declList) {
        this.funDecl = funDecl;
        this.declList = declList;
    }

    public VarDeclNode getVarDecl() {
        return varDecl;
    }

    public FunDeclNode getFunDecl() {
        return funDecl;
    }

    public ArrayList<DeclListNode> getDeclList() {
        return declList;
    }

    public void accept(MyVisitor visitor) {
        visitor.visit(this);
    }
}