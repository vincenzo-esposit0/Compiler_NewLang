package nodes;

public class TypeNode extends ASTNode{

    public static final String INTEGER = "integer";
    public static final String BOOL = "bool";
    public static final String REAL = "real";
    public static final String STRING = "string";
    public static final String CHAR = "char";

    private String typeVar;

    public TypeNode(String name, String typeVar) {
        super(name);
        this.typeVar = typeVar;
    }

    public String getTypeVar() {
        return typeVar;
    }

}
