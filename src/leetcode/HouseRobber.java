package leetcode;

public class HouseRobber {

    public int rob(int[] nums) {
        if(nums.length==1) {
            return nums[0];
        }

        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        dp[1] = Integer.max(dp[0], nums[1]);

        for(int i=2; i<nums.length; i++) {
            dp[i] = Integer.max(dp[i-1], dp[i-2] + nums[i]);
        }

        return dp[nums.length-1];
    }
}
