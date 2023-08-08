package nodes;

import visitor.MyVisitor;

import java.util.ArrayList;

public class ParamDeclListNode {

    private ArrayList<NonEmptyParamDeclListNode> nonEmptyParamDeclList;

    public ParamDeclListNode(ArrayList<NonEmptyParamDeclListNode> nonEmptyParamDeclList) {
        this.nonEmptyParamDeclList = nonEmptyParamDeclList;
    }

    public ParamDeclListNode() {

    }

    public ArrayList<NonEmptyParamDeclListNode> getNonEmptyParamDeclList() {
        return nonEmptyParamDeclList;
    }

    public void accept(MyVisitor visitor) {
        visitor.visit(this);
    }

}
