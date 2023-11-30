package nodes;

import java.util.ArrayList;

public class VarDeclNode extends ASTNode{

    private boolean isVar;

    private String type;

    private ArrayList<IdInitNode> idInitList;

    private ArrayList<IdInitNode> idInitObblList;

    public VarDeclNode(String name, String type, ArrayList<IdInitNode> idInitList) {
        super(name);
        this.isVar = false;
        this.type = type;
        this.idInitList = idInitList;
    }

    public VarDeclNode(String name, ArrayList<IdInitNode> idInitObblList) {
        super(name);
        this.isVar = true;
        this.idInitObblList = idInitObblList;
    }

    public boolean isVar() {
        return isVar;
    }

    public String getType() {
        return type;
    }

    public ArrayList<IdInitNode> getIdInitList() {
        return idInitList;
    }

    public ArrayList<IdInitNode> getIdInitObblList() {
        return idInitObblList;
    }

    @Override
    public String toString() {
        return "VarDeclNode{" +
                "isVar=" + isVar +
                ", type='" + type + '\'' +
                ", idInitList=" + idInitList +
                ", idInitObblList=" + idInitObblList +
                '}';
    }
}
