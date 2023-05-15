package nodes;

import visitor.MyVisitor;

public class MainFunDeclNode {

    private FunDeclNode funDecl;

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