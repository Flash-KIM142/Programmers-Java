// 2024.01.31 2024 카카오 겨울 인턴십 - 소요시간: 81분

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class 주사위고르기 {

    static int diceNum;
    static List<int[]> diceCombs = new ArrayList<>();
    static List<int[]> scoreCombs = new ArrayList<>();
    static boolean[] visited;

    public static void main(String[] args) {
        int[][] dice = new int[][]{{1, 2, 3, 4, 5, 6}, {3, 3, 3, 3, 4, 4}, {1, 3, 3, 4, 4, 4}, {1, 1, 4, 4, 5, 5}};
        int[] result = solution(dice);
        for (int r : result) {
            System.out.println(r);
        }
    }

    public static int[] solution(int[][] dice) {
        diceNum = dice.length;
        int[] answer = new int[diceNum / 2];
        visited = new boolean[diceNum];
        diceCombination(diceNum / 2, 0, 0, new int[diceNum / 2]);
        int maxWin = 0;
        scoreCombination(diceNum / 2, 0, new int[diceNum / 2]);

        /* 각 주사위 조합마다 승리수 계산 */
        for (int[] comb : diceCombs) {
            int win = 0;
            boolean[] tmp = new boolean[diceNum];
            for (int a : comb) {
                tmp[a] = true;
            }
            int idx = 0;
            int[] enemyComb = new int[diceNum / 2];
            for (int i = 0; i < diceNum; i++) {
                if (!tmp[i]) {
                    enemyComb[idx++] = i;
                }
            }

            int[] myScores = new int[scoreCombs.size()];
            int[] enemyScores = new int[scoreCombs.size()];
            for (int i = 0; i < scoreCombs.size(); i++) {
                int[] curScoreComb = scoreCombs.get(i);
                int mine = 0;
                int yours = 0;
                for (int j = 0; j < curScoreComb.length; j++) {
                    mine += dice[comb[j]][curScoreComb[j]];
                    yours += dice[enemyComb[j]][curScoreComb[j]];
                }

                myScores[i] = mine;
                enemyScores[i] = yours;
            }

            Arrays.sort(myScores);
            Arrays.sort(enemyScores);

            for (int score : myScores) {
                if (score > enemyScores[myScores.length - 1]) {
                    win += myScores.length;
                    continue;
                }

                int l = 0;
                int r = myScores.length;

                while (l <= r) {
                    int mid = (l + r) / 2;
                    if (score > enemyScores[mid]) {
                        l = mid + 1;
                    } else {
                        r = mid - 1;
                    }
                }
                win += l;
            }

            if (win > maxWin) {
                answer = comb;
                maxWin = win;
            }
        }

        for (int i = 0; i < answer.length; i++) {
            answer[i]++;
        }
        return answer;
    }

    static void diceCombination(int n, int cnt, int idx, int[] cur) {
        if (cnt == n) {
            int[] tmp = new int[n];
            for (int i = 0; i < n; i++) {
                tmp[i] = cur[i];
            }
            diceCombs.add(tmp);
            return;
        }

        for (int i = idx; i < diceNum; i++) {
            if (visited[i]) {
                continue;
            }
            visited[i] = true;
            cur[cnt] = i;
            diceCombination(n, cnt + 1, i + 1, cur);
            visited[i] = false;
        }
    }

    static void scoreCombination(int n, int cnt, int[] cur) {
        if (cnt == n) {
            int[] tmp = new int[n];
            for (int i = 0; i < n; i++) {
                tmp[i] = cur[i];
            }
            scoreCombs.add(tmp);
            return;
        }

        for (int i = 0; i < 6; i++) {
            cur[cnt] = i;
            scoreCombination(n, cnt + 1, cur);
        }
    }
}