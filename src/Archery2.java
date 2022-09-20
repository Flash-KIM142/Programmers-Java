import java.util.*;

public class Archery2 {
    static Queue<int[]> everyComb = new LinkedList<>();
    static Queue<Info> winningComb = new LinkedList<>();
    static List<int[]> maxDiffComb = new ArrayList<>();
    static int maxDiff = 0;

    public static void main(String[] args) {
        int[] info = {2,1,1,1,0,0,0,0,0,0,0};
        int[] res = solution(5, info);
        for(int i=0; i<res.length; i++){
            System.out.println(res[i]);
        }
    }

    static int[] solution(int n, int[] info) {
        int[] answer = {};

        backtracking(n, 0, 0, new int[11]);
        createWinningComb(info);
        if(winningComb.isEmpty())   return new int[]{-1};
        createMaxDiffComb();

        if(maxDiffComb.size()>1){
            maxDiffComb.sort( (o1, o2) -> {
                for(int i=10; i>=0; i--){
                    if(o1[i]!=o2[i]) return o2[i] - o1[i];
                }
                return 0;
            });
            answer = maxDiffComb.get(0);
        }
        else    answer = maxDiffComb.get(0);

        return answer;
    }

    static void backtracking(int n, int cnt, int idx, int[] info){ // 모든 조합 다 찾기
        if(cnt==n){
            everyComb.add(info); // info.clone() 안 했더니 깊은 복사 돼서 다 똑같은 놈 적용됨
            return;
        }

        for(int i=idx; i<11; i++){
            info[i]++;
            backtracking(n, cnt+1, i, info);
            info[i]--;
        }
    }

    static void createWinningComb(int[] ap){
        while(!everyComb.isEmpty()){
            int[] ry = everyComb.poll();
            whoWins(ap, ry);
        }
    }

    static void whoWins(int[] ap, int[] ry){
        int apSum = 0;
        int rySum = 0;

        for(int i=0; i<11; i++){
            int score = 10 - i;

            if(ap[i]==0 && ry[i]==0)    continue;

            if(ap[i]>=ry[i])    apSum += score;
            else    rySum += score;
        }

        if(apSum<rySum){
            int curDiff = rySum - apSum;
            winningComb.add(new Info(ry, curDiff));
            if(curDiff>maxDiff) maxDiff = curDiff;
        }

        return;
    }

    static void createMaxDiffComb(){
        while(!winningComb.isEmpty()){
            Info ry = winningComb.poll();
            if(ry.diff==maxDiff)    maxDiffComb.add(ry.score);
        }
    }

    static class Info{
        int[] score;
        int diff;

        Info(int[] score, int diff){
            this.score = score;
            this.diff = diff;
        }
    }
}
