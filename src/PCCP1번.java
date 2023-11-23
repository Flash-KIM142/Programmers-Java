// 2023.11.23 PCCP

import java.util.HashMap;
import java.util.Map;

public class PCCP1번 {

    static Map<Integer, Integer> attackInfo = new HashMap<>();
    static int endTime = 0;
    static int curHealth = 0;
    static int successiveTime = 0;

    public static int solution(int[] bandage, int health, int[][] attacks) {
        curHealth = health;
        for (int[] attack : attacks) {
            attackInfo.put(attack[0], attack[1]);
            endTime = attack[0];
        }

        /* 1초부터 마지막 공격 시각까지 체력 정보 초기화 */
        for (int i = 1; i <= endTime; i++) {
            if (attackInfo.containsKey(i)) { // 공격이 있다면
                curHealth -= attackInfo.get(i);
                successiveTime = 0;

                if (curHealth <= 0) {
                    return -1;
                }
            } else { // 공격이 없다면
                curHealth += bandage[1];
                if (curHealth > health) {
                    curHealth = health;
                }

                successiveTime++;
                if (successiveTime == bandage[0]) {
                    curHealth += bandage[2];
                    successiveTime = 0;
                }
            }
        }

        if (curHealth <= 0) {
            return -1;
        }
        return curHealth;
    }

    public static void main(String[] args) {
        int[] bandage = new int[]{5, 1, 5};
        int health = 30;
        int[][] attacks = new int[][]{{2, 10}, {9, 15}, {10, 5}, {11, 5}};
        int result = solution(bandage, health, attacks);
        System.out.println(result);
    }
}