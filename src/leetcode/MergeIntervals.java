package leetcode;

import java.util.*;

public class MergeIntervals {

    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, (o1, o2) -> {
            if(o1[0]==o2[0]) {
                return o1[1] - o2[1];
            }
            return o1[0] - o2[0];
        });

        List<int[]> res_list = new ArrayList<>();
        int start = 0;
        int end = 0;
        for(int i=1; i<intervals.length; i++) {
            if(intervals[i-1][1] >= intervals[i][0]) {
                end++;
                if(intervals[i-1][1] > intervals[i][1]) {
                    int tmp = intervals[i-1][1];
                    intervals[i-1][1] = intervals[i][1];
                    intervals[i][1] = tmp;
                }
            }
            else {
                res_list.add(new int[]{intervals[start][0], intervals[end][1]});
                start = i;
                end = i;
            }
        }
        res_list.add(new int[]{intervals[start][0], intervals[end][1]});

        int[][] res = new int[res_list.size()][2];
        for(int i=0; i<res_list.size(); i++) {
            res[i][0] = res_list.get(i)[0];
            res[i][1] = res_list.get(i)[1];
        }

        return res;
    }
}
