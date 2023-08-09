package nodes;

import visitor.MyVisitor;

public class MainFunDeclNode {

    private FunDeclNode funDecl;

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MainFunDeclNode(FunDeclNode funDecl) {
        this.funDecl = funDecl;
    }

    public FunDeclNode getFunDecl() {
        return funDecl;
    }

    public void accept(MyVisitor visitor) {
        visitor.visit(this);
    }

}