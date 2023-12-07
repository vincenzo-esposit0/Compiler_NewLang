package table;

import java.util.ArrayList;

public class ParInitialize {

    private ArrayList<Integer> paramsTypeList;

    private ArrayList<Boolean> paramsOutList;

    public ParInitialize(ArrayList<Integer> paramsTypeList, ArrayList<Boolean> paramsOutList) {
        this.paramsTypeList = paramsTypeList;
        this.paramsOutList = paramsOutList;
    }

    public ArrayList<Integer> getParamsTypeList() {
        return paramsTypeList;
    }

    public ArrayList<Boolean> getParamsOutList() {
        return paramsOutList;
    }

    public void addParamsTypeList(int parTypeCheck){
        this.paramsTypeList.add(parTypeCheck);
    }

    public void addParamsOutList(boolean out){
        this.paramsOutList.add(out);
    }

    @Override
    public String toString() {
        return "ParInitialize{" +
                "paramsTypeList=" + paramsTypeList +
                ", paramsOutList=" + paramsOutList +
                '}';
    }
}
