package nodes;

import table.SymbolTable;

import java.util.ArrayList;

public class InitForStatNode extends StatNode{

    private VarDeclNode varDeclNode;
    private ArrayList<StatNode> statList;
    private ExprNode cond;
    private ArrayList<AssignStatNode> loopList;
    private SymbolTable symbolTable;

    public InitForStatNode(String name, VarDeclNode varDeclNode, ArrayList<StatNode> statList, ExprNode cond, ArrayList<AssignStatNode> loopList) {
        super(name);
        this.varDeclNode = varDeclNode;
        this.statList = statList;
        this.cond = cond;
        this.loopList = loopList;
    }

    public VarDeclNode getVarDeclNode() {
        return varDeclNode;
    }

    public void setVarDeclNode(VarDeclNode varDeclNode) {
        this.varDeclNode = varDeclNode;
    }

    public ArrayList<StatNode> getStatList() {
        return statList;
    }

    public void setStatList(ArrayList<StatNode> statList) {
        this.statList = statList;
    }

    public ExprNode getCond() {
        return cond;
    }

    public void setCond(ExprNode cond) {
        this.cond = cond;
    }

    public ArrayList<AssignStatNode> getLoopList() {
        return loopList;
    }

    public void setLoopList(ArrayList<AssignStatNode> loopList) {
        this.loopList = loopList;
    }

    public SymbolTable getSymbolTable() {
        return symbolTable;
    }

    public void setSymbolTable(SymbolTable symbolTable) {
        this.symbolTable = symbolTable;
    }
}
