package leetcode;

import java.util.*;

public class SimplifyPath {

    public String simplifyPath(String path) {
        Stack<String> stk = new Stack<>();
        stk.add("/");
        String[] parts = path.split("/");
        for(int i=0; i<parts.length; i++) {
            if(parts[i].isEmpty()) {
                continue;
            }
            if(parts[i].equals(".")) {
                continue;
            }
            if(parts[i].equals("..")) {
                stk.pop();
                if(stk.isEmpty()) {
                    stk.add("/");
                    continue;
                }
                stk.pop();
                continue;
            }
            stk.add(parts[i]);

            if(!stk.peek().equals("/")) {
                stk.add("/");
            }
        }
        stk.pop();

        String answer = "";
        while(!stk.isEmpty()) {
            answer = stk.pop() + answer;
        }
        return answer.equals("") ? "/":answer;
    }
}
