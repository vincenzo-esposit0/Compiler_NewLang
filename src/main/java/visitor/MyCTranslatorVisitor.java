package visitor;

import esercitazione5.sym;
import nodes.*;
import table.SymbolRecord;
import table.SymbolTable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

public class MyCTranslatorVisitor implements MyVisitor {

    private Stack<SymbolTable> stackScope = new Stack<>();
    private String codeGeneratorC = "";
    private boolean varDeclGlobal = true;

    @Override
    public String visit(ASTNode node) {
        codeGeneratorC = "";

        switch (node.getClass().getSimpleName()) {
            case "ProgramNode" -> visitProgramNode((ProgramNode) node);
            //case "VarDeclNode" -> visitVarDeclNode((VarDeclNode) node);
            case "FunDeclNode" -> visitFunDeclNode((FunDeclNode) node);
            case "BodyNode" -> visitBodyNode((BodyNode) node);
            case "ParDeclNode" -> visitParDeclNode((ParDeclNode) node);
            case "FunCallNode" -> visitFunCallNode((FunCallNode) node);
            case "FunCallExprNode" -> visitFunCallExprNode((FunCallExprNode) node);
            case "FunCallStatNode" -> visitFunCallStatNode((FunCallStatNode) node);
            case "IfStatNode" -> visitIfStatNode((IfStatNode) node);
            case "ElseNode" -> visitElseNode((ElseNode) node);
            case "ForStatNode" -> visitForStatNode((ForStatNode) node);
            case "WhileStatNode" -> visitWhileStatNode((WhileStatNode) node);
            case "ReadStatNode" -> visitReadStatNode((ReadStatNode) node);
            case "WriteStatNode" -> visitWriteStatNode((WriteStatNode) node);
            case "AssignStatNode" -> visitAssignStatNode((AssignStatNode) node);

        }

        return codeGeneratorC;
    }

    private void visitProgramNode(ProgramNode node) {
        StringBuilder sb = new StringBuilder();

        stackScope.push(node.getSymbolTable());
        codeGeneratorC += "#include <stdio.h>\n#include <stdlib.h>\n#include <string.h>\n#include <math.h>\n#include <malloc.h>\n";
        codeGeneratorC += "#define true 1\n";
        codeGeneratorC += "#define false 0\n";

        ArrayList<VarDeclNode> varDeclNodeList = node.getVarDeclList();
        ArrayList<FunDeclNode> funDeclNodeList = node.getFunDeclList();

        Collections.reverse(varDeclNodeList);

        //Definisco i prototipi delle funzioni
        for(FunDeclNode funElement: funDeclNodeList){
            FunDeclNode funDecl = funElement.getFunDecl();

            if(funDecl != null){
                //Intestazione della funzione
                sb.append(converterNumericToStringType(funDecl.getAstType())).append(" ").append(funDecl.getId().getNomeId()).append("(");

                //Analisi dei parametri
                if(funDecl.getParDeclList() != null){
                    for(ParDeclNode parElement: funDecl.getParDeclList()){
                        //Questo for mi serve per inserire nei prototipi i tipi che si aspetta di ottenere
                        for(IdInitNode idElement: parElement.getIdList()){

                            //Verifica se il tipo convertito è STRING
                            if(typeConverter(parElement.getTypeVar()).equals("char *")){
                                sb.append("char *");
                            } else {
                                sb.append(parElement.getTypeVar());
                            }

                            if(parElement.getOut().equals("OUT")){
                                sb.append("*");
                            }
                            sb.append(",");
                        }
                    }
                    //Tolgo l'ultima virgola che viene messa in automatico
                    sb.deleteCharAt(sb.length()-1);
                }
                sb.append(");\n");
            }
        }

        codeGeneratorC += sb.toString();

        //Funzioni per convertire integer e double a string e la funzione concat
        codeGeneratorC += """
                char* intToString(int var){
                    char *int_str = malloc(256);
                    sprintf(int_str, "%d", var);
                    return int_str;
                }
            
                char* doubleToString(double var){
                    char *double_str = malloc(256);
                    sprintf(double_str, "%f", var);
                    return double_str;
                }
            
                char* boolToString(int var){
                    if (var == 1){
                        return "true";
                    }
                    if (var == 0){
                        return "false";
                    }
                    return "";
                }
            
                char* concat(char *s1, char* i) {
                    char* s = malloc(256);
                    sprintf(s, "%s%s", s1, i);
                    return s;
                }
                """;

        String code = codeGeneratorC;
        sb.setLength(0);

        for(VarDeclNode varDecl : varDeclNodeList){
            if(varDecl != null){
                sb.append((varDecl).accept(this));
            }
        }

        varDeclGlobal = false;
        for(FunDeclNode funDecl : funDeclNodeList){
            if(funDecl != null){
                sb.append(funDecl.accept(this));
            }
        }

        codeGeneratorC = code + sb;
        stackScope.pop();
    }

