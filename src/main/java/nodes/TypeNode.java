package nodes;

import visitor.MyVisitor;

public class TypeNode {
    public static final String INTEGER = "integer";
    public static final String BOOL = "bool";
    public static final String REAL = "real";
    public static final String STRING = "string";
    public static final String CHAR = "char";

    private String type;

    public TypeNode(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void accept(MyVisitor visitor) {
        visitor.visit(this);
    }

}
