package nodes;

import visitor.MyVisitor;

import java.util.ArrayList;

public class IdInitListNode {

    private String id;
    private ExprNode expression;
    public ArrayList<IdInitListNode> idInitList;

    public IdInitListNode(ArrayList<IdInitListNode> idInitList, String id, ExprNode expression) {
        this.idInitList = idInitList;
        this.id = id;
        this.expression = expression;
    }

    public ArrayList<IdInitListNode> getIdInitList() {
        return idInitList;
    }

    public String getId() {
        return id;
    }

    public ExprNode getExpression() {
        return expression;
    }

    public void accept(MyVisitor visitor) {
        visitor.visit(this);
    }

}
