package nodes;

import visitor.MyVisitor;

public class StatNode {

    private Object typeStat;

    public StatNode() {

    }

    public StatNode(Object typeStat) {
        this.typeStat = typeStat;
    }

    public Object getTypeStat() {
        return typeStat;
    }

    public void accept(MyVisitor visitor) {
        visitor.visit(this);
    }

}
