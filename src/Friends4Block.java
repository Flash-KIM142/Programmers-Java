import java.util.Stack;

public class Friends4Block {
    static int[][] move = { {0,1}, {1,0}, {1,1} }; // 자신,동,남,남동
    static char[][] boardAry;
    static int cnt = 0;

    static int solution(int m, int n, String[] board) {
        boardAry = new char[m][n];
        for(int i=0; i<m; i++)
            boardAry[i] = board[i].toCharArray();

        while(checkAndDelete(m,n))
            fallDown(m,n);

        int answer = cnt;
        return answer;
    }

    static Boolean checkAndDelete(int row, int col){
        boolean[][] visited = new boolean[row][col];
        boolean flag = false;

        for(int r=0; r<row-1; r++){
            for(int c=0; c<col-1; c++){
                char prev = boardAry[r][c];
                if(prev=='0')   continue; // 만약 빈 곳이면 넘겨
                int check = 0;

                for(int dir=0; dir<3; dir++){ // 나머지 세 칸 확인
                    char next = boardAry[r+move[dir][0]][c+move[dir][1]];
                    if(next=='0')   break;
                    if(prev!=next)  break;
                    check++;
                }
                if(check==3){ // 만약 네 칸 모두 동일한 모양이면 방문 정보 갱신
                    flag = true; // fallDown 실행해야 할지 결정해줄 변수
                    visited[r][c] = true;
                    for(int dir=0; dir<3; dir++)
                        visited[r+move[dir][0]][c+move[dir][1]] = true;
                }
            }
        }

        for(int r=0; r<row; r++)
            for(int c=0; c<col; c++)
                if(visited[r][c]) {
                    boardAry[r][c] = '0';
                    cnt++;
                }

        return flag;
    }

    static void fallDown(int row, int col){ // 삭제 후 떨구는 메소드
        Stack<Character> stack = new Stack<>();

        for(int c=0; c<col; c++){
            for(int r=row-1; r>=0; r--){
                if(boardAry[r][c]!='0') stack.add(boardAry[r][c]);
            }
            for(int r=0; r<row; r++){
                if(r<row-stack.size())  boardAry[r][c] = '0';
                else boardAry[r][c] = stack.pop();
            }
        }
    }

    public static void main(String[] args) {
        int m = 6;
        int n = 6;
        String[] board = { "TTTANT", "RRFACC", "RRRFCC", "TRRRAA", "TTMMMF", "TMMTTJ" };
        int answer = solution(m,n,board);
        System.out.println(answer);
    }
}