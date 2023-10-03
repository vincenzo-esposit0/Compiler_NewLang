package nodes;

public class IdNode extends ASTNode{

    public String value;

    public IdNode(String name, String value) {
        super(name);
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
