public class CharacterNode implements Node<Character>{
    private char data;

    public CharacterNode(char data){
        this.data = data;
    }

    @Override
    public Character getData(){
        return this.data;
    }

    @Override
    public void accept(NodeVisitor visitor) {
        visitor.visit(this);
    }

}
