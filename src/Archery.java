import java.util.ArrayList;

public class Archery {
    static int max = Integer.MIN_VALUE;
    static ArrayList<int[]> list = new ArrayList<>();

    static int[] solution(int n, int[] info) {
        int[] ryanInfo = new int[11];
        combination(n, 0, 0, info, ryanInfo);
        if(max==Integer.MIN_VALUE)  return new int[]{-1};
        else{
            list.sort((o1, o2) -> {
                for (int i = 10; i >= 0; i--)
                    if (o1[i] != o2[i]) return o2[i] - o1[i];
                return 0;
            });
            return list.get(0);
        }
    }

    static void combination(int n, int cnt, int idx, int[] appeachInfo, int[] ryanInfo) {
        if (cnt == n) { // 화살 다 쐈으면
            int ryan = 0;
            int appeach = 0;
            for (int i = 0; i <= 10; i++) { // 둘이 점수 비교해서 더 큰 놈 찾기
                if (appeachInfo[i] == 0 && ryanInfo[i] == 0) continue;

                if (appeachInfo[i] >= ryanInfo[i]) appeach += 10 - i;
                else ryan += 10 - i;
            }
            if (ryan - appeach > max && ryan > appeach) {
                max = ryan - appeach;
                list.clear();
                list.add(ryanInfo.clone());
            }
            else if(ryan - appeach == max)  list.add(ryanInfo.clone());
            return;
        }

        for(int i=idx; i<11; i++){
            ryanInfo[i]++;
            combination(n, cnt+1, i, appeachInfo, ryanInfo);
            ryanInfo[i]--;
        }
    }

    public static void main(String[] args) {
        int n = 1;
        int[] info = {1,0,0,0,0,0,0,0,0,0,0};
        int[] answer = solution(n, info);
        for (int a : answer) System.out.println(a + " ");
    }
}