package visitor;

import esercitazione5.sym;
import exceptions.IncompatibleNumberParamException;
import exceptions.IncompatibleTypeException;
import exceptions.NotFunctionException;
import exceptions.VariableNotDeclaredException;
import nodes.ASTNode;
import nodes.*;
import table.SymbolRecord;
import table.SymbolTable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

public class MyTypeVisitor implements MyVisitor {

    private Stack<SymbolTable> stack;

    public MyTypeVisitor() {
        this.stack = new Stack<SymbolTable>();
    }

    @Override
    public String visit(ASTNode node) {
        switch (node.getClass().getSimpleName()) {
            case "ProgramNode" -> visitProgramNode((ProgramNode) node);
            case "BodyNode" -> visitBodyNode((BodyNode) node);
            case "VarDeclNode" -> visitVarDeclNode((VarDeclNode) node);
            case "FunDeclNode" -> visitFunDeclNode((FunDeclNode) node);
            case "IfStatNode" -> visitIfStatNode((IfStatNode) node);
            case "ForStatNode" -> visitForStatNode((ForStatNode) node);
            case "WhileStatNode" -> visitWhileStatNode((WhileStatNode) node);
            case "AssignStatNode" -> visitAssignStatNode((AssignStatNode) node);
            case "BiVarExprNode" -> visitBiVarExprNode((BiVarExprNode) node);
            case "UniVarExprNode" -> visitUniVarExprNode((UniVarExprNode) node);
            case "ReadStatNode" -> visitReadStatNode((ReadStatNode) node);
            case "WriteStatNode" -> visitWriteStatNode((WriteStatNode) node);
            case "FunCallExprNode" -> visitFunCallExprNode((FunCallExprNode) node);
            case "FunCallStatNode" -> visitFunCallStatNode((FunCallStatNode) node);
            case "FunCallNode" -> visitFunCallNode((FunCallNode) node);
            case "ConstNode" -> visitConstNode((ConstNode) node);
            case "IdNode" -> visitIdNode((IdNode) node);
        }

        return null;
    }

    private void visitProgramNode(ProgramNode node) {

        System.out.println("MyTypeVisitor: inside visitProgramNode");

        stack.push(node.getSymbolTable());
        ArrayList<VarDeclNode> varDeclList = node.getVarDeclList();
        ArrayList<FunDeclNode> funDeclList = node.getFunDeclList();

        visitNodeList(varDeclList);
        visitNodeList(funDeclList);

        stack.pop();
    }

    private void visitVarDeclNode(VarDeclNode node) {

        String varType = node.getType();
        int typeChecker = MyTypeChecker.getInferenceType(varType);

        ArrayList<IdInitNode> idInitList = node.getIdInitList();
        ArrayList<IdInitNode> idInitObblList = node.getIdInitObblList();

        if (varType.equals("VAR")) {
            for (IdInitNode element : idInitObblList) {
                element.getId().accept(this);
            }
        } else {
            for (IdInitNode idElement : idInitList) {
                ExprNode exprNode = idElement.getExpr();
                idElement.getId().accept(this);

                if (exprNode != null) {
                    exprNode.accept(this);
                    idElement.setAstType(MyTypeChecker.AssignOperations(exprNode.getAstType(), typeChecker));
                }
            }
        }

    }

