package visitor;

import exceptions.*;
import nodes.ASTNode;
import nodes.*;
import esercitazione5.*;
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
            case "ReturnStatNode" -> visitReturnStatNode((ReturnStatNode) node);
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
        stack.push(node.getSymbolTable());

        ArrayList<VarDeclNode> varDeclList = node.getVarDeclList();
        ArrayList<FunDeclNode> funDeclList = node.getFunDeclList();

        visitNodeList(varDeclList);
        visitNodeList(funDeclList);

        stack.pop();
    }

    private void visitVarDeclNode(VarDeclNode node) {

        int typeChecker = 0;

        if(node.getType() != null) {
            String varType = node.getType();
            typeChecker = MyTypeChecker.getInferenceType(varType);
        }

        if (node.isVar()) {
            processVariableDeclaration(node.getIdInitObblList());
        } else {
            processVariableAssignment(node.getIdInitList(), typeChecker);
        }
    }

    private void visitFunDeclNode(FunDeclNode node) {
        //Se il node è main assegna node.getFunDecl(); altrimenti assegna node.
        FunDeclNode funDeclNode = node.isMain() ? node.getFunDecl() : node;

        if (funDeclNode != null) {
            funDeclNode.getId().setAstType(funDeclNode.getAstType());
        }

        assert funDeclNode != null;
        stack.push(funDeclNode.getSymbolTable());

        processParameterDeclarations(funDeclNode);

        System.out.println("Fundecl " + funDeclNode.getId().getNomeId());

        if(!funDeclNode.getTypeOrVoid().equals("VOID") && !returnStatIsPresent(funDeclNode.getBody().getStatList())){
            throw new MissingReturnException("La funzione non presenta return");
        } else {
            BodyNode body = funDeclNode.getBody();
            body.accept(this);
        }

        stack.pop();
    }

    private boolean returnStatIsPresent(ArrayList<StatNode> statList){
        for(StatNode stat : statList){
            if(stat instanceof ReturnStatNode) {
                return true;
            }
        }
        return false;
    }

    private void visitBodyNode(BodyNode node) {

        ArrayList<VarDeclNode> varDeclList = node.getVarDeclList();
        ArrayList<StatNode> statList = node.getStatList();

        visitNodeList(varDeclList);
        visitNodeList(statList);

    }

    private void visitIfStatNode(IfStatNode node) {
        stack.push(node.getSymbolTable());
        System.out.println("ifstat " + node.getSymbolTable());

        ExprNode exprCondition = node.getExpr();
        exprCondition.accept(this);

        if (exprCondition.getAstType() == sym.BOOL) {
            BodyNode bodyNode = node.getBody();
            bodyNode.accept(this);

            if (node.getElseStat() != null) {
                BodyNode elseBodyNode = node.getElseStat().getBody();

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

    private void visitReturnStatNode(ReturnStatNode node) {
        System.out.println("return ricorrenza");
        int returnTypeFun = 0;
        if(stack.peek() != null){
            System.out.println(stack.peek());
            String functionName = stack.peek().getFunctionName();

            if (functionName != null) {
                SymbolRecord symbolRecord = lookup(functionName);
                if(symbolRecord != null) {
                    System.out.println("return symbolRecord " + symbolRecord);
                    returnTypeFun = symbolRecord.getReturnTypeFun();
                    System.out.println("return returntypefun " + returnTypeFun);
                }
            }
        }

        ExprNode exprNode = node.getExpr();

        System.out.println("return -----> " + returnTypeFun + " " + exprNode);

        //controllo il tipo di ritorno della funzione se expr è presente oppure no
        //non è VOID e l'espressione è presente
        if((returnTypeFun != 0 && returnTypeFun != 12) && exprNode != null){
            exprNode.accept(this);
            if(!MyTypeChecker.returnChecker(returnTypeFun, exprNode.getAstType())) {
                throw new IncompatibleTypeException("I tipi non combaciano con i ritorni delle funzioni");
            }
            //non è VOID e l'espressione è null, deve dare errore
        } else if ((returnTypeFun != 0 && returnTypeFun != 12) && exprNode == null) {
            throw new ReturnException("Il tipo di ritorno è " + returnTypeFun + " ma non sono presenti espressioni");
            //è VOID e l'espressione è null, deve proseguire
        } else if (returnTypeFun == 12 && exprNode == null){
            return;
        }

    }

    private void visitBiVarExprNode(BiVarExprNode node) {
        String operation = node.getModeExpr();
        ExprNode exprNode1 = node.getExprNode1();
        ExprNode exprNode2 = node.getExprNode2();

        exprNode1.accept(this);
        exprNode2.accept(this);

        int typeChecker = switch (operation) {
            case "PLUS", "MINUS", "TIMES", "DIV", "POW", "AND", "OR", "STR_CONCAT", "EQUALS", "NE", "LT", "LE", "GT", "GR" ->
                   MyTypeChecker.binaryOperations(operation, exprNode1.getAstType(), exprNode2.getAstType());
            default -> sym.error;
        };

        if (typeChecker != sym.error) {
            node.setAstType(typeChecker);
        } else {
            throw new IncompatibleTypeException("Il tipo " + exprNode1.getModeExpr() + " e il tipo " + exprNode2.getModeExpr() + " non sono compatibili.");
        }
    }

    private void visitUniVarExprNode(UniVarExprNode node) {
        String operation = node.getModeExpr();
        ExprNode exprNode = node.getExprNode();

        exprNode.accept(this);

        int typeChecker = sym.error;

        if (operation.equals("MINUS")) {
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

            if(exprNode != null){
                exprNode.accept(this);
            }

            SymbolRecord symbolRecord = lookup(idElement.getId().getNomeId());

            if(symbolRecord != null){
                idElement.setAstType(symbolRecord.getTypeVar());
            }
        }

        node.setAstType(sym.VOID);
    }

    private void visitWriteStatNode(WriteStatNode node) {
        ArrayList<ExprNode> exprNodeList = node.getExprList();

        visitNodeList(exprNodeList);

        node.setAstType(sym.VOID);
    }

    private void visitFunCallStatNode(FunCallStatNode node) {
        FunCallNode funCallNode = node.getFunCall();

        visitFunCallNode(funCallNode);
    }

    private void visitFunCallExprNode(FunCallExprNode node) {
        FunCallNode funCallNode = node.getFunCall();
        String functionName = funCallNode.getId().getNomeId();

        SymbolRecord symbolRecord = lookup(functionName);

        node.setAstType(symbolRecord.getReturnTypeFun());

        visitFunCallNode(funCallNode);
    }

    private void visitFunCallNode(FunCallNode node) {
        String functionName = node.getId().getNomeId();

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

            Collections.reverse(exprNodeList);
            Collections.reverse(parCallList);
        }

        ArrayList<Integer> parFunList = functionSymbolRecord.getParInitialize().getParamsTypeList();
        ArrayList<Boolean> parFunListOut = functionSymbolRecord.getParInitialize().getParamsOutList();

        /**
         * Controllo se la lista dei tipi e la lista degli out combacia con la lista di parametri della funzione chiamata
         */
        if (parCallList.size() == parFunList.size() && parCallList.size() == parFunListOut.size()) {
            for (int i = 0; i < parCallList.size(); i++) {
                if (!MyTypeChecker.returnChecker(parCallList.get(i), parFunList.get(i))) {
                    throw new IncompatibleTypeException("I tipi dei parametri della chiamata a funzione " + functionName + " non coincidono con la firma della funzione");
                }
            }
        } else {
            throw new IncompatibleNumberParamException("Il numero di parametri passati alla chiamata della funzione " + functionName + " non coincide con la firma della funzione");
        }

        /**
         * Controllo se exprElement nella posizione i è out e controllo se è istanza di IdNode;
         * Se non è istanza di IdNode potrebbe significare che è un BiVarExprNode
         */
        if (exprNodeList != null) {
            for (int i = 0; i < exprNodeList.size(); i++) {
                ExprNode exprElement = exprNodeList.get(i);

                if (parFunListOut.get(i) && !(exprElement instanceof IdNode)) {
                    throw new IncompatibleParamException("Errore nel passaggio del parametro per riferimento per la funzione: " + functionName);
                }
            }
        }

        int type = functionSymbolRecord.getReturnTypeFun();

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

        node.setAstType(typeChecking);
    }

    void visitIdNode(IdNode node) {
        String nomeId = node.getNomeId();
        SymbolRecord symbolRecord = lookup(nomeId);

        int typeVar = symbolRecord.getTypeVar();

        node.setAstType(typeVar);
    }

    private void visitNodeList(ArrayList<? extends ASTNode> nodeList) {
        if (nodeList != null) {
            for (int i = 0; i < nodeList.size(); i++) {
                nodeList.get(i).accept(this);
            }
        }
    }

    private void processVariableDeclaration(ArrayList<IdInitNode> idInitObblList) {
        if (idInitObblList != null) {
            for (IdInitNode element : idInitObblList) {
                element.getId().accept(this);
            }
        }
    }

    private void processVariableAssignment(ArrayList<IdInitNode> idInitList, int typeChecker) {
        if (idInitList != null) {
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

    private void processParameterDeclarations(FunDeclNode funDeclNode) {
        ArrayList<ParDeclNode> parDeclList = funDeclNode.getParDeclList();
        if (parDeclList != null) {
            for (ParDeclNode param : parDeclList) {
                for (IdInitNode element : param.getIdList()) {
                    element.getId().accept(this);
                }
            }
        }
    }

    public SymbolRecord lookup(String item){
        SymbolRecord found = null;
        for (SymbolTable current : stack){
            if(current != null){
                found = current.get(item);
                if (found != null) {
                    return found;
                }
            }
        }
        throw new MissingItemException("Item " + item + " non presente.");
    }

}
