package nodes;

import visitor.MyVisitor;

import java.util.ArrayList;

public class BodyNode {

    private String nome;
    private ArrayList<VarDeclListNode> varDeclList;
    private ArrayList<StatListNode> statList;

    public BodyNode(String nome, ArrayList<VarDeclListNode> varDeclList, ArrayList<StatListNode> statList) {
        this.nome = nome;
        this.varDeclList = varDeclList;
        this.statList = statList;
    }

    public String getNome() {
        return nome;
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
