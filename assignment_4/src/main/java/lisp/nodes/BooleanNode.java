package lisp.nodes;

public class BooleanNode implements Node<Boolean> {
    private boolean data;

    public BooleanNode(boolean data) {
        this.data = data;
    }

    @Override
    public Boolean getData() {
        return data;
    }

    @Override
    public void accept(NodeVisitor visiter){
        visiter.visit(this);
    }
}
