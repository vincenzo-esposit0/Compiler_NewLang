package nodes;

public class IdInitNode extends ASTNode{

    private IdNode id;

    private ExprNode expr;

    private ConstNode constant;

    public IdInitNode(String name, IdNode id, ExprNode expr, ConstNode constant) {
        super(name);
        this.id = id;
        this.expr = expr;
        this.constant = constant;
    }

    public IdNode getId() {
        return id;
    }

    public ExprNode getExpr() {
        return expr;
    }

    public ConstNode getConstant() {
        return constant;
    }

    @Override
    public String toString() {
        return "IdInitNode{" +
                "id=" + id +
                ", expr=" + expr +
                ", constant=" + constant +
                '}';
    }
}
