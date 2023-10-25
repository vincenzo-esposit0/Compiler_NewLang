package nodes;

import visitor.MyVisitor;

public abstract class ASTNode {

    private String name;

    private Integer astType;

    public ASTNode(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Integer getAstType() {
        return astType;
    }

    public String accept(MyVisitor visitor) {
        return visitor.visit(this);
    }

}
