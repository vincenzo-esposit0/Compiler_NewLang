package visitor;

import nodes.*;

import java.util.ArrayList;

public class MySyntaxTree implements MyVisitor{

    private String treeContent = "";

    @Override
    public String toString() {
        return "MySyntaxTree{" +
                "treeContent='" + treeContent + '\'' +
                '}';
    }

    @Override
    public void visit(ASTNode node) {

        //ProgramNode
        if (node instanceof ProgramNode) {
            treeContent = String.format("<%s>", node.getName());

            ArrayList<VarDeclNode> varDeclListNode = ((ProgramNode) node).getVarDeclList();
            for (VarDeclNode varDecl : varDeclListNode) {
                if (varDecl != null) {
                    varDecl.accept(this);
                }
            }

            ArrayList<FunDeclNode> funDeclListNode = ((ProgramNode) node).getFunDeclList();
            for (FunDeclNode funDecl : funDeclListNode) {
                if (funDecl != null) {
                    funDecl.accept(this);
                }
            }

            treeContent += String.format("</%s>", node.getName());
            treeContent.toString();
        }
    }
