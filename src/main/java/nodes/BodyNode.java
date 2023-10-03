package nodes;

import java.util.ArrayList;

public class BodyNode extends ASTNode{

    private ArrayList<VarDeclNode> varDeclList;
    private ArrayList<StatNode> statList;

    public BodyNode(String name, ArrayList<VarDeclNode> varDeclList, ArrayList<StatNode> statList) {
        super(name);
        this.varDeclList = varDeclList;
        this.statList = statList;
    }

    public ArrayList<VarDeclNode> getVarDeclList() {
        return varDeclList;
    }

    public ArrayList<StatNode> getStatList() {
        return statList;
    }

}
