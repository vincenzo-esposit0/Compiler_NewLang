package visitor;

import esercitazione5.sym;
import nodes.*;
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
            case "VarDeclNode" -> visitVarDeclNode((VarDeclNode) node);
            case "FunDeclNode" -> visitFunDeclNode((FunDeclNode) node);
            case "BodyNode" -> visitBodyNode((BodyNode) node);
            case "ParDeclNode" -> visitParDeclNode((ParDeclNode) node);

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

                        sb.append("#define ").append(idElement.getId().getNomeId()).append(" (").append(typeC).append(" )");
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

        FunDeclNode funDeclNode = node.getFunDecl();

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
                //sb.deleteCharAt(sb.length() - 1);
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

        /**
         * Siccome nel nostro linguaggio possiamo utilizzare una variabile prima che la si dichiari,
         * metto prime le dichiarazioni senza assegnamento e poi quelle con assegnamento
         * così sono sicuro che anche se le utilizzo prima di dichiararle va bene lo stesso in C
         */
        for(VarDeclNode varElement: varDeclNodeList){
            for(IdInitNode idElement: varElement.getIdInitList()){
                if(idElement.getExpr() == null){
                    varNotAssignedList.add(varElement);
                } else {
                    varAssignedList.add(varElement);
                }
            }
        }

        varDeclNodeList.clear();

        ArrayList<VarDeclNode> allVarDeclList =  new ArrayList<>();

        varNotAssignedList.addAll(varAssignedList);
        allVarDeclList.addAll(varNotAssignedList);

        //Aggiungo la lista delle variabili riordinate
        varDeclNodeList.addAll(allVarDeclList);

        for(VarDeclNode varElement: varDeclNodeList){
            if(varElement != null){
                sb.append(varElement.accept(this));
            }
        }

        for(StatNode statElement: statNodeList){
            if(statElement != null){
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

}




