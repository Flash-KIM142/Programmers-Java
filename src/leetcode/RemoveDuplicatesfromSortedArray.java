package leetcode;

public class RemoveDuplicatesfromSortedArray {

    public int removeDuplicates(int[] nums) {
        int cur_unique_idx = 1;
        int prev = nums[0];
        for(int i=1; i<nums.length; i++) {
            int cur = nums[i];
            if(cur==prev) {
                continue;
            }

            nums[cur_unique_idx++] = cur;
            prev = cur;
        }
        return cur_unique_idx;
    }
}
