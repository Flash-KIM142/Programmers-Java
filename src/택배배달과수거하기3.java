// 2023.11.19 2023 카카오 블라인드

import java.util.ArrayList;
import java.util.List;

public class 택배배달과수거하기3 {

    static List<Integer> deliveryDestinations = new ArrayList<>();
    static List<Integer> pickupDestinations = new ArrayList<>();

    public static long solution(int cap, int n, int[] deliveries, int[] pickups) {
        initDestinations(cap, n, deliveries, pickups);
        List<Integer> finalDestinations = new ArrayList<>();

        int moveCnt = Math.max(deliveryDestinations.size(), pickupDestinations.size());
        for (int i = 0; i < moveCnt; i++) {
            int deliveryDestination = 0;
            if (i < deliveryDestinations.size()) {
                deliveryDestination = deliveryDestinations.get(i);
            }

            int pickupDestination = 0;
            if (i < pickupDestinations.size()) {
                pickupDestination = pickupDestinations.get(i);
            }

            finalDestinations.add(Math.max(deliveryDestination, pickupDestination));
        }

        long answer = 0;
        for (Integer distance : finalDestinations) {
            answer += distance * 2;
        }
        return answer;
    }

    static void initDestinations(int cap, int n, int[] deliveries, int[] pickups) {
        int deliveryCap = cap;
        int pickupCap = cap;

        /* 배달 목적지 초기화 */
        for (int i = n - 1; i >= 0; i--) {
            if (deliveries[i] == 0) {
                continue;
            }
            if (deliveryCap == cap) {
                deliveryDestinations.add(i + 1);
            }

            if (deliveryCap - deliveries[i] >= 0) {
                deliveryCap -= deliveries[i];
                deliveries[i] = 0;
            } else {
                deliveries[i] -= deliveryCap;
                deliveryCap = cap;
                i++;
            }
        }

        /* 픽업 목적지 초기화 */
        for (int i = n - 1; i >= 0; i--) {
            if (pickups[i] == 0) {
                continue;
            }
            if (pickupCap == cap) {
                pickupDestinations.add(i + 1);
            }

            if (pickupCap - pickups[i] >= 0) {
                pickupCap -= pickups[i];
                pickups[i] = 0;
            } else {
                pickups[i] -= pickupCap;
                pickupCap = cap;
                i++;
            }
        }
    }

    public static void main(String[] args) {
        long result = solution(1, 5, new int[]{5, 5, 5, 5, 5}, new int[]{5, 5, 5, 5, 5});
        System.out.println(result);
    }
}