// 2023.11.24 2019 카카오 블라인드, 소요시간: 33분

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class 실패율 {

    public static int[] solution(int N, int[] stages) {
        int[] successCnt = new int[N + 1];
        int[] tryCnt = new int[N + 2];

        for (int stage : stages) {
            for (int i = 1; i < stage; i++) {
                successCnt[i]++;
                tryCnt[i]++;
            }
            tryCnt[stage]++;
        }

        List<double[]> list = new ArrayList<>(); // [0]은 레벨, [1]은 실패율
        for (int i = 1; i <= N; i++) {
            if (tryCnt[i] == 0) {
                list.add(new double[]{i, 0.0});
                continue;
            }
            int failCnt = tryCnt[i] - successCnt[i];
            list.add(new double[]{i, (double) failCnt / tryCnt[i]});
        }

        list.sort(new Comparator<double[]>() {
            @Override
            public int compare(double[] o1, double[] o2) {
                double failCmp = o2[1] - o1[1];
                if (failCmp == 0) {
                    if (o1[0] < o2[0]) {
                        return -1;
                    } else {
                        return 1;
                    }
                } else {
                    if (failCmp > 0) {
                        return 1;
                    } else {
                        return -1;
                    }
                }
            }
        });

        int[] answer = new int[N];
        for (int i = 0; i < N; i++) {
            answer[i] = (int) list.get(i)[0];
        }

        return answer;
    }

    public static void main(String[] args) {
        int N = 5;
        int[] stages = new int[]{1, 1, 1, 2, 3, 4};
        int[] result = solution(N, stages);
        for (int n : result) {
            System.out.println(n);
        }
    }
}