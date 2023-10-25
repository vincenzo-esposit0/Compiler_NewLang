package visitor;

import nodes.*;
import table.SymbolRecord;
import table.SymbolTable;
import esercitazione5.sym;

import java.util.ArrayList;
import java.util.Stack;
import java.util.zip.InflaterInputStream;

public class MyScopeVisitor implements MyVisitor{

    private Stack<SymbolTable> stackScope;

    private SymbolTable symbolTable;

    public MyScopeVisitor() {
        this.stackScope = new Stack<SymbolTable>();
    }

    @Override
    public String visit(ASTNode node) {
        if(node instanceof ProgramNode){
            visitProgramNode((ProgramNode) node);
        }
        else if(node instanceof BodyNode){
            visitBodyNode((BodyNode) node);
        }
        else if(node instanceof VarDeclNode){
            visitVarDeclNode((VarDeclNode) node);
        }
        else if(node instanceof FunDeclNode){
            visitFunDeclNode((FunDeclNode) node);
        }
        else if(node instanceof ParDeclNode){
            visitParDeclNode((ParDeclNode) node);
        }
        else if(node instanceof IfStatNode){
            visitIfStatNode((IfStatNode) node);
        }
        else if(node instanceof ElseNode){
            visitElseNode((ElseNode) node);
        }
        else if(node instanceof ForStatNode){
            visitForStatNode((ForStatNode) node);
        }
        else if(node instanceof WhileStatNode){
            visitWhileStatNode((WhileStatNode) node);
        }

        return null;
    }

    private void visitProgramNode(ProgramNode node) {
        symbolTable = new SymbolTable("Global");
        stackScope.push(symbolTable);
        node.setSymbolTable(symbolTable);

        ArrayList<VarDeclNode> varDeclListNode = ((ProgramNode) node).getVarDeclList();
        for (VarDeclNode varDecl : varDeclListNode) {
            if (varDecl != null) {
                varDecl.accept(this);
            }
        }

        //capire come gestire il main
        ArrayList<FunDeclNode> funDeclListNode = ((ProgramNode) node).getFunDeclList();
        for (FunDeclNode funDecl : funDeclListNode) {
            if (funDecl != null) {
                funDecl.accept(this);
            }
        }

        stackScope.pop();
        node.setAstType(sym.VOID);
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

        if(node.getType().getName() == "VAR" ) {
            node.setAstType(sym.error);

            for (IdInitNode idElement : idInit) {
                String nomeID = idElement.getId().getNomeId();

                if (!stackScope.peek().containsKey(nomeID)) {
                    int typeCheck = MyTypeChecker.getInferenceType(idElement.getConstant().getName());

                    stackScope.peek().put(nomeID, new SymbolRecord(nomeID, "var", typeCheck));

                    node.setAstType(typeCheck);
                }
                else {
                    throw new Error("Id: " + nomeID + " già dichiarato all'interno dello scope");
                }
            }
        }
        else {
            int typeCheck = MyTypeChecker.getInferenceType(node.getType().getTypeVar());

            for (IdInitNode idElement : idInit) {
                String nomeID = idElement.getId().getNomeId();

                if (!stackScope.peek().containsKey(nomeID)) {
                    stackScope.peek().put(nomeID, new SymbolRecord(nomeID, "var", typeCheck));
                }
                else {
                    throw new Error("Id: " + nomeID + " già dichiarato all'interno dello scope");
                }
            }

            node.setAstType(typeCheck);

            }

    }

    private void visitFunDeclNode(FunDeclNode node) {

    }

    private void visitParDeclNode(ASTNode node) {

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
        Integer typeCheck = sym.INTEGER;
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
