import java.io.*;

public class SocialDistance {
    static int[][] move = { {0,-2}, {-2,0}, {0,2}, {2,0}, {0,-1}, {-1,-1}, {-1,0}, {-1,1}, {0,1}, {1,1}, {1,0}, {1,-1} };

    static int[] solution(String[][] places){
        int[] answer = new int[5];
        int i = 0;
        for(String[] place: places){
            answer[i++] = checkPlace(place);
        }
        return answer;
    }

    static Integer checkPlace(String[] place){
        int result = 1;
        boolean[][] visited = new boolean[5][5];

        for(int r=0; r<5; r++){
            for(int c=0; c<5; c++){
                char cur = place[r].charAt(c);

                if(cur!='P' || visited[r][c])   continue; // 응시자 아니거나 이미 방문한 응시자면 패

                for(int dir=0; dir<12; dir++){
                    int n_row = r + move[dir][0];
                    int n_col = c + move[dir][1];

                    if(n_row<0 || n_row>=5 || n_col<0 || n_col>=5)  continue; // 범위 밖이면 패스

                    if(place[n_row].charAt(n_col)=='P'){ // 맨해튼 거리 2 이하인 지점에 다른 응시자 있으면
                        switch(dir){
                            case 4: case 6: case 8: case 10: // 바로 붙어있는 곳에 응시자 있으면 바로 조건 탈락
                                return 0;

                            case 0:
                                if(place[r].charAt(c-1)=='O')   return 0;
                                break;
                            case 1:
                                if(place[r-1].charAt(c)=='O')   return 0;
                                break;
                            case 2:
                                if(place[r].charAt(c+1)=='O')   return 0;
                                break;
                            case 3:
                                if(place[r+1].charAt(c)=='O')   return 0;
                                break;
                            case 5:
                                if(place[r].charAt(c-1)=='O' || place[r-1].charAt(c)=='O')  return 0;
                                break;
                            case 7:
                                if(place[r-1].charAt(c)=='O' || place[r].charAt(c+1)=='O')  return 0;
                                break;
                            case 9:
                                if(place[r].charAt(c+1)=='O' || place[r+1].charAt(c)=='O')  return 0;
                                break;
                            case 11:
                                if(place[r+1].charAt(c)=='O' || place[r].charAt(c-1)=='O')  return 0;
                                break;
                        }
                    }
                    else    continue;
                }
                visited[r][c] = true;
            }
        }
        return result;
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
