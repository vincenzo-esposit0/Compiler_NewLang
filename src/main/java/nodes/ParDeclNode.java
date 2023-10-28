package nodes;

import java.util.ArrayList;

public class ParDeclNode extends ASTNode{

    private String typeVar;

    private ArrayList<IdInitNode> idList;

    private Boolean out;  //se è OUT implica che è un puntatore e viene settato a 1 altrimenti 0

    public ParDeclNode(String name, String typeVar, ArrayList<IdInitNode> idList, Boolean out) {
        super(name);
        this.typeVar = typeVar;
        this.idList = idList;
        this.out = out;
    }

    public String getTypeVar() {
        return typeVar;
    }

    public ArrayList<IdInitNode> getIdList() {
        return idList;
    }

    public Boolean getOut() {
        return out;
    }

    public void setTypeVar(String typeVar) {
        this.typeVar = typeVar;
    }

    public void setIdList(ArrayList<IdInitNode> idList) {
        this.idList = idList;
    }

    public void setOut(Boolean out) {
        this.out = out;
    }
}
