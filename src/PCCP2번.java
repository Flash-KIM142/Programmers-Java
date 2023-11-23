// 2023.11.23 PCCP
// 처음에 모든 맵을 돌며 각 칸마다 연결된 영역 수 초기화해서 칼럼별로 돌며 r=0~rSize-1 더하기만 하면 됨

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class PCCP2번 {

    static int rSize, cSize;
    static int[][] connectionIdx;
    static int connIdx = 1;
    static Map<Integer, Integer> connectionInfo = new HashMap<>();

    public static int solution(int[][] land) {
        int answer = 0;
        rSize = land.length;
        cSize = land[0].length;
        connectionIdx = new int[rSize][cSize];

        initConnectedInfo(land);

        for (int c = 0; c < cSize; c++) {
            int tmpAmount = 0;
            Set<Integer> connectionIdxs = new HashSet<>();
            for (int r = 0; r < rSize; r++) {
                if (connectionIdx[r][c] == 0) {
                    continue;
                }
                connectionIdxs.add(connectionIdx[r][c]);
            }

            for (Integer idx : connectionIdxs) {
                tmpAmount += connectionInfo.get(idx);
            }

            answer = Math.max(answer, tmpAmount);
        }
        return answer;
    }

    static void initConnectedInfo(int[][] land) {
        for (int r = 0; r < rSize; r++) {
            for (int c = 0; c < cSize; c++) {
                boolean[][] visited = new boolean[rSize][cSize];
                if (connectionIdx[r][c] > 0) {
                    continue;
                }
                if (land[r][c] == 0) {
                    continue;
                }

                Set<Pos> visitedPos = new HashSet<>();
                visitedPos.add(new Pos(r, c));
                int connection = dfs(land, r, c, visited, visitedPos);
                connectionInfo.put(connIdx, connection);

                for (Pos p : visitedPos) {
                    connectionIdx[p.r][p.c] = connIdx;
                }
                connIdx++;
            }
        }
    }

    private static int dfs(int[][] grid, int r, int c, boolean[][] visited, Set<Pos> visitedPos) {
        if (r < 0 || r >= rSize || c < 0 || c >= cSize || grid[r][c] == 0 || visited[r][c]) {
            return 0;
        }
        visited[r][c] = true;
        visitedPos.add(new Pos(r, c));

        int count = 1;
        count += dfs(grid, r - 1, c, visited, visitedPos); // Up
        count += dfs(grid, r + 1, c, visited, visitedPos); // Down
        count += dfs(grid, r, c - 1, visited, visitedPos); // Left
        count += dfs(grid, r, c + 1, visited, visitedPos); // Right

        return count;
    }

    static class Pos {
        int r;
        int c;

        Pos(int r, int c) {
            this.r = r;
            this.c = c;
        }

        @Override
        public boolean equals(Object o) {
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            if (this == o) {
                return true;
            }

            Pos cmp = (Pos) o;
            return r == cmp.r && c == cmp.c;
        }

        @Override
        public int hashCode() {
            return Objects.hash(r, c);
        }
    }

    public static void main(String[] args) {
        int result = solution(new int[][]{{0, 0, 0, 1, 1, 1, 0, 0}, {0, 0, 0, 0, 1, 1, 0, 0}, {1, 1, 0, 0, 0, 1, 1, 0},
                {1, 1, 1, 0, 0, 0, 0, 0}, {1, 1, 1, 0, 0, 0, 1, 1}});
        System.out.println(result);
    }
}