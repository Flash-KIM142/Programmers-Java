public class MatrixRotation {
    static int result = Integer.MAX_VALUE;

    static int[] solution(int rows, int columns, int[][] queries) {
        int[] answer = new int[queries.length];
        int[][] next = new int[rows][columns];
        int value = 1;
        for(int r=0; r<rows; r++)
            for(int c=0; c<columns; c++)
                next[r][c] = value++;

        for(int i=0; i<queries.length; i++) {
            next = rotate(queries[i], next);
            answer[i] = result;
            result = Integer.MAX_VALUE;
        }
        return answer;
    }

    static int[][] rotate(int[] query, int[][] next){
        int r1 = query[0]-1;    int c1 = query[1]-1;
        int r2 = query[2]-1;    int c2 = query[3]-1;

        int[] part1 = new int[c2-c1];   int[] part2 = new int[r2-r1];
        int[] part3 = new int[c2-c1];   int[] part4 = new int[r2-r1];

        /** 네 파트 값 저장해두기 */
        int i=0;
        for(int c=c1; c<=c2-1; c++) // part1 저장
            part1[i++] = next[r1][c];

        i=0;
        for(int r=r1; r<=r2-1; r++) // part2 저장
            part2[i++] = next[r][c2];

        i=0;
        for(int c=c2; c>=c1+1; c--) // part3 저장
            part3[i++] = next[r2][c];

        i=0;
        for(int r=r2; r>=r1+1; r--) // part4 저장
            part4[i++] = next[r][c1];

        /** 여기부터 새로운 값 채워 넣기 */
        i=0;
        for(int c=c1+1; c<=c2; c++){ // part1 한 칸 밀어주기
            result = Math.min(result, part1[i]);
            next[r1][c] = part1[i++];
        }

        i=0;
        for(int r=r1+1; r<=r2; r++){ // part2 한 칸 밀어주기
            result = Math.min(result, part2[i]);
            next[r][c2] = part2[i++];
        }

        i=0;
        for(int c=c2-1; c>=c1; c--){ // part3 한 칸 밀어주기
            result = Math.min(result, part3[i]);
            next[r2][c] = part3[i++];
        }

        i=0;
        for(int r=r2-1; r>=r1; r--){ // part4 한 칸 밀어주기
            result = Math.min(result, part4[i]);
            next[r][c1] = part4[i++];
        }

        return next;
    }

    public static void main(String[] args) {
        int rows = 6;
        int columns = 6;
        int[][] queries = {{2,2,5,4},{3,3,6,6},{5,1,6,3}};
        int[] answer = solution(rows,columns,queries);
        for(int a: answer) System.out.println(a);
    }
}