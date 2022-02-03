import java.io.*;
import java.util.LinkedList;
import java.util.Queue;

public class ColoringBook {
    static boolean[][] visited;
    static int[][] move = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    static Queue<int[]> q = new LinkedList<>();

    static int[] solution(int m, int n, int[][] picture) {
        int[] answer = new int[2];
        int numberOfArea = 0;
        int maxSizeOfOneArea = 0;
        visited = new boolean[m][n];

        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                if (picture[r][c] == 0 || visited[r][c]) continue; // 0이면 OR 방문했으면 패스
                int curSize = getAreaSize(r, c, m, n, picture);
                maxSizeOfOneArea = (maxSizeOfOneArea < curSize ? curSize : maxSizeOfOneArea);
                numberOfArea++;
            }
        }

        answer[0] = numberOfArea;
        answer[1] = maxSizeOfOneArea;
        return answer;
    }

    static Integer getAreaSize(int row, int col, int m, int n, int[][] picture) {
        int size = 0;
        int curNum = picture[row][col];
        q.add(new int[]{row, col});
        visited[row][col] = true;
        size++;

        while (!q.isEmpty()) {
            int[] curPos = q.poll();
            int curRow = curPos[0];
            int curCol = curPos[1];

            for (int dir = 0; dir < 4; dir++) {
                int newRow = curRow + move[dir][0];
                int newCol = curCol + move[dir][1];
                if (!isInRange(newRow, newCol, m, n) || visited[newRow][newCol]) continue; // 범위 밖 OR 이미 방문이면 넘기자
                if (picture[newRow][newCol] != curNum) continue; // 다른 색깔이면 넘기자

                q.add(new int[]{newRow, newCol});
                visited[newRow][newCol] = true;
                size++;
            }
        }

        return size;
    }

    static Boolean isInRange(int row, int col, int m, int n) {
        if (row < 0 || row >= m || col < 0 || col >= n) return false;
        return true;
    }

    public static void main(String args[]) throws IOException {
        BufferedWriter bfw = new BufferedWriter(new OutputStreamWriter(System.out));
        int m = 6;
        int n = 4;
        int[][] picture = {{1, 1, 1, 0}, {1, 1, 1, 0}, {0, 0, 0, 1}, {0, 0, 0, 1}, {0, 0, 0, 1}, {0, 0, 0, 1}};
        int[] answer = solution(m, n, picture);
        bfw.write(answer[0] + " " + answer[1]);
        bfw.close();
    }
}