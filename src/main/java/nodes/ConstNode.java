package nodes;

import visitor.MyVisitor;

public class ConstNode {

    private Object value;

    public ConstNode(Object value) {
        this.value = value;
    }

    public ConstNode(Integer iValue) {
        this.value = iValue;
    }

    public Object getValue() {
        return value;
    }

    public void accept(MyVisitor visitor) {
        visitor.visit(this);
    }

}
