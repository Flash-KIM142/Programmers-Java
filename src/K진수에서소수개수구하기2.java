import java.util.*;

public class K진수에서소수개수구하기2 {

    public int solution(int n, int k) {
        int answer = 0;
        String n_to_k_numeric = convert_to_k_numeric(n, k);
        String[] split = n_to_k_numeric.split("0");
        for(String s: split){
            if(s.equals("")){
                continue;
            }
            if(is_prime_number(Long.parseLong(s))){
                answer++;
            }
        }
        return answer;
    }

    String convert_to_k_numeric(int n, int k) {
        Stack<Integer> stk = new Stack<>();
        while(n>0){
            stk.add(n % k);
            n /= k;
        }

        String res = "";
        while(!stk.isEmpty()){
            res += stk.pop();
        }

        return res;
    }

    boolean is_prime_number(Long n) {
        if(n==1){
            return false;
        }
        if(n==2){
            return true;
        }

        double sqrt_n = Math.sqrt(n);
        long sqrt_n_to_int = (long) sqrt_n;

        for(int i=2; i<=sqrt_n_to_int; i++){
            if(n % i == 0){
                return false;
            }
        }

        return true;
    }
}
