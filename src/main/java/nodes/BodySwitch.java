package nodes;

import java.util.ArrayList;

public class BodySwitch extends ASTNode{
    private ConstNode constNode;
    private ArrayList<StatNode> statList;

    public BodySwitch(String name, ConstNode constNode, ArrayList<StatNode> statList) {
        super(name);
        this.constNode = constNode;
        this.statList = statList;
    }

    public ConstNode getConstNode() {
        return constNode;
    }

    public void setConstNode(ConstNode constNode) {
        this.constNode = constNode;
    }

    public ArrayList<StatNode> getStatList() {
        return statList;
    }

    public void setStatList(ArrayList<StatNode> statList) {
        this.statList = statList;
    }
}
