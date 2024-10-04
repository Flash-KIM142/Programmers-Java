import java.util.*;

public class 순위검색2 {

    static String[][] resume_options = {{"cpp", "java", "python"}, {"backend", "frontend"}, {"junior", "senior"}, {"chicken", "pizza"}};

    public static void main(String[] args) {
        String[] info = {"java backend junior pizza 150", "python frontend senior chicken 210", "python frontend senior chicken 150", "cpp backend senior pizza 260", "cpp backend senior pizza 260",  "cpp backend senior pizza 260", "java backend junior chicken 80", "python backend senior chicken 50"};
        String[] query = {"- and backend and senior and - 150",};
        int[] res = solution(info, query);
        for(int n: res) {
            System.out.println(n);
        }
    }

    static int[] solution(String[] info, String[] query) {
        int[] answer = new int[query.length];

        /* 지원자 정보 map 형태로 바꾸기 */
        Map<String, List<Integer>> applicant_map = new HashMap<>();
        for(String appl: info) {
            String[] split = appl.split(" ");
            String resume = split[0] + split[1] + split[2] + split[3];
            Integer score = Integer.parseInt(split[4]);
            if(!applicant_map.containsKey(resume)) {
                applicant_map.put(resume, new ArrayList<>());
            }
            applicant_map.get(resume).add(score);
        }

        /* 지원자 정보 조건별 코테 점수 이분 탐색을 위한 sort */
        for(String key: applicant_map.keySet()) {
            Collections.sort(applicant_map.get(key));
        }

        /* query 하나씩 돌며 해당하는 지원자수 구하기 */
        for(int i=0; i<query.length; i++) {
            String cur_query = query[i];
            cur_query = cur_query.replace(" and ", " ");
            String[] split = cur_query.split(" ");

            /* 최종적으로 현재 query에서 가능한 모든 resume 큐에 넣기 */
            Queue<String> q = new LinkedList<>();
            q.add("");
            for(int j=0; j<4; j++) {
                int cur_q_size = q.size();
                for(int k=0; k<cur_q_size; k++) {
                    String cur_resume = q.poll();
                    if(split[j].equals("-")) {
                        for(int l=0; l<resume_options[j].length; l++) {
                            String next_resume = cur_resume + resume_options[j][l];
                            q.add(next_resume);
                        }
                    }
                    else {
                        q.add(cur_resume + split[j]);
                    }
                }
            }

            /* 큐에 저장된 가능한 모든 resume를 만족하는 지원자 찾기 */
            Integer cur_score = Integer.parseInt(split[4]);
            int cnt = 0;
            while(!q.isEmpty()) {
                String key = q.poll();
                if(applicant_map.containsKey(key)) {
                    cnt += binary_search(applicant_map.get(key), cur_score);
                }
            }
            answer[i] = cnt;
        }

        return answer;
    }

    static int binary_search(List<Integer> list, int target) {
        int l = 0;
        int r = list.size() - 1;

        while(l <= r) {
            int mid = (l + r) / 2;
            if(list.get(mid) < target) {
                l = mid + 1;
            }
            else if(list.get(mid) > target) {
                r = mid - 1;
            }
            else {
                return list.size() - mid;
            }
        }

        return list.size() - l;
    }
}