    private void visitVarDeclNode(VarDeclNode node) {
        StringBuilder sb = new StringBuilder();

        String varType = node.getType();

        ArrayList<IdInitNode> idInitNodeList = node.getIdInitList();

        //Controllo se il flag della variabili globali è TRUE
        if(varDeclGlobal){
            for(IdInitNode idElement : idInitNodeList){
                String typeC = typeConverter(converterNumericToStringType(idElement.getId().getAstType()));

                //Se è di tipo VAR
                if(varType.equals("VAR")){
                    ConstNode costante = idElement.getConstant();

                    //Assegna il typo dato dal converter
                    sb.append(typeC).append(" ").append(idElement.getId().getNomeId()).append(" = ");
                    sb.append(costante.accept(this)).append(";");
                }
                //Se la variabile non è di tipo VAR
                else {
                    if(idElement.getExpr() != null) {

                        sb.append(typeC).append(" ").append(idElement.getId().getNomeId()).append(" (").append(" )");
                        sb.append(idElement.getExpr().accept(this));
                    } else {
                        sb.append(typeConverter(varType)).append(" ").append(idElement.getId().getNomeId()).append(";");

                    }
                }

                sb.append("\n");
            }
        }
        //Se il flag della variabili globali è FALSE
        else {
            if(varType.equals("VAR")){
                for(IdInitNode idElement: idInitNodeList){
                    String typeC = typeConverter(converterNumericToStringType(idElement.getId().getAstType()));

                    //Dichiaro la variabile prendendo il tipo dal typeConverter
                    sb.append(typeC).append(" ").append(idElement.getId().getNomeId()).append(" = ").append(idElement.getConstant().accept(this)).append(";\n");
                }
            } else{
                for(IdInitNode idElement: idInitNodeList){
                    //Se è una STRINGA viene trattato diversamente
                    if(typeConverter(varType).equals("char *")){
                        //Il typeConverter mi restituirà il tipo in C
                        sb.append(typeConverter(varType)).append(idElement.getId().getNomeId());

                        if (idElement.getExpr() == null) {
                            sb.append(" = \"\"");
                        }

                    } else {
                        sb.append(typeConverter(varType)).append(" ").append(idElement.getId().getNomeId());
                    }

                    if (idElement.getExpr() != null) {
                        sb.append(" = ").append(idElement.getExpr().accept(this));
                    }
                    sb.append(";\n");
                }
            }
        }

        codeGeneratorC += sb.toString();
    }

    private void visitFunDeclNode(FunDeclNode node) {
        StringBuilder sb = new StringBuilder();

        FunDeclNode funDeclNode;

        if(node.isMain()){
            funDeclNode = node.getFunDecl();
        } else{
            funDeclNode = node;
        }

        stackScope.push(funDeclNode.getSymbolTable());

        if(funDeclNode.isMain()){
            codeGeneratorC += "int main(int argc, char** argv){ \n";
            codeGeneratorC += funDeclNode.getId().getNomeId()+"(";

            //Generazione dei parametri della funzione
            if(funDeclNode.getParDeclList() != null){
                for (ParDeclNode parDeclNode : funDeclNode.getParDeclList()) {
                    int sizeParInput = parDeclNode.getIdList().size();

                    for(int i=0;i<sizeParInput;i++){
                        if (parDeclNode.getAstType() == sym.INTEGER) {
                            sb.append('0');
                        }
                        else if (parDeclNode.getAstType() == sym.REAL) {
                            sb.append("0.0");
                        }
                        else if (parDeclNode.getAstType() == sym.STRING) {
                            if(parDeclNode.getOut().equals("OUT")){
                                sb.append("argv");
                            }
                            else{
                                sb.append("\"\"");
                            }
                        }
                        else if (parDeclNode.getAstType() == sym.CHAR) {
                            sb.append("''");
                        }
                        else if (parDeclNode.getAstType() == sym.BOOL) {
                            sb.append("false");
                        }

                        sb.append(',');
                    }
                }
                //Toglie l'ultima virgola aggiunta
                sb.deleteCharAt(sb.length()-1);
            }
            codeGeneratorC += sb + ");\n";
            codeGeneratorC += "return (EXIT_SUCCESS);\n}\n";
        }
        else {
            codeGeneratorC += typeConverter(funDeclNode.getTypeOrVoid()) + " " + funDeclNode.getId().getNomeId() + "(";

            ArrayList<ParDeclNode> parDeclNodeList = funDeclNode.getParDeclList();
            String code = codeGeneratorC;
            sb.setLength(0);

            if (parDeclNodeList != null) {
                for (ParDeclNode parElement : parDeclNodeList) {
                    sb.append(parElement.accept(this));
                }
            }
            codeGeneratorC = code + sb + "){\n";
            codeGeneratorC += funDeclNode.getBody().accept(this);
            codeGeneratorC += "}\n";

            stackScope.pop();
        }
    }

