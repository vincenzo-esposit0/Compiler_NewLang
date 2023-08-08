package nodes;

import visitor.MyVisitor;

public class ElseNode {

    private BodyNode body;

    public ElseNode(BodyNode body) {
        this.body = body;
    }

    public BodyNode getBody() {
        return body;
    }

    public void accept(MyVisitor visitor) {
        visitor.visit(this);
    }

}
