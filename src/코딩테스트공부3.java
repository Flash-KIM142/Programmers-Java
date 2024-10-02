import java.util.*;

public class 코딩테스트공부3 {

    public int solution(int alp, int cop, int[][] problems) {
        int answer = 0;
        int target_alp = 0;
        int target_cop = 0;
        /* 목표 능력치 초기화 */
        List<int[]> problem_list = new ArrayList<>();
        problem_list.add(new int[]{0,0,1,0,1});
        problem_list.add(new int[]{0,0,0,1,1});
        for(int[] problem: problems){
            target_alp = Integer.max(target_alp, problem[0]);
            target_cop = Integer.max(target_cop, problem[1]);
            problem_list.add(problem);
        }

        if(alp>=target_alp && cop>=target_cop){
            return 0;
        }

        int[][] dist = new int[151][151];
        for(int i=0; i<151; i++){
            for(int j=0; j<151; j++){
                dist[i][j] = 999999999;
            }
        }
        dist[alp][cop] = 0;

        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{0,alp,cop});

        while(!q.isEmpty()){
            int[] tmp = q.poll();
            int cur_time = tmp[0];
            int cur_alp = tmp[1];
            int cur_cop = tmp[2];

            if(dist[cur_alp][cur_cop]<cur_time){
                continue;
            }

            for(int[] problem: problem_list){
                if(cur_alp<problem[0] || cur_cop<problem[1]){
                    continue;
                }

                int nxt_time = cur_time + problem[4];
                int nxt_alp = cur_alp + problem[2];
                int nxt_cop = cur_cop + problem[3];
                if(nxt_alp>target_alp){
                    nxt_alp = target_alp;
                }
                if(nxt_cop>target_cop){
                    nxt_cop = target_cop;
                }

                if(dist[nxt_alp][nxt_cop] > nxt_time){
                    q.add(new int[]{nxt_time, nxt_alp, nxt_cop});
                    dist[nxt_alp][nxt_cop] = nxt_time;
                }
            }
        }

        answer = dist[target_alp][target_cop];
        return answer;
    }
}
