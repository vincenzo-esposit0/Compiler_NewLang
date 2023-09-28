package nodes;

import visitor.MyVisitor;

import java.util.ArrayList;

public class AssignStatNode extends StatNode{

    private String nome;
    private ArrayList<IdListNode> idList;
    private ArrayList<ExprListNode> exprList;

    public AssignStatNode(String nome, ArrayList<IdListNode> idList, ArrayList<ExprListNode> exprList) {
        this.nome = nome;
        this.idList = idList;
        this.exprList = exprList;
    }

    public String getNome() {
        return nome;
    }

    public ArrayList<IdListNode> getIdList() {
        return idList;
    }

    public ArrayList<ExprListNode> getExprList() {
        return exprList;
    }

    public void accept(MyVisitor visitor) {
        visitor.visit(this);
    }
}
