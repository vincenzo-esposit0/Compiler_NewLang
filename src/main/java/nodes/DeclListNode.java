package nodes;

import visitor.MyVisitor;

import java.util.ArrayList;

public class DeclListNode {
    private String nome;
    private VarDeclNode varDecl;
    private FunDeclNode funDecl;
    private ArrayList<DeclListNode> declList1;

    public DeclListNode(String nome, VarDeclNode varDecl, ArrayList<DeclListNode> declList1) {
        this.nome = nome;
        this.varDecl = varDecl;
        this.declList1 = declList1;
    }

    public DeclListNode(String nome, FunDeclNode funDecl, ArrayList<DeclListNode> declList1) {
        this.nome = nome;
        this.funDecl = funDecl;
        this.declList1 = declList1;
    }

    public String getNome() {
        return nome;
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