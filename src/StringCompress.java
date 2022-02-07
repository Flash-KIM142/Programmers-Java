import java.io.*;
import java.util.LinkedList;
import java.util.Queue;

public class StringCompress {
    static int solution(String s) {
        int answer = s.length();
        int len = s.length();
        
        for (int i = 1; i <= len / 2; i++) {
            int res = compressor(i, s);
            answer = (res < answer ? res : answer);
        }
        return answer;
    }

    static Integer compressor(int n, String s) {
        StringBuilder sb = new StringBuilder();
        Queue<String> q = new LinkedList<>();

        int i=0;
        while(i<s.length()){
            if(i+n<=s.length()) q.add(s.substring(i, i+n));
            else    q.add(s.substring(i));
            i += n;
        }

        i=1;
        String prev = q.poll();
        while(!q.isEmpty()){
            String cur = q.poll();
            if(cur.equals(prev)) i++;
            else{
                if(i==1)    sb.append(prev);
                else    sb.append(i+prev);
                i = 1;
            }
            prev = cur;
            if(q.size()==0){
                if(i==1)    sb.append(cur);
                else    sb.append(i+cur);
            }
        }

        return sb.toString().length();
    }

    public static void main(String[] args) throws IOException {
        BufferedWriter bfw = new BufferedWriter(new OutputStreamWriter(System.out));
        String s = "abcabcabcabcdededededede";
        int ans = solution(s);
        bfw.write(ans + "");
        bfw.close();
    }
}