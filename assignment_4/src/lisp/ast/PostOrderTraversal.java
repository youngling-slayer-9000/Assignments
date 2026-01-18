package lisp.ast;

import lisp.nodes.Node;
import lisp.nodes.SymbolNode;
import lisp.nodes.NodeVisitor;

public class PostOrderTraversal {

    public static void traverse(Node<?> node, NodeVisitor visitor) {

        if (node instanceof SymbolNode sym) {
            for (Node<?> child : sym.getChildren()) {
                traverse(child, visitor);
            }
        }

        node.accept(visitor);
    }
}
