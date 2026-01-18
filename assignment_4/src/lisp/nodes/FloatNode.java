package lisp.nodes;

public class FloatNode implements Node<Double> {
    private double data;

    public FloatNode(double data){
        this.data = data;
    }

    @Override
    public Double getData(){
        return this.data;
    }

    @Override
    public void accept(NodeVisitor visitor) {
        visitor.visit(this);
    }
}
