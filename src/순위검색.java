// 2023.11.22 2021 카카오 블라인드, 소요시간: 57분

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class 순위검색 {

    static Map<String, List<Integer>> applierInfo = new HashMap<>();
    static int total = 0;

    public static int[] solution(String[] info, String[] query) {
        /* 지원자 정보 초기화 */
        for (String apply : info) {
            String[] split = apply.split(" ");
            String key = split[0] + split[1] + split[2] + split[3];
            Integer codingPoint = Integer.parseInt(split[4]);

            applierInfo.putIfAbsent(key, new ArrayList<>());
            List<Integer> value = applierInfo.get(key);
            value.add(codingPoint);
            applierInfo.put(key, value);
        }
        /* 각 지원자 정보별 코딩 점수 정렬 */
        for (String key : applierInfo.keySet()) {
            Collections.sort(applierInfo.get(key));
        }

        int[] answer = new int[query.length];
        for (int i = 0; i < query.length; i++) {
            String q = query[i];
            total = 0;
            q = q.replace(" and ", " ");
            String[] conditions = q.split(" ");

            combination(conditions, 0, "");

            answer[i] = total;
        }

        return answer;
    }

    static void combination(String[] conditions, int cnt, String cur) {
        if (cnt == 4) {
            int targetPoint = Integer.parseInt(conditions[4]);
            if (!applierInfo.containsKey(cur)) {
                return;
            }
            List<Integer> candidates = applierInfo.get(cur);

            /* lower bound */
            int l = 0;
            int r = candidates.size();
            while (l < r) {
                int mid = (l + r) / 2;

                if (targetPoint > candidates.get(mid)) {
                    l = mid + 1;
                } else {
                    r = mid;
                }
            }
            total += candidates.size() - l;
            return;
        }

        if (cnt == 0) {
            if (conditions[cnt].equals("-")) {
                combination(conditions, 1, cur + "cpp");
                combination(conditions, 1, cur + "java");
                combination(conditions, 1, cur + "python");
            } else {
                combination(conditions, 1, cur + conditions[cnt]);
            }
        } else if (cnt == 1) {
            if (conditions[cnt].equals("-")) {
                combination(conditions, 2, cur + "frontend");
                combination(conditions, 2, cur + "backend");
            } else {
                combination(conditions, 2, cur + conditions[cnt]);
            }
        } else if (cnt == 2) {
            if (conditions[cnt].equals("-")) {
                combination(conditions, 3, cur + "junior");
                combination(conditions, 3, cur + "senior");
            } else {
                combination(conditions, 3, cur + conditions[cnt]);
            }
        } else if (cnt == 3) {
            if (conditions[cnt].equals("-")) {
                combination(conditions, 4, cur + "chicken");
                combination(conditions, 4, cur + "pizza");
            } else {
                combination(conditions, 4, cur + conditions[cnt]);
            }
        }
    }

    public static void main(String[] args) {
        String[] info = new String[]{"java backend junior pizza 150", "python frontend senior chicken 210",
                "python frontend senior chicken 150", "cpp backend senior pizza 260", "java backend junior chicken 80",
                "python backend senior chicken 50"};
        String[] query = new String[]{"python and frontend and senior and chicken 200"};
        int[] result = solution(info, query);
        for (int n : result) {
            System.out.println(n);
        }
    }
}