package nodes;

import java.util.ArrayList;

public class ExprListMapsum {
    private ArrayList<ArrayList<ExprNode>> exprListMapsum;

    public ExprListMapsum() {
        this.exprListMapsum = new ArrayList<>();
    }

    public void add(ArrayList<ExprNode> exprList){
        exprListMapsum.add(exprList);
    }

    public ArrayList<ArrayList<ExprNode>> getExprListMapsum() {
        return exprListMapsum;
    }

    public void setExprListMapsum(ArrayList<ArrayList<ExprNode>> exprListMapsum) {
        this.exprListMapsum = exprListMapsum;
    }
}
