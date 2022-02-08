import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.StringTokenizer;

public class ReportResult {
    static HashMap<String, Integer> map = new HashMap<>();
    static ArrayList< HashSet<Integer> > list = new ArrayList<>();

    static int[] solution(String[] id_list, String[] report, int k) {
        int[] answer = new int[id_list.length];
        int[] howManyReported = new int[id_list.length];
        for(int i=0; i<id_list.length; i++) {
            list.add(new HashSet<>());
            map.put(id_list[i], i);
        }

        for(String rep: report){
            StringTokenizer stk = new StringTokenizer(rep);
            int whoReports = map.get(stk.nextToken());
            int whoGotReported = map.get(stk.nextToken());
            list.get(whoReports).add(whoGotReported);
        }

        for(int i=0; i< id_list.length; i++){
            HashSet<Integer> cur = list.get(i);
            for(int target: cur)
                howManyReported[target]++;
        }

        for(int i=0; i<id_list.length; i++){
            HashSet<Integer> cur = list.get(i);
            int cnt = 0;
            for(int target: cur)
                if(howManyReported[target]>=k)  cnt++;
            answer[i] = cnt;
        }

        return answer;
    }

    public static void main(String[] args) throws IOException {
        BufferedWriter bfw = new BufferedWriter(new OutputStreamWriter(System.out));
        String[] id_list = {"con", "ryan"};
        String[] report = {"ryan con", "ryan con", "ryan con", "ryan con"};
        int k = 3;
        int[] result = solution(id_list, report, k);
        for(int a: result)  bfw.write(a + " ");
        bfw.close();
    }
}