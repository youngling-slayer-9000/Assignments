package lisp.nodes;

public class IdentifierNode implements Node<String> {

    private final String name;

    public IdentifierNode(String name) {
        this.name = name;
    }

    @Override
    public String getData() {
        return name;
    }

    @Override
    public void accept(NodeVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public String toString() {
        return "IdentifierNode(" + name + ")";
    }
}
