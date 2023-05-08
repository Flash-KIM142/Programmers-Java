import java.util.*;

public class 택배배달과수거하기2 {

    public static void main(String[] args) {
        System.out.println(solution(2, 2, new int[]{0,0}, new int[]{1,0}));
    }

    static long solution2(int cap, int n , int[] deliveries, int[] pickups){
        long answer = 0;

        int give = 0;
        int take = 0;
        for(int i=n-1 ;i>=0;--i) {
            if(deliveries[i]!=0 || pickups[i]!=0) {
                int cnt=0;
                while(give < deliveries[i] || take< pickups[i])
                {
                    ++cnt;
                    give+=cap;
                    take+=cap;
                }
                give -= deliveries[i];
                take -=  pickups[i];
                answer = answer + ((long) (i + 1) *cnt*2);
            }
        }
        return answer;
    }

    static long solution(int cap, int n, int[] deliveries, int[] pickups) {
        long answer = 0;
        if(deliveries[n-1]!=0 || pickups[n-1]!=0)   answer += n+n;
        Queue<Integer> delQ = new LinkedList<>();
        Queue<Integer> pickQ = new LinkedList<>();

        getStopPoint(cap, n, deliveries, delQ);
        getStopPoint(cap, n, pickups, pickQ);

        int len = Math.min(delQ.size(), pickQ.size());

        for(int i=0; i<len; i++){
            int del = delQ.poll();
            int pick = pickQ.poll();
            int max = Math.max(del, pick);
            answer += max + max;
        }

        if(!delQ.isEmpty()){
            while(!delQ.isEmpty()){
                int tmp = delQ.poll();
                answer += tmp + tmp;
            }
        }

        if(!pickQ.isEmpty()){
            while(!pickQ.isEmpty()){
                int tmp = pickQ.poll();
                answer += tmp + tmp;
            }
        }

        return answer;
    }

    static void getStopPoint(int cap, int n, int[] ary, Queue<Integer> q) {
        int cnt = 0;

        for(int i=n-1; i>=0; i--){
            if(ary[i]==0)   continue;
            if(cnt==cap){
                q.add(i+1);
                cnt = 0;
            }

            if(cnt+ary[i]<cap){
                cnt += ary[i];
                ary[i] = 0;
            }
            else if(cnt+ary[i]>cap){
                ary[i] -= (cap - cnt);
                q.add(i+1);
                cnt = 0;
                i++;
            }
            else{
                cnt = cap;
                ary[i] = 0;
            }
        }
    }
}
