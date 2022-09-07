import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Maximize2 {

    static List<Long> results;

    public static void main(String[] args) {
        String exp = "100-200*300-500+20";
        System.out.println(solution(exp));
    }

    static long solution(String expression) {
        results = new ArrayList<>();
        List<Character> operatorList;
        List<String> operandList;

        operatorList = new ArrayList<>();
        for(int i=0; i<expression.length(); i++){
            char cur = expression.charAt(i);
            if(cur=='-' || cur=='*' || cur=='+'){
                operatorList.add(cur);
            }
        }

        String[] operands = expression.replaceAll("[-+*]", " ").split(" ");
        operandList = new ArrayList<>(Arrays.asList(operands));

        List<Character> orgOperatorList = new ArrayList<>(operatorList);
        List<String> orgOperandList = new ArrayList<>(operandList);

        totalOperation(orgOperatorList, orgOperandList, '+','-','*');
        orgOperatorList = new ArrayList<>(operatorList);
        orgOperandList = new ArrayList<>(operandList);

        totalOperation(orgOperatorList, orgOperandList,'-','+','*');
        orgOperatorList = new ArrayList<>(operatorList);
        orgOperandList = new ArrayList<>(operandList);

        totalOperation(orgOperatorList, orgOperandList,'+','*','-');
        orgOperatorList = new ArrayList<>(operatorList);
        orgOperandList = new ArrayList<>(operandList);

        totalOperation(orgOperatorList, orgOperandList,'-','*','+');
        orgOperatorList = new ArrayList<>(operatorList);
        orgOperandList = new ArrayList<>(operandList);

        totalOperation(orgOperatorList, orgOperandList,'*','+','-');
        orgOperatorList = new ArrayList<>(operatorList);
        orgOperandList = new ArrayList<>(operandList);

        totalOperation(orgOperatorList, orgOperandList,'+','-','+');


        long answer = Math.abs(results.get(0));
        for (Long aLong : results) {
            long num = Math.abs(aLong);
            answer = Math.max(answer, num);
        }
        return answer;
    }

    static long calculator(long a, long b, char op){
        long result = 0;
        if(op=='+') result = a+b;
        else if(op=='-')    result = a-b;
        else    result = a*b;

        return result;
    }

    static void partialOperation(List<Character> operatorList, List<String> operandList, char op){
        for(int i=0; i<operatorList.size(); i++){
            if(operatorList.get(i)==op){
                long a = Long.parseLong(operandList.get(i));
                long b = Long.parseLong(operandList.get(i+1));
                long result = calculator(a,b,op);

                operandList.remove(i);
                operandList.remove(i);
                operatorList.remove(i);
                operandList.add(i, Long.toString(result));
                i--;
            }
        }
    }

    static void totalOperation(List<Character> operatorList, List<String> operandList, char op1, char op2, char op3){
        long result = 0;

        partialOperation(operatorList, operandList, op1);
        partialOperation(operatorList, operandList, op2);
        partialOperation(operatorList, operandList, op3);

        result = Long.parseLong(operandList.get(0));
        results.add(result);
    }
}
