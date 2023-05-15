package nodes;

import visitor.MyVisitor;

public class IdInitObblListNode {

    private String id;
    private ConstNode constant;

    public IdInitObblListNode(String id, ConstNode constant) {
        this.id = id;
        this.constant = constant;
    }

    public String getId() {
        return id;
    }

    public ConstNode getConstant() {
        return constant;
    }

    public void accept(MyVisitor visitor) {
        visitor.visit(this);
    }

}
