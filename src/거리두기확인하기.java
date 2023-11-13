// 2023.11.09 2021 카카오 채용연계형 인턴십

import java.util.Arrays;

public class 거리두기확인하기 {

    public static void main(String[] args) {
        String[][] places = new String[][]{{"POOOP", "OXXOX", "OPXPX", "OOXOX", "POXXP"},
                {"POOPX", "OXPXP", "PXXXO", "OXXXO", "OOOPP"}, {"PXOPX", "OXOXP", "OXPOX", "OXXOP", "PXPOX"},
                {"OOOXX", "XOOOX", "OOOXX", "OXOOX", "OOOOO"}, {"PXPXP", "XPXPX", "PXPXP", "XPXPX", "PXPXP"}};

        System.out.println(Arrays.toString(solution(places)));
    }

    /* 상 하 좌 우 순서 */
    static int[] dirRow = {-1, 1, 0, 0,};
    static int[] dirCol = {0, 0, -1, 1};

    /* 북서 북동 남서 남동 순서 */
    static int[] crossRow = {-1, -1, 1, 1};
    static int[] crossCol = {-1, 1, -1, 1};

    public static int[] solution(String[][] places) {
        int[] result = {1, 1, 1, 1, 1};
        char[][] map = new char[5][5];

        int cellIdx = 0;
        /* 방 하나 초기화 */
        for (String[] cell : places) {
            for (int r = 0; r < 5; r++) {
                String row = cell[r];
                for (int c = 0; c < 5; c++) {
                    char cur = row.charAt(c);
                    map[r][c] = cur;
                }
            }

            /* 방 하나 넘겨서 거리두기 잘 지켜지고 있는지 판별 */
            result[cellIdx++] = checkACell(map);
        }

        return result;
    }

    public static int checkACell(char[][] map) {
        int result = 1;
        boolean[][] visited = new boolean[5][5];

        /* P가 있는 지점에서 distance가 2인 총 12개의 지점을 체크해줘야 한다. */
        for (int r = 0; r < 5; r++) {
            for (int c = 0; c < 5; c++) {
                if (map[r][c] == 'P') {
                    /* 가장 먼저 바로 붙어있는 4개의 지점에 대해 검사 */
                    for (int d = 0; d < 4; d++) {
                        int targetR = r + dirRow[d];
                        int targetC = c + dirCol[d];

                        if (isOutOfMap(targetR, targetC)) {
                            continue;
                        }
                        if (visited[targetR][targetC]) {
                            continue;
                        }

                        if (map[targetR][targetC] == 'P') {
                            return 0;
                        }
                    }

                    /* 직선 방향으로 2칸 떨어져있는 지점 4개들에 대해 검사 */
                    for (int d = 0; d < 4; d++) {
                        int targetR = r + 2 * dirRow[d];
                        int targetC = c + 2 * dirCol[d];

                        if (isOutOfMap(targetR, targetC)) {
                            continue;
                        }
                        if (visited[targetR][targetC]) {
                            continue;
                        }

                        if (map[targetR][targetC] == 'P') {
                            int betweenR = r + dirRow[d];
                            int betweenC = c + dirCol[d];

                            if (map[betweenR][betweenC] == 'O') { // 이미 이 지점이 P는 아니란 걸 위에서 검증함. O만 검증하면 됨.
                                return 0;
                            }
                        }
                    }

                    /* 대각선 방향으로 있는 지점 4개들에 대해 검사 */
                    for (int d = 0; d < 4; d++) {
                        int targetR = r + crossRow[d];
                        int targetC = c + crossCol[d];

                        if (isOutOfMap(targetR, targetC)) {
                            continue;
                        }
                        if (visited[targetR][targetC]) {
                            continue;
                        }

                        if (map[targetR][targetC] == 'P') {
                            /* 대각선 지점으로 가기 위해 거칠 수 있는 두 지점들에 대해 검사 */
                            if (map[targetR][c] == 'O' || map[r][targetC] == 'O') {
                                return 0;
                            }
                        }
                    }

                    visited[r][c] = true;
                }
            }
        }

        return result;
    }

    public static boolean isOutOfMap(int r, int c) {
        return r < 0 || r > 4 || c < 0 || c > 4;
    }
}