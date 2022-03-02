import java.io.*;
import java.util.ArrayList;
import java.util.Stack;
import java.util.StringTokenizer;

public class TableEditing {
    static ArrayList<Info> list = new ArrayList<>();

    static String solution(int n, int k, String[] cmd) {
        init(n);
        StringBuilder sb = new StringBuilder();
        Stack<Integer> deleted = new Stack<>();

        for(String thisCmd: cmd){
            StringTokenizer stk = new StringTokenizer(thisCmd);
            String order = stk.nextToken();
            switch (order) {
                case "C":  // 삭제
                    list.get(k).status = 0;
                    deleted.add(k);
                    if (list.get(k).prev != -1) list.get(list.get(k).prev).next = list.get(k).next;
                    if (list.get(k).next != -1) list.get(list.get(k).next).prev = list.get(k).prev;

                    k = (list.get(k).next == -1 ? list.get(k).prev : list.get(k).next); // 마지막 셀이면 그 전으로, 아니면 그 후로
                    break;
                case "Z":  // 복구
                    int restoredPos = deleted.pop();
                    list.get(restoredPos).status = 1;
                    if (list.get(restoredPos).prev != -1)
                        list.get(list.get(restoredPos).prev).next = restoredPos;
                    if (list.get(restoredPos).next != -1)
                        list.get(list.get(restoredPos).next).prev = restoredPos;
                    break;
                case "U": { // up
                    int distance = Integer.parseInt(stk.nextToken());
                    while (distance-- > 0)
                        k = list.get(k).prev;
                    break;
                }
                default: { // down
                    int distance = Integer.parseInt(stk.nextToken());
                    while (distance-- > 0)
                        k = list.get(k).next;
                    break;
                }
            }
        }

        for(int i=0; i<n; i++)
            sb.append((list.get(i).status==1 ? 'O' : 'X'));
        return sb.toString();
    }

    static void init(int n){
        for(int i=0; i<=n-2; i++)
            list.add(new Info(i-1, i+1, 1));
        list.add(new Info(n-2, -1, 1));
    }

    static class Info{
        int prev, next, status;

        Info(int prev, int next, int status){
            this.prev = prev;
            this.next = next;
            this.status = status;
        }
    }

    public static void main(String args[]) throws IOException {
        BufferedWriter bfw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = 8; int k = 2;
        String[] cmd = { "D 2", "C", "U 3", "C", "D 4", "C", "U 2", "Z", "Z", "U 1", "C" };

        String res = solution(n,k,cmd);
        bfw.write(res);
        bfw.close();
    }
}