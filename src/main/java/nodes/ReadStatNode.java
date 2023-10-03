package nodes;

import java.util.ArrayList;

public class ReadStatNode extends StatNode{

    private ArrayList<IdInitNode> idList;
    private ConstNode stringConst;

    public ReadStatNode(String name, ArrayList<IdInitNode> idList, ConstNode stringConst) {
        super(name);
        this.idList = idList;
        this.stringConst = stringConst;
    }

    public ArrayList<IdInitNode> getIdList() {
        return idList;
    }

    public ConstNode getStringConst() {
        return stringConst;
    }

}
