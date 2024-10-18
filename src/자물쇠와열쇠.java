import java.util.*;

public class 자물쇠와열쇠 {

    public static void main(String[] args) {
        int[][] key = new int[][]{{1}};
        int[][] lock = new int[][]{{0}};
        boolean answer = solution(key, lock);
        System.out.println(answer);
    }

    public static boolean solution(int[][] key, int[][] lock) {
        List<int[][]> rotated_key = new ArrayList<>();
        rotated_key.add(key);
        int cnt = 0;
        while(cnt!=3) {
            rotated_key.add(rotate_clockwise(rotated_key.get(rotated_key.size()-1)));
            cnt++;
        }

        int N = lock.length;
        int M = key.length;
        int[][] extended_lock = new int[N+2*M-2][N+2*M-2];
        for(int i=0; i<N+2*M-2; i++) {
            Arrays.fill(extended_lock[i], 1);
        }
        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                extended_lock[i+M-1][j+M-1] = lock[i][j];
            }
        }

        for(int i=0; i<4; i++) {
            int[][] cur_key = rotated_key.get(i);
            for(int r=0; r<=N+M-2; r++) {
                for(int c=0; c<=N+M-2; c++) {
                    boolean is_match = key_lock_match(cur_key, extended_lock, r, c, N, M);
                    if(is_match) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    static boolean key_lock_match(int[][] key, int[][] extended_lock, int head_r, int head_c, int N, int M) {
        int[][] tmp_extended_lock = new int[extended_lock.length][extended_lock.length];
        for(int r=0; r<extended_lock.length; r++) {
            for(int c=0; c<extended_lock.length; c++) {
                tmp_extended_lock[r][c] = extended_lock[r][c];
            }
        }

        for(int r=0; r<key.length; r++) {
            for(int c=0; c<key.length; c++) {
                tmp_extended_lock[r+head_r][c+head_c] += key[r][c];
            }
        }

        for(int r=M-1; r<=N+M-2; r++) {
            for(int c=M-1; c<=N+M-2; c++) {
                if(tmp_extended_lock[r][c]!=1) {
                    return false;
                }
            }
        }

        return true;
    }

    static int[][] rotate_clockwise(int[][] target) {
        int n = target.length;
        int[][] res = new int[n][n];
        for(int r=0; r<n; r++) {
            for(int c=0; c<n; c++) {
                res[r][c] = target[n-c-1][r];
            }
        }
        return res;
    }
}
