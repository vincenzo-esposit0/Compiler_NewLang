package nodes;

import visitor.MyVisitor;

public class IdNode {

    public String nome;
    public String value;

    public IdNode(String nome, String value) {
        this.nome = nome;
        this.value = value;
    }

    public String getNome() {
        return nome;
    }

    public String getValue() {
        return value;
    }

    public void accept(MyVisitor visitor) {
        visitor.visit(this);
    }
}
