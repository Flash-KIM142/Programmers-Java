import java.util.LinkedList;
import java.util.Queue;

public class MovingBlocks {
    static int N;
    static int[][] move = { {1,0}, {-1,0}, {0,1}, {0,-1} };
    static int[][] map;

    static int solution(int[][] board) {
        N = board.length;
        map = new int[N][N];
        for(int i=0; i<N; i++)  map[i] = board[i].clone();

        int answer = BFS();
        return answer;
    }

    static Integer BFS(){
        boolean[][][] visited = new boolean[2][N][N];
        Queue<Info> q = new LinkedList<>();
        q.offer(new Info(0,0, 0,1, 0, 0));
        visited[0][0][0] = true;
        visited[0][0][1] = true;

        while(!q.isEmpty()){
            Info cur = q.poll();
            if( (cur.r1==N-1 && cur.c1==N-1) || (cur.r2==N-1 && cur.c2==N-1) )  return cur.cnt;

            for(int dir=0; dir<4; dir++){
                int next_r1 = cur.r1 + move[dir][0];  int next_c1 = cur.c1 + move[dir][1];
                int next_r2 = cur.r2 + move[dir][0];  int next_c2 = cur.c2 + move[dir][1];
                if(isValid(next_r1,next_c1,next_r2,next_c2)){ // 범위 안에 있고 벽이 아닌지 확인
                    if (!visited[cur.type][next_r1][next_c1] || !visited[cur.type][next_r2][next_c2]) {
                        visited[cur.type][next_r1][next_c1] = true;
                        visited[cur.type][next_r2][next_c2] = true;
                        q.offer(new Info(next_r1,next_c1, next_r2,next_c2, cur.type, cur.cnt+1));
                    }

                    int change_type = (cur.type==0 ? 1 : 0); // 회전 시키자
                    if( (cur.type==0 && dir<2) || (cur.type==1 && dir>1) ){ // 가로로 있을 때는 세로 방향으로 회전, 세로로 있을 때는 가로로 회전
                        if(!visited[change_type][next_r1][next_c1] || !visited[change_type][cur.r1][cur.c1]) {
                            visited[change_type][next_r1][next_c1] = true;
                            visited[change_type][cur.r1][cur.c1] = true;
                            q.offer(new Info(next_r1,next_c1, cur.r1,cur.c1, change_type, cur.cnt+1));
                        }

                        if(!visited[change_type][next_r2][next_c2] || !visited[change_type][cur.r2][cur.c2]) {
                            visited[change_type][next_r2][next_c2] = true;
                            visited[change_type][cur.r2][cur.c2] = true;
                            q.offer(new Info(next_r2,next_c2, cur.r2,cur.c2, change_type, cur.cnt+1));
                        }
                    }
                }
            }
        }
        return 0;
    }

    static Boolean isValid(int r1, int c1, int r2, int c2){
        if(r1<0 || r1>=N || c1<0 || c1>=N || r2<0 || r2>=N || c2<0 || c2>=N)    return false;
        if(map[r1][c1]==1 || map[r2][c2]==1)    return false;
        return true;
    }

    static class Info{
        int r1; int c1;
        int r2; int c2;
        int type;
        int cnt;

        Info(int r1, int c1, int r2, int c2, int type, int cnt){
            this.r1 = r1;   this.c1 = c1;
            this.r2 = r2;   this.c2 = c2;
            this.type = type;
            this.cnt = cnt;
        }
    }

    public static void main(String[] args) {
        int[][] board = {{0, 0, 0, 0, 0, 0, 1}, {1, 1, 1, 1, 0, 0, 1}, {0, 0, 0, 0, 0, 0, 0}, {0, 0, 1, 1, 1, 0, 0}, {0, 1, 1, 1, 1, 1, 0}, {0, 0, 0, 0, 0, 1, 0}, {0, 0, 1, 0, 0, 0, 0}};
        int res = solution(board);
        System.out.println(res);
    }
}