package lisp.nodes;

import java.util.ArrayList;

public class SymbolNode implements Node<String> {

    private String symbol;
    public ArrayList<Node<?>> children;

    public SymbolNode(String symbol){

        this.symbol = symbol;
        children = new ArrayList<>();
    }

    public String getData(){
        return this.symbol;
    }

    public ArrayList<Node<?>> getChildren(){
        return this.children;
    }

    @Override
    public void accept(NodeVisitor visitor) {
        visitor.visit(this);
    }

}
