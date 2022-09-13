import java.util.*;

public class ReportResult2 {
    static HashMap<String, Integer> reportedNum = new HashMap<>();
    static HashMap<String, Set<String>> iReported = new HashMap<>();

    static int[] solution(String[] id_list, String[] report, int k) {
        int[] answer = new int[id_list.length];
        init(id_list);
        parsingReport(report);

        int idx = 0;
        for (String me : id_list) {
            Set<String> you = iReported.get(me);
            int n = 0;

            for (String s : you)
                if (reportedNum.get(s) >= k) n++;

            answer[idx++] = n;
        }

        return answer;
    }

    static void parsingReport(String[] report){
        for(String r: report){
            String[] split = r.split(" ");
            String me = split[0];
            String you = split[1];

            if(!iReported.get(me).contains(you)){
                reportedNum.replace(you, reportedNum.get(you)+1);
            }

            iReported.get(me).add(you);
        }
    }

    static void init(String[] id_list){
        for(String id: id_list){
            reportedNum.put(id, 0);

            Set<String> tmp = new HashSet<>();
            iReported.put(id, tmp);
        }
    }
}
