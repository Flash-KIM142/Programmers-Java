import java.io.*;
import java.util.*;

public class Maximize {
    static long result = Long.MIN_VALUE;
    static char[] priority = { '+', '-', '*' };
    static boolean[] checked = new boolean[3];
    static LinkedList<Long> operands = new LinkedList<>();
    static LinkedList<Character> operators = new LinkedList<>();

    static long solution(String expression) {
        divideOperatorsOperands(expression);
        backTracking(0, new char[3]);
        return result;
    }

    public static void main(String args[]) throws IOException {
        BufferedWriter bfw = new BufferedWriter(new OutputStreamWriter(System.out));
        String expression = "50*6-3*2";
        bfw.write(String.valueOf(solution(expression)));
        bfw.close();
    }

    static void backTracking(int cnt, char[] op){
        if(cnt==3){
            LinkedList<Long> tmpOperands = new LinkedList<>();   LinkedList<Character> tmpOperators = new LinkedList<>();
            tmpOperands.addAll(operands);   tmpOperators.addAll(operators);

            for(int i=0; i<3; i++){
                for(int j=0; j<tmpOperators.size(); j++){
                    if(op[i]==tmpOperators.get(j)){
                        long tmp = calculator(tmpOperands.remove(j), tmpOperands.remove(j), op[i]);
                        tmpOperands.add(j, tmp);
                        tmpOperators.remove(j);
                        j--;
                    }
                }
            }
            result = (result<Math.abs(tmpOperands.peek()) ? Math.abs(tmpOperands.peek()) : result);
            return;
        }
        for(int i=0; i<3; i++){ // 가능한 모든 우선순위 조합을 만들자
            if(!checked[i]){
                checked[i] = true;
                op[cnt] = priority[i];
                backTracking(cnt+1, op);
                checked[i] = false;
            }
        }
    }

    static Long calculator(Long a, Long b, char operator){
        long result = 0;
        switch(operator){
            case '+':
                result = a + b;
                break;
            case '-':
                result = a - b;
                break;
            case '*':
                result = a * b;
                break;
        }
        return result;
    }

    static void divideOperatorsOperands(String expression){
        String tmpOperands = "";
        for(int i=0; i<expression.length(); i++){
            char cur = expression.charAt(i);
            if(cur=='+' || cur=='-' || cur=='*') {
                operands.add(Long.parseLong(tmpOperands));
                tmpOperands = "";
                operators.add(cur);
            }
            else{
                tmpOperands += cur;
            }
        }
        operands.add(Long.parseLong(tmpOperands));
    }
}