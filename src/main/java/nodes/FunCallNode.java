package nodes;

import visitor.MyVisitor;

import java.util.ArrayList;

public class FunCallNode extends StatNode{

    private String nome;
    private IdNode id;
    private ArrayList<ExprListNode> exprList;

    public FunCallNode(String nome, IdNode id, ArrayList<ExprListNode> exprList) {
        this.nome = nome;
        this.id = id;
        this.exprList = exprList;
    }

    public String getNome() {
        return nome;
    }

    public IdNode getId() {
        return id;
    }

    public ArrayList<ExprListNode> getExprList() {
        return exprList;
    }

    public void accept(MyVisitor visitor) {
        visitor.visit(this);
    }
}
