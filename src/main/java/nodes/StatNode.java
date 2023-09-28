package nodes;

import visitor.MyVisitor;

public class StatNode {

    private String nome;
    private Object typeStat;

    public StatNode() {

    }

    public StatNode(String nome, Object typeStat) {
        this.nome = nome;
        this.typeStat = typeStat;
    }

    public String getNome() {
        return nome;
    }

    public Object getTypeStat() {
        return typeStat;
    }

    public void accept(MyVisitor visitor) {
        visitor.visit(this);
    }

}
