package nodes;

import visitor.MyVisitor;

public abstract class ASTNode {

    private String name;

    private Integer getType;

    public ASTNode(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Integer getGetType() {
        return getType;
    }

    public void accept(MyVisitor visitor) {
        visitor.visit(this);
    }

}
