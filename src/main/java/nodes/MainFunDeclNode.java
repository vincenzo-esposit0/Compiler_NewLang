package nodes;

import visitor.MyVisitor;

public class MainFunDeclNode {

    private String name;
    private FunDeclNode funDecl;

    public MainFunDeclNode(String name, FunDeclNode funDecl) {
        this.name = name;
        this.funDecl = funDecl;
    }

    public String getName() {
        return name;
    }

    public FunDeclNode getFunDecl() {
        return funDecl;
    }

    public void accept(MyVisitor visitor) {
        visitor.visit(this);
    }

}