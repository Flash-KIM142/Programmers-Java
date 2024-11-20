package leetcode;

import java.util.Stack;

public class EvaluateReversePolishNotation {

    public int evalRPN(String[] tokens) {
        Stack<String> st = new Stack<>();
        for(String token: tokens) {
            if(!token.equals("+") && !token.equals("-") && !token.equals("*") && !token.equals("/")) {
                st.add(token);
            }
            else {
                int operand2 = Integer.parseInt(st.pop());
                int operand1 = Integer.parseInt(st.pop());

                switch(token) {
                    case "+":
                        st.add(operand1 + operand2 + "");
                        break;

                    case "-":
                        st.add(operand1 - operand2 + "");
                        break;

                    case "*":
                        st.add(operand1 * operand2 + "");
                        break;

                    case "/":
                        st.add(operand1 / operand2 + "");
                        break;

                    default:
                        break;
                }
            }
        }

        return Integer.parseInt(st.pop());
    }
}
