import java.util.*;

public class 두큐합같게만들기 {

    public static void main(String[] args) {
        int[] queue1 = {1,2,1,2};
        int[] queue2 = {1,10,1,2};
        System.out.println(solution(queue1, queue2));
    }

    static int solution(int[] queue1, int[] queue2){
        int totalNum = 0;
        long qSum1 = 0;
        long qSum2 = 0;
        Queue<Integer> q1 = new LinkedList<>();
        Queue<Integer> q2 = new LinkedList<>();

        for(int n: queue1) {
            qSum1 += n;
            totalNum++;
            q1.add(n);
        }
        for(int n: queue2) {
            qSum2 += n;
            totalNum++;
            q2.add(n);
        }

        if((qSum1 + qSum2)%2!=0)    return -1;

        int cnt = 0;
        while(true){
            int tmp;
            if(q1.isEmpty() || q2.isEmpty())    return -1;

            if(qSum1>qSum2){
                tmp = q1.poll();
                q2.add(tmp);
                qSum1 -= tmp;
                qSum2 += tmp;
            }
            else if(qSum2>qSum1){
                tmp = q2.poll();
                q1.add(tmp);
                qSum1 += tmp;
                qSum2 -= tmp;
            }
            else    break;

            cnt++;
            if(cnt>=totalNum + totalNum/2) return -1;
        }

        return cnt;
    }
}
