package visitor;

import com.sun.nio.file.ExtendedOpenOption;
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

            treeContent += String.format("</%s>", node.getName()) + "\n";
            return treeContent;
        }

        if (node instanceof VarDeclNode){
            treeContent = String.format("<%s>", "VarDecl");
            ArrayList<IdInitNode> idInit = ((VarDeclNode) node).getIdInitList();
            if(idInit != null){
                for (IdInitNode idElement : idInit) {
                    if (idElement != null) {
                        treeContent += idElement.accept(this);
                    }
                }
            }

            ArrayList<IdInitNode> idInitObbl = ((VarDeclNode) node).getIdInitObblList();
            if(idInitObbl != null){
                for (IdInitNode idElement : idInitObbl) {
                    if (idElement != null) {
                        treeContent += idElement.accept(this);
                    }
                }
            }
            treeContent += String.format("</%s>", "VarDecl") + "\n";
            return treeContent;
        }

        if(node instanceof IdInitNode){
            treeContent = String.format("<%s>", "IdInitNode");


            IdNode id = ((IdInitNode) node).getId();
            treeContent += id.accept(this);

            ExprNode exprNode = ((IdInitNode) node).getExpr();
            if(exprNode != null){
                treeContent += exprNode.accept(this);
            }
            ConstNode constNode = ((IdInitNode) node).getConstant();
            if(constNode != null){
                treeContent += constNode.accept(this);
            }

            treeContent += String.format("</%s>", "IdInitNode")+ "\n";
            return treeContent;
        }

        if(node instanceof IdNode){
            treeContent = String.format("<%s>", "IdNode");
            treeContent += String.format("<%s>", ((IdNode)node).getValue());
            treeContent += String.format("</%s>", "IdNode") + "\n";
            return treeContent;
        }
        if(node instanceof ConstNode){
            treeContent = String.format("<%s>", "ConstNode");
            treeContent += String.format("<%s>", ((ConstNode)node).getValue());
            treeContent += String.format("</%s>", "ConstNode") + "\n";
            return treeContent;
        }
        if(node instanceof ExprNode){
            treeContent = String.format("<%s>", "ExprNode");
            //treeContent += String.format("<%s>", ((ExprNode)node).);
            treeContent += String.format("</%s>", "ExprNode") + "\n";
            return treeContent;
        }

        if(node instanceof FunDeclNode){
            if(((FunDeclNode) node).isMain()){
                treeContent = String.format("<%s>", "Main");

                FunDeclNode funDeclNode= ((FunDeclNode) node).getFunDecl();
                treeContent += funDeclNode.accept(this);

                treeContent += String.format("</%s>", "Main") + "\n";

            }
            else{
                treeContent = String.format("<%s>", "FunDeclNode");

                IdNode idNode = ((FunDeclNode) node).getId();
                treeContent += idNode.accept(this);

                ArrayList<ParDeclNode> parDeclList = ((FunDeclNode) node).getParDeclList();
                if(parDeclList != null){
                    for(ParDeclNode parDecl : parDeclList ){
                        treeContent += parDecl.accept(this);
                    }
                }

                treeContent += String.format("<%s>", "ReturnType");
                String typeOrVoid = ((FunDeclNode) node).getTypeOrVoid();
                treeContent += typeOrVoid;
                treeContent = String.format("<%s>", "ReturnType");

                BodyNode body = ((FunDeclNode) node).getBody();
                treeContent += body.accept(this);

                treeContent += String.format("</%s>", "Main") + "\n";
            }
            return treeContent;
        }

        if(node instanceof BodyNode){
            treeContent = String.format("<%s>", "Body");

            ArrayList<VarDeclNode> varDeclListNode = ((BodyNode) node).getVarDeclList();
            for (VarDeclNode varDecl : varDeclListNode) {
                if (varDecl != null) {
                    treeContent += varDecl.accept(this);
                }
            }

            ArrayList<StatNode> statList = ((BodyNode) node).getStatList();
            for (StatNode stat : statList) {
                if (stat != null) {
                    treeContent += stat.accept(this);
                }
            }

            treeContent += String.format("</%s>", "Body") + "\n";
            return treeContent;
        }

        if(node instanceof StatNode){
            if(node instanceof IfStatNode){
                treeContent = String.format("<%s>", "IfStat");

                ExprNode expr = ((IfStatNode) node).getExpr();
                treeContent += expr.accept(this);

                BodyNode body = ((IfStatNode) node).getBody();
                treeContent += body.accept(this);

                ElseNode elseStat = ((IfStatNode) node).getElseStat();
                treeContent += elseStat.accept(this);

                treeContent += String.format("</%s>", "IfStat") + "\n";

            }
            else if(node instanceof ElseNode){
                treeContent = String.format("<%s>", "ElseStat");

                BodyNode body = ((ElseNode) node).getBody();
                treeContent += body.accept(this);

                treeContent = String.format("</%s>", "ElseStat") + "\n";

            }
            else if(node instanceof ForStatNode){
                treeContent = String.format("<%s>", "ForStat");

                IdNode id = ((ForStatNode) node).getId();
                treeContent += id.accept(this);

                ConstNode const1 = ((ForStatNode) node).getIntConst1();
                treeContent += const1.accept(this);

                ConstNode const2 = ((ForStatNode) node).getIntConst2();
                treeContent += const2.accept(this);

                BodyNode body = ((ForStatNode) node).getBody();
                treeContent += body.accept(this);

                treeContent = String.format("</%s>", "ForStat") + "\n";
            }
            else if(node instanceof WhileStatNode){
                treeContent = String.format("<%s>", "WhileStat");

                ExprNode expr = ((WhileStatNode) node).getExpr();
                treeContent += expr.accept(this);

                BodyNode body = ((WhileStatNode) node).getBody();
                treeContent += body.accept(this);

                treeContent = String.format("</%s>", "WhileStat") + "\n";
            }

            return treeContent;
        }



        return treeContent;
    }
}
