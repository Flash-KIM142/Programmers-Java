package leetcode;

import java.util.*;

public class Two_Sum {

    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> num_idx_map = new HashMap<>();
        int a = 0;
        int b = 0;
        for(int i=0; i<nums.length; i++) {
            if(num_idx_map.containsKey(target - nums[i])) {
                a = i;
                b = num_idx_map.get(target - nums[i]);
                break;
            }
            num_idx_map.put(nums[i], i);
        }

        return new int[]{a, b};
    }
}
