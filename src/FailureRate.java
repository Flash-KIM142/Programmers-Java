import java.io.*;
import java.util.Comparator;
import java.util.LinkedList;

public class FailureRate {
    static int[] solution(int N, int[] stages) {
        int[] answer = new int[N];
        int[] howManyTried = new int[N+1];
        int[] howManyFailed = new int[N+1];
        for(int stage: stages){
            int tries = stage;
            if(tries==N+1)  tries = N; // N+1 에서 실패했다는 건 다 통과했다는 뜻, 따라서 막 스테이지에 츄라이 인원 추가
            howManyTried[tries]++;
            if(stage<=N)    howManyFailed[stage]++;
        }

        int sum = 0;
        for(int i=N; i>=1; i--){
            sum += howManyTried[i];
            howManyTried[i] = sum;
        }

        LinkedList<FailureInfo> list = new LinkedList<>();
        for(int i=1; i<=N; i++)
            list.add(new FailureInfo(i, howManyTried[i] != 0 ? (float)howManyFailed[i]/(float)howManyTried[i] : 0));

        list.sort(Comparator.comparing(FailureInfo::getFail).reversed().thenComparing(FailureInfo::getStage));
        for(int i=0; i<N; i++)  answer[i] = list.get(i).stage;

        return answer;
    }

    static class FailureInfo{
        int stage;
        float fail;

        FailureInfo(int stage, float fail){
            this.stage = stage;
            this.fail = fail;
        }

        Integer getStage(){ return stage;}
        Float getFail(){ return fail; }
    }

    public static void main(String args[]) throws IOException {
        BufferedWriter bfw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = 5;
        int[] stages = { 1,2,2,1,3 };
        for(int a: solution(n, stages)) bfw.write(a + " ");
        bfw.close();
    }
}