// 2024.01.31 카카오 2024 겨울 인턴십분 - 소요시간: 23분

import java.util.HashMap;
import java.util.Map;

public class 가장많이받은선물 {

    public static void main(String[] args) {
        String[] friends = new String[]{"muzi", "ryan", "frodo", "neo"};
        String[] gifts = new String[]{"muzi frodo", "muzi frodo", "ryan muzi", "ryan muzi", "ryan muzi", "frodo muzi",
                "frodo ryan", "neo muzi"};
        int result = solution(friends, gifts);
        System.out.println(result);
    }

    public static int solution(String[] friends, String[] gifts) {
        int answer = 0;
        Map<String, Integer> friendsIdx = new HashMap<>();
        for (int i = 0; i < friends.length; i++) {
            friendsIdx.put(friends[i], i);
        }

        /* 선물 주고 받은 기록 */
        int[][] giftsMap = new int[friends.length][friends.length];
        for (String gift : gifts) {
            String[] splits = gift.split(" ");
            int giver = friendsIdx.get(splits[0]);
            int receiver = friendsIdx.get(splits[1]);

            giftsMap[giver][receiver]++;
        }

        /* 선물 지수 계산 */
        int[] giftCredit = new int[friends.length];
        for (int i = 0; i < friends.length; i++) {
            int giveNum = 0;
            int receiveNum = 0;

            for (int j = 0; j < friends.length; j++) {
                giveNum += giftsMap[i][j];
                receiveNum += giftsMap[j][i];
            }
            giftCredit[i] = giveNum - receiveNum;
        }

        /* 한 명씩 다음 달 받을 선물 개수 구하고 최댓값 갱신하기 */
        for (int i = 0; i < friends.length; i++) {
            int cnt = 0;
            for (int j = 0; j < friends.length; j++) {
                if (i == j) {
                    continue;
                }

                if (giftsMap[i][j] < giftsMap[j][i]) { // 자기가 받은 게 더 많은 상대면 스킵
                    continue;
                } else if (giftsMap[i][j] > giftsMap[j][i]) { // 자기가 준 게 더 많으면 선물 하나 받기
                    cnt++;
                } else { // 주고 받은 기록이 없거나, 똑같이 주고 받았다면 선물 지수 비교하기
                    if (giftCredit[i] <= giftCredit[j]) {
                        continue;
                    }
                    cnt++;
                }
            }

            answer = Math.max(answer, cnt);
        }

        return answer;
    }
}