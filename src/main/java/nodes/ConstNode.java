package nodes;

import visitor.MyVisitor;

public class ConstNode {

    private Object typeNode;
    private String typeString;

    public ConstNode(String typeString, Object typeNode) {
        this.typeNode = typeNode;
        this.typeString = typeString;
    }

    public Object getTypeNode() {
        return typeNode;
    }

    public String getTypeString() {
        return typeString;
    }

    public void accept(MyVisitor visitor) {
        visitor.visit(this);
    }

}
