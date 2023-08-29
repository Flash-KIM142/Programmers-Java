import java.util.*;

public class 등산코스정하기 {
    public static void main(String[] args) {
        int n = 7;
        int[][] paths = {{1,4,4}, {1,6,1}, {1,7,3}, {2,5,2}, {3,7,4}, {5,6,6},};
        int[] gates = {1,};
        int[] summits = {2,3,4};

        System.out.println(Arrays.toString(solution(n, paths, gates, summits)));
    }

    static int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        int[] answer = new int[2];
        final int INTEGER_MAX = 2000000000;

        /* gate 인지 아닌지 + gate 별로 intensity 정보 담을 map 선언 */
        boolean[] isGate = new boolean[n+1];
        for(int a: gates)   isGate[a] = true;

        /* 산봉우리별 intensity 값 담을 배열 */
        int[] intensity = new int[n+1];
        Arrays.fill(intensity, INTEGER_MAX);

        /* summit 인지 아닌지 */
        boolean[] isSummit = new boolean[n+1];
        for(int s: summits) isSummit[s] = true;

        /* 경로간의 비용을 저장 */
        List<Node>[] pathCost = new List[n+1];
        for(int i=1; i<n+1; i++)    pathCost[i] = new ArrayList<>();

        for(int[] path: paths){
            int s = path[0];
            int e = path[1];
            int w = path[2];

            if(isGate[s]|| isSummit[e]){
                pathCost[s].add(new Node(e,w));
            }
            else if(isSummit[s] || isGate[e]){
                pathCost[e].add(new Node(s,w));
            }
            else{
                pathCost[s].add(new Node(e,w));
                pathCost[e].add(new Node(s,w));
            }
        }

        Queue<Node> q = new LinkedList<>();
        for(int gate: gates){
            q.add(new Node(gate, 0));
            intensity[gate] = 0;
        }

        while(!q.isEmpty()){
            Node cur = q.poll();
            if(cur.cost > intensity[cur.pos])    continue;

            List<Node> nextPosList = pathCost[cur.pos];
            if(nextPosList == null)   continue;
            for(int i=0; i<nextPosList.size(); i++){
                Node next = nextPosList.get(i);

                int cost = Math.max(intensity[cur.pos], next.cost);
                if(intensity[i]>cost){
                    intensity[i] = cost;
                    q.add(new Node(i, cost));
                }
            }
        }

        Arrays.sort(summits);
        int a = n+1;
        int b = INTEGER_MAX;
        for(int s: summits){
            if(b>intensity[s]){
                a = s;
                b = intensity[s];
            }
        }

        answer[0] = a; answer[1] = b;
        return answer;
    }

    static class Node{
        int pos;
        int cost;

        Node(int pos, int cost){
            this.pos = pos;
            this.cost = cost;
        }
    }

//    static int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
//        int[] answer = new int[2];
//        final int INTEGER_MAX = 2000000000;
//
//        /* gate 인지 아닌지 + gate 별로 intensity 정보 담을 map 선언 */
//        boolean[] isGate = new boolean[n+1];
//        for(int a: gates)   isGate[a] = true;
//
//        /* 산봉우리별 intensity 값 담을 배열 */
//        int[] intensity = new int[n+1];
//        Arrays.fill(intensity, INTEGER_MAX);
//
//        /* summit 인지 아닌지 */
//        boolean[] isSummit = new boolean[n+1];
//        for(int s: summits) isSummit[s] = true;
//
//        /* 경로간의 비용을 저장 */
//        int[][] pathCost = new int[n+1][n+1];
//        for(int[] path: paths){
//            int s = path[0];
//            int e = path[1];
//
//            if(isGate[s] || isSummit[e]){
//                pathCost[s][e] = path[2];
//            }
//            else if(isGate[e] || isSummit[s]){
//                pathCost[e][s] = path[2];
//            }
//            else{
//                pathCost[s][e] = path[2];
//                pathCost[e][s] = path[2];
//            }
//        }
//
//        Queue<Node> q = new LinkedList<>();
//        for(int gate: gates){
//            q.add(new Node(gate, 0));
//            intensity[gate] = 0;
//        }
//
//        while(!q.isEmpty()){
//            Node cur = q.poll();
//            if(cur.cost > intensity[cur.curPos])    continue;
//
//            int[] nextPosCost = pathCost[cur.curPos];
//            for(int i=1; i<=n; i++){
//                if(nextPosCost[i]!=0){
//                    int cost = Math.max(intensity[cur.curPos], nextPosCost[i]);
//                    if(intensity[i]>cost){
//                        intensity[i] = cost;
//                        q.add(new Node(i, cost));
//                    }
//                }
//            }
//        }
//
//
//        Arrays.sort(summits);
//        int a = n+1;
//        int b = INTEGER_MAX;
//        for(int s: summits){
//            if(b>intensity[s]){
//                a = s;
//                b = intensity[s];
//            }
//        }
//
//        answer[0] = a; answer[1] = b;
//        return answer;
//    }
//
//    static class Node{
//        int curPos;
//        int cost;
//
//        Node(int curPos, int cost){
//            this.curPos = curPos;
//            this.cost = cost;
//        }
//    }
}