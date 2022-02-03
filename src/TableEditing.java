import java.io.*;
import java.util.Stack;
import java.util.StringTokenizer;

public class TableEditing {
    static Stack<int[]> stack = new Stack<>();
    static int curPos;
    static int[] prevPos, nextPos;
    static StringBuilder sb = new StringBuilder();

    static String solution(int n, int k, String[] cmd) {
        init(n, k);
        operateOrder(cmd);

        return sb.toString();
    }

    static void operateOrder(String[] cmd){
        for(String order: cmd){
            StringTokenizer stk = new StringTokenizer(order);
            int howFar = 0;
            switch(stk.nextToken()){
                case "C":
                    stack.add(new int[]{prevPos[curPos], curPos, nextPos[curPos]});
                    if(prevPos[curPos]!=-1) nextPos[prevPos[curPos]] = nextPos[curPos];
                    if(nextPos[curPos]!=-1) prevPos[nextPos[curPos]] = prevPos[curPos];

                    sb.setCharAt(curPos, 'X');
                    if(nextPos[curPos]!=-1) curPos = nextPos[curPos];
                    else    curPos = prevPos[curPos]; // 맨 밑 칸이면 위로 올리기
                    break;

                case "Z":
                    int[] restore = stack.pop();
                    if(restore[0]!=-1)  nextPos[restore[0]] = restore[1];
                    if(restore[2]!=-1)  prevPos[restore[2]] = restore[1];
                    sb.setCharAt(restore[1], 'O');
                    break;

                case "U":
                    howFar = Integer.parseInt(stk.nextToken());
                    while(howFar-- > 0) curPos = prevPos[curPos];
                    break;

                case "D":
                    howFar = Integer.parseInt(stk.nextToken());
                    while(howFar-- > 0) curPos = nextPos[curPos];
                    break;
            }
        }
    }

    static void init(int n, int k){
        curPos = k;

        prevPos = new int[n];   nextPos = new int[n];
        for(int i=0; i<n; i++){
            prevPos[i] = i-1;
            nextPos[i] = i+1;
            sb.append('O');
        }
        nextPos[n-1] = -1;
    }

    public static void main(String args[]) throws IOException {
        BufferedWriter bfw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = 8; int k = 2;
        String[] cmd = { "D 2", "C", "U 3", "C", "D 4", "C", "U 2", "Z", "Z", "U 1", "C" };

        bfw.write(solution(n,k,cmd));
        bfw.close();
    }
}