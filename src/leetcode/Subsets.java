package leetcode;

import java.util.*;

public class Subsets {

    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>(){{
            add(new ArrayList<>());
        }};

        int end = 1;
        for(int i=0; i<nums.length; i++) {
            int num = nums[i];
            for(int j=0; j<end; j++) {
                List<Integer> tmp = new ArrayList<>();
                tmp.addAll(res.get(j));
                tmp.add(num);
                res.add(tmp);
            }
            end *= 2;
        }

        return res;
    }
}
