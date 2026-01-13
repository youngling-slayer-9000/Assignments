import java.util.ArrayList;

public class SymbolNode implements Node<String>{

    private String symbol;
    public ArrayList<Node<?>> children;

    public SymbolNode(String symbol){
        this.symbol = symbol;
    }

    public String getData(){
        return this.symbol;
    }

    @Override
    public void accept(NodeVisitor visitor) {
        visitor.visit(this);
    }

}
