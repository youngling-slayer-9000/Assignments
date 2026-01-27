package lisp.ast;

import lisp.nodes.*;
import lisp.operations.Operatable;
import lisp.operations.Operations;
import lisp.symbolTable.SymbolTable;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Evaluation implements NodeVisitor {

    private final Stack<Object> st = new Stack<>();
    private final SymbolTable env;
    public Evaluation(SymbolTable env) {
        this.env = env;
    }

    public Object result() {
        return st.peek();
    }


    @Override public void visit(IntegerNode ptr) {
        st.push(ptr.getData());
    }
    @Override public void visit(BooleanNode ptr) {
        st.push(ptr.getData());
    }
    @Override public void visit(CharacterNode ptr) {
        st.push(ptr.getData());
    }
    @Override public void visit(FloatNode ptr) {
        st.push(ptr.getData());
    }

    @Override
    public void visit(IdentifierNode ptr) {
        st.push(env.getOrThrow(ptr.getData()));
    }


    @Override
    public void visit(SymbolNode ptr) {

        String op = ptr.getData();
        List<Node<?>> kids = ptr.getChildren();


        if (op.equalsIgnoreCase("define")) {
            String varName = ((IdentifierNode) kids.get(0)).getData();
            Object value = st.pop();
            env.register(varName, value);
            st.push(value);
            return;
        }


        if (op.equalsIgnoreCase("if")) {
            boolean cond = (Boolean) st.pop();  // condition already evaluated
            Node<?> branch = cond? kids.get(1):kids.get(2);
            PostOrderTraversal.traverse(branch, this);
            return;
        }

        Operatable operation = Operations.get(op);

        int n = kids.size();
        List<Object> args = new ArrayList<>();
        for (int i = 0; i < n; i++)
            args.add(0, st.pop());

        Object answer = operation.apply(args);
        st.push(answer);
    }

}
