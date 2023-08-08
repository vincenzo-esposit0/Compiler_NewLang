package visitor;

import nodes.*;

public interface MyVisitor {
    void visit(ProgramNode node);
    void visit(DeclListNode node);
    void visit(MainFunDeclNode node);
    void visit(VarDeclNode node);
    void visit(FunDeclNode node);
    void visit(TypeNode node);
    void visit(IdNode node);
    void visit(IdInitListNode node);
    void visit(IdInitObblListNode node);
    void visit(ParDeclNode node);
    void visit(ConstNode node);
    void visit(BodyNode node);
    void visit(ParamDeclListNode node);
    void visit(NonEmptyParamDeclListNode node);
    void visit(VarDeclListNode node);
    void visit(StatNode node);
    void visit(StatListNode node);
    void visit(IfStatNode node);
    void visit(ElseNode node);
    void visit(WhileStatNode node);
    void visit(ForStatNode node);
    void visit(ReadStatNode node);
    void visit(WriteStatNode node);
    void visit(AssignStatNode node);
    void visit(FunCallNode node);
    void visit(ExprNode node);
    void visit(IdListNode node);
    void visit(ExprListNode node);

}
