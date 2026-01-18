package lisp.ast;

import lisp.nodes.Node;

import java.util.HashMap;
import java.util.Objects;

public class Operations {

    private static HashMap<String, Operatable> operations = new HashMap<>();

    static{


        operations.put("+",(args) -> {
            int res = 0;
            for(Object node : args){
                res += (Integer)node;
            }
            return res;
        });

        operations.put("-" , (args)->{
            int res = (Integer) args.get(0);
            for (int i = 1 ; i < args.size() ; i++)
                res -= (Integer) args.get(i);
             return res;

        });
        operations.put("*" , (args)->{
            int product = 1;
            for(Object node : args){
                product *= (Integer)node;
            }
            return product;
        });

        operations.put("/" , (args)->{
           int res = (Integer)args.get(0);
           for(int i = 1 ; i < args.size() ; i++){
                int denominator = (Integer)args.get(i);
                res = res /denominator;
           }
           return res;

        });

        /* conditional operators */
        operations.put("<" , (args)->{
           return (Integer)args.get(0)  < (Integer)args.get(1);
        });
        operations.put(">" , (args)->{
            return (Integer)args.get(0)  > (Integer)args.get(1);
        });
        operations.put("<=" , (args)->{
            return (Integer)args.get(0)  <= (Integer)args.get(1);
        });
        operations.put(">=" , (args)->{
            return (Integer)args.get(0)  >= (Integer)args.get(1);
        });
        operations.put("==" , (args)->{
            return Objects.equals((Integer) args.get(0), (Integer) args.get(1));
        });
        operations.put("!=" , (args)->{
            return !args.get(0).equals(args.get(1));
        });


        /* Logical operators */

        operations.put("AND", args -> {
            boolean res = true;
            for(Object x : args)
                res = res && (Boolean) x;
            return res;
        });

        operations.put("OR", args -> {
            boolean res = false;
            for(Object x : args)
                res = res || (Boolean) x;
            return res;
        });

        operations.put("NOT", args -> !(Boolean) args.get(0));

    }

    public static Operatable get(String symbol) {
        return operations.get(symbol);
    }
}
