// 2023.11.21 2022 카카오 블라인드

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class 신고결과받기 {

    static Map<String, Set<String>> reportInfo = new HashMap<>();
    static Map<String, Integer> reportedInfo = new HashMap<>();

    public static int[] solution(String[] id_list, String[] report, int k) {
        int[] answer = new int[id_list.length];

        /* 모든 유저의 신고 정보 초기화 */
        for (String user : id_list) {
            reportInfo.put(user, new HashSet<>());
            reportedInfo.put(user, 0);
        }

        /* 유저들의 신고 정보 기록 */
        for (String reportCase : report) {
            String[] split = reportCase.split(" ");
            String reporter = split[0];
            String reportee = split[1];

            reportInfo.get(reporter).add(reportee);
        }

        /* 신고당한 횟수 초기화 */
        for (Set<String> reportedUsers : reportInfo.values()) {
            for (String reportedUser : reportedUsers) {
                reportedInfo.put(reportedUser, reportedInfo.get(reportedUser) + 1);
            }
        }

        for (int i = 0; i < id_list.length; i++) {
            int cnt = 0;
            Set<String> reportedUser = reportInfo.get(id_list[i]);
            for (String user : reportedUser) {
                if (reportedInfo.get(user) >= k) {
                    cnt++;
                }
            }
            answer[i] = cnt;
        }

        return answer;
    }

    public static void main(String[] args) {
        String[] id_list = new String[]{"con", "ryan"};
        String[] report = new String[]{"ryan con", "ryan con", "ryan con", "ryan con"};
        int k = 2;
        int[] result = solution(id_list, report, k);
        for (int n : result) {
            System.out.println(n);
        }
    }
}