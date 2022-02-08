import java.io.*;
import java.util.ArrayList;

public class PillarConstruction {
    static int[][] kidoong, bo;

    static int[][] solution(int n, int[][] build_frame) {
        kidoong = new int[n+1][n+1];
        bo = new int[n+1][n+1];

        for(int[] info: build_frame){
            switch(info[3]){ // 설치 or 삭제
                case 0: // 삭제
                    if(info[2]==0)
                        kidoong[info[0]][info[1]] = 0;
                        if(!isStillOkay(n))  kidoong[info[0]][info[1]] = 1;
                    if(info[2]==1)
                        bo[info[0]][info[1]] = 0;
                        if(!isStillOkay(n))  bo[info[0]][info[1]] = 1;
                    break;

                case 1: // 설치
                    if(info[2]==0)
                        if(checkKidoong(info[0],info[1]))   kidoong[info[0]][info[1]] = 1;
                    if(info[2]==1)
                        if(checkBo(info[0],info[1]))    bo[info[0]][info[1]] = 1;
                    break;
            }
        }

        ArrayList<int[]> list = new ArrayList<>();
        for(int i=0; i<=n; i++){
            for(int j=0; j<=n; j++){
                if(kidoong[i][j]==1){
                    int[] tmpKidoong = { i, j, 0 };
                    list.add(tmpKidoong);
                }
                if(bo[i][j]==1){
                    int[] tmpBo = { i, j, 1 };
                    list.add(tmpBo);
                }
            }
        }
        int[][] answer = list.toArray(new int[list.size()][3]);
        return answer;
    }

    static Boolean checkKidoong(int x, int y){
        if(y==0)    return true; // 바닥에 설치할 경우
        else if((x>0 && bo[x-1][y]==1) || bo[x][y]==1)   return true; // 그 자리에 보가 있을 경우
        else if(y>0 && kidoong[x][y-1]==1)  return true; // 아래 기둥이 있을 경우
        else    return false; // 싹 다 만족 못하면 무시
    }

    static Boolean checkBo(int x, int y){
        if(y>0 && (kidoong[x][y-1]==1 || kidoong[x+1][y-1]==1))    return true; // 한 쪽에 기둥이 있을 때
        else if(x>0 && bo[x-1][y]==1 && bo[x+1][y]==1) return true; // 양쪽에 보가 있을 때
        else return false; // 싹 다 만족 못하면 무시
    }

    static Boolean isStillOkay(int n){
        for(int i=0; i<=n; i++){
            for(int j=0; j<=n; j++){
                if(kidoong[i][j]==1 && !checkKidoong(i,j))   return false;
                else if(bo[i][j]==1 && !checkBo(i,j))   return false;
            }
        }
        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedWriter bfw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = 5;
        int[][] build_frame = { {1,0,0,1},{1,1,1,1},{2,1,0,1},{2,2,1,1},{5,0,0,1},{5,1,0,1},{4,2,1,1},{3,2,1,1} };
        int[][] result = solution(n, build_frame);
        for(int[] line: result) bfw.write(line[0] + " " + line[1] + " " + line[2] + "\n");
        bfw.close();
    }
}