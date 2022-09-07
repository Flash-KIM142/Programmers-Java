public class SocialDistance2 {
    static int[][] dir = {{-2,0}, {-1,-1}, {-1,0}, {-1,1}, {0,-2}, {0,-1}, {0,1}, {0,2}, {1,-1}, {1,0}, {1,1}, {2,0}}; // 총 12가지 가능한 방향

    static int[] solution(String[][] places) {
        int[] answer = new int[]{1,1,1,1,1};

        for(int rm=0; rm<5; rm++){
            answer[rm] = checkOneRm(places[rm]);
        }

        return answer;
    }

    static int checkOneRm(String[] place){
        int[][] info = new int[5][5];

        for(int i=0; i<5; i++){ // 방 하나 정보 받기
            String curRow = place[i]; // 한 줄
            for(int j=0; j<5; j++){
                char one = curRow.charAt(j);

                switch(one){
                    case 'P': // 사람 앉아있음
                        info[i][j] = 1;
                        break;

                    case 'O': // 빈 자리
                        info[i][j] = 0;
                        break;

                    case 'X': // 파티션
                        info[i][j] = 2;
                        break;
                }
            }
        }

        for(int r=0; r<5; r++){
            for(int c=0; c<5; c++){
                if(info[r][c]==1){ // 사람 있으면
                    for(int d=0; d<12; d++){
                        int nR = r + dir[d][0];
                        int nC = c + dir[d][1];

                        if(isInRange(nR, nC) && info[nR][nC]==1){ // 이동한 곳이 범위 안에 있으면 확인

                            switch(d){
                                case 2: case 5: case 6: case 9:
                                    return 0;

                                case 0: // 위로 두 칸
                                    if(info[r-1][c]!=2) return 0;
                                    break;

                                case 1: // 북서쪽
                                    if(info[r-1][c]!=2 || info[r][c-1]!=2)  return 0;
                                    break;

                                case 3: // 북동쪽
                                    if(info[r-1][c]!=2 || info[r][c+1]!=2)  return 0;
                                    break;

                                case 4: // 왼쪽 두 칸
                                    if(info[r][c-1]!=2) return 0;
                                    break;

                                case 7: // 오른쪽 두 칸
                                    if(info[r][c+1]!=2) return 0;
                                    break;

                                case 8: // 남서쪽
                                    if(info[r][c-1]!=2 || info[r+1][c]!=2)  return 0;
                                    break;

                                case 10: // 남동쪽
                                    if(info[r][c+1]!=2 || info[r+1][c]!=2)  return 0;
                                    break;

                                case 11: // 아래로 두칸
                                    if(info[r+1][c]!=2) return 0;
                                    break;
                            }
                        }
                    }
                }
            }
        }

        return 1;
    }

    static Boolean isInRange(int row, int col){
        if(row<0 || row>=5 || col<0 || col>=5)   return false;
        return true;
    }
}
