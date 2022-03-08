import java.util.*;

public class TaxiFee {
    static int[][] dist;
    static ArrayList<Node>[] edges;
    static final int INF = Integer.MAX_VALUE;

    static Integer solution(int n, int s, int a, int b, int[][] fares){
        edgesInit(n, fares);
        dist = new int[n+1][n+1];
        for(int i=1; i<=n; i++)
            dist[i] = dijkstra(i, n);

        int answer = dist[s][a] + dist[s][b];
        for(int i=1; i<=n; i++)
            answer = Math.min(answer, dist[s][i]+dist[i][a]+dist[i][b]);

        return answer;
    }

    static int[] dijkstra(int start, int n){
        int[] tmpDist = new int[n+1];
        for(int i=1; i<=n; i++)
            tmpDist[i] = INF;
        tmpDist[start] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(start, 0));

        while(!pq.isEmpty()){
            Node tmp = pq.poll();
            int cur_pos = tmp.idx;
            int cur_weight = tmp.weight;
            if(tmpDist[cur_pos]<cur_weight) continue;

            ArrayList<Node> next_nodes = edges[cur_pos];
            for(Node next: next_nodes){
                int next_pos = next.idx;
                int next_weight = next.weight;

                if(tmpDist[next_pos] > cur_weight + next_weight){
                    tmpDist[next_pos] = cur_weight + next_weight;
                    pq.offer(new Node(next_pos, tmpDist[next_pos]));
                }
            }
        }
        return tmpDist;
    }

    static class Node implements Comparable<Node> {
        int idx;
        int weight;

        Node(int idx, int weight){
            this.idx = idx;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node cmp){
            return this.weight - cmp.weight;
        }
    }

    static void edgesInit(int n, int[][] fares){
        edges = new ArrayList[n+1];
        for(int i=1; i<=n; i++)
            edges[i] = new ArrayList<Node>();

        for(int[] fare: fares){
            int start = fare[0];
            int end = fare[1];
            int weight = fare[2];
            edges[start].add(new Node(end, weight));
            edges[end].add(new Node(start, weight));
        }
    }

    public static void main(String[] args) {
        int n = 6;
        int s = 4;
        int a = 6;
        int b = 2;
        int[][] fares = {{4, 1, 10}, {3, 5, 24}, {5, 6, 2}, {3, 1, 41},
                {5, 1, 24}, {4, 6, 50}, {2, 4, 66}, {2, 3, 22}, {1, 6, 25}};
        int res = solution(n, s, a, b, fares);
        System.out.println(res);
    }
}