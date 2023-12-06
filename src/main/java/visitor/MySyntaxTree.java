package visitor;

import nodes.*;
import java.util.ArrayList;

public class MySyntaxTree implements MyVisitor {

    private String treeContent = "";

    @Override
    public String visit(ASTNode node) {
        switch (node.getClass().getSimpleName()) {
            case "ProgramNode" -> {
                return visitProgramNode((ProgramNode) node);
            }
            case "VarDeclNode" -> {
                return visitVarDeclNode((VarDeclNode) node);
            }
            case "IdInitNode" -> {
                return visitIdInitNode((IdInitNode) node);
            }
            case "IdNode" -> {
                return visitIdNode((IdNode) node);
            }
            case "ConstNode" -> {
                return visitConstNode((ConstNode) node);
            }
            case "BiVarExprNode" -> {
                return visitBiVarExprNode((BiVarExprNode) node);
            }
            case "UniVarExprNode" -> {
                return visitUniVarExprNode((UniVarExprNode) node);
            }
            case "FunCallExprNode" -> {
                return visitFunCallExprNode((FunCallExprNode) node);
            }
            case "FunDeclNode" -> {
                return visitFunDeclNode((FunDeclNode) node);
            }
            case "BodyNode" -> {
                return visitBodyNode((BodyNode) node);
            }
            case "ParDeclNode" -> {
                return visitParDeclNode((ParDeclNode) node);
            }
            case "IfStatNode" -> {
                return visitIfStatNode((IfStatNode) node);
            }
            case "ElseNode" -> {
                return visitElseNode((ElseNode) node);
            }
            case "ForStatNode" -> {
                return visitForStatNode((ForStatNode) node);
            }
            case "WhileStatNode" -> {
                return visitWhileStatNode((WhileStatNode) node);
            }
            case "AssignStatNode" -> {
                return visitAssignStatNode((AssignStatNode) node);
            }
            case "FunCallStatNode" -> {
                return visitFunCallStatNode((FunCallStatNode) node);
            }
            case "ReadStatNode" -> {
                return visitReadStatNode((ReadStatNode) node);
            }
            case "WriteStatNode" -> {
                return visitWriteStatNode((WriteStatNode) node);
            }
            case "FunCallNode" -> {
                return visitFunCallNode((FunCallNode) node);
            }
            case "ReturnStatNode" -> {
                return visitReturnStatNode((ReturnStatNode)node);
            }
        }

        return treeContent;
    }

    private String visitProgramNode(ProgramNode node) {
        treeContent = String.format("<%s>", node.getName()) + "\n";

        ArrayList<VarDeclNode> varDeclListNode = node.getVarDeclList();
        for (VarDeclNode varDecl : varDeclListNode) {
            if (varDecl != null) {
                treeContent += varDecl.accept(this);
            }
        }

        ArrayList<FunDeclNode> funDeclListNode = node.getFunDeclList();
        for (FunDeclNode funDecl : funDeclListNode) {
            if (funDecl != null) {
                treeContent += funDecl.accept(this);
            }
        }

        treeContent += String.format("</%s>", node.getName()) + "\n";

        return treeContent;
    }

