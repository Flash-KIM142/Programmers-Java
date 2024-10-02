import java.util.*;

public class 양궁대회2 {

    static int max_diff = -1;
    static List<int[]> candidates = new ArrayList<>();

    public static int[] solution(int n, int[] info) {
        int[] answer = {};

        /* 라이언이 맞출 수 있는 모든 경우의 수를 구하며, 승자 판별하기 */
        int[] cur_score = new int[11];
        combination(n, 0, 0, cur_score, info);

        /* 라이언이 승리 불가할 경우 */
        if(candidates.size() == 0){
            return new int[]{-1};
        }

        /* 가장 낮은 점수를 더 많이 맞춘 정답 구하기 */
        candidates.sort((o1, o2) -> {
            for(int i=10; i>=0; i--){
                if(o1[i]!=o2[i]){
                    return o2[i] - o1[i];
                }
            }
            return 0;
        });

        answer = candidates.get(0);
        return answer;
    }

    static void combination(int n, int cnt, int idx, int[] cur_score, int[] appeach){
        if(cnt==n){ // 경우 1가지 완성되면 승자 판별하기
            judge(cur_score, appeach);
            return;
        }

        for(int i=idx; i<11; i++){
            cur_score[i]++;
            combination(n, cnt+1, i, cur_score, appeach);
            cur_score[i]--;
        }
    }

    static void judge(int[] ryan, int[] appeach) {
        int ryan_score = 0;
        int appeach_score = 0;
        for(int i=0; i<11; i++){
            if(ryan[i] + appeach[i] == 0) { // 둘 다 못 맞췄으면 skip
                continue;
            }

            int score = 10 - i;
            if(ryan[i] > appeach[i]) {
                ryan_score += score;
            }
            else{
                appeach_score += score;
            }
        }

        if(ryan_score > appeach_score && ryan_score - appeach_score >= max_diff) { // 라이언 승 + 최대 점수차
            if(ryan_score - appeach_score > max_diff){ // 최대 점수차 갱신
                max_diff = ryan_score - appeach_score;
                candidates = new ArrayList<>();
            }
            candidates.add(ryan.clone());
        }
    }

    public static void main(String[] args) {
        int n = 5;
        int[] info = {2,1,1,1,0,0,0,0,0,0,0};
        int[] answer = solution(n, info);
        for (int a : answer) System.out.println(a + " ");
    }
}
