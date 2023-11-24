// 2023.11.24 2021 Dev-Matching

import java.util.HashMap;
import java.util.Map;

public class 다단계칫솔판매 {

    static Map<String, String> reference = new HashMap<>(); // 추천 관계 정보 map. key는 당사자, value는 추천인
    static Map<String, Integer> salesReport = new HashMap<>(); // 각 판매원의 판매 실적 정보

    public static int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        /* 추천 관계 정보 초기화 */
        for (int i = 0; i < enroll.length; i++) {
            reference.put(enroll[i], referral[i]);
            salesReport.put(enroll[i], 0);
        }

        /* 이익 분배 */
        for (int i = 0; i < seller.length; i++) {
            String sell = seller[i];
            int sales = 100 * amount[i];

            while (reference.containsKey(sell)) {
                String beneficiary = reference.get(sell);
                int shareOfBeneficiary = sales / 10;
                int shareOfSeller = sales - shareOfBeneficiary;

                salesReport.put(sell, salesReport.get(sell) + shareOfSeller);

                sell = beneficiary;
                sales = shareOfBeneficiary;
                if (sales == 0) { // 다음 분배받을 사람의 이익이 0원이라면 종료
                    break;
                }
            }
        }

        int[] answer = new int[enroll.length];
        for (int i = 0; i < enroll.length; i++) {
            answer[i] = salesReport.get(enroll[i]);
        }

        return answer;
    }

    public static void main(String[] args) {
        String[] enroll = new String[]{"john", "mary", "edward", "sam", "emily", "jaimie", "tod", "young"};
        String[] referral = new String[]{"-", "-", "mary", "edward", "mary", "mary", "jaimie", "edward"};
        String[] seller = new String[]{"young", "john", "tod", "emily", "mary"};
        int[] amount = new int[]{12, 4, 2, 5, 10};
        int[] result = solution(enroll, referral, seller, amount);
        for (int n : result) {
            System.out.println(n);
        }
    }
}