package nodes;

public class ConstNode extends ExprNode{

    private Object value;

    public ConstNode(String name, String modeExpr, Object value) {
        super(name, modeExpr);
        this.value = value;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}
