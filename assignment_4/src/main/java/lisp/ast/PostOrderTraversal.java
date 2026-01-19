package lisp.ast;

import lisp.nodes.Node;
import lisp.nodes.NodeVisitor;
import lisp.nodes.SymbolNode;

import java.util.List;

public class PostOrderTraversal {

    public static void traverse(Node<?> node, NodeVisitor visitor) {

        if (node instanceof SymbolNode sym) {
            String op = sym.getData();
            List<Node<?>> kids = sym.getChildren();

            if (op.equalsIgnoreCase("define")) {
                traverse(kids.get(1), visitor);
                node.accept(visitor);
                return;
            }

            if (op.equalsIgnoreCase("if")) {
                traverse(kids.get(0), visitor);
                node.accept(visitor);
                return;
            }

            for (Node<?> child : kids) {
                traverse(child, visitor);
            }
        }

        node.accept(visitor);
    }
}
