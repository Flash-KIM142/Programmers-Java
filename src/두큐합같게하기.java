import java.util.*;

public class 두큐합같게하기 {

    public static void main(String[] args){
        int[] queue1 = {3,2,7,2};
        int[] queue2 = {4,6,5,1};
        System.out.println(solution(queue1, queue2));
    }

    static int solution(int[] queue1, int[] queue2) {
        Queue<Integer> q1 = new LinkedList<>();
        Queue<Integer> q2 = new LinkedList<>();

        long sum1=0, sum2=0;
        int initLength = queue1.length;

        for(int i=0; i<initLength; i++){
            q1.offer(queue1[i]);
            q2.offer(queue2[i]);

            sum1 += queue1[i];
            sum2 += queue2[i];
        }

        if(sum1+sum2%2==1)  return -1;

        int cnt = 0;

        while(sum1!=sum2){
            if(sum1>sum2){
                int tmp = q1.poll();
                sum1 -= tmp;
                sum2 += tmp;
                q2.offer(tmp);
                cnt++;
            }
            else if(sum1<sum2){
                int tmp = q2.poll();
                sum1 += tmp;
                sum2 -= tmp;
                q1.offer(tmp);
                cnt++;
            }

            if(cnt>=initLength*3)   return -1;
        }

        return cnt;
    }
}
