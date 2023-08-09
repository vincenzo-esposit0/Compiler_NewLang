package visitor;

import nodes.*;

import java.util.ArrayList;

public class MySyntaxTree implements MyVisitor{

    private String treeContent= "";

    @Override
    public void visit(ProgramNode programNode) {
        treeContent = String.format("<%s>", "Root");

        processDeclList(programNode.getDeclList1());
        programNode.getMainFunDecl().accept(this);
        processDeclList(programNode.getDeclList2());

        treeContent += String.format("</%s>", "Root");
    }

    private void processDeclList(ArrayList<DeclListNode> declList) {
        if (!declList.isEmpty()) {
            treeContent += String.format("<%s>", "DeclList");

            for (DeclListNode decl : declList) {
                if (decl instanceof VarDeclNode) {
                    VarDeclNode varDeclNode = (VarDeclNode) decl;
                    varDeclNode.accept(this);
                }

                if (decl instanceof FunDeclNode) {
                    FunDeclNode funDeclNode = (FunDeclNode) decl;
                    funDeclNode.accept(this);
                }
            }

            treeContent += String.format("</%s>", "DeclList");
        }
    }

    @Override
    public void visit(DeclListNode declListNode) {
        treeContent = String.format("<%s>", declListNode.getName());

        this.treeContent = String.format("<%s>", declListNode.getName());
        declListNode.getVarDecl().accept(this);
        this.treeContent += String.format("<%s>", declListNode.getName());
        declListNode.getFunDecl().accept(this);
        processDeclList(declListNode.getDeclList1());

        this.treeContent = String.format("</%s>", declListNode.getName());
    }

    @Override
    public void visit(MainFunDeclNode mainFunDeclNode) {
        this.treeContent += String.format("<%s>", mainFunDeclNode.getName());
        mainFunDeclNode.getFunDecl().accept(this);
        this.treeContent += String.format("</%s>", mainFunDeclNode.getName());
    }


    @Override
    public void visit(VarDeclNode node) {

    }

    @Override
    public void visit(FunDeclNode node) {

    }

    @Override
    public void visit(TypeNode node) {

    }

    @Override
    public void visit(IdNode node) {

    }

    @Override
    public void visit(IdInitListNode node) {

    }

    @Override
    public void visit(IdInitObblListNode node) {

    }

    @Override
    public void visit(ParDeclNode node) {

    }

    @Override
    public void visit(ConstNode node) {

    }

    @Override
    public void visit(BodyNode node) {

    }

    @Override
    public void visit(ParamDeclListNode node) {

    }

    @Override
    public void visit(NonEmptyParamDeclListNode node) {

    }

    @Override
    public void visit(VarDeclListNode node) {

    }

    @Override
    public void visit(StatNode node) {

    }

    @Override
    public void visit(StatListNode node) {

    }

    @Override
    public void visit(IfStatNode node) {

    }

    @Override
    public void visit(ElseNode node) {

    }

    @Override
    public void visit(WhileStatNode node) {

    }

    @Override
    public void visit(ForStatNode node) {

    }

    @Override
    public void visit(ReadStatNode node) {

    }

    @Override
    public void visit(WriteStatNode node) {

    }

    @Override
    public void visit(AssignStatNode node) {

    }

    @Override
    public void visit(FunCallNode node) {

    }

    @Override
    public void visit(ExprNode node) {

    }

    @Override
    public void visit(IdListNode node) {

    }

    @Override
    public void visit(ExprListNode node) {

    }
}