    private void visitBodyNode(BodyNode node) {
        StringBuilder sb = new StringBuilder();

        ArrayList<VarDeclNode> varDeclNodeList = node.getVarDeclList();
        ArrayList<StatNode> statNodeList = node.getStatList();

        ArrayList<VarDeclNode> varNotAssignedList = new ArrayList<>();
        ArrayList<VarDeclNode> varAssignedList = new ArrayList<>();

        // Separo le dichiarazioni di variabili in due liste: assegnate e non assegnate
        for (VarDeclNode varElement : varDeclNodeList) {
            for (IdInitNode idElement : varElement.getIdInitList()) {
                if (idElement.getExpr() == null) {
                    varNotAssignedList.add(varElement);
                } else {
                    varAssignedList.add(varElement);
                }
            }
        }

        // Unisco le due liste mantenendo l'ordine delle variabili non assegnate prima di quelle assegnate
        varNotAssignedList.addAll(varAssignedList);
        varDeclNodeList.clear();
        varDeclNodeList.addAll(varNotAssignedList);

        // Itero sulla lista ordinata e genero il codice
        for (VarDeclNode varElement : varDeclNodeList) {
            if (varElement != null) {
                sb.append(varElement.accept(this));
            }
        }

        // Itero sulla lista delle istruzioni e genero il codice
        for (StatNode statElement : statNodeList) {
            if (statElement != null) {
                sb.append(statElement.accept(this));
            }
        }

        codeGeneratorC = sb.toString();
    }

    private void visitParDeclNode(ParDeclNode node) {
        StringBuilder sb = new StringBuilder();
        String typeC = node.getTypeVar();

        ArrayList<IdInitNode> idInitNodeList = node.getIdList();

        for(IdInitNode idElement: idInitNodeList){
            if(typeConverter(node.getTypeVar()).equals("char *")){
                sb.append(typeC);
            } else {
                sb.append(typeC).append(" ");
            }

            if(node.getOut()){
                sb.append("*");
            }

            sb.append(idElement.getId().getNomeId()).append(",");
        }

        sb.deleteCharAt(sb.length() - 1);

        codeGeneratorC = sb.toString();
    }

    private void visitFunCallNode(FunCallNode node) {
        StringBuilder sb = new StringBuilder();

        String nomeID = node.getId().getNomeId();
        ArrayList<ExprNode> exprNodeList = node.getExprList();

        SymbolRecord symbolRecord = lookup(nomeID);

        ArrayList<Integer> paramsTypeList = symbolRecord.getParInitialize().getParamsTypeList();
        ArrayList<Boolean> paramsOutList = symbolRecord.getParInitialize().getParamsOutList();

        System.out.println("--------->" + symbolRecord.toString());

        codeGeneratorC += nomeID + "(";

        String codeC = codeGeneratorC;
    }

    private void visitFunCallExprNode(FunCallExprNode node) {
        FunCallNode funCallNode = node.getFunCall();

        funCallNode.accept(this);
    }

    private void visitFunCallStatNode(FunCallStatNode node) {
        FunCallNode funCallNode = node.getFunCall();

        funCallNode.accept(this);
    }

    private void visitIfStatNode(IfStatNode node) {
        StringBuilder sb = new StringBuilder();

        ExprNode exprNode = node.getExpr();
        BodyNode bodyNode = node.getBody();

        stackScope.push(node.getSymbolTable());

        sb.append("if(").append(exprNode.accept(this)).append("){\n");
        sb.append(bodyNode.accept(this)).append("}\n");

        if(node.getElseStat() != null){
            sb.append("else").append("){\n");
            sb.append(node.getElseStat().accept(this)).append("}\n");
        }

        codeGeneratorC = sb.toString();
        stackScope.pop();
    }

    private void visitElseNode(ElseNode node) {
        StringBuilder sb = new StringBuilder();
        BodyNode bodyNode = node.getBody();

        stackScope.push(node.getSymbolTable());

        sb.append(bodyNode.accept(this));

        codeGeneratorC = sb.toString();
        stackScope.pop();
    }

