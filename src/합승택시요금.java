// 2023.11.22 2021 카카오 블라인드, 소요시간: 45분

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class 합승택시요금 {

    static final int INF = Integer.MAX_VALUE;

    static List[] map;
    static int[][] totalDijkstra;

    public static int solution(int n, int s, int a, int b, int[][] fares) {
        init(n, fares);
        initTotalDijkstra(n);

        int answer = totalDijkstra[s][a] + totalDijkstra[s][b];
        for (int i = 1; i <= n; i++) { // 경유지점
            answer = Math.min(answer, totalDijkstra[s][i] + totalDijkstra[i][a] + totalDijkstra[i][b]);
        }

        return answer;
    }

    static void initTotalDijkstra(int n) {
        for (int i = 1; i <= n; i++) {
            Queue<Node> q = new LinkedList<>();
            q.add(new Node(i, 0));
            totalDijkstra[i][i] = 0;

            while (!q.isEmpty()) {
                Node cur = q.poll();
                if (cur.cost > totalDijkstra[i][cur.pos]) {
                    continue;
                }

                List<int[]> nexts = map[cur.pos];
                for (int[] next : nexts) {
                    int end = next[0];
                    int cost = next[1];

                    if (cur.cost + cost < totalDijkstra[i][end]) {
                        totalDijkstra[i][end] = cur.cost + cost;
                        q.add(new Node(end, cur.cost + cost));
                    }
                }
            }
        }
    }

    static void init(int n, int[][] fares) {
        totalDijkstra = new int[n + 1][n + 1];
        map = new List[n + 1];
        for (int i = 1; i <= n; i++) {
            Arrays.fill(totalDijkstra[i], INF);
            map[i] = new ArrayList<int[]>();
        }
        for (int[] fare : fares) {
            int start = fare[0];
            int end = fare[1];
            int cost = fare[2];

            map[start].add(new int[]{end, cost});
            map[end].add(new int[]{start, cost});
        }
    }

    static class Node {
        int pos;
        int cost;

        Node(int pos, int cost) {
            this.pos = pos;
            this.cost = cost;
        }
    }

    public static void main(String[] args) {
        int result = solution(7, 3, 4, 1, new int[][]{{5, 7, 9}, {4, 6, 4}, {3, 6, 1}, {3, 2, 3}, {2, 1, 6}});
        System.out.println(result);
    }
}