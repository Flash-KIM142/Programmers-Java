import java.io.*;
import java.util.*;

public class StringCompress {
    static ArrayList<Integer> list = new ArrayList<>();

    static int solution(String s){
        if(s.length()==1)   return 1;
        for(int i=1; i<=s.length()/2; i++){
            compress(i, s);
        }
        Collections.sort(list);
        return list.get(0);
    }

    static void compress(int n, String s){
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
                else    sb.append(i).append(prev);
                i = 1;
            }
            prev = cur;
            if(q.size()==0){
                if(i==1)    sb.append(cur);
                else    sb.append(i).append(cur);
            }
        }
        list.add(sb.toString().length());
    }

    public static void main(String[] args) throws IOException {
        BufferedWriter bfw = new BufferedWriter(new OutputStreamWriter(System.out));
        String s = "a";
        int ans = solution(s);
        bfw.write(ans + "");
        bfw.close();
    }
}