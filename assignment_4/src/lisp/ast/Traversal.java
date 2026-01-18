package lisp.ast;

import lisp.nodes.*;
import lisp.symbolTable.SymbolTable;

import java.util.ArrayList;
import java.util.List;

public class Traversal {

    public static Object eval(Node<?> node, SymbolTable env) {

        // ---------- leaf nodes ----------
        if (node instanceof IntegerNode) return ((IntegerNode) node).getData();
        if (node instanceof BooleanNode) return ((BooleanNode) node).getData();

        if (node instanceof IdentifierNode) {
            return env.get(((IdentifierNode) node).getData());
        }

        // check if its an operator
        SymbolNode opNode = (SymbolNode) node;
        String op = opNode.getData();
        List<Node<?>> kids = opNode.getChildren();

          // check if its a variable, resgiter it and  return the  value
        if (op.equals("define")) {
            String varName = ((IdentifierNode) kids.get(0)).getData();
            Object value = eval(kids.get(1), env);
            env.register(varName, value);
            return value;
        }

        // evaluate all children first (postorder)
        List<Object> vals = new ArrayList<>();
        for (Node<?> child : kids) {
            vals.add(eval(child, env));
        }

       Operatable func = Operations.get(op);

        if(func ==  null) {

            return null;

        }

        return func.process(vals);
    }

}
