package visitor;

import esercitazione5.sym;
import nodes.ASTNode;
import nodes.*;
import table.SymbolTable;

import java.util.ArrayList;
import java.util.Stack;

public class MyTypeVisitor implements MyVisitor{

    private Stack<SymbolTable> stack;

    public MyTypeVisitor() {
        this.stack = new Stack<SymbolTable>();
    }

    @Override
    public String visit(ASTNode node) {
        switch (node.getClass().getSimpleName()) {
            case "ProgramNode":
                visitProgramNode((ProgramNode) node);
                break;
            case "BodyNode":
                visitBodyNode((BodyNode) node);
                break;
            case "VarDeclNode":
                visitVarDeclNode((VarDeclNode) node);
                break;
            case "FunDeclNode":
                visitFunDeclNode((FunDeclNode) node);
                break;
            case "IfStatNode":
                visitIfStatNode((IfStatNode) node);
                break;
            case "ForStatNode":
                visitForStatNode((ForStatNode) node);
                break;
            case "WhileStatNode":
                visitWhileStatNode((WhileStatNode) node);
                break;
            case "AssignStatNode":
                visitAssignStatNode((AssignStatNode) node);
                break;
            case "BiVarExprNode":
                visitBiVarExprNode((BiVarExprNode) node);
                break;
            case "UniVarExprNode":
                visitUniVarExprNode((UniVarExprNode) node);
                break;

        }

        return null;
    }

    private void visitProgramNode(ProgramNode node) {

        stack.push(node.getSymbolTable());
        ArrayList<VarDeclNode> varDeclList = node.getVarDeclList();
        ArrayList<FunDeclNode> funDeclList = node.getFunDeclList();

        visitNodeList(varDeclList);
        visitNodeList(funDeclList);
        
        stack.pop();
    }

    private void visitVarDeclNode(VarDeclNode node) {

        String varType = node.getType();
        int type = MyTypeChecker.getInferenceType(varType);

        ArrayList<IdInitNode> idInitList = node.getIdInitList();
        ArrayList<IdInitNode> idInitObblList = node.getIdInitObblList();

        if(varType.equals("VAR")){
            for(IdInitNode element : idInitObblList){
                element.getId().accept(this);
            }
        } else {
            for(IdInitNode idElement : idInitList){
                ExprNode exprNode = idElement.getExpr();
                idElement.getId().accept(this);

                if(exprNode != null){
                    exprNode.accept(this);
                    idElement.setAstType(MyTypeChecker.AssignOperations(exprNode.getAstType(),type));
                }
            }
        }

    }

    private void visitFunDeclNode(FunDeclNode node) {
        FunDeclNode funDecl = node.getFunDecl();
        funDecl.getId().setAstType(funDecl.getAstType());

        stack.push(funDecl.getSymbolTable());

        ArrayList<ParDeclNode> parDeclList = funDecl.getParDeclList();
        if(parDeclList != null){
            for (ParDeclNode param : parDeclList) {
                for (IdInitNode element : param.getIdList()) {
                    element.getId().accept(this);
                }
            }
        }

        BodyNode body = funDecl.getBody();
        body.accept(this);

        stack.pop();
    }

    private void visitBodyNode(BodyNode node) {

        ArrayList<VarDeclNode> varDeclList = node.getVarDeclList();
        ArrayList<StatNode> statList = node.getStatList();

        visitNodeList(varDeclList);
        visitNodeList(statList);

    }

    private void visitIfStatNode(IfStatNode node) {
        stack.push(node.getSymbolTable());

        ExprNode exprCondition = node.getExpr();
        exprCondition.accept(this);

        if(exprCondition.getAstType() == sym.BOOL){
            BodyNode bodyNode = node.getBody();
            bodyNode.accept(this);

            BodyNode elseBodyNode = node.getElseStat().getBody();
            if(elseBodyNode != null){
                stack.push(node.getElseSymbolTable());
                elseBodyNode.accept(this);
                stack.pop();
            }
        } else {
            throw new Error("La condizione dell'if deve essere un BOOL");
        }

        stack.pop();
    }

    private void visitForStatNode(ForStatNode node) {
        stack.push(node.getSymbolTable());

        ExprNode intConst1 = node.getIntConst1();
        ExprNode intConst2 = node.getIntConst2();

        node.getId().accept(this);
        intConst1.accept(this);
        intConst2.accept(this);

        if(intConst1.getAstType() == sym.INTEGER && intConst2.getAstType() == sym.INTEGER){
            BodyNode bodyNode = node.getBody();
            bodyNode.accept(this);
        } else{
            throw new Error("I tipi del for devono essere INTEGER");
        }

        stack.pop();
    }

    private void visitWhileStatNode(WhileStatNode node) {
        stack.push(node.getSymbolTable());

        ExprNode conditionNode = node.getExpr();
        conditionNode.accept(this);

        if(conditionNode.getAstType() == sym.BOOL){
            BodyNode bodyNode = node.getBody();
            bodyNode.accept(this);
        }else{
            throw new Error("La condizione del while deve essere BOOL");
        }

        stack.pop();
    }

    private void visitAssignStatNode(AssignStatNode node) {
        ArrayList<IdInitNode> idList = node.getIdList();
        ArrayList<ExprNode> exprList = node.getExprList();

        if (exprList.size() == 1) {
            ExprNode exprNode = exprList.get(0);
            exprNode.accept(this);

            for (IdInitNode idInit : idList) {
                IdNode idNode = idInit.getId();
                idNode.accept(this);

                int assignedType = MyTypeChecker.AssignOperations(exprNode.getAstType(), idNode.getAstType());
                idInit.setAstType(assignedType);
            }

           node.setAstType(sym.VOID);
        }else {
            if (exprList.size() == idList.size()) {
                for (ExprNode exprNode : exprList) {
                    exprNode.accept(this);

                    for (IdInitNode idInit : idList) {
                        IdNode idNode = idInit.getId();
                        idNode.accept(this);

                        int assignedType = MyTypeChecker.AssignOperations(exprNode.getAstType(), idNode.getAstType());
                        idInit.setAstType(assignedType);
                    }
                }
            } else {
                throw new Error("Il numero di variabili non coincide con il numero di espressioni da assegnare");
            }

            node.setAstType(sym.VOID);
        }
    }

    private void visitBiVarExprNode(BiVarExprNode node) {
    }

    private void visitUniVarExprNode(UniVarExprNode node) {
    }

    private void visitNodeList(ArrayList<? extends ASTNode> nodeList) {
        if (nodeList != null) {
            for (int i = 0; i <= nodeList.size(); i++){
                nodeList.get(i).accept(this);
            }
        }
    }

}
