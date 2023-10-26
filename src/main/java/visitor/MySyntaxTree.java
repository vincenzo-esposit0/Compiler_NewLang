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
            treeContent = String.format("<%s>", node.getName()) + "\n";

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
            treeContent = String.format("<%s>", "VarDecl") + "\n";
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
            treeContent = String.format("<%s>", "IdInitNode") + "\n";

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

        if(node instanceof ExprNode){
            if(node instanceof IdNode){
                treeContent = String.format("<%s>", "IdNode") + "\n";
                treeContent += String.format("%s", ((IdNode) node).getNomeId()) + "\n";
                treeContent += String.format("</%s>", "IdNode") + "\n";
            }
            else if(node instanceof ConstNode){
                treeContent = String.format("<%s>", "ConstNode") + "\n";

                treeContent += "<ModeExpr>" + String.format("%s", ((ConstNode) node).getModeExpr()) + "</ModeExpr>";

                treeContent += String.format("%s", ((ConstNode)node).getValue()) + "\n";
                treeContent += String.format("</%s>", "ConstNode") + "\n";
            }
            else if(node instanceof BiVarExprNode){
                treeContent = String.format("<%s>", "BiVarExprNode") + "\n";

                ExprNode exprNode1 = ((BiVarExprNode) node).getExprNode1();
                treeContent += exprNode1.accept(this);

                ExprNode exprNode2 = ((BiVarExprNode) node).getExprNode2();
                treeContent += exprNode2.accept(this);

                treeContent += String.format("</%s>", "BiVarExprNode") + "\n";
            }
            else if(node instanceof UniVarExprNode){
                treeContent = String.format("<%s>", "UniVarExprNode") + "\n";

                ExprNode exprNode = ((UniVarExprNode) node).getExprNode();
                treeContent += exprNode.accept(this);

                treeContent += String.format("</%s>", "UniVarExprNode") + "\n";
            }
            else if(node instanceof FunCallNode){
                treeContent = String.format("<%s>", "FunCallNode") + "\n";

                IdNode idNode = ((FunCallNode) node).getId();
                treeContent += idNode.accept(this);

                ArrayList<ExprNode> exprNode = ((FunCallNode) node).getExprList();
                for (ExprNode expr : exprNode) {
                    if (expr != null) {
                        treeContent += expr.accept(this);
                    }
                }

                treeContent += String.format("</%s>", "FunCallNode") + "\n";
            }

            return treeContent;
        }

        if(node instanceof FunDeclNode){
            if(((FunDeclNode) node).isMain()){
                treeContent = String.format("<%s>", "Main") + "\n";

                FunDeclNode funDeclNode= ((FunDeclNode) node).getFunDecl();
                treeContent += funDeclNode.accept(this);

                treeContent += String.format("</%s>", "Main") + "\n";

            }
            else{
                treeContent = String.format("<%s>", "FunDeclNode") + "\n";

                treeContent += "<NameFunDeclNode>" + String.format("%s", node.getName()) + "</NameFunDeclNode>" + "\n";

                IdNode idNode = ((FunDeclNode) node).getId();
                treeContent += idNode.accept(this);

                ArrayList<ParDeclNode> parDeclList = ((FunDeclNode) node).getParDeclList();
                if(parDeclList != null){
                    for(ParDeclNode parDecl : parDeclList ){
                        treeContent += parDecl.accept(this);
                    }
                }

                treeContent += "<ReturnType>" + String.format("%s", ((FunDeclNode) node).getTypeOrVoid()) + "</ReturnType>" + "\n";

                BodyNode body = ((FunDeclNode) node).getBody();
                treeContent += body.accept(this);

                treeContent += String.format("</%s>", "FunDeclNode") + "\n";
            }

            return treeContent;
        }

        if(node instanceof BodyNode){
            treeContent = String.format("<%s>", "Body") + "\n";

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
                treeContent = String.format("<%s>", "IfStat") + "\n";

                ExprNode expr = ((IfStatNode) node).getExpr();
                treeContent += expr.accept(this);

                BodyNode body = ((IfStatNode) node).getBody();
                treeContent += body.accept(this);

                ElseNode elseStat = ((IfStatNode) node).getElseStat();
                if(elseStat != null) {
                    treeContent += elseStat.accept(this);
                }

                treeContent += String.format("</%s>", "IfStat") + "\n";

            }
            else if(node instanceof ElseNode){
                treeContent = String.format("<%s>", "ElseStat") + "\n";

                BodyNode body = ((ElseNode) node).getBody();
                treeContent += body.accept(this);

                treeContent += String.format("</%s>", "ElseStat") + "\n";

            }
            else if(node instanceof ForStatNode){
                treeContent = String.format("<%s>", "ForStat") + "\n";

                IdNode id = ((ForStatNode) node).getId();
                treeContent += id.accept(this);

                ConstNode const1 = ((ForStatNode) node).getIntConst1();
                treeContent += const1.accept(this);

                ConstNode const2 = ((ForStatNode) node).getIntConst2();
                treeContent += const2.accept(this);

                BodyNode body = ((ForStatNode) node).getBody();
                treeContent += body.accept(this);

                treeContent += String.format("</%s>", "ForStat") + "\n";
            }
            else if(node instanceof WhileStatNode){
                treeContent = String.format("<%s>", "WhileStat") + "\n";

                ExprNode expr = ((WhileStatNode) node).getExpr();
                treeContent += expr.accept(this);

                BodyNode body = ((WhileStatNode) node).getBody();
                treeContent += body.accept(this);

                treeContent += String.format("</%s>", "WhileStat") + "\n";
            }
            else if(node instanceof AssignStatNode){
                treeContent = String.format("<%s>", "AssignStat") + "\n";

                ArrayList<IdInitNode> idList = ((AssignStatNode) node).getIdList();
                for (IdInitNode idElement : idList) {
                    if (idElement != null) {
                        treeContent += idElement.accept(this);
                    }
                }

                ArrayList<ExprNode> exprNode = ((AssignStatNode) node).getExprList();
                for (ExprNode expr : exprNode) {
                    if (expr != null) {
                        treeContent += expr.accept(this);
                    }
                }

                treeContent += String.format("</%s>", "AssignStat") + "\n";

            }
            else if(node instanceof ReadStatNode){
                System.out.println("inside if readStatNode");
                treeContent = String.format("<%s>", "ReadStat") + "\n";

                ArrayList<IdInitNode> idList = ((ReadStatNode) node).getIdList();
                for (IdInitNode idElement : idList) {
                    if (idElement != null) {
                        treeContent += idElement.accept(this);
                    }
                }

                ConstNode stringConst = ((ReadStatNode) node).getStringConst();
                treeContent += stringConst.accept(this);

                treeContent += String.format("</%s>", "ReadStat") + "\n";

            }
            else if(node instanceof WriteStatNode){
                treeContent = String.format("<%s>", "WriteStat") + "\n";

                ArrayList<ExprNode> exprNode = ((WriteStatNode) node).getExprList();
                for (ExprNode expr : exprNode) {
                    if (expr != null) {
                        treeContent += expr.accept(this);
                    }
                }

                String typeWrite = ((WriteStatNode) node).getTypeWrite();
                treeContent += String.format("%s", typeWrite);

                treeContent += String.format("</%s>", "WriteStat") + "\n";

            }

            return treeContent;
        }

        if(node instanceof ParDeclNode){
            treeContent = String.format("<%s>", "ParDeclNode") + "\n";

            TypeNode type = ((ParDeclNode) node).getTypeVar();
            treeContent += type.accept(this);

            ArrayList<IdInitNode> idList = ((ParDeclNode) node).getIdList();
            if(idList != null){
                for (IdInitNode idElement : idList) {
                    if (idElement != null) {
                        treeContent += idElement.accept(this);
                    }
                }
            }

            treeContent += "<Out>" + String.format("%s", ((ParDeclNode) node).getOut()) + "</Out>" + "\n";

            treeContent += String.format("</%s>", "ParDeclNode") + "\n";

            return treeContent;
        }

        if(node instanceof TypeNode){
            treeContent = String.format("<%s>", "TypeNode") + "\n";

            treeContent += "<TypeVar>" + String.format("%s", ((TypeNode) node).getTypeVar()) + "</TypeVar>";

            treeContent += String.format("</%s>", "TypeNode") + "\n";

            return treeContent;
        }

        return treeContent;
    }
}
