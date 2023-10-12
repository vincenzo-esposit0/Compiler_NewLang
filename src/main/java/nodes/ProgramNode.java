package nodes;

import java.util.ArrayList;

public class ProgramNode extends ASTNode{

    private ArrayList<VarDeclNode> varDeclList = new ArrayList<>();

    private ArrayList<FunDeclNode> funDeclList = new ArrayList<>();

    public ProgramNode(String name, ArrayList<ASTNode> declList1, FunDeclNode mainFunDecl, ArrayList<ASTNode> declList2) {
        super(name);
        for (ASTNode node : declList1) {
            if (node instanceof VarDeclNode) {
                varDeclList.add((VarDeclNode) node);
            } else if (node instanceof FunDeclNode) {
                funDeclList.add((FunDeclNode) node);
            }
        }

        this.funDeclList.add(mainFunDecl);

        for (ASTNode node : declList2) {
            if (node instanceof VarDeclNode) {
                varDeclList.add((VarDeclNode) node);
            } else if (node instanceof FunDeclNode) {
                funDeclList.add((FunDeclNode) node);
            }
        }
    }

    public ArrayList<VarDeclNode> getVarDeclList() {
        return varDeclList;
    }

    public ArrayList<FunDeclNode> getFunDeclList() {
        return funDeclList;
    }

}