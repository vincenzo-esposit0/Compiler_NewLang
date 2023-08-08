package nodes;

import visitor.MyVisitor;

import java.util.ArrayList;

public class ExprListNode {

    private ExprNode expr;
    private ArrayList<ExprListNode> exprList;

    public ExprListNode(ExprNode expr, ArrayList<ExprListNode> exprList) {
        this.expr = expr;
        this.exprList = exprList;
    }

    public ExprNode getExpr() {
        return expr;
    }

    public ArrayList<ExprListNode> getExprList() {
        return exprList;
    }

    public void accept(MyVisitor visitor) {
        visitor.visit(this);
    }
}
