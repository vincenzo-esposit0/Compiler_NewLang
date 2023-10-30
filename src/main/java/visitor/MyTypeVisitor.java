package visitor;

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
        if(node instanceof ProgramNode){
            visitProgramNode((ProgramNode) node);
        }else if(node instanceof VarDeclNode){
            visitVarDeclNode((VarDeclNode) node);
        }else if(node instanceof FunDeclNode){
            visitFunDeclNode((FunDeclNode) node);
        }else if(node instanceof BodyNode){
            visitBodyNode((BodyNode) node);
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
            for(IdInitNode element : idInitList){
                ExprNode expr = element.getExpr();
                element.getId().accept(this);

                if(expr != null){
                    expr.accept(this);
                    element.setAstType(MyTypeChecker.AssignOperations(expr.getAstType(),type));
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

    private void visitNodeList(ArrayList<? extends ASTNode> nodeList) {
        if (nodeList != null) {
            for (int i = 0; i <= nodeList.size(); i++){
                nodeList.get(i).accept(this);
            }
        }
    }

}
