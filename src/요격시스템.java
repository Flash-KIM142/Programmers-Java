import java.util.Arrays;
import java.util.Comparator;

public class 요격시스템 {

    public static void main(String[] args) {

    }

    static public int solution(int[][] targets) {
        Arrays.sort(targets, new Comparator<int[]>(){
            @Override
            public int compare(int[] o1, int[] o2){
                if(o1[0]==o2[0])    return o1[1] - o2[1];
                else    return o1[0] - o2[0];
            }
        });

        int coverage = targets[0][1];
        int answer = 1;

        for(int i=1; i<targets.length; i++){
            int[] tmp = targets[i];
            if(tmp[0]<coverage) continue;

            answer++;
            coverage = tmp[1];
        }

        return answer;
    }
}
