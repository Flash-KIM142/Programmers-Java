import java.util.ArrayList;
import java.util.PriorityQueue;

public class TaxiFee {
    static int[][] dist;
    static ArrayList<Node>[] edges;

    static int solution(int n, int s, int a, int b, int[][] fares) {
        int answer = 0;

        /** 각 정점에 대한 최소 비용 배열 초기화 */
        distInit(n);

        /** 가중치 간선 정보 저장하기 */
        edgesInit(n, fares);

        /** 다익스트라 알고리즘 이용해서 모든 정점에 대해
         * 자기 자신 제외한 다른 정점까지의 최소 비용 구하기 */
        getDistance(n);

        /** 합승할 경우 최소 비용 구하기 */
        answer = getCarPoolFee(n,s,a,b);

        return answer;
    }

    static void distInit(int n) {
        dist = new int[n + 1][n + 1];
        for (int i = 0; i < n + 1; i++)
            for (int j = 0; j < n + 1; j++)
                dist[i][j] = Integer.MAX_VALUE;
    }

    static void edgesInit(int n, int[][] fares){
        edges = new ArrayList[n + 1];
        for (int i = 0; i < n + 1; i++) edges[i] = new ArrayList<>(); // 초기화

        for (int[] fare : fares) { // 간선 정보 저장하기
            int start = fare[0];
            int end = fare[1];
            int weight = fare[2];
            edges[start].add(new Node(end, weight));
            edges[end].add(new Node(start, weight));
        }
    }

    static void getDistance(int n){
        for(int i=1; i<=n; i++){
            dist[i][i] = 0; // 자기 자신에게 가는 비용은 당연히 0
            PriorityQueue<Node> q = new PriorityQueue<>();
            q.offer(new Node(i,0));

            while(!q.isEmpty()){
                Node cur = q.poll();
                if(dist[i][cur.end] < cur.cost)    continue; // 이미 최소비용이면 패스

                for(int j=0; j<edges[cur.end].size(); j++){
                    Node next = edges[cur.end].get(j);

                    if(dist[i][next.end] > cur.cost + next.cost){
                        dist[i][next.end] = cur.cost + next.cost;
                        q.offer(new Node(next.end, cur.cost + next.cost));
                    }
                }
            }
        }
    }

    static Integer getCarPoolFee(int n, int s, int a, int b){
        int min = Integer.MAX_VALUE;
        for(int i=1; i<=n; i++){
            min = Math.min(min, dist[s][i] + dist[i][a] + dist[i][b]);
        }
        return min;
    }

    static class Node implements Comparable<Node> {
        int end;
        int cost;

        Node(int end, int cost) {
            this.end = end;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node next) {
            return this.cost - next.cost;
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