package leetcode;

import java.util.ArrayList;
import java.util.List;

public class SummaryRanges {

    public List<String> summaryRanges(int[] nums) {
        List<String> result = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        int l = 0;
        int r = 0;
        while(r<nums.length) {
            if(l==r) {
                sb = new StringBuilder();
                sb.append(nums[l]);
            }

            if(r==nums.length-1) {
                if(l!=r) {
                    sb.append("->").append(nums[r]);
                }
                result.add(sb.toString());
                break;
            }
            else {
                if(nums[r+1]==nums[r]+1) {
                    r++;
                }
                else {
                    if(l!=r) {
                        sb.append("->").append(nums[r]);
                    }
                    result.add(sb.toString());
                    r++;
                    l = r;
                }
            }
        }

        return result;
    }
}
