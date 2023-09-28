package nodes;

import visitor.MyVisitor;

public class ElseNode {

    private String nome;
    private BodyNode body;

    public ElseNode(String nome, BodyNode body) {
        this.nome = nome;
        this.body = body;
    }

    public String getNome() {
        return nome;
    }

    public BodyNode getBody() {
        return body;
    }

    public void accept(MyVisitor visitor) {
        visitor.visit(this);
    }

}
