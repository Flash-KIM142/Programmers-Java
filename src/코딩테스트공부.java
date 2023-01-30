public class 코딩테스트공부 {

    public static void main(String[] args) {
        int alp = 0;
        int cop = 0;
        int[][] problems = { {0,0,2,1,2}, {4,5,3,1,2}, {4,11,4,0,2}, {10,4,0,4,2} };

        System.out.println(solution(alp,cop,problems));
    }

    static final int INF = 2000000000;

    static int solution(int alp, int cop, int[][] problems) {
        int answer = 0;
        int alp_target = 0, cop_target = 0;

        for(int[] prob: problems){
            alp_target = Math.max(alp_target, prob[0]);
            cop_target = Math.max(cop_target, prob[1]);
        }

        if(alp>=alp_target && cop>=cop_target)   return 0;
        if(alp>=alp_target) alp = alp_target;
        if(cop>=cop_target) cop = cop_target;

        int[][] visit = new int[alp_target+2][cop_target+2];

        for(int i=alp; i<alp_target+2; i++){
            for(int j=cop; j<cop_target+2; j++)
                visit[i][j] = INF;
        }
        visit[alp][cop] = 0;

        for(int i=alp; i<=alp_target; i++){
            for(int j=cop; j<=cop_target; j++){

                visit[i+1][j] = Math.min(visit[i+1][j], visit[i][j]+1);
                visit[i][j+1] = Math.min(visit[i][j+1], visit[i][j]+1);

                for(int p=0; p<problems.length; p++){
                    if(i<problems[p][0] || j<problems[p][1])    continue; // 못 푸는 문제

                    int nxt_alp = i + problems[p][2];
                    int nxt_cop = j + problems[p][3];
                    int nxt_cost = visit[i][j] + problems[p][4];

                    if(nxt_alp>alp_target)  nxt_alp = alp_target;
                    if(nxt_cop>cop_target)  nxt_cop = cop_target;

                    visit[nxt_alp][nxt_cop] = Math.min(visit[nxt_alp][nxt_cop], nxt_cost);
                }
            }
        }

        return visit[alp_target][cop_target];
    }
}