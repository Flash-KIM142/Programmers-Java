import java.util.LinkedList;
import java.util.Queue;

class ColoringBook2 {
    static int[] dirRow = { -1, 1, 0, 0};
    static int[] dirCol = { 0, 0, -1, 1};
    static boolean[][] visited;
    static Queue<Point> q = new LinkedList<>();

    public static void main(String[] args) {
        int[][] picture = {{1, 1, 1, 0}, {1, 2, 2, 0}, {1, 0, 0, 1}, {0, 0, 0, 1}, {0, 0, 0, 3}, {0, 0, 0, 3}};

        int[] result = solution(6, 4, picture);
        for (int i : result) {
            System.out.println("i = " + i);
        }
    }

    static int[] solution(int m, int n, int[][] picture) {
        int numberOfArea = 0;
        int maxSizeOfOneArea = 0;
        visited = new boolean[m][n];

        for(int r = 0; r<m; r++){
            for(int c = 0; c<n; c++){
                if(picture[r][c]==0 || visited[r][c])   continue;
                Point p = new Point(r,c);
                int curAreaSize = getAreaSize(p, m, n, picture);
                maxSizeOfOneArea = Math.max(maxSizeOfOneArea, curAreaSize);
                numberOfArea++;
            }
        }

        int[] answer = new int[2];
        answer[0] = numberOfArea;
        answer[1] = maxSizeOfOneArea;
        return answer;
    }

    static int getAreaSize(Point p, int m, int n, int[][] picture){
        int size = 1;
        int curClr = picture[p.row][p.col];
        q.add(p);
        visited[p.row][p.col] = true;

        while(!q.isEmpty()){
            Point cur = q.poll();

            for(int d = 0; d<4; d++){
                int nRow = cur.row + dirRow[d];
                int nCol = cur.col + dirCol[d];
                Point nxt = new Point(nRow, nCol);

                if(!isInRange(nRow, nCol, m, n) || visited[nRow][nCol]) continue;
                if(picture[nRow][nCol] != curClr)   continue;

                q.add(nxt);
                visited[nRow][nCol] = true;
                size++;
            }
        }

        return size;
    }

    static boolean isInRange(int row, int col, int m, int n){
        return row >= 0 && row < m && col >= 0 && col < n;
    }

    static class Point{
        int row;
        int col;

        public Point(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }
}
