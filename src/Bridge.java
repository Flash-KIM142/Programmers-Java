import java.io.*;

public class Bridge {
    static int solution(int[] stones, int k) {
        int answer = 0;
        int min = 1;    int max = 200000000;
        while(min<=max){
            int mid = (min+max)/2;
            if(isCrossable(stones, k, mid)){
                min = mid+1;
                answer = Math.max(answer, mid);
            }
            else{
                max = mid-1;
            }
        }
        return answer;
    }

    static Boolean isCrossable(int[] stones, int k, int mid){
        int howFarAtOnce = 0;
        for(int num: stones){
            if(num - mid < 0)   howFarAtOnce++;
            else    howFarAtOnce = 0;
            if(howFarAtOnce==k) return false;
        }
        return true;
    }

    public static void main(String args[]) throws IOException {
        BufferedWriter bfw = new BufferedWriter(new OutputStreamWriter(System.out));
        int[] stones = {2, 4, 5, 3, 2, 1, 4, 2, 5, 1};
        int k = 3;
        bfw.write(String.valueOf(solution(stones, k)));
        bfw.close();
    }
}