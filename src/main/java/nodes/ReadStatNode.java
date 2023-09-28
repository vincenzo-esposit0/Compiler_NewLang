package nodes;

import visitor.MyVisitor;

import java.util.ArrayList;

public class ReadStatNode extends StatNode{

    private String nome;
    private ArrayList<IdListNode> idList;
    private ConstNode stringConst;

    public ReadStatNode(String nome, ArrayList<IdListNode> idList, ConstNode stringConst) {
        this.nome = nome;
        this.idList = idList;
        this.stringConst = stringConst;
    }

    public String getNome() {
        return nome;
    }

    public ArrayList<IdListNode> getIdList() {
        return idList;
    }

    public ConstNode getStringConst() {
        return stringConst;
    }

    public void accept(MyVisitor visitor) {
        visitor.visit(this);
    }

}
