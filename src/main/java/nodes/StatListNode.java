package nodes;

import visitor.MyVisitor;

import java.util.ArrayList;

public class StatListNode {

    private String nome;
    private StatNode stat;
    private ArrayList<StatListNode> statList;

    public StatListNode() {

    }

    public StatListNode(String nome, StatNode stat, ArrayList<StatListNode> statList) {
        this.nome = nome;
        this.stat = stat;
        this.statList = statList;
    }

    public String getNome() {
        return nome;
    }

    public StatNode getStat() {
        return stat;
    }

    public ArrayList<StatListNode> getStatList() {
        return statList;
    }

    public void accept(MyVisitor visitor) {
        visitor.visit(this);
    }
}
