package nodes;

import visitor.MyVisitor;

public class CharConstNode extends ConstNode{

    public char value;

    public CharConstNode(char value) {
        this.value = value;
    }

    public char getValue() {
        return value;
    }

    public void accept(MyVisitor visitor) {
        visitor.visit(this);
    }

}
