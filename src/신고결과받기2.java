import java.util.*;

public class 신고결과받기2 {

    public int[] solution(String[] id_list, String[] report, int k) {
        int n = id_list.length;
        int[] answer = new int[n];

        /* 유저 이름별로 index 붙여주기 */
        Map<String, Integer> user_index = new HashMap<>();
        for(int i=0; i<n; i++){
            user_index.put(id_list[i], i);
        }

        /* 각자가 신고한 유저 기록, 각자가 신고당한 기록 초기화 */
        int[][] report_record = new int[n][n];
        for(String r: report){
            String[] split = r.split(" ");
            int reporter = user_index.get(split[0]);
            int reportee = user_index.get(split[1]);

            if(report_record[reporter][reportee] == 0) {
                report_record[reporter][reportee] = 1;
            }
        }

        /* 정지된 사용자 판별 */
        boolean[] suspended_user = new boolean[n];
        for(int c = 0; c < n; c++){
            int cnt = 0;
            for(int r = 0; r < n; r++){
                if(c==r){
                    continue;
                }
                cnt += report_record[r][c];
            }
            if(cnt >= k){
                suspended_user[c] = true;
            }
        }

        /* 정답 계산 */
        for(int r = 0; r < n; r++){
            int cnt = 0;
            for(int c = 0; c < n; c++){
                if(r==c){
                    continue;
                }
                if(report_record[r][c]==1 && suspended_user[c]){
                    cnt++;
                }
            }
            answer[r] = cnt;
        }

        return answer;
    }
}
