package nodes;

import visitor.MyVisitor;

public class IntegerConstNode extends ConstNode {

    public int value;

    public IntegerConstNode(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void accept(MyVisitor visitor) {
        visitor.visit(this);
    }

}
