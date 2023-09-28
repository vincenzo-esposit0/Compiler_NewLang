package nodes;

import visitor.MyVisitor;

import java.util.ArrayList;

public class VarDeclNode {

    private String nome;
    private TypeNode type;
    private ArrayList<IdInitListNode> idInitList;
    private ArrayList<IdInitObblListNode> idInitObblList;

    public VarDeclNode(String nome, TypeNode type, ArrayList<IdInitListNode> idInitList) {
        this.nome = nome;
        this.type = type;
        this.idInitList = idInitList;
    }

    public VarDeclNode(ArrayList<IdInitObblListNode> idInitObblList) {
        this.idInitObblList = idInitObblList;
    }

    public TypeNode getType() {
        return type;
    }

    public ArrayList<IdInitListNode> getIdInitList() {
        return idInitList;
    }

    public ArrayList<IdInitObblListNode> getIdInitObblList() {
        return idInitObblList;
    }

    public void accept(MyVisitor visitor) {
        visitor.visit(this);
    }

}
