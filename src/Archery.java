import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class Archery {
    static int appeachScore, ryanScore;
    static int max = Integer.MIN_VALUE;
    static ArrayList<int[]> list = new ArrayList<>();

    static int[] solution(int n, int[] info) {
        int[] ry = new int[11];
        backTracking(0, 0, n, info, ry);

        if(list.size()==0)  return new int[]{-1}; // 가능한 결과 없을 때
        Collections.sort(list, (o1,o2) -> {
            for(int i=10; i>=0; i--)
                if(o1[i]!=o2[i])    return o2[i]-o1[i];
            return 0;
        });

        return list.get(0);
    }

    static void backTracking(int cnt,int idx, int n, int[] ap, int[] ry){
        if(cnt==n){ // 라이언이 화살 다 쐈으면 점수 계산해주자
            getScore(ap, ry);

            if(ryanScore>appeachScore){ // 라이언이 이길 경우
                int diff = ryanScore - appeachScore;
                if(diff>max){
                    max = diff;
                    list.clear();
                    list.add(ry.clone());
                }
                else if(diff==max)  list.add(ry.clone());
            }
            return;
        }

        for(int i=idx; i<11; i++){
            ry[i]++;
            backTracking(cnt+1, i, n, ap, ry);
            ry[i]--;
        }
    }

    static void getScore(int[] ap, int[] ry){ // 둘의 결과를 넘겨주면 각각으 점수 계산
        appeachScore = 0;   ryanScore = 0;
        for(int i=0; i<11; i++){
            int appeach = ap[i];
            int ryan = ry[i];
            if(appeach==0 && ryan==0)   continue;

            if(ryan>appeach) // 라이언이 점수를 얻는 경우
                ryanScore += (10-i);
            else // 어피치가 점수를 얻는 경우
                appeachScore += (10-i);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedWriter bfw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = 10;
        int[] info = { 0,0,0,0,0,0,0,0,3,4,3 };
        int[] answer = solution(n, info);
        for(int a: answer)  bfw.write(a + " ");
        bfw.close();
    }
}