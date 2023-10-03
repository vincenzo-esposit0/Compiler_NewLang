package nodes;

import java.util.ArrayList;

public class FunDeclNode extends ASTNode{

    private boolean isMain;

    private FunDeclNode funDecl;

    private IdNode id;

    private ArrayList<ParDeclNode> parDeclList;

    private String typeOrVoid;

    private BodyNode body;

    public FunDeclNode(String name, FunDeclNode funDecl) {
        super(name);

        this.isMain = true;
        this.funDecl = funDecl;
    }

    public FunDeclNode(String name, IdNode id, ArrayList<ParDeclNode> parDeclList, String typeOrVoid, BodyNode body) {
        super(name);

        this.isMain = false;
        this.id = id;
        this.parDeclList = parDeclList;
        this.typeOrVoid = typeOrVoid;
        this.body = body;
    }

    public boolean isMain() {
        return isMain;
    }

    public FunDeclNode getFunDecl() {
        return funDecl;
    }

    public IdNode getId() {
        return id;
    }

    public ArrayList<ParDeclNode> getParDeclList() {
        return parDeclList;
    }

    public String getTypeOrVoid() {
        return typeOrVoid;
    }

    public BodyNode getBody() {
        return body;
    }

}

