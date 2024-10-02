import java.util.*;

public class 두큐합같게만들기3 {

    public int solution(int[] queue1, int[] queue2) {
        int answer = 0;
        /* Queue 자료구조로 옮기기 */
        Queue<Integer> q1 = new LinkedList<>();
        Queue<Integer> q2 = new LinkedList<>();
        long target_sum = 0;
        long sum1 = 0;
        long sum2 = 0;
        for(int i=0; i<queue1.length; i++){
            q1.add(queue1[i]);
            q2.add(queue2[i]);
            sum1 += queue1[i];
            sum2 += queue2[i];
            target_sum += queue1[i] + queue2[i];
        }
        if(target_sum % 2 == 1){ // 전체 합이 홀수면 끝
            return -1;
        }

        /* 최소 횟수 구하기 */
        while(sum1!=sum2){ // 서로 합이 같아질 때까지
            if(answer > queue1.length * 3){
                return -1;
            }

            int num = 0;
            if(sum1 > sum2){ // q1 에서 빼서 q2에 주기
                num = q1.poll();
                sum1 -= num;
                q2.add(num);
                sum2 += num;
            }
            else{
                num = q2.poll();
                sum2 -= num;
                q1.add(num);
                sum1 += num;
            }
            answer++;
        }

        return answer;
    }
}
