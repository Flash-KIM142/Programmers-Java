package leetcode;

import java.util.*;

public class ZigzagConversion {

    public String convert(String s, int numRows) {
        List[] rows = new List[numRows];
        int len = s.length();
        int k = 2 * numRows - 2;
        if(k==0) {
            return s;
        }

        for(int i=0; i<numRows; i++) {
            rows[i] = new ArrayList<Character>();
            int idx = i;
            int idx1 = k - i;
            if(i==0 || i==numRows-1) {
                while(idx<len) {
                    rows[i].add(s.charAt(idx));
                    idx += k;
                }
            }
            else {
                Queue<Integer> q = new LinkedList<>();
                while(true) {
                    if(idx<len) {
                        q.add(idx);
                        idx += k;
                    }
                    else {
                        break;
                    }

                    if(idx1<len) {
                        q.add(idx1);
                        idx1 += k;
                    }
                    else {
                        break;
                    }
                }

                while(!q.isEmpty()) {
                    rows[i].add(s.charAt(q.poll()));
                }
            }
        }

        String res = "";
        for(int i=0; i<numRows; i++) {
            List<Character> cur = rows[i];
            for(Character c: cur) {
                res += c;
            }
        }

        return res;
    }
}
