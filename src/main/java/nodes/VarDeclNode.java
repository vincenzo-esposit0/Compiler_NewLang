package nodes;

import java.util.ArrayList;

public class VarDeclNode extends ASTNode{

    private boolean isVar;

    private TypeNode type;

    private ArrayList<IdInitNode> idInitList;

    private ArrayList<IdInitNode> idInitObblList;

    public VarDeclNode(String name, TypeNode type, ArrayList<IdInitNode> idInitList) {
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

    public TypeNode getType() {
        return type;
    }

    public ArrayList<IdInitNode> getIdInitList() {
        return idInitList;
    }

    public ArrayList<IdInitNode> getIdInitObblList() {
        return idInitObblList;
    }


}
