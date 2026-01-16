package lisp.nodes;

public class NodeFactory {

    public static Node<?> createNode(String type, String value){

        if(type == null || value == null){
            throw new IllegalArgumentException("Type or value cant be null");
        }


        if(type == "integer"){
                return new IntegerNode(Integer.parseInt(value));
        }else if(type == "character"){
            return new CharacterNode(value.charAt(0));
        }else if(type == "symbol"){
            return new SymbolNode(value);
        }
        else{
            throw new IllegalArgumentException("Unknown node type: " + type);
        }


    }

}
