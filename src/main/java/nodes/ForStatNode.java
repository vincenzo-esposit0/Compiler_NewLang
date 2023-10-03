package nodes;

public class ForStatNode extends StatNode{

    private IdNode id;
    private ConstNode intConst1;
    private ConstNode intConst2;
    private BodyNode body;

    public ForStatNode(String name, IdNode id, ConstNode intConst1, ConstNode intConst2, BodyNode body) {
        super(name);
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

}
