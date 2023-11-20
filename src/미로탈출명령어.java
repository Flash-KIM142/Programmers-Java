// 2023.11.21 2023 카카오 블라인드

public class 미로탈출명령어 {

    static int[] dirR = {1, 0, 0, -1};
    static int[] dirC = {0, -1, 1, 0}; // 하 상 우 좌
    static char[] dirType = {'d', 'l', 'r', 'u'};
    static String answer = "";

    public static String solution(int n, int m, int x, int y, int r, int c, int k) {
        int distance = Math.abs(x - r) + Math.abs(y - c);
        if (k - distance < 0 || (k - distance) % 2 != 0) {
            return "impossible";
        }

        dfs("", x, y, r, c, n, m, k);
        return answer;
    }

    static void dfs(String s, int curR, int curC, int r, int c, int n, int m, int k) {
        if (!answer.isEmpty()) {
            return;
        }
        if (s.length() + Math.abs(curR - r) + Math.abs(curC - c) > k) {
            return;
        }
        if (s.length() == k && (curR == r && curC == c)) {
            for (int i = 0; i < k; i++) {
                int idx = s.charAt(i) - '0';
                answer += dirType[idx];
            }
            return;
        }

        for (int dir = 0; dir < 4; dir++) {
            int nextR = curR + dirR[dir];
            int nextC = curC + dirC[dir];

            if (nextR <= 0 || nextR > n || nextC <= 0 || nextC > m) {
                continue;
            }

            s += dir;
            dfs(s, nextR, nextC, r, c, n, m, k);
            s = s.substring(0, s.length() - 1);
        }
    }

    public static void main(String[] args) {
        String result = solution(3, 4, 2, 3, 3, 1, 5);
        System.out.println(result);
    }
}