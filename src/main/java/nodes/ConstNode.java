package nodes;

public class ConstNode extends ASTNode{

    private Object value;

    public ConstNode(String name, Object value) {
        super(name);
        this.value = value;
    }

    public Object getValue() {
        return value;
    }

}
