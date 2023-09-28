package nodes;

import visitor.MyVisitor;

import java.util.ArrayList;

public class ProgramNode {
    private String nomeProgramNode;
    private ArrayList<DeclListNode> declList1 = new ArrayList<DeclListNode>();
    private MainFunDeclNode mainFunDecl;
    private ArrayList<DeclListNode> declList2 = new ArrayList<DeclListNode>();

    public ProgramNode(String nomeProgramNode, ArrayList<DeclListNode> declList1, MainFunDeclNode mainFunDecl, ArrayList<DeclListNode> declList2) {
        this.nomeProgramNode = nomeProgramNode;
        this.declList1 = declList1;
        this.mainFunDecl = mainFunDecl;
        this.declList2 = declList2;
    }

    public String getNomeProgramNode() {
        return nomeProgramNode;
    }

    public ArrayList<DeclListNode> getDeclList1() {
        return declList1;
    }

    public MainFunDeclNode getMainFunDecl() {
        return mainFunDecl;
    }

    public ArrayList<DeclListNode> getDeclList2() {
        return declList2;
    }

    public void accept(MyVisitor visitor) {
        visitor.visit(this);
    }

}