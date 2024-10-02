import java.util.*;

public class 등산코스정하기4 {

    List<Node>[] map;
    Set<Integer> gate_set = new HashSet<>();
    Set<Integer> summit_set = new HashSet<>();
    Integer MAX_INTENSITY = 999999999;
    int[] intensity;

    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {

        /* map 초기화 */
        map = new List[n+1];
        for(int i=1; i<=n; i++){
            map[i] = new LinkedList<>();
        }
        for(int[] path: paths){ // 각 노드가 어떤 노드로 연결되는지만 저장하고, 거리값은 paths 그대로 사용
            map[path[0]].add(new Node(path[1], path[2]));
            map[path[1]].add(new Node(path[0], path[2]));
        }

        /* 입구와 봉우리 초기화 */
        for(int gate: gates){
            gate_set.add(gate);
        }
        for(int summit: summits){
            summit_set.add(summit);
        }

        update_intensity(n);

        int answer_summit = 0;
        int answer_intensity = MAX_INTENSITY;
        for(int summit: summits){
            if(intensity[summit] < answer_intensity){
                answer_summit = summit;
                answer_intensity = intensity[summit];
            }
            else if(intensity[summit]==answer_intensity && answer_summit>summit){
                answer_summit = summit;
            }
        }

        return new int[]{answer_summit, answer_intensity};
    }

    void update_intensity(int n){
        intensity = new int[n+1];
        for(int i=1; i<=n; i++){
            intensity[i] = MAX_INTENSITY;
        }

        Queue<int[]> q = new LinkedList<>(); // {cur_pos, cur_intensity}
        for(int gate: gate_set){
            intensity[gate] = 0;
            q.add(new int[]{gate, 0});
        }

        while(!q.isEmpty()){
            int[] cur = q.poll();
            int cur_pos = cur[0];
            int cur_intensity = cur[1];

            if(intensity[cur_pos] < cur_intensity){
                continue;
            }

            List<Node> next_node_list = map[cur_pos];
            for(Node next_node: next_node_list){
                if(gate_set.contains(next_node.pos)){ // 또다른 출입구를 만나면 스킵
                    continue;
                }

                int next_intensity = next_node.dist > cur_intensity ? next_node.dist : cur_intensity;
                if(summit_set.contains(next_node.pos)){ // 봉우리 만나면 후보군에 결과값 저장하고 스킵
                    if(intensity[next_node.pos] > next_intensity){
                        intensity[next_node.pos] = next_intensity;
                    }
                    continue;
                }
                if(intensity[next_node.pos] > next_intensity){ // 행선지의 기존 intensity보다 더 작은 intensity를 가질 때 갱신
                    intensity[next_node.pos] = next_intensity;
                    q.add(new int[]{next_node.pos, next_intensity});
                }
            }
        }
    }

    class Node {
        int pos;
        int dist;

        Node(int pos, int dist){
            this.pos = pos;
            this.dist = dist;
        }
    }
}
