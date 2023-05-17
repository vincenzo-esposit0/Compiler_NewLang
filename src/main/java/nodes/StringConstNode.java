package nodes;

import visitor.MyVisitor;

public class StringConstNode extends ConstNode {

    public String value;

    public StringConstNode(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void accept(MyVisitor visitor) {
        visitor.visit(this);
    }

}
