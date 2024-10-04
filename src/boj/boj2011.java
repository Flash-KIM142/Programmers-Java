package boj;

import java.util.*;

public class boj2011 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        int N = input.length();
        int[] nums = new int[N + 1];
        for(int i=1; i<=N; i++) {
            nums[i] = input.charAt(i-1) - '0';
        }
        if(nums[1] == 0) {
            System.out.println(0);
            return;
        }

        int[][] dp = new int[N + 1][2];

        dp[1][0] = 1;

        for(int i=2; i<=N; i++) {
            if(nums[i] == 0) {
                if(nums[i-1]==0 || nums[i-1]>2) {
                    System.out.println(0);
                    return;
                }
            }

            if(nums[i]>0) {
                dp[i][0] = (dp[i-1][0] + dp[i-1][1]) % 1000000;
            }

            int two_digits_from_prev = nums[i-1] * 10 + nums[i];
            if(two_digits_from_prev>=10 && two_digits_from_prev<=26) {
                if(i==2) {
                    dp[i][1] = 1;
                }
                else {
                    dp[i][1] = (dp[i-2][0] + dp[i-2][1]) % 1000000;
                }
            }
        }

        int res = dp[N][0] + dp[N][1];
        System.out.println(res);
    }
}
