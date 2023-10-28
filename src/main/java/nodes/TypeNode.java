package nodes;

public class TypeNode extends ASTNode{

    private String typeVar;

    public TypeNode(String name, String typeVar) {
        super(name);
        this.typeVar = typeVar;
    }

    public String getTypeVar() {
        return typeVar;
    }

}
