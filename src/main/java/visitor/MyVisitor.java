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
    void visit(CharConstNode node);
    void visit(StringConstNode node);
    void visit(IntegerConstNode node);
    void visit(RealConstNode node);
    void visit(BooleanConstNode node);

}
