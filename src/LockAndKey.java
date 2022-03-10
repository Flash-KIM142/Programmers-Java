import java.util.ArrayList;

class LockAndKey{
    static int[][] extended_lock;
    static ArrayList<int[][]> totalKeys = new ArrayList<>();
    static int n,m;

    static boolean solution(int[][] key, int[][] lock){
        n = lock.length;
        m = key.length;
        int new_size = 3 * lock.length - 2;
        extended_lock = new int[new_size][new_size];
        createTotalKeys(key);

        for(int r=0; r<2*n-1; r++){
            for(int c=0; c<2*n-1; c++){
                for(int[][] cur_key: totalKeys){
                    for(int n_r=0; n_r<n; n_r++){
                        for(int n_c=0; n_c<n; n_c++){
                            extended_lock[n_r+n-1][n_c+n-1] = lock[n_r][n_c];
                        }
                    }
                    if(overlap(cur_key, r, c))  return true;
                }
            }
        }
        return false;
    }

    static boolean overlap(int[][] key, int r, int c){
        for(int i=0; i<m; i++)
            for(int j=0; j<m; j++)
                extended_lock[r+i][c+j] += key[i][j];

        for(int i=0; i<n; i++)
            for(int j=0; j<n; j++)
                if(extended_lock[n+i-1][n+j-1]!=1)  return false;

        return true;
    }

    static void createTotalKeys(int[][] org){
        totalKeys.add(org);
        totalKeys.add(rotateKey(totalKeys.get(totalKeys.size()-1)));
        totalKeys.add(rotateKey(totalKeys.get(totalKeys.size()-1)));
        totalKeys.add(rotateKey(totalKeys.get(totalKeys.size()-1)));
    }

    static int[][] rotateKey(int[][] key){
        int[][] r90 = new int[m][m];
        for(int r=0; r<m; r++)
            for(int c=0; c<m; c++)
                r90[r][c] = key[m-1-c][r];

        return r90;
    }

    public static void main(String[] args) {
        int[][] key = {{0, 0, 0}, {1, 0, 0}, {0, 1, 1}};
        int[][] lock = {{1, 1, 1}, {1, 1, 0}, {1, 0, 1}};
        boolean res = solution(key, lock);
        System.out.println(res);
    }
}