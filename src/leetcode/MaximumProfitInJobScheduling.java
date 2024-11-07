package leetcode;

import java.util.Arrays;

public class MaximumProfitInJobScheduling {

    public static void main(String[] args) {
        int[] startTime = {24, 24, 7, 2, 1, 13, 6, 14, 18, 24};
        int[] endTime = {27, 27, 20, 7, 14, 22, 20, 24, 19, 27};
        int[] profit = {6, 1, 4, 2, 3, 6, 5, 6, 9, 8};
        int answer = jobScheduling(startTime, endTime, profit);
        System.out.println(answer);
    }

    public static int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
        int N = startTime.length;
        Node[] ary = new Node[N];
        for(int i=0; i<N; i++) {
            ary[i] = new Node(startTime[i], endTime[i], profit[i]);
        }
        Arrays.sort(ary, (a, b) -> (a.end - b.end));

        int[] dp = new int[N];
        dp[0] = ary[0].cost;
        for(int i=1; i<N; i++) {
            int lastNonOverlappingJobIdx = findLastNonOverlappingJobIdx(ary, i, ary[i].start);
            int curMaxProfit = ary[i].cost;
            if(lastNonOverlappingJobIdx!=-1) {
                curMaxProfit += dp[lastNonOverlappingJobIdx];
            }

            dp[i] = Math.max(dp[i-1], curMaxProfit);
        }

        return dp[N-1];
    }

    public static int findLastNonOverlappingJobIdx(Node[] ary, int size, int target) {
        int l=0;
        int r = size;

        while(l<r) {
            int mid = (l+r)/2;
            if(target<ary[mid].end) {
                r = mid;
            }
            else {
                l = mid + 1;
            }
        }

        return l-1;
    }

    static class Node {
        int start;
        int end;
        int cost;

        Node(int start, int end, int cost) {
            this.start = start;
            this.end = end;
            this.cost = cost;
        }
    }
}
