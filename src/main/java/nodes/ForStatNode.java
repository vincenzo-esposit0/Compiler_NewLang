package nodes;

import visitor.MyVisitor;

public class ForStatNode extends StatNode{

    private String nome;
    private IdNode id;
    private ConstNode intConst1;
    private ConstNode intConst2;
    private BodyNode body;

    public ForStatNode(String nome, IdNode id, ConstNode intConst1, ConstNode intConst2, BodyNode body) {
        this.nome = nome;
        this.id = id;
        this.intConst1 = intConst1;
        this.intConst2 = intConst2;
        this.body = body;
    }

    public IdNode getId() {
        return id;
    }

    public ConstNode getIntConst1() {
        return intConst1;
    }

    public ConstNode getIntConst2() {
        return intConst2;
    }

    public BodyNode getBody() {
        return body;
    }

    public void accept(MyVisitor visitor) {
        visitor.visit(this);
    }

}
