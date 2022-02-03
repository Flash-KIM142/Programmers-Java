import java.io.*;
import java.util.HashMap;
import java.util.Arrays;
import java.util.Queue;
import java.util.HashSet;
import java.util.LinkedList;

public class Jewelry {
    static int[] solution(String[] gems) {
        HashSet<String> kinds = new HashSet<>(Arrays.asList(gems));
        HashMap<String, Integer> hm = new HashMap<>();
        Queue<String> q = new LinkedList<>();

        if(kinds.size()==1) return new int[]{1,1};

        int start=0, tmp_start=0;
        int min_distance = Integer.MAX_VALUE;

        for(String gem: gems){
            q.add(gem);
            hm.put(gem, hm.getOrDefault(gem,0)+1);

            while(true){
                String tmp = q.peek();
                if(hm.get(tmp)>1){
                    hm.put(tmp, hm.get(tmp)-1);
                    q.poll();
                    tmp_start++;
                }
                else    break;
            }

            if(hm.size()==kinds.size() && min_distance>q.size()){
                min_distance = q.size();
                start = tmp_start;
            }
        }

        return new int[]{start+1, start+min_distance};
    }

    public static void main(String args[]) throws IOException {
        BufferedWriter bfw = new BufferedWriter(new OutputStreamWriter(System.out));
        String[] gems = {"AA", "AB", "AC", "AA", "AC"};
        int[] answer = solution(gems);
        bfw.write(answer[0] + " " +answer[1]);
        bfw.close();
    }
}