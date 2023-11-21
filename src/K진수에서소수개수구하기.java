// 2023.11.21 2022 카카오 블라인드

import java.util.Stack;

public class K진수에서소수개수구하기 {

    public static int solution(int n, int k) {
        int answer = 0;
        String number = String.valueOf(n);
        if (k != 10) {
            number = convertToK(n, k);
        }

        String[] splits = number.split("0");
        for (String split : splits) {
            if (split.isEmpty()) {
                continue;
            }
            if (isPrimeNumber(Long.parseLong(split))) {
                answer++;
            }
        }

        return answer;
    }

    static boolean isPrimeNumber(long n) {
        if (n == 1) {
            return false;
        }
        if (n == 2 || n == 3) {
            return true;
        }

        int end = (int) Math.sqrt(n);
        for (int i = 2; i <= end; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    static String convertToK(int n, int k) {
        StringBuilder sb = new StringBuilder();
        Stack<Integer> stk = new Stack<>();
        while (n > 0) {
            stk.add(n % k);
            n /= k;
        }
        while (!stk.isEmpty()) {
            sb.append(stk.pop());
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        int result = solution(157, 10);
        System.out.println(result);
    }
}