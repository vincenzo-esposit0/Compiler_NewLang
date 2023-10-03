package nodes;

public class ElseNode extends ASTNode{

    private BodyNode body;

    public ElseNode(String name, BodyNode body) {
        super(name);
        this.body = body;
    }

    public BodyNode getBody() {
        return body;
    }

}