    private void visitFunDeclNode(FunDeclNode node) {
        FunDeclNode funDecl = node.getFunDecl();
        funDecl.getId().setAstType(funDecl.getAstType());

        stack.push(funDecl.getSymbolTable());

        ArrayList<ParDeclNode> parDeclList = funDecl.getParDeclList();
        if (parDeclList != null) {
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

        if (exprCondition.getAstType() == sym.BOOL) {
            BodyNode bodyNode = node.getBody();
            bodyNode.accept(this);

            BodyNode elseBodyNode = node.getElseStat().getBody();
            if (elseBodyNode != null) {
                stack.push(node.getElseSymbolTable());
                elseBodyNode.accept(this);
                stack.pop();
            }
        } else {
            throw new IncompatibleTypeException("La condizione dell'if deve essere un BOOL");
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

        if (intConst1.getAstType() == sym.INTEGER && intConst2.getAstType() == sym.INTEGER) {
            BodyNode bodyNode = node.getBody();
            bodyNode.accept(this);
        } else {
            throw new IncompatibleTypeException("I tipi del for devono essere INTEGER");
        }

        stack.pop();
    }

    private void visitWhileStatNode(WhileStatNode node) {
        stack.push(node.getSymbolTable());

        ExprNode conditionNode = node.getExpr();
        conditionNode.accept(this);

        if (conditionNode.getAstType() == sym.BOOL) {
            BodyNode bodyNode = node.getBody();
            bodyNode.accept(this);
        } else {
            throw new IncompatibleTypeException("La condizione del while deve essere BOOL");
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
        } else {
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
                throw new Error("Il numero di variabili non coincide con il numero di espressioni da assegnare.");
            }

            node.setAstType(sym.VOID);
        }
    }

    private void visitBiVarExprNode(BiVarExprNode node) {
        String operation = node.getModeExpr();
        System.out.println(operation);
        ExprNode exprNode1 = node.getExprNode1();
        ExprNode exprNode2 = node.getExprNode2();
        System.out.println(exprNode2.getModeExpr() + " " + exprNode2.getAstType() + " " + exprNode2.getName());

        exprNode1.accept(this);
        exprNode2.accept(this);

        int typeChecker = sym.error;

        if (operation.equals("PLUS") || operation.equals("MINUS") || operation.equals("TIMES") || operation.equals("DIV") || operation.equals("POW")) {
            typeChecker = MyTypeChecker.binaryOperations(operation, exprNode1.getAstType(), exprNode2.getAstType());
        } else if (operation.equals("AND") || operation.equals("OR")) {
            typeChecker = MyTypeChecker.binaryOperations(operation, exprNode1.getAstType(), exprNode2.getAstType());
        } else if (operation.equals("STR_CONCAT")) {
            typeChecker = MyTypeChecker.binaryOperations(operation, exprNode1.getAstType(), exprNode2.getAstType());
        } else if (operation.equals("EQUALS") || operation.equals("NE") || operation.equals("LT") || operation.equals("LE") || operation.equals("GT") || operation.equals("GR")) {
            typeChecker = MyTypeChecker.binaryOperations(operation, exprNode1.getAstType(), exprNode2.getAstType());
        }

        if (typeChecker != sym.error) {
            node.setAstType(typeChecker);
        } else {
            throw new IncompatibleTypeException("Il tipo " + exprNode1.getModeExpr() + " e il tipo " + exprNode2.getModeExpr() + " non sono compatibili.");
        }
    }

    private void visitUniVarExprNode(UniVarExprNode node) {
        String operation = node.getName();
        ExprNode exprNode = node.getExprNode();

        exprNode.accept(this);

        int typeChecker = sym.error;

        if (operation.equals("UMINUS")) {
            typeChecker = MyTypeChecker.unaryOperations(operation, exprNode.getAstType());
        } else if (operation.equals("NOT")) {
            typeChecker = MyTypeChecker.unaryOperations(operation, exprNode.getAstType());
        }

        if (typeChecker != sym.error) {
            node.setAstType(typeChecker);
        } else {
            throw new IncompatibleTypeException("L'operazione " + operation + " non è compatibile con i tipi.");
        }

    }

    private void visitReadStatNode(ReadStatNode node) {
        for (IdInitNode idElement : node.getIdList()) {
            ExprNode exprNode = idElement.getExpr();
            exprNode.accept(this);

            idElement.setAstType(sym.VOID);
        }

        node.setAstType(sym.VOID);
    }

    private void visitWriteStatNode(WriteStatNode node) {
        ArrayList<ExprNode> exprNodeList = node.getExprList();

        visitNodeList(exprNodeList);

        node.setAstType(sym.VOID);
    }

    private void visitFunCallStatNode(FunCallStatNode node) {
        System.out.println("MyTypeVisitor: inside visitFunCallStat");

        FunCallNode funCallNode = node.getFunCall();

        visitFunCallNode(funCallNode);
    }

    private void visitFunCallExprNode(FunCallExprNode node) {
        System.out.println("MyTypeVisitor: inside visitFunCallExpr");

        FunCallNode funCallNode = node.getFunCall();

        visitFunCallNode(funCallNode);
    }

    private void visitFunCallNode(FunCallNode node) {
        System.out.println("MyTypeVisitor: inside visitFunCall");

        String functionName = node.getName();
        SymbolRecord functionSymbolRecord = lookup(functionName);

        if (!functionSymbolRecord.getKind().equals("FUN"))
            throw new NotFunctionException(functionName + " is not a function!");

        ArrayList<Integer> parCallList = new ArrayList<>();
        ArrayList<ExprNode> exprNodeList = node.getExprList();

        if (exprNodeList != null) {
            for (ExprNode expr : exprNodeList) {
                expr.accept(this);
                parCallList.add(expr.getAstType());
            }
        }

        ArrayList<Integer> parFunList = functionSymbolRecord.getParInitialize().getParamsTypeList();
        ArrayList<Boolean> parFunListOut = functionSymbolRecord.getParInitialize().getParamsOutList();

        Collections.reverse(parCallList);

        /**
         * Controllo se la lista dei tipi e la lista degli out combacia con la lista di parametri della funzione chiamata
         */

        if (parCallList.size() == parFunList.size()  && parCallList.size() == parFunListOut.size()) {
            for (int i = 0; i < parCallList.size(); i++) {
                if (!parCallList.get(i).equals(parFunList.get(i))) {
                    throw new IncompatibleTypeException("I tipi dei parametri della chiamata a funzione " + functionName + " non coincidono con la firma della funzione");
                }

                if (!parCallList.get(i).equals(parFunListOut.get(i))) {
                    throw new IncompatibleTypeException("I tipi dei parametri della chiamata a funzione " + functionName + " non coincidono con la firma della funzione");
                }
            }
        } else {
            throw new IncompatibleNumberParamException("Il numero di parametri passati alla chiamata della funzione " + functionName + " non coincide con la firma della funzione");
        }

        int type = functionSymbolRecord.getTypeVar();
        System.out.println("MyTypeVisitor: " + type);

        if (type != sym.error) {
            // Imposta il tipo della funzione nella chiamata e nel nodo stesso
            node.getId().setAstType(type);
            node.setAstType(type);
        } else {
            throw new IncompatibleTypeException("Incompatible type: " + type);
        }
    }

    private void visitConstNode(ConstNode node) {
        int typeChecking = MyTypeChecker.getInferenceType(node.getModeExpr());
        System.out.println("visitConstNode: " + typeChecking);

        node.setAstType(typeChecking);
    }

    void visitIdNode(IdNode node) {
        String nomeId = node.getNomeId();
        SymbolRecord symbolRecord = lookup(nomeId);
        int typeVar = symbolRecord.getTypeVar();
        System.out.println("visitIdNode: " + typeVar);
        node.setAstType(typeVar);
    }

    private void visitNodeList(ArrayList<? extends ASTNode> nodeList) {
        if (nodeList != null) {
            for (int i = 0; i < nodeList.size(); i++) {
                System.out.println("inside visitNodeList " +nodeList.get(i).getName());

                nodeList.get(i).accept(this);
            }
        }
    }

    public SymbolRecord lookup(String item){
        SymbolRecord found = null;
        for (SymbolTable current : stack){
            found = current.get(item);
            if(found != null) {
                return found;
            }
        }

        throw new VariableNotDeclaredException("Not Declared: " + item);
    }

}
