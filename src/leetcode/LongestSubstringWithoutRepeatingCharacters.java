package leetcode;

import java.util.*;

public class LongestSubstringWithoutRepeatingCharacters {

    public int lengthOfLongestSubstring(String s) {
        char[] ary = s.toCharArray();
        int res = 0;
        Map<Character, Integer> map = new HashMap<>();
        int start = 0;

        for(int i=0; i<ary.length; i++) {
            if(map.containsKey(ary[i])) {
                start = Integer.max(start ,map.get(ary[i]) + 1);
            }
            map.put(ary[i], i);

            res = Integer.max(res, i - start + 1);
        }
        return res;
    }
}
