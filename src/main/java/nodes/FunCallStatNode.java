package nodes;

import java.util.ArrayList;

public class FunCallStatNode extends StatNode {

    FunCallNode funCall;

    public FunCallStatNode(String name, FunCallNode funCall) {
        super(name);
        this.funCall = funCall;
    }

    public FunCallNode getFunCall() {
        return funCall;
    }
}
