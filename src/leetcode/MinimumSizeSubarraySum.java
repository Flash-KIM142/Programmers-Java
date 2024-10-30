package leetcode;

public class MinimumSizeSubarraySum {

    public int minSubArrayLen(int target, int[] nums) {
        int l = 0;
        int r = 0;
        int min_len = 0;
        int cur_sum = 0;
        while(r<=nums.length) {
            if(r==nums.length && cur_sum<target) {
                return min_len;
            }
            if(cur_sum<target) {
                cur_sum += nums[r++];
            }
            else {
                if(min_len>0) {
                    min_len = Integer.min(min_len, r-l);
                }
                else {
                    min_len = r-l;
                }
                cur_sum -= nums[l++];
            }
            if(min_len==1) {
                return 1;
            }
        }

        return min_len;
    }
}
