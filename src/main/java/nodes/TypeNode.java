package nodes;

import visitor.MyVisitor;

public class TypeNode {
    private String nome;
    public static final String INTEGER = "integer";
    public static final String BOOL = "bool";
    public static final String REAL = "real";
    public static final String STRING = "string";
    public static final String CHAR = "char";

    private String type;

    public TypeNode(String nome, String type) {
        this.nome = nome;
        this.type = type;
    }

    public String getNome() {
        return nome;
    }

    public String getType() {
        return type;
    }

    public void accept(MyVisitor visitor) {
        visitor.visit(this);
    }

}
