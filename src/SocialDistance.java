import java.io.*;

public class SocialDistance {
    static char[][] cur_room = new char[5][5]; // 방 하나 정보 받을 배열
    static int[][] move = { {0,-2}, {-2,0}, {0,2}, {2,0}, {0,-1}, {-1,-1},
            {-1,0}, {-1,1}, {0,1}, {1,1}, {1,0}, {1,-1} }; // 맨해튼 거리가 1,2인 모든 움직임

    static int[] solution(String[][] places) {
        int[] answer = new int[5];

        for(int room_num=0; room_num<5; room_num++){
            for(int row=0; row<5; row++){
                cur_room[row] = places[room_num][row].toCharArray(); // 방 정보 받기
            }
            answer[room_num] = isDistanceAlright();
        }

        return answer;
    }

    static Integer isDistanceAlright(){
        boolean[][] visited = new boolean[5][5];

        for(int r=0; r<5; r++){
            for(int c=0; c<5; c++){
                if(cur_room[r][c]!='P') continue;

                for(int dir=0; dir<12; dir++){
                    int n_row = r + move[dir][0];   int n_col = c + move[dir][1];
                    if(!isInRagne(n_row,n_col)) continue; // 범위 밖이면 패스
                    if(cur_room[n_row][n_col]!='P') continue; // 사람 없으면 패스
                    if(visited[n_row][n_col])   continue;   // 이미 검사한 사람이면 패스
                    if(dir==4 || dir==6 || dir==8 || dir==10)   return 0; // 바로 옆에 사람 붙어있으면 규칙 위반

                    if(!isBlockedPath(r,c,dir)) return 0;
                }
                visited[r][c] = true;
            }
        }
        return 1;
    }

    static Boolean isBlockedPath(int r, int c, int dir){
        switch(dir){
            case 0:
                return (cur_room[r+move[4][0]][c+move[4][1]] == 'X');
            case 1:
                return (cur_room[r+move[6][0]][c+move[6][1]] == 'X');
            case 2:
                return (cur_room[r+move[8][0]][c+move[8][1]] == 'X');
            case 3:
                return (cur_room[r+move[10][0]][c+move[10][1]] == 'X');
            case 5:
                return ((cur_room[r+move[4][0]][c+move[4][1]] == 'X') && (cur_room[r+move[6][0]][c+move[6][1]] == 'X'));
            case 7:
                return ((cur_room[r+move[6][0]][c+move[6][1]] == 'X') && (cur_room[r+move[8][0]][c+move[8][1]] == 'X'));
            case 9:
                return ((cur_room[r+move[8][0]][c+move[8][1]] == 'X') && (cur_room[r+move[10][0]][c+move[10][1]] == 'X'));
            case 11:
                return ((cur_room[r+move[4][0]][c+move[4][1]] == 'X') && (cur_room[r+move[10][0]][c+move[10][1]] == 'X'));
        }
        return false;
    }

    static Boolean isInRagne(int row, int col){
        if(row<0 || row>=5 || col<0 || col>=5)  return false;
        return true;
    }

    public static void main(String args[]) throws IOException {
        BufferedWriter bfw = new BufferedWriter(new OutputStreamWriter(System.out));
        String[][] places = { {"POOOP", "OXXOX", "OPXPX", "OOXOX", "POXXP"},
                                {"POOPX", "OXPXP", "PXXXO", "OXXXO", "OOOPP"},
                                {"PXOPX", "OXOXP", "OXPOX", "OXXOP", "PXPOX"},
                                {"OOOXX", "XOOOX", "OOOXX", "OXOOX", "OOOOO"},
                                {"PXPXP", "XPXPX", "PXPXP", "XPXPX", "PXPXP"} };
        int[] answer = solution(places);
        for(int i: answer)
            bfw.write(i + " ");
        bfw.close();
    }
}
