import java.io.*;
import java.util.Stack;

public class BracketTransform {
    static String solution(String p) {
        String answer = "";
        if(p.equals("")) return answer; // 비었으면 바로 return

        String u="",v="";
        int open = 0, close = 0;
        for(int i=0; i<p.length(); i++){
            if(p.charAt(i)=='(') open++;
            else    close++;
            if(open==close){
                u = p.substring(0,i+1);
                v = p.substring(i+1);
                break;
            }
        }

        if(isRight(u)){
            answer = u + solution(v);
        }
        else{
            u = u.substring(1,u.length()-1);
            StringBuilder sb = new StringBuilder();
            for(int i=0; i<u.length(); i++){
                if(u.charAt(i)=='(')    sb.append(')');
                else    sb.append('(');
            }
            u = sb.toString();
            answer = "(" + solution(v) + ")" + u;
        }

        return answer;
    }

    static Boolean isRight(String s){
        Stack<Character> stack = new Stack<>();
        for(int i=0; i<s.length(); i++){
            char cur = s.charAt(i);
            if(stack.size()==0) stack.add(cur);
            else{
                char top = stack.peek();
                if(top=='(' && cur==')')    stack.pop();
                else    stack.add(cur);
            }
        }

        return stack.size()==0 ? true : false;
    }

    public static void main(String[] args) throws IOException {
        BufferedWriter bfw = new BufferedWriter(new OutputStreamWriter(System.out));
        String p = "()))((()";
        String answer = solution(p);
        bfw.write(answer);
        bfw.close();
    }
}