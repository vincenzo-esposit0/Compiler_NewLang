package nodes;

import visitor.MyVisitor;

import java.util.ArrayList;

public class FunDeclNode {
    private IdNode id;
    private ArrayList<ParamDeclListNode> paramDeclList;
    private String typeOrVoid;
    private BodyNode body;

    public FunDeclNode(IdNode id, ArrayList<ParamDeclListNode> paramDeclList, String typeOrVoid, BodyNode body) {
        this.id = id;
        this.paramDeclList = paramDeclList;
        this.typeOrVoid = typeOrVoid;
        this.body = body;
    }

    public IdNode getId() {
        return id;
    }

    public ArrayList<ParamDeclListNode> getParamDeclList() {
        return paramDeclList;
    }

    public String getTypeOrVoid() {
        return typeOrVoid;
    }

    public BodyNode getBody() {
        return body;
    }

    public void accept(MyVisitor visitor) {
        visitor.visit(this);
    }
}

