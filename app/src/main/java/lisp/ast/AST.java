package lisp.ast;

import lisp.nodes.Node;
import lisp.parser.Parser;
import lisp.nodes.*;
import lisp.symbolTable.SymbolTable;

import java.util.ArrayList;

public class AST {

    Parser parser;


    public AST() {

        parser = Parser.getInstance();
    }


    public Node<?> construction(String expr) {

        ArrayList<String> exprList = parser.parse(expr);

        if (exprList.isEmpty()) {
            throw new IllegalArgumentException("Empty expression: " + expr);
        }

        String first = exprList.get(0);
        for(String s : exprList){
            System.out.print(s + " | ");
        }
        System.out.println();
        // Checking whether it is an operator or not
        if ( isIf(first) ||  isDefine(first) || isArithmetic(first) || isLogical(first) || isComparator(first)) {

            SymbolNode root = (SymbolNode) NodeFactory.createNode("symbol", first);

            for (int i = 1; i < exprList.size(); i++) {
                Node<?> child = construction(exprList.get(i));
                root.children.add(child);
            }

            return root;
        }

        // Leaf node
        if(isInteger(first)) {
            return NodeFactory.createNode("integer", first);
        }
        else if(isFloat(first)) {
            return NodeFactory.createNode("float", first);
        }
        else if(isCharacterLiteral(first)) {
            return NodeFactory.createNode("character", String.valueOf(first.charAt(1)));
        }
        else if(isBooleanLiteral(first)) {
            return NodeFactory.createNode("boolean", first);
        }
        else{
            return NodeFactory.createNode("identifier", first);
        }
    }


    public boolean isComparator(String str) {
        return str.equals(">") ||str.equals("<") || str.equals(">=") || str.equals("<=")
                || str.equals("==") || str.equals("!=");
    }

    public boolean isBooleanLiteral(String str) {
        return str.equalsIgnoreCase("true") || str.equalsIgnoreCase("false");
    }

    public boolean isIf(String str){
        return str.equalsIgnoreCase("if");
    }


    private boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (Exception e) {
            return false;
        }
    }


    private boolean isFloat(String s) {
        try{
            Double.parseDouble(s);
            return s.contains(".");
        }catch(Exception e) {
            return false;
        }
    }

    private boolean isCharacterLiteral(String s) {
        return s.length() == 3 && s.startsWith("'") && s.endsWith("'");
    }

    public boolean isArithmetic(String str) {
        return str.equals("+") || str.equals("-") || str.equals("*") || str.equals("/");
    }

    public boolean isDefine(String str){
        return str.equals("define");
    }

    public boolean isLogical(String str) {
        return str.equals("AND") || str.equals("OR") || str.equals("NOT");
    }


}
