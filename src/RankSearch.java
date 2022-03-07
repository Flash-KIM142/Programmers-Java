import java.util.*;

public class RankSearch {
    static HashMap<String, ArrayList<Integer>> hm = new HashMap<>();

    static int[] solution(String[] info, String[] query){
        for (String value : info) {
            String[] split = value.split(" ");
            combination(split, "", 0);
        }

        int[] answer = new int[query.length];
        for(String key: hm.keySet())
            Collections.sort(hm.get(key));

        int idx = 0;
        for(String value: query){
            value = value.replaceAll(" and ", "");
            String[] split = value.split(" ");
            int score = Integer.parseInt(split[1]);
            answer[idx++] = hm.containsKey(split[0]) ? binarySearch(split[0], score) : 0;
        }
        return answer;
    }

    static Integer binarySearch(String query, int score){
        ArrayList<Integer> target = hm.get(query);
        int left = 0; int right = target.size() - 1;

        while(left<=right){
            int mid = (left+right)/2;
            if(target.get(mid)<score)   left = mid + 1;
            else    right = mid - 1;
        }
        return target.size() - left;
    }

    static void combination(String[] infos, String oneCase, int cnt){
        if(cnt==4){
            if(!hm.containsKey(oneCase)){
                ArrayList<Integer> scores = new ArrayList<>();
                hm.put(oneCase, scores);
            }
            hm.get(oneCase).add(Integer.parseInt(infos[4]));
            return;
        }
        combination(infos, oneCase + "-", cnt+1);
        combination(infos, oneCase + infos[cnt], cnt+1);
    }

    public static void main(String[] args) {
        String[] info = {"java backend junior pizza 150","python frontend senior chicken 210","python frontend senior chicken 150","cpp backend senior pizza 260","java backend junior chicken 80","python backend senior chicken 50"};
        String[] query = {"java and backend and junior and pizza 100","python and frontend and senior and chicken 200","cpp and - and senior and pizza 250","- and backend and senior and - 150","- and - and - and chicken 100","- and - and - and - 150"};
        int[] res = solution(info, query);
        for(int a: res) System.out.println(a);
    }
}