    private String visitVarDeclNode(VarDeclNode node) {
        treeContent = String.format("<%s>", "VarDecl") + "\n";
        ArrayList<IdInitNode> idInit = node.getIdInitList();
        if(idInit != null){
            for (IdInitNode idElement : idInit) {
                if (idElement != null) {
                    treeContent += idElement.accept(this);
                }
            }
        }

        ArrayList<IdInitNode> idInitObbl = node.getIdInitObblList();
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

    private String visitIdInitNode(IdInitNode node) {
        treeContent = String.format("<%s>", "IdInitNode") + "\n";

        IdNode id = node.getId();
        treeContent += id.accept(this);

        ExprNode exprNode = node.getExpr();
        if(exprNode != null){
            treeContent += exprNode.accept(this);
        }
        ConstNode constNode = node.getConstant();
        if(constNode != null){
            treeContent += constNode.accept(this);
        }

        treeContent += String.format("</%s>", "IdInitNode")+ "\n";

        return treeContent;
    }

    private String visitIdNode(IdNode node) {
        treeContent = String.format("<%s>", "IdNode") + "\n";

        treeContent += String.format("%s", node.getNomeId()) + "\n";

        treeContent += String.format("</%s>", "IdNode") + "\n";

        return treeContent;
    }

    private String visitConstNode(ConstNode node) {
        treeContent = String.format("<%s>", "ConstNode") + "\n";

        treeContent += "<ModeExpr>" + String.format("%s", node.getModeExpr()) + "</ModeExpr> ";

        treeContent += String.format("%s", node.getValue()) + "\n";
        treeContent += String.format("</%s>", "ConstNode") + "\n";

        return treeContent;
    }

    private String visitBiVarExprNode(BiVarExprNode node) {
        treeContent = String.format("<%s>", "BiVarExprNode") + "\n";

        ExprNode exprNode1 = node.getExprNode1();
        treeContent += exprNode1.accept(this);

        ExprNode exprNode2 = node.getExprNode2();
        treeContent += exprNode2.accept(this);

        treeContent += String.format("</%s>", "BiVarExprNode") + "\n";

        return treeContent;
    }

    private String visitUniVarExprNode(UniVarExprNode node) {
        treeContent = String.format("<%s>", "UniVarExprNode") + "\n";

        ExprNode exprNode = node.getExprNode();
        treeContent += exprNode.accept(this);

        treeContent += String.format("</%s>", "UniVarExprNode") + "\n";

        return treeContent;
    }

    private String visitFunCallExprNode(FunCallExprNode node) {
        treeContent = String.format("<%s>", "FunCallExprNode") + "\n";

        FunCallNode funCall = node.getFunCall();
        treeContent += funCall.accept(this);

        treeContent += String.format("</%s>", "FunCallExprNode") + "\n";

        return treeContent;
    }

    private String visitFunDeclNode(FunDeclNode node) {
        if(node.isMain()){
            treeContent = String.format("<%s>", "Main") + "\n";

            FunDeclNode funDeclNode= node.getFunDecl();
            treeContent += funDeclNode.accept(this);

            treeContent += String.format("</%s>", "Main") + "\n";

        }
        else{
            treeContent = String.format("<%s>", "FunDeclNode") + "\n";

            treeContent += "<NameFunDeclNode>" + String.format("%s", node.getName()) + "</NameFunDeclNode>" + "\n";

            IdNode idNode = node.getId();
            treeContent += idNode.accept(this);

            ArrayList<ParDeclNode> parDeclList = node.getParDeclList();
            if(parDeclList != null){
                for(ParDeclNode parDecl : parDeclList ){
                    treeContent += parDecl.accept(this);
                }
            }

            treeContent += "<ReturnType>" + String.format("%s", node.getTypeOrVoid()) + "</ReturnType>" + "\n";

            BodyNode body = node.getBody();
            treeContent += body.accept(this);

            treeContent += String.format("</%s>", "FunDeclNode") + "\n";
        }

        return treeContent;
    }

    private String visitBodyNode(BodyNode node) {
        treeContent = String.format("<%s>", "Body") + "\n";

        ArrayList<VarDeclNode> varDeclListNode = node.getVarDeclList();
        for (VarDeclNode varDecl : varDeclListNode) {
            if (varDecl != null) {
                treeContent += varDecl.accept(this);
            }
        }

        ArrayList<StatNode> statList = node.getStatList();
        for (StatNode stat : statList) {
            if (stat != null) {
                if(stat.getName().equals("Expr")){
                    treeContent += "Return\n";
                    treeContent += stat.accept(this);

                } else {
                    treeContent += stat.accept(this);
                }
            }
        }

        treeContent += String.format("</%s>", "Body") + "\n";

        return treeContent;
    }

    private String visitIfStatNode(IfStatNode node) {
        treeContent = String.format("<%s>", "IfStat") + "\n";

        ExprNode expr = node.getExpr();
        treeContent += expr.accept(this);

        BodyNode body = node.getBody();
        treeContent += body.accept(this);

        ElseNode elseStat = node.getElseStat();
        if(elseStat != null) {
            treeContent += elseStat.accept(this);
        }

        treeContent += String.format("</%s>", "IfStat") + "\n";

        return treeContent;
    }

    private String visitElseNode(ElseNode node) {
        treeContent = String.format("<%s>", "ElseStat") + "\n";

        BodyNode body = node.getBody();
        treeContent += body.accept(this);

        treeContent += String.format("</%s>", "ElseStat") + "\n";

        return treeContent;
    }

    private String visitForStatNode(ForStatNode node) {
        treeContent = String.format("<%s>", "ForStat") + "\n";

        IdNode id = node.getId();
        treeContent += id.accept(this);

        ConstNode const1 = node.getIntConst1();
        treeContent += const1.accept(this);

        ConstNode const2 = node.getIntConst2();
        treeContent += const2.accept(this);

        BodyNode body = node.getBody();
        treeContent += body.accept(this);

        treeContent += String.format("</%s>", "ForStat") + "\n";

        return treeContent;
    }

    private String visitWhileStatNode(WhileStatNode node) {
        treeContent = String.format("<%s>", "WhileStat") + "\n";

        ExprNode expr = node.getExpr();
        treeContent += expr.accept(this);

        BodyNode body = node.getBody();
        treeContent += body.accept(this);

        treeContent += String.format("</%s>", "WhileStat") + "\n";

        return treeContent;
    }

    private String visitAssignStatNode(AssignStatNode node) {
        treeContent = String.format("<%s>", "AssignStat") + "\n";

        ArrayList<IdInitNode> idList = node.getIdList();
        for (IdInitNode idElement : idList) {
            if (idElement != null) {
                treeContent += idElement.accept(this);
            }
        }

        ArrayList<ExprNode> exprNode = node.getExprList();
        for (ExprNode expr : exprNode) {
            if (expr != null) {
                treeContent += expr.accept(this);
            }
        }

        treeContent += String.format("</%s>", "AssignStat") + "\n";

        return treeContent;
    }

    private String visitFunCallStatNode(FunCallStatNode node) {
        treeContent = String.format("<%s>", "FunCallStatNode") + "\n";

        FunCallNode funCall = node.getFunCall();
        treeContent += funCall.accept(this);

        treeContent += String.format("</%s>", "FunCallStatNode") + "\n";

        return treeContent;
    }

    private String visitReadStatNode(ReadStatNode node) {
        treeContent = String.format("<%s>", "ReadStat") + "\n";

        ArrayList<IdInitNode> idList = node.getIdList();
        for (IdInitNode idElement : idList) {
            if (idElement != null) {
                treeContent += idElement.accept(this);
            }
        }

        ConstNode stringConst = node.getStringConst();
        treeContent += stringConst.accept(this);

        treeContent += String.format("</%s>", "ReadStat") + "\n";

        return treeContent;
    }

    private String visitWriteStatNode(WriteStatNode node) {
        treeContent = String.format("<%s>", "WriteStat") + "\n";

        ArrayList<ExprNode> exprNode = node.getExprList();
        for (ExprNode expr : exprNode) {
            if (expr != null) {
                treeContent += expr.accept(this);
            }
        }

        String typeWrite = node.getTypeWrite();
        treeContent += String.format("%s", typeWrite);

        treeContent += String.format("</%s>", "WriteStat") + "\n";

        return treeContent;
    }

    private String visitFunCallNode(FunCallNode node) {
        treeContent = String.format("<%s>", "FunCall") + "\n";

        IdNode id = node.getId();
        treeContent += id.accept(this);

        ArrayList<ExprNode> exprNode = node.getExprList();
        for (ExprNode expr : exprNode) {
            if (expr != null) {
                treeContent += expr.accept(this);
            }
        }

        treeContent += String.format("</%s>", "FunCall") + "\n";
        return treeContent;
    }

    private String visitParDeclNode(ParDeclNode node) {
        treeContent = String.format("<%s>", "ParDeclNode") + "\n";

        treeContent += "<Type>" + String.format("%s", node.getTypeVar()) + "</Type>" + "\n";

        ArrayList<IdInitNode> idList = node.getIdList();
        if(idList != null){
            for (IdInitNode idElement : idList) {
                if (idElement != null) {
                    treeContent += idElement.accept(this);
                }
            }
        }

        treeContent += "<Out>" + String.format("%s", node.getOut()) + "</Out>" + "\n";

        treeContent += String.format("</%s>", "ParDeclNode") + "\n";

        return treeContent;
    }

    private String visitReturnStatNode(ReturnStatNode node) {
        treeContent = String.format("<%s>", "ReturnStatNode") + "\n";

        ExprNode exprNode = node.getExpr();
        treeContent += "RETURN " + exprNode.accept(this);

        treeContent += String.format("</%s>", "ReturnStatNode") + "\n";

        return treeContent;
    }

}
