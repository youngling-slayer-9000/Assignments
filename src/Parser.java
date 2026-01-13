import java.util.ArrayList;

public class Parser {

    public Parser() {
        System.out.println("Parser constructor");
    }

    public ArrayList<String> parse(String expr) {

        ArrayList<String> exprList = new ArrayList<>();
        if (expr == null) return exprList;

        expr = expr.trim();


        if (expr.startsWith("(") && expr.endsWith(")")) {
            expr = expr.substring(1, expr.length() - 1).trim();
        }

        if (!expr.contains(" ") && !expr.contains("(") && !expr.contains(")")) {
            exprList.add(expr);
            return exprList;
        }

        StringBuilder token = new StringBuilder();
        int depth = 0;

        for (int i = 0; i < expr.length(); i++) {
            char ch = expr.charAt(i);

            if (ch == '(') depth++;
            if (ch == ')') depth--;

            if (ch == ' ' && depth == 0) {
                if (token.length() > 0) {
                    exprList.add(token.toString().trim());
                    token.setLength(0);
                }
            } else {
                token.append(ch);
            }
        }

        if (token.length() > 0) {
            exprList.add(token.toString().trim());
        }

        return exprList;
    }

    public static class Helper {
        public static Parser ptr  = new Parser();


    }

    public static Parser getInstance(){
        return Helper.ptr;
    }
}