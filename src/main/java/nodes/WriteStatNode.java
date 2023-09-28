package nodes;

import visitor.MyVisitor;

import java.util.ArrayList;

public class WriteStatNode extends StatNode{

    private String nome;
    private ArrayList<ExprListNode> exprList;
    private String writeType;

    public WriteStatNode(String nome, ArrayList<ExprListNode> exprList, String writeType) {
        this.nome = nome;
        this.exprList = exprList;
        this.writeType = writeType;
    }

    public ArrayList<ExprListNode> getExprList() {
        return exprList;
    }

    public String getWriteType() {
        return writeType;
    }

    public void accept(MyVisitor visitor) {
        visitor.visit(this);
    }

}
