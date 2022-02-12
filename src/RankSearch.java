import java.util.*;

public class RankSearch {
    static HashMap<String, ArrayList<Integer>> applyMap = new HashMap<>();

    static int[] solution(String[] info, String[] query) {
        int[] answer = new int[query.length];
        for(String a: info){ // 가능한 모든 조합 만들기
            String[] apply = a.split(" ");
            combination(apply, "", 0);
        }

        for(String key: applyMap.keySet()) // 가능한 조합들 각각의 점수 리스트를 오름차순 정렬하자
            Collections.sort(applyMap.get(key));

        int idx = 0;
        for(String q: query){ // 쿼리 배열 순회하며 가능한 조합들 applyMap에서 찾아주기
            q = q.replaceAll(" and ", "");
            String[] hire = q.split(" ");
            int score = Integer.parseInt(hire[1]);
            answer[idx++] = applyMap.containsKey(hire[0]) ? binarySearch(hire[0], score) : 0;
        }

        return answer;
    }

    static void combination(String[] apply, String aCase, int cnt){
        if(cnt==4){
            if(!applyMap.containsKey(aCase)){
                ArrayList<Integer> tmp = new ArrayList<>();
                applyMap.put(aCase, tmp);
            }
            applyMap.get(aCase).add(Integer.parseInt(apply[4]));
            return;
        }
        combination(apply, aCase + "-", cnt+1);
        combination(apply, aCase + apply[cnt], cnt+1);
    }

    static Integer binarySearch(String s, int score){
        ArrayList<Integer> cases = applyMap.get(s);
        int left = 0; int right = cases.size()-1;

        while(left<=right){
            int mid = (left+right)/2;

            if(cases.get(mid)<score)    left = mid + 1;
            else    right = mid - 1;
        }
        return cases.size() - left;
    }

    public static void main(String[] args) {
        String[] info = {"java backend junior pizza 150","python frontend senior chicken 210","python frontend senior chicken 150","cpp backend senior pizza 260","java backend junior chicken 80","python backend senior chicken 50"};
        String[] query = {"java and backend and junior and pizza 100","python and frontend and senior and chicken 200","cpp and - and senior and pizza 250","- and backend and senior and - 150","- and - and - and chicken 100","- and - and - and - 150"};
        int[] res = solution(info, query);
        for(int a: res) System.out.println(a);
    }
}