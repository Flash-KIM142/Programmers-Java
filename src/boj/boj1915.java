package boj;

import java.util.*;
import java.io.*;

public class boj1915 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] dp = new int[N+1][M+1];
        for(int r=1; r<=N; r++) {
            String input = br.readLine();
            for(int c=1; c<=M; c++) {
                dp[r][c] = input.charAt(c-1) - '0';
            }
        }

        int max_len = 0;
        for(int r=1; r<=N; r++) {
            for(int c=1; c<=M; c++) {
                if(dp[r][c]==0) {
                    continue;
                }
                int tmp_min_len = dp[r-1][c-1];
                tmp_min_len = Integer.min(tmp_min_len, dp[r-1][c]);
                tmp_min_len = Integer.min(tmp_min_len, dp[r][c-1]);

                dp[r][c] = tmp_min_len + 1;
                max_len = Integer.max(max_len, dp[r][c]);
            }
        }

        int res = max_len * max_len;
        System.out.println(res);
    }
}
