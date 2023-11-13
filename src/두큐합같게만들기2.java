// 2023.11.14 2022 카카오 테크 인턴십

import java.util.LinkedList;
import java.util.Queue;

public class 두큐합같게만들기2 {

    static long targetSum;

    public static int solution(int[] queue1, int[] queue2) {
        targetSum = initTargetSum(queue1, queue2);
        if (targetSum % 2 != 0) {
            return -1;
        }
        targetSum /= 2;

        Queue<Integer> q1 = new LinkedList<>();
        long sum1 = 0;
        for (int n : queue1) {
            q1.add(n);
            sum1 += n;
        }
        Queue<Integer> q2 = new LinkedList<>();
        long sum2 = 0;
        for (int n : queue2) {
            q2.add(n);
            sum2 += n;
        }

        int cnt = 0;
        while (sum1 != targetSum) {
            if (q1.isEmpty() || q2.isEmpty()) {
                return -1;
            }
            if (cnt == queue1.length * 3) {
                return -1;
            }

            if (sum1 > sum2) {
                int n = q1.poll();
                sum1 -= n;
                sum2 += n;
                q2.add(n);
            } else {
                int n = q2.poll();
                sum2 -= n;
                sum1 += n;
                q1.add(n);
            }
            cnt++;
        }

        return cnt;
    }

    static long initTargetSum(int[] q1, int[] q2) {
        long sum = 0;
        for (int n : q1) {
            sum += n;
        }
        for (int n : q2) {
            sum += n;
        }
        return sum;
    }

    public static void main(String[] args) {
        int[] q1 = new int[]{1};
        int[] q2 = new int[]{1};
        int result = solution(q1, q2);
        System.out.println(result);
    }
}