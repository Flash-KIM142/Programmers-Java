import java.util.*;

public class MazeEscape {
    static int[][] edges;
    private static final int INF = Integer.MAX_VALUE;

    static int solution(int n, int start, int end, int[][] roads, int[] traps) {
        edgesInit(n, roads);

        int answer = dijkstra(n, start, end, traps);
        return answer;
    }

    static Integer dijkstra(int n, int start, int end, int[] traps){
        boolean[][] visited = new boolean[n+1][1 << 10];
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(start,0,0));

        while(!pq.isEmpty()){
            Node cur = pq.poll();
            if(cur.end==end)    return cur.weight;

            if(visited[cur.end][cur.state]) continue;
            visited[cur.end][cur.state] = true;

            boolean isCurTrapOn = false;
            HashSet<Integer> isTrapOn = new HashSet<>();
            for(int i=0; i<traps.length; i++){
                int bit = 1 << i;
                if((cur.state & bit) != 0){ // 함정 켜졌을 경우
                    if(traps[i]==cur.end){
                        cur.state &= ~bit;
                    }
                    else{
                        isTrapOn.add(traps[i]);
                    }
                }
                else{ // 함정 안 켜졌을 경우
                    if(traps[i]==cur.end){
                        cur.state |= bit;
                        isTrapOn.add(traps[i]);
                        isCurTrapOn = true;
                    }
                }
            }

            for(int next=1; next<=n; next++){
                if(next==cur.end)   continue;
                boolean isNextTrapOn = isTrapOn.contains(next) ? true: false;

                if(isCurTrapOn == isNextTrapOn){
                    if(edges[cur.end][next] != INF){
                        pq.offer(new Node(next, cur.weight + edges[cur.end][next], cur.state));
                    }
                }
                else{
                    if(edges[next][cur.end] != INF){
                        pq.offer(new Node(next, cur.weight + edges[next][cur.end], cur.state));
                    }
                }
            }
        }
        return 0;
    }

    static void edgesInit(int n, int[][] roads){
        edges = new int[n+1][n+1];
        for(int i=1; i<=n; i++){
            for(int j=1; j<=n; j++){
                if(i==j)    edges[i][j] = 0;
                else    edges[i][j] = INF;
            }
        }

        for(int[] road: roads){
            int start = road[0];
            int end = road[1];
            int weight = road[2];

            if(edges[start][end] > weight)
                edges[start][end] = weight;
        }
    }

    static class Node implements Comparable<Node> {
        int end;
        int weight;
        int state;

        Node(int end, int weight, int state){
            this.end = end;
            this.weight = weight;
            this.state = state;
        }

        @Override
        public int compareTo(Node nxt){
            return this.weight - nxt.weight;
        }
    }

    public static void main(String[] args) {
        int n = 4; int start = 1; int end = 4;
        int[][] roads = {{1, 2, 1}, {3, 2, 1}, {2, 4, 1}};
        int[] traps = {2,3};
        int answer = solution(n,start,end,roads,traps);
        System.out.println(answer);
    }
}