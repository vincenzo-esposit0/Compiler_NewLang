package visitor;

import nodes.*;

import java.util.ArrayList;

public class MySyntaxTree implements MyVisitor {

    private String treeContent = "";

    @Override
    public String visit(ASTNode node) {

        //ProgramNode
        if (node instanceof ProgramNode) {
            treeContent = String.format("<%s>", node.getName());

            ArrayList<VarDeclNode> varDeclListNode = ((ProgramNode) node).getVarDeclList();
            for (VarDeclNode varDecl : varDeclListNode) {
                if (varDecl != null) {
                    treeContent += varDecl.accept(this);
                }
            }

            ArrayList<FunDeclNode> funDeclListNode = ((ProgramNode) node).getFunDeclList();
            for (FunDeclNode funDecl : funDeclListNode) {
                if (funDecl != null) {
                    treeContent += funDecl.accept(this);
                }
            }

            treeContent += String.format("</%s>", node.getName());
        }

        if (node instanceof VarDeclNode){
            treeContent = String.format("<%s>", node.getName());


            treeContent += String.format("<%s>", ((VarDeclNode) node).isVar());

            treeContent += String.format("</%s>", node.getName());


            ArrayList<IdInitNode> idInitNode = ((VarDeclNode) node).getIdInitList();
            if(idInitNode != null){
                for (IdInitNode idInit : idInitNode) {
                    if (idInit != null) {
                        treeContent += idInit.accept(this);
                    }
                }
            }

            ArrayList<IdInitNode> idInitObbl = ((VarDeclNode) node).getIdInitObblList();
            if(idInitObbl != null){
                for (IdInitNode idInit : idInitObbl) {
                    if (idInit != null) {
                        treeContent += idInit.accept(this);
                    }
                }
            }


            treeContent += String.format("</%s>", node.getName());




        }

        if(node instanceof IdInitNode){


        }
        return treeContent;
    }
}
