package nodes;

import table.SymbolTable;

import java.util.ArrayList;

public class FunDeclNode extends ASTNode{

    private boolean isMain;
    private FunDeclNode funDecl;
    private IdNode id;
    private ArrayList<ParDeclNode> parDeclList;
    private String typeOrVoid;
    private BodyNode body;
    private SymbolTable symbolTable;

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

    public void setMain(boolean main) {
        isMain = main;
    }

    public void setFunDecl(FunDeclNode funDecl) {
        this.funDecl = funDecl;
    }

    public void setId(IdNode id) {
        this.id = id;
    }

    public void setParDeclList(ArrayList<ParDeclNode> parDeclList) {
        this.parDeclList = parDeclList;
    }

    public void setTypeOrVoid(String typeOrVoid) {
        this.typeOrVoid = typeOrVoid;
    }

    public void setBody(BodyNode body) {
        this.body = body;
    }

    public SymbolTable getSymbolTable() {
        return symbolTable;
    }

    public void setSymbolTable(SymbolTable symbolTable) {
        this.symbolTable = symbolTable;
    }
}

