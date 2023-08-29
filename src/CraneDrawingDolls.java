import java.util.Stack;

public class CraneDrawingDolls {
    static Stack<Integer> stk = new Stack<>();

    public static void main(String[] args) {
        int[][] board = new int[][]{{0, 0, 0, 0, 0}, {0, 0, 1, 0, 3}, {0, 2, 5, 0, 1}, {4, 2, 4, 4, 2}, {3, 5, 1, 3, 1}};
        int[] moves = new int[]{1,5,3,5,1,2,1,4};

        System.out.println(solution(board, moves));
    }

    static int solution(int[][] board, int[] moves) {
        int answer = 0;
        int size = board[0].length;

        for (int col : moves) {
            for(int row = 0; row<size; row++){
                if(board[row][col-1]==0)    continue;

                if(!stk.isEmpty()){
                    int top = stk.peek();
                    if(top==board[row][col-1]){
                        stk.pop();
                        answer+=2;
                    }
                    else{
                        stk.add(board[row][col - 1]);
                    }
                }
                else{
                    stk.add(board[row][col-1]);
                }
                board[row][col-1] = 0;
                break;
            }
        }

        return answer;
    }




}
