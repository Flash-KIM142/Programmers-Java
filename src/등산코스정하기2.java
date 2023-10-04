import java.util.*;

public class 등산코스정하기2 {

    public static void main(String[] args) {
        int n = 6;
        int[][] paths = {{1, 2, 3}, {2, 3, 5}, {2, 4, 2}, {2, 5, 4,}, {3, 4, 4}, {4, 5, 3}, {4, 6, 1}, {5, 6, 1}};
        int[] gates = {1, 3};
        int[] summits = {5};

        System.out.println(Arrays.toString(solution(n, paths, gates, summits)));
    }

    static public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        List<int[]>[] map = new LinkedList[n + 1];
        for (int i = 0; i < n + 1; i++) map[i] = new LinkedList<>();

        boolean[] isGate = new boolean[n + 1];
        for(int gate: gates)    isGate[gate] = true;

        boolean[] isSummit = new boolean[n + 1];
        for(int summit: summits)    isSummit[summit] = true;

        int[] intensity = new int[n + 1];
        for(int i=1; i<=n; i++) intensity[i] = Integer.MAX_VALUE;

        for (int[] path : paths) { // map 구축
            int start = path[0];
            int end = path[1];
            int weight = path[2];

            if(isGate[start] || isSummit[end]){
                map[start].add(new int[]{end, weight});
            }
            else if(isSummit[start] || isGate[end]){
                map[end].add(new int[]{start, weight});
            }
            else{
                map[start].add(new int[]{end, weight});
                map[end].add(new int[]{start, weight});
            }
        }

        Queue<int[]> q = new LinkedList<>();
        for(int gate: gates) {
                q.add(new int[]{gate, 0});
                intensity[gate] = 0;
        }

        while(!q.isEmpty()){
            int[] cur = q.poll();
            int curPos = cur[0];
            int curWeight = cur[1];

            if(curWeight > intensity[curPos])   continue;

            List<int[]> nextPosList = map[curPos];
            for (int[] next : nextPosList) {
                int nextPos = next[0];
                int nextWeight = next[1];

                int dist = Math.max(intensity[curPos], nextWeight);
                if (intensity[nextPos] > dist) {
                    intensity[nextPos] = dist;
                    q.add(new int[]{nextPos, dist});
                }
            }
        }

        int summit = n+1;
        int minVal = Integer.MAX_VALUE;
        Arrays.sort(summits);
        for(int s: summits){
            if(minVal>intensity[s]){
                summit = s;
                minVal = intensity[s];
            }
        }

        return new int[]{summit, minVal};
    }
}