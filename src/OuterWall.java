import java.util.*;

public class OuterWall {
    static int N, min;
    static int[] Weak;
    static int[] Dist;

    static int solution(int n, int[] weak, int[] dist) {
        Arrays.sort(dist);
        N = n;
        Weak = weak;
        Dist = dist;
        min = Integer.MAX_VALUE;

        for(int i=0; i<weak.length; i++){
            search(1, i, 0);
        }

        if(min == Integer.MAX_VALUE)    return -1;

        return min;
    }

    static void search(int cnt, int weakIdx, int visited){
        if(cnt > Dist.length)   return;
        if(cnt >= min)  return;

        for(int i=0; i<Weak.length; i++){
            int nextWeakIdx = (weakIdx + i) % Weak.length;
            int distance = Weak[nextWeakIdx] - Weak[weakIdx];

            if(nextWeakIdx < weakIdx)   distance += N;

            if(distance > Dist[Dist.length - cnt])  break;

            visited  |= 1 << nextWeakIdx;
        }

        if(visited == (1 << Weak.length) - 1) {
            min = cnt;
            return;
        }

        for(int i=0; i<Weak.length; i++){
            if( (visited & (1 << i)) != 0 ) continue;

            search(cnt+1, i, visited);
        }
    }

    public static void main(String[] args) {
        int n = 12;
        int[] weak = {1,5,6,10};
        int[] dist = {1,2,3,4};
        System.out.println(solution(n,weak,dist));
    }
}