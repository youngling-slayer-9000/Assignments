public class IntegerNode implements Node<Integer> {
    private int data;

    public IntegerNode(int data){
        this.data = data;
    }

    @Override
    public Integer getData(){
        return this.data;
    }

    @Override
    public void accept(NodeVisitor visitor) {
        visitor.visit(this);
    }
}
