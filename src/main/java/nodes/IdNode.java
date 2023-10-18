package nodes;

public class IdNode extends ExprNode{

    public String nomeId;

    public IdNode(String name, String modeExpr, String nomeId) {
        super(name, modeExpr);
        this.nomeId = nomeId;
    }

    public String getNomeId() {
        return nomeId;
    }

    public void setNomeId(String nomeId) {
        this.nomeId = nomeId;
    }
}
