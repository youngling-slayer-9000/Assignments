package lisp.ast;

import lisp.nodes.IntegerNode;
import lisp.nodes.*;

import java.util.ArrayList;
import java.util.List;

public class Traversal {

    public static int eval(Node<?> node) {

        // leaf node
        if (node instanceof IntegerNode) {
            return ((IntegerNode) node).getData();
        }

        // operator node
        if (!(node instanceof SymbolNode)) {
            throw new RuntimeException("Unknown node type: " + node.getClass());
        }

        SymbolNode opNode = (SymbolNode) node;
        String op = opNode.getData();
        List<Node<?>> kids = opNode.getChildren();

        if (kids == null || kids.isEmpty()) {
            throw new RuntimeException("Operator node has no children: " + op);
        }

        // evaluate all children first (postorder)
        List<Integer> vals = new ArrayList<>();
        for (Node<?> child : kids) {
            vals.add(eval(child));
        }

        // reduce values
        return switch (op) {

            case "+" -> {
                int sum = 0;
                for (int v : vals) sum += v;
                yield sum;
            }

            case "*" -> {
                int prod = 1;
                for (int v : vals) prod *= v;
                yield prod;
            }

            case "-" -> {
                int res = vals.get(0);
                for (int i = 1; i < vals.size(); i++) res -= vals.get(i);
                yield res;
            }

            case "/" -> {
                int res = vals.get(0);
                for (int i = 1; i < vals.size(); i++) {
                    int divisor = vals.get(i);
                    if (divisor == 0) {
                        throw new ArithmeticException("Division by zero");
                    }
                    res /= divisor;
                }
                yield res;
            }

            default -> throw new RuntimeException("Unknown operator: " + op);
        };
    }
}
