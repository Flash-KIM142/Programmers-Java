package boj;

import java.util.*;

public class boj12852 {

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        if(n==1){
            System.out.println(0);
            System.out.println(1);
            return;
        }

        int[] dp = new int[n+1];
        int[] track = new int[n+1];

        for(int i=2; i<=n; i++){
            dp[i] = dp[i-1] + 1;
            track[i] = i-1;

            if(i%2==0){
                if(dp[i] > dp[i/2] + 1){
                    dp[i] = dp[i/2] + 1;
                    track[i] = i/2;
                }
            }

            if(i%3==0){
                if(dp[i] > dp[i/3] + 1){
                    dp[i] = dp[i/3] + 1;
                    track[i] = i/3;
                }
            }
        }

        System.out.println(dp[n]);
        System.out.print(n + " ");
        int t = n;
        for(int i=0; i<dp[n]; i++){
            System.out.print(track[t]);
            t = track[t];
        }
    }
}
