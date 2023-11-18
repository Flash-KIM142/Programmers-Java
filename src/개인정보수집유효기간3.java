// 2023.11.18 2023 카카오 블라인드

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class 개인정보수집유효기간3 {

    public static int[] solution(String today, String[] terms, String[] privacies) {
        List<Integer> result = new ArrayList<>();
        Map<String, Integer> termExpiration = new HashMap<>();
        for (String term : terms) {
            String[] splits = term.split(" ");
            termExpiration.put(splits[0], Integer.parseInt(splits[1]));
        }

        int idx = 0;
        int todayDateToDays = dateToDays(today);
        for (String privacy : privacies) {
            String[] splits = privacy.split(" ");
            String date = splits[0];
            String termType = splits[1];

            int termExpiredDays = dateToDays(date) + termExpiration.get(termType) * 28;
            if (termExpiredDays <= todayDateToDays) {
                result.add(idx);
            }
            idx++;
        }

        Collections.sort(result);
        int[] answer = new int[result.size()];
        for (int i = 0; i < result.size(); i++) {
            answer[i] = result.get(i) + 1;
        }
        return answer;
    }

    static int dateToDays(String date) {
        String[] splits = date.split("\\.");
        int year = Integer.parseInt(splits[0]);
        int month = Integer.parseInt(splits[1]);
        int day = Integer.parseInt(splits[2]);

        return year * 12 * 28 + month * 28 + day;
    }

    public static void main(String[] args) {
        String today = "2022.05.19";
        String[] terms = new String[]{"A 6", "B 12", "C 3"};
        String[] privacies = new String[]{"2021.05.02 A", "2021.07.01 B", "2022.02.19 C", "2022.02.20 C"};
        int[] result = solution(today, terms, privacies);
        for (int n : result) {
            System.out.println(n);
        }
    }
}