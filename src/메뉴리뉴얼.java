// 2023.11.22 2021 카카오 블라인드, 소요시간: 31분

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class 메뉴리뉴얼 {

    static List<String> result = new ArrayList<>();

    public static String[] solution(String[] orders, int[] course) {
        for (int courseLen : course) {
            Map<String, Integer> map = new HashMap<>();

            for (String order : orders) {
                if (order.length() < courseLen) {
                    continue;
                }

                combination(map, order, "", 0, 0, courseLen);
            }
            /* map 중 빈도수 높은 것들 result에 추가 */
            int maxAppearance = 0;
            for (String key : map.keySet()) {
                maxAppearance = Math.max(maxAppearance, map.get(key));
            }

            if (maxAppearance < 2) {
                continue;
            }
            for (String key : map.keySet()) {
                if (map.get(key) == maxAppearance) {
                    result.add(key);
                }
            }
        }

        Collections.sort(result);
        String[] answer = new String[result.size()];
        for (int i = 0; i < answer.length; i++) {
            answer[i] = result.get(i);
        }
        return answer;
    }

    static void combination(Map<String, Integer> map, String order, String cur, int idx, int cnt, int n) {
        if (cnt == n) {
            /* map에 넣기 전에 정렬 */
            char[] chars = cur.toCharArray();
            Arrays.sort(chars);
            String ordered = "";
            for (int i = 0; i < cur.length(); i++) {
                ordered += chars[i];
            }

            if (map.containsKey(ordered)) {
                map.put(ordered, map.get(ordered) + 1);
            } else {
                map.put(ordered, 1);
            }
            return;
        }

        for (int i = idx; i < order.length(); i++) {
            cur += order.charAt(i);
            combination(map, order, cur, i + 1, cnt + 1, n);
            cur = cur.substring(0, cur.length() - 1);
        }
    }

    public static void main(String[] args) {
        String[] orders = new String[]{"ABCFG", "AC", "CDE", "ACDE", "BCFG", "ACDEH"};
        int[] course = new int[]{2, 3, 4};
        String[] result = solution(orders, course);
        for (String s : result) {
            System.out.println(s);
        }
    }
}