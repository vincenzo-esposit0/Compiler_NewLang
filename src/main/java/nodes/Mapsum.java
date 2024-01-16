package nodes;

public class Mapsum extends ExprNode{
    private IdNode id;
    private ExprListMapsum exprListMapsum;

    public Mapsum(String name, String modeExpr, IdNode id, ExprListMapsum exprListMapsum) {
        super(name, modeExpr);
        this.id = id;
        this.exprListMapsum = exprListMapsum;
    }

    public IdNode getId() {
        return id;
    }

    public void setId(IdNode id) {
        this.id = id;
    }

    public ExprListMapsum getExprListMapsum() {
        return exprListMapsum;
    }

    public void setExprListMapsum(ExprListMapsum exprListMapsum) {
        this.exprListMapsum = exprListMapsum;
    }
}
