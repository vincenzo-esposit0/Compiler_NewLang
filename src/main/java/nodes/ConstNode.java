package nodes;

public class ConstNode extends ExprNode{

    private String value;

    public ConstNode(String name, String modeExpr, String value) {
        super(name, modeExpr);
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "ConstNode{" +
                "value='" + value + '\'' +
                '}';
    }
}
