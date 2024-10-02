import java.util.*;

public class 양과늑대2 {

    static List[] map;

    static int solution(int[] info, int[][] edges) {
        int answer = 0;
        int N = info.length;

        /* 노드 연결관계 초기화 */
        map = new List[N];
        for(int i=0; i<N; i++) {
            map[i] = new ArrayList<Integer>();
        }
        for(int[] edge: edges) {
            map[edge[0]].add(edge[1]);
            map[edge[1]].add(edge[0]);
        }

        Queue<Info> q = new LinkedList<>();
        Set<Integer> is_visited = new HashSet<>(){{
            add(0);
        }};
        q.add(new Info(0, 1, 0, 0, is_visited));

        while(!q.isEmpty()) {
            Info cur = q.poll();
            answer = Integer.max(answer, cur.sheep);
            if(cur.is_visited.size() * 2 < cur.trace){
                continue;
            }

            List<Integer> next_node_list = map[cur.node];
            for(Integer next_node: next_node_list) {
                int next_sheep = cur.sheep;
                int next_wolf = cur.wolf;
                if(!cur.is_visited.contains(next_node)) {
                    if(info[next_node] == 0) {
                        next_sheep += 1;
                    }
                    else{
                        next_wolf += 1;
                    }
                }

                if(next_wolf >= next_sheep){
                    continue;
                }

                int next_trace = cur.trace + 1;
                Set<Integer> next_is_visited = new HashSet<>(cur.is_visited){{
                    add(next_node);
                }};

                q.add(new Info(next_node, next_sheep, next_wolf, next_trace, next_is_visited));
            }
        }

        return answer;
    }

    static class Info {
        int node;
        int sheep;
        int wolf;
        int trace;
        Set<Integer> is_visited;

        Info(int node, int sheep, int wolf, int trace, Set<Integer> is_visited) {
            this.node = node;
            this.sheep = sheep;
            this.wolf = wolf;
            this.trace = trace;
            this.is_visited = is_visited;
        }
    }

    public static void main(String[] args) {
        int[] info = new int[]{0, 1, 0, 1, 1, 0, 1, 0, 0, 1, 0};
        int[][] edges = {{0, 1}, {0, 2}, {1,3},{1,4},{2,5},{2,6},{3,7},{4,8},{6,9},{9,10}};
        System.out.println(solution(info, edges));
    }
}
