// 2023.11.15 2022 카카오 테크 인턴십

import java.util.Arrays;

public class 코딩테스트공부2 {

    static int alpGoal, copGoal;
    static int[][] minTime;
    static final int INF = Integer.MAX_VALUE;

    static int solution(int alp, int cop, int[][] problems) {
        init(alp, cop, problems);
        if (alp >= alpGoal && cop >= copGoal) {
            return 0;
        }
        if (alp > alpGoal) {
            alp = alpGoal;
        }
        if (cop > copGoal) {
            cop = copGoal;
        }

        for (int i = alp; i <= alpGoal; i++) {
            for (int j = cop; j <= copGoal; j++) {
                minTime[i + 1][j] = Math.min(minTime[i + 1][j], minTime[i][j] + 1);
                minTime[i][j + 1] = Math.min(minTime[i][j + 1], minTime[i][j] + 1);

                for (int[] problem : problems) {
                    if (problem[0] > i || problem[1] > j) {
                        continue;
                    }

                    int nextAlp = i + problem[2];
                    int nextCop = j + problem[3];
                    int nextTime = minTime[i][j] + problem[4];
                    if (nextAlp > alpGoal) {
                        nextAlp = alpGoal;
                    }
                    if (nextCop > copGoal) {
                        nextCop = copGoal;
                    }

                    minTime[nextAlp][nextCop] = Math.min(minTime[nextAlp][nextCop], nextTime);
                }
            }
        }

        return minTime[alpGoal][copGoal];
    }

    static void init(int alp, int cop, int[][] problems) {
        alpGoal = 0;
        copGoal = 0;
        for (int[] problem : problems) {
            alpGoal = Math.max(alpGoal, problem[0]);
            copGoal = Math.max(copGoal, problem[1]);
        }
        minTime = new int[alpGoal + 2][copGoal + 2];
        for (int i = 0; i < alpGoal + 2; i++) {
            Arrays.fill(minTime[i], INF);
        }
        if (alp > alpGoal) {
            alp = alpGoal;
        }
        if (cop > copGoal) {
            cop = copGoal;
        }
        minTime[alp][cop] = 0;
    }

    public static void main(String[] args) {
        int alp = 10;
        int cop = 10;
        int[][] problems = new int[][]{{10, 15, 2, 1, 2}, {20, 20, 3, 3, 4}};
        int result = solution(alp, cop, problems);
        System.out.println(result);
    }
}