    private void visitForStatNode(ForStatNode node) {
        StringBuilder sb = new StringBuilder();

        String intConst1 = node.getIntConst1().getValue();
        String intConst2 = node.getIntConst2().getValue();
        BodyNode bodyNode = node.getBody();
        String id = node.getId().getNomeId();

        stackScope.push(node.getSymbolTable());

        sb.append("int ").append(id).append(";\n");
        sb.append("for(").append(id).append(" = ").append(intConst1).append(";").append(" ").append(id).append(" <= ").append(intConst2).append(";").append(id).append("++").append("){\n");
        sb.append(bodyNode.accept(this)).append("}\n");

        codeGeneratorC = sb.toString();
        stackScope.pop();
    }

    private void visitWhileStatNode(WhileStatNode node) {
        StringBuilder sb = new StringBuilder();

        ExprNode exprNode = node.getExpr();
        BodyNode bodyNode = node.getBody();

        stackScope.push(node.getSymbolTable());

        sb.append("while(").append(exprNode.accept(this)).append("){\n");
        sb.append(bodyNode.accept(this)).append("}\n");

        codeGeneratorC = sb.toString();
        stackScope.pop();
    }

    private void visitReadStatNode(ReadStatNode node) {
        StringBuilder sb = new StringBuilder();

        ArrayList<IdInitNode> idInitNodeList = node.getIdList();
        ConstNode constNode = node.getStringConst();

        if(constNode != null){
            sb.append("printf(\"").append(constNode.getValue()).append("\");\n");
        }

        codeGeneratorC += sb.toString();

        for(IdInitNode idElement: idInitNodeList){
            if(idElement.getAstType() == sym.STRING){
                sb.append("scanf(").append(formatOut(idElement.getId().getAstType())).append(",").append(idElement.getId().getNomeId()).append(");\n");
            } else{
                sb.append("scanf(").append(formatOut(idElement.getId().getAstType())).append(",&").append(idElement.getId().getNomeId()).append(");\n");
            }
        }

        codeGeneratorC += sb.toString();
    }

    private void visitWriteStatNode(WriteStatNode node) {
        StringBuilder sb = new StringBuilder();

        ArrayList<ExprNode> exprNodeList = node.getExprList();
        String typeWrite = node.getTypeWrite();

        Collections.reverse(exprNodeList);

        for(ExprNode exprElement: exprNodeList){
            if(typeWrite == "WRITE"){
                sb.append("printf(").append(formatOut(exprElement.getAstType())).append(",").append(exprElement.accept(this)).append(" );");
            } else {
                sb.append("printf(").append(formatOut(exprElement.getAstType())).append(",").append(exprElement.accept(this)).append(" );").append("\n");
            }
        }

        codeGeneratorC = sb.toString();
    }

    private void visitAssignStatNode(AssignStatNode node) {
        StringBuilder sb = new StringBuilder();

        ArrayList<IdInitNode> idInitNodeList = node.getIdList();
        ArrayList<ExprNode> exprNodeList = node.getExprList();

        if(exprNodeList.size() == 1){
            for(IdInitNode idElement: idInitNodeList){
                sb.append(idElement.getId().accept(this)).append(" = ").append(exprNodeList.get(0).accept(this)).append(";\n");
            }
        } else {
            for (int i = 0; i < idInitNodeList.size(); i++) {
                sb.append(idInitNodeList.get(i).getId().getNomeId()).append(" = ");
                sb.append(exprNodeList.get(i).accept(this)).append(";\n");
            }
        }

        codeGeneratorC = sb.toString();
    }




    public String typeConverter(String typeConverter){
        return switch (typeConverter) {
            case "STRING" -> "char *";
            case "REAL" -> "float" ;
            case "INTEGER", "BOOL" -> "int";
            case "CHAR" -> "char";
            case "VOID" -> "void";
            default -> "ERROR";
        };
    }

    private String converterNumericToStringType(int numericType){
        return switch (numericType) {
            case sym.INTEGER -> "INTEGER";
            case sym.REAL -> "REAL" ;
            case sym.BOOL -> "BOOL";
            case sym.STRING -> "STRING";
            case sym.CHAR -> "CHAR";
            case sym.VOID -> "VOID";
            default -> "ERROR";
        };
    }

    private String formatOut(int type){
        switch (type) {
            case sym.INTEGER:
                return "\"%d\"";
            case sym.REAL:
                return "\"%f\"";
            case sym.BOOL, sym.STRING:
                return "\"%s\"";
            case sym.CHAR:
                return "\"%c\"";
            default:
                return "ERROR";
        }
    }

    public SymbolRecord lookup(String item){
        SymbolRecord found = null;
        for (SymbolTable current : stackScope){
            found = current.get(item);
            if(found != null)
                break;
        }

        return found;
    }

}




