package nodes;

import visitor.MyVisitor;

import java.util.ArrayList;

public class StatListNode {

    private StatNode stat;
    private ArrayList<StatListNode> statList;

    public StatListNode() {

    }

    public StatListNode(StatNode stat, ArrayList<StatListNode> statList) {
        this.stat = stat;
        this.statList = statList;
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
