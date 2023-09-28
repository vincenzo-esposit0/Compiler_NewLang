package nodes;

import visitor.MyVisitor;

import java.util.ArrayList;

public class VarDeclListNode {

    private String nome;
    private VarDeclNode varDecl;
    private ArrayList<VarDeclListNode> varDeclList;

    public VarDeclListNode() {

    }

    public VarDeclListNode(String nome, VarDeclNode varDecl, ArrayList<VarDeclListNode> varDeclList) {
        this.nome = nome;
        this.varDecl = varDecl;
        this.varDeclList = varDeclList;
    }

    public String getNome() {
        return nome;
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
