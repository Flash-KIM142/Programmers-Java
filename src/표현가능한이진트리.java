// 2023.09.21

import java.util.Arrays;

public class 표현가능한이진트리 {

    public static void main(String[] args) {
        long[] numbers = new long[]{15};
        System.out.println(Arrays.toString(solution(numbers)));
    }

    static public int[] solution(long[] numbers) {
        int[] answer = new int[numbers.length];

        for(int i = 0; i < numbers.length; i++) {
            String cur = Long.toBinaryString(numbers[i]);
            int j = 0;
            while((int)Math.pow(2, j)-1 < cur.length()) {
                j++;
            }
            while((int)Math.pow(2, j)-1 != cur.length()) {
                cur = "0"+ cur;
            }
            if(isBinaryTree(cur)) {
                answer[i] = 1;
            }
        }

        return answer;
    }

    static Boolean isBinaryTree(String s) {
        if(s.length()==1)   return true;

        char mid = s.charAt(s.length() / 2);
        String left = s.substring(0, s.length() / 2);
        String right = s.substring(s.length() / 2 + 1);

        if(mid=='0' && (left.charAt(left.length()/2)=='1' || right.charAt(right.length()/2)=='1'))  return false;

        return isBinaryTree(left) && isBinaryTree(right);
    }
}