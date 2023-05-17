package nodes;

import visitor.MyVisitor;

public class RealConstNode extends ConstNode {

    public float value;

    public RealConstNode(float value) {
        this.value = value;
    }

    public float getValue() {
        return value;
    }

    public void accept(MyVisitor visitor) {
        visitor.visit(this);
    }

}
