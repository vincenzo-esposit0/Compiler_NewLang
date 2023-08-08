package nodes;

import visitor.MyVisitor;

import java.util.ArrayList;

public class NonEmptyParamDeclListNode {

    private ParDeclNode parDecl;
    private ArrayList<NonEmptyParamDeclListNode> nonEmptyParamDeclList;

    public NonEmptyParamDeclListNode(ParDeclNode parDecl, ArrayList<NonEmptyParamDeclListNode> nonEmptyParamDeclList) {
        this.parDecl = parDecl;
        this.nonEmptyParamDeclList = nonEmptyParamDeclList;
    }

    public ParDeclNode getParDecl() {
        return parDecl;
    }

    public ArrayList<NonEmptyParamDeclListNode> getNonEmptyParamDeclList() {
        return nonEmptyParamDeclList;
    }

    public void accept(MyVisitor visitor) {
        visitor.visit(this);
    }

}
