package nodes;

import visitor.MyVisitor;

import java.util.ArrayList;

public class ProgramNode {
    private ArrayList<DeclListNode> declList1 = new ArrayList<DeclListNode>();
    private MainFunDeclNode mainFunDecl;
    private ArrayList<DeclListNode> declList2 = new ArrayList<DeclListNode>();

    public ProgramNode(ArrayList<DeclListNode> declList1, MainFunDeclNode mainFunDecl, ArrayList<DeclListNode> declList2) {
        this.declList1 = declList1;
        this.mainFunDecl = mainFunDecl;
        this.declList2 = declList2;
    }

    public ArrayList<DeclListNode> getDeclList1() {
        return declList1;
    }

    public void setDeclList1(ArrayList<DeclListNode> declList1) {
        this.declList1 = declList1;
    }

    public MainFunDeclNode getMainFunDecl() {
        return mainFunDecl;
    }

    public void setMainFunDecl(MainFunDeclNode mainFunDecl) {
        this.mainFunDecl = mainFunDecl;
    }

    public ArrayList<DeclListNode> getDeclList2() {
        return declList2;
    }

    public void setDeclList2(ArrayList<DeclListNode> declList2) {
        this.declList2 = declList2;
    }

    public void accept(MyVisitor visitor) {
        visitor.visit(this);
    }

}