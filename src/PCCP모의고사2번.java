// 2023.11.23 PCCP 모의고사, 소요시간: 20분
// 각 col마다 하나씩 뽑되, 뽑힌 위치들의 row가 중복될 수 없게 뽑았을 때 최댓값 구하기

public class PCCP모의고사2번 {

    static int rSize, cSize;
    static int max = 0;

    public static int solution(int[][] ability) {
        rSize = ability.length;
        cSize = ability[0].length;
        combination(new int[cSize], 0, new boolean[rSize], ability);

        return max;
    }

    static void combination(int[] cur, int cnt, boolean[] visited, int[][] ability) {
        if (cnt == cSize) {
            int tmp = 0;
            for (int c = 0; c < cSize; c++) {
                tmp += cur[c];
            }
            max = Math.max(max, tmp);
            return;
        }

        for (int i = 0; i < rSize; i++) {
            if (visited[i]) {
                continue;
            }
            cur[cnt] = ability[i][cnt];
            visited[i] = true;
            combination(cur, cnt + 1, visited, ability);
            visited[i] = false;
        }
    }

    public static void main(String[] args) {
        int[][] ability = new int[][]{{40, 10, 10}, {20, 5, 0}, {30, 30, 30}, {70, 0, 70}, {100, 100, 100}};
        int result = solution(ability);
        System.out.println(result);
    }
}