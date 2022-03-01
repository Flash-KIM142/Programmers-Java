import java.util.PriorityQueue;
import java.io.*;

public class RaceTrack {
    static int[][][] visited; // 4 방향별 방문 정보
    static int[][] dir = { {-1,0}, {1,0}, {0,-1}, {0,1} }; // 상하좌우

    static int solution(int[][] board) {
        int n = board.length;
        int answer = 0;
        visited = new int[n][n][4];
        PriorityQueue<Info> q = new PriorityQueue<>();

        visited[0][0][0] = visited[0][0][1] = visited[0][0][2] = visited[0][0][3] = -1;
        if(board[0][1]==0) {
            q.add(new Info(0, 1, 3,1));
            visited[0][1][0] = 1;
        }
        if(board[1][0]==0) {
            q.add(new Info(1, 0, 1,1));
            visited[1][0][1] = 1;
        }

        while(!q.isEmpty()){
            Info cur = q.poll();
            if(cur.row==n-1 && cur.col==n-1){
                answer = cur.price;
                break;
            }

            for(int n_dir=0; n_dir<4; n_dir++){
                int n_row = cur.row + dir[n_dir][0];
                int n_col = cur.col + dir[n_dir][1];
                int n_price = cur.price + ((cur.dir == n_dir) ? 1 : 6);

                if(n_row<0 || n_row>=n || n_col<0 || n_col>=n || board[n_row][n_col]==1)  continue; // 범위 밖 OR 벽이면 패스
                if(visited[n_row][n_col][n_dir]!=0 && visited[n_row][n_col][n_dir] < n_price)    continue; // 이미 방문했으면 패스

                visited[n_row][n_col][n_dir] = n_price;
                q.add(new Info(n_row,n_col,n_dir,n_price));
            }
        }

        return answer*100;
    }

    static class Info implements Comparable<Info> {
        int row;
        int col;
        int dir;
        int price;

        Info(int row, int col, int dir, int price){
            this.row = row;
            this.col = col;
            this.dir = dir;
            this.price = price;
        }

        @Override
        public int compareTo(Info cmp){
            return this.price - cmp.price;
        }
    }

    public static void main(String args[]) throws IOException {
        BufferedWriter bfw = new BufferedWriter(new OutputStreamWriter(System.out));
        int[][] board = {{0,0,0,0,0,0,0,1},{0,0,0,0,0,0,0,0},{0,0,0,0,0,1,0,0},{0,0,0,0,1,0,0,0},{0,0,0,1,0,0,0,1},{0,0,1,0,0,0,1,0},{0,1,0,0,0,1,0,0},{1,0,0,0,0,0,0,0}};
        bfw.write(String.valueOf(solution(board)));
        bfw.close();
    }
}