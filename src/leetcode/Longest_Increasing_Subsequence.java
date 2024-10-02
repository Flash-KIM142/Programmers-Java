package leetcode;

public class Longest_Increasing_Subsequence {

    public int lengthOfLIS(int[] nums) {
        int[] dp = new int[2501];
        for(int i=0; i<nums.length; i++) {
            dp[i] = 1;
        }
        for(int i=0; i<nums.length; i++) {
            for(int j=0; j<i; j++) {
                if(nums[i] > nums[j] && dp[i] < dp[j] + 1) {
                    dp[i] = dp[j] + 1;
                }
            }
        }

        int answer = -1;
        for(int i=0; i<nums.length; i++) {
            answer = Integer.max(answer, dp[i]);
        }
        return answer;
    }
}
