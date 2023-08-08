package nodes;

import visitor.MyVisitor;

import java.util.ArrayList;

public class DeclListNode {
    private VarDeclNode varDecl;
    private FunDeclNode funDecl;
    private ArrayList<DeclListNode> declList1;

    public DeclListNode() {

    }

    public DeclListNode(VarDeclNode varDecl, ArrayList<DeclListNode> declList1) {
        this.varDecl = varDecl;
        this.declList1 = declList1;
    }

    public DeclListNode(FunDeclNode funDecl, ArrayList<DeclListNode> declList1) {
        this.funDecl = funDecl;
        this.declList1 = declList1;
    }

    public VarDeclNode getVarDecl() {
        return varDecl;
    }

    public FunDeclNode getFunDecl() {
        return funDecl;
    }

    public ArrayList<DeclListNode> getDeclList1() {
        return declList1;
    }

    public void accept(MyVisitor visitor) {
        visitor.visit(this);
    }
}