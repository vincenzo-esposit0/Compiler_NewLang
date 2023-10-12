package visitor;

import nodes.ASTNode;

public interface MyVisitor {

    String visit(ASTNode node);

}
