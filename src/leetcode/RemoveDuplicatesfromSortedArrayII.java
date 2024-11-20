package leetcode;

public class RemoveDuplicatesfromSortedArrayII {

    public int removeDuplicates(int[] nums) {
        int prev = nums[0];
        int curNumCnt = 1;
        int totalCnt = 1;
        if(nums.length==1) {
            return 1;
        }

        for(int i=1; i<nums.length; i++) {
            int cur = nums[i];
            if(cur==prev) {
                if(curNumCnt<2) {
                    curNumCnt++;
                    nums[totalCnt++] = cur;
                }
                else {
                    continue;
                }
            }
            else {
                curNumCnt = 1;
                nums[totalCnt++] = cur;
                prev = cur;
            }
        }

        return totalCnt;
    }
}
