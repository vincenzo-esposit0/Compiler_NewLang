package nodes;

import visitor.MyVisitor;

public class BooleanConstNode extends ConstNode {

    public boolean value;

    public BooleanConstNode(boolean value) {
        this.value = value;
    }

    public boolean getValue() {
        return value;
    }

    public void accept(MyVisitor visitor) {
        visitor.visit(this);
    }

}
