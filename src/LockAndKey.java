public class LockAndKey {
    static int[][][] keys = new int[4][][];
    static int[][] extendedLock;

    static boolean solution(int[][] key, int[][] lock) {
        keys[0] = key;
        keys[1] = rotateKey(key);
        keys[2] = rotateKey(keys[1]);
        keys[3] = rotateKey(keys[2]);

        int extension = key.length-1;
        extendedLock = new int[lock.length + 2*extension][lock.length + 2*extension];

        for(int i=0; i<lock.length+extension; i++){ // 겹치는 부분 처리
            for(int j=0; j<lock.length+extension; j++){ // 겹치는 부분 처리
                for(int k=0; k<4; k++){ // key 종류 네 개
                    for(int r=0; r<lock.length; r++){ // 확장된 자물쇠 만들기
                        for(int c=0; c<lock.length; c++){
                            extendedLock[r+extension][c+extension] = lock[r][c];
                        }
                    }
                    setExtendedLock(i,j,keys[k]);
                    if(isOpen(extendedLock, extension, lock.length))    return true;
                }
            }
        }

        return false;
    }

    static int[][] rotateKey(int[][] src){
        int n = src.length;
        int[][] res = new int[n][n];

        int r=0,c=0;
        for(int col=0; col<n; col++) {
            for(int row=n-1; row>=0; row--){
                res[r][c] = src[row][col];
                c++;
            }
            r++;    c=0;
        }
        return res;
    }

    static void setExtendedLock(int r, int c, int[][] key){
        int len = key.length;
        for(int i=0; i<len; i++)
            for(int j=0; j<len; j++)
                extendedLock[r+i][c+j] += key[i][j];
    }

    static Boolean isOpen(int[][] map, int ext, int len){
        for(int i=0; i<len; i++)
            for(int j=0; j<len; j++)
                if(map[i+ext][j+ext] != 1) return false;

        return true;
    }

    public static void main(String[] args) {
        int[][] key = {{0, 0, 0}, {1, 0, 0}, {0, 1, 1}};
        int[][] lock = {{1, 1, 1}, {1, 1, 0}, {1, 0, 1}};
        System.out.println(solution(key, lock));
    }
}