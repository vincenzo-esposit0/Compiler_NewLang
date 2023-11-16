package visitor;

import esercitazione5.sym;
import exceptions.AlreadyDeclaredVariableException;
import nodes.*;
import table.ParInitialize;
import table.SymbolRecord;
import table.SymbolTable;

import java.util.ArrayList;
import java.util.Stack;

public class MyScopeVisitor implements MyVisitor{

    private Stack<SymbolTable> stackScope;

    private SymbolTable symbolTable;

    public MyScopeVisitor() {
        this.stackScope = new Stack<SymbolTable>();
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
            case "ParDeclNode":
                visitParDeclNode((ParDeclNode) node);
                break;
            case "IfStatNode":
                visitIfStatNode((IfStatNode) node);
                break;
            case "ElseNode":
                visitElseNode((ElseNode) node);
                break;
            case "ForStatNode":
                visitForStatNode((ForStatNode) node);
                break;
            case "WhileStatNode":
                visitWhileStatNode((WhileStatNode) node);
                break;
        }

        return null;
    }

    private void visitProgramNode(ProgramNode node) {
        symbolTable = new SymbolTable("Global");
        stackScope.push(symbolTable);
        node.setSymbolTable(symbolTable);


        ArrayList<VarDeclNode> varDeclListNode = node.getVarDeclList();
        visitNodeList(varDeclListNode);

        ArrayList<FunDeclNode> funDeclListNode = node.getFunDeclList();
        visitNodeList(funDeclListNode);

        stackScope.pop();

        node.setAstType(sym.VOID);
    }

    private void visitNodeList(ArrayList<? extends ASTNode> nodeList) {
        if (nodeList != null) {
            for (int i = 0; i <= nodeList.size(); i++){
                nodeList.get(i).accept(this);
            }
        }
    }

    private void visitBodyNode(BodyNode node) {
        ArrayList<VarDeclNode> varDeclListNode = node.getVarDeclList();
        for (VarDeclNode varDecl : varDeclListNode) {
            if (varDecl != null) {
                varDecl.accept(this);
            }
        }

        ArrayList<StatNode> statList = node.getStatList();
        for (StatNode stat : statList) {
            if (stat != null) {
                stat.accept(this);
            }
        }

        node.setAstType(sym.VOID);
    }

    private void visitVarDeclNode(VarDeclNode node) {
        ArrayList<IdInitNode> idInit = node.getIdInitList();

        if(node.getType().equals("VAR") ) {

            for (IdInitNode idElement : idInit) {
                String nomeID = idElement.getId().getNomeId();

                if (!stackScope.peek().containsKey(nomeID)) {
                    int typeCheck = MyTypeChecker.getInferenceType(idElement.getConstant().getName());

                    stackScope.peek().put(nomeID, new SymbolRecord(nomeID, "var", typeCheck));

                    node.setAstType(typeCheck);
                }
                else {
                    node.setAstType(sym.error);
                    throw new AlreadyDeclaredVariableException("Identifier is already declared within the scope: " + nomeID);
                }
            }
        }
        else {
            int typeCheck = MyTypeChecker.getInferenceType(node.getType());

            for (IdInitNode idElement : idInit) {
                String nomeID = idElement.getId().getNomeId();

                if (!stackScope.peek().containsKey(nomeID)) {
                    stackScope.peek().put(nomeID, new SymbolRecord(nomeID, "var", typeCheck));
                }
                else {
                    throw new AlreadyDeclaredVariableException("Identifier is already declared within the scope: " + nomeID);
                }
            }
            node.setAstType(typeCheck);
        }
    }

    private void visitFunDeclNode(FunDeclNode node) {
        FunDeclNode funDeclNode = node.getFunDecl();
        String nomeID = funDeclNode.getId().getNomeId();
        int returnTypeCheck = node.getAstType();

        SymbolTable symbolTableGlobal = stackScope.peek();

        if(!stackScope.peek().containsKey(nomeID)){
            symbolTable = new SymbolTable("FUN", nomeID);
            stackScope.push(symbolTable);

            ArrayList<ParDeclNode> parDeclList = node.getParDeclList();

            ArrayList<Integer> parTypesList = new ArrayList<>();
            ArrayList<Boolean> parOutList = new ArrayList<>();

            ParInitialize parInitialize = new ParInitialize(parTypesList, parOutList);

            if (parDeclList != null) {
                for (ParDeclNode parElement : parDeclList) {
                    parElement.accept(this);
                    for (int i = 0; i < parElement.getIdList().size(); i++) {
                        int parTypeCheck = MyTypeChecker.getInferenceType(parElement.getTypeVar());

                        parInitialize.addParamsTypeList(parTypeCheck);
                        parInitialize.addParamsOutList(parElement.getOut());
                    }
                }
            }

            symbolTableGlobal.put(nomeID, new SymbolRecord(nomeID, "FUN", parInitialize, returnTypeCheck));

            funDeclNode.getBody().accept(this);

        } else {
            throw new AlreadyDeclaredVariableException("Identifier of function is already declared within the scope: " + nomeID);
        }

        funDeclNode.setAstType(returnTypeCheck);
        node.setAstType(returnTypeCheck);
        funDeclNode.setSymbolTable(stackScope.peek());
        stackScope.pop();
    }


    private void visitParDeclNode(ParDeclNode node) {
        ArrayList<IdInitNode> idInitNodeList = node.getIdList();
        int typeCheck = MyTypeChecker.getInferenceType(node.getTypeVar());

        for(IdInitNode idElement: idInitNodeList){
            String nomeID = idElement.getId().getNomeId();

            if (!stackScope.peek().containsKey(nomeID)) {
                if(node.getOut().equals(Boolean.TRUE)) {
                    stackScope.peek().put(nomeID, new SymbolRecord(nomeID, "var", typeCheck, true));
                } else {
                    stackScope.peek().put(nomeID, new SymbolRecord(nomeID, "var", typeCheck));
                }
            }
            else {
                throw new AlreadyDeclaredVariableException("Identifier is already declared within the scope: " + nomeID);
            }

            idElement.setAstType(sym.VOID);
        }

        node.setAstType(typeCheck);
    }


    private void visitIfStatNode(IfStatNode node) {
        symbolTable = new SymbolTable("IF");
        stackScope.push(symbolTable);
        node.setSymbolTable(symbolTable);
        node.getBody().accept(this);
        stackScope.pop();

        if(node.getElseSymbolTable() != null){
            symbolTable = new SymbolTable("ELSE");
            stackScope.push(symbolTable);
            node.setSymbolTable(symbolTable);
            node.getBody().accept(this);
            stackScope.pop();
        }

        node.setAstType(sym.VOID);
    }

    private void visitElseNode(ElseNode node) {
        symbolTable = new SymbolTable("ELSE");
        stackScope.push(symbolTable);
        node.setSymbolTable(symbolTable);
        node.getBody().accept(this);

        node.setAstType(sym.VOID);

        stackScope.pop();
    }

    private void visitForStatNode(ForStatNode node) {
        symbolTable = new SymbolTable("FOR");
        stackScope.push(symbolTable);

        String i = node.getId().getNomeId();
        int typeCheck = sym.INTEGER;
        stackScope.peek().put(i, new SymbolRecord(i, "var", typeCheck));

        node.setSymbolTable(symbolTable);
        node.getBody().accept(this);

        node.setAstType(sym.VOID);

        stackScope.pop();
    }

    private void visitWhileStatNode(WhileStatNode node) {
        symbolTable = new SymbolTable("WHILE");
        stackScope.push(symbolTable);
        node.setSymbolTable(symbolTable);
        node.getBody().accept(this);

        node.setAstType(sym.VOID);

        stackScope.pop();
    }

}
