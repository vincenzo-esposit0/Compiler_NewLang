package nodes;

import visitor.MyVisitor;

public class ConstNode {

    private String nome;
    private Object value;

    public ConstNode(String nome, Object value) {
        this.nome = nome;
        this.value = value;
    }

    public String getNome() {
        return nome;
    }

    public ConstNode(Integer iValue) {
        this.value = iValue;
    }

    public Object getValue() {
        return value;
    }

    public void accept(MyVisitor visitor) {
        visitor.visit(this);
    }

}
