package nodes;

import java.util.ArrayList;

public class SwitchStatNode extends StatNode{
    private IdNode id;
    private ArrayList<BodySwitch> bodySwitchList;

    public SwitchStatNode(String name, IdNode id, ArrayList<BodySwitch> bodySwitchList) {
        super(name);
        this.id = id;
        this.bodySwitchList = bodySwitchList;
    }

    public IdNode getId() {
        return id;
    }

    public void setId(IdNode id) {
        this.id = id;
    }

    public ArrayList<BodySwitch> getBodySwitchList() {
        return bodySwitchList;
    }

    public void setBodySwitchList(ArrayList<BodySwitch> bodySwitchList) {
        this.bodySwitchList = bodySwitchList;
    }
}
