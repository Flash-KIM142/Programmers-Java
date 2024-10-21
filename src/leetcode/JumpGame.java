package leetcode;

public class JumpGame {

    public boolean canJump(int[] nums) {
        if(nums.length==1) {
            return true;
        }
        if(nums[0]==0) {
            return false;
        }

        int max_reached = 0;

        int[] reachable = new int[nums.length-1];
        for(int i=0; i<reachable.length; i++) {
            reachable[i] = i + nums[i];
        }
        for(int i=0; i<reachable.length; i++) {
            if(i>max_reached) {
                continue;
            }
            max_reached = Integer.max(max_reached, reachable[i]);
            if(max_reached>=nums.length-1) {
                return true;
            }
        }

        return false;
    }
}
