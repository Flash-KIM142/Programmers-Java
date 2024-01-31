// 2024.01.31 카카오 2024 겨울 인턴십분 - 소요시간: 70분

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class 도넛과막대그래프 {

    public static void main(String[] args) {
        int[][] edges = new int[][]{{4, 11}, {1, 12}, {8, 3}, {12, 7}, {4, 2}, {7, 11}, {4, 8}, {9, 6}, {10, 11},
                {6, 10}, {3, 5}, {11, 1}, {5, 3}, {11, 9}, {3, 8}};
        int[] result = solution(edges);
        for (int a : result) {
            System.out.println(a);
        }
    }

    public static int[] solution(int[][] edges) {
        int[] answer = new int[4];
        int[][] startOrEnd = new int[1000001][2];
        List<Integer>[] map = new List[1000001];
        Set<Integer> notCenterIdx = new HashSet<>();
        for (int i = 1; i <= 1000000; i++) {
            map[i] = new ArrayList<Integer>();
        }

        for (int[] edge : edges) {
            startOrEnd[edge[0]][0]++;
            startOrEnd[edge[1]][1]++;

            map[edge[0]].add(edge[1]);
            notCenterIdx.add(edge[1]);
        }

        for (int i = 1; i <= 1000000; i++) {
            if (startOrEnd[i][0] <= 1) {
                continue;
            }
            if (!notCenterIdx.contains(i)) {
                answer[0] = i;
                break;
            }
        }

        for (Integer node : map[answer[0]]) {
            int cur = node;
            int edgeCnt = 0;

            while (true) {

                if (map[cur].size() == 0) { // 막다른 노드. 즉 막대형
                    answer[2]++;
                    break;
                }

                if (map[cur].size() >= 2) {
                    answer[3]++;
                    break;
                }

                if (edgeCnt > 0 && map[cur].get(0).equals(node)) {
                    answer[1]++;
                    break;
                }

                edgeCnt++;
                cur = map[cur].get(0);
            }
        }

        return answer;
    }
}