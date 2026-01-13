import java.util.ArrayList;

public class AST {

    Parser parser;

    public AST() {
        parser = Parser.getInstance();
    }

    public boolean isArithmetic(String str) {
        return str.equals("+") || str.equals("-") || str.equals("*") || str.equals("/");
    }

    public boolean isLogical(String str) {
        return str.equals("AND") || str.equals("OR") || str.equals("NOT");
    }

    public Node<?> construction(String expr) {

        ArrayList<String> exprList = parser.parse(expr);
        String first = exprList.get(0);

        // Operator node
        if (isArithmetic(first) || isLogical(first)) {

            SymbolNode root = (SymbolNode) NodeFactory.createNode("symbol", first);

            for (int i = 1; i < exprList.size(); i++) {
                Node<?> child = construction(exprList.get(i));
                root.children.add(child);
            }

            return root;
        }

        // Leaf node
        if (isInteger(first)) {
            return NodeFactory.createNode("integer", first);
        } else {
            return NodeFactory.createNode("identifier", first);
        }
    }


    private boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
