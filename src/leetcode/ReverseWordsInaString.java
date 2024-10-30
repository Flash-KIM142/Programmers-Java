package leetcode;

import java.util.*;

public class ReverseWordsInaString {

    public String reverseWords(String s) {
        String[] parts = s.split(" ");
        Stack<String> stk = new Stack<>();
        for(String part: parts) {
            if(part.equals("")) {
                continue;
            }
            stk.add(part);
        }
        StringBuilder sb = new StringBuilder();
        while(!stk.isEmpty()) {
            String cur = stk.pop();
            if(stk.isEmpty()) {
                sb.append(cur);
            }
            else {
                sb.append(cur + " ");
            }
        }
        return sb.toString();
    }
}
