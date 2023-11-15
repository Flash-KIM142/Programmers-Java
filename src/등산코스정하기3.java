// 2023.11.15 2022 카카오 테크 인턴십

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class 등산코스정하기3 {

    static final int INF = Integer.MAX_VALUE;

    static List<Edge>[] map;
    static List<int[]> candidates = new ArrayList<>();
    static Set<Integer> gateSet = new HashSet<>();
    static Set<Integer> summitSet = new HashSet<>();
    static int[] intensities;

    public static int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        init(n, paths, gates, summits);
        Queue<Node> q = new LinkedList<>();
        for (int gate : gates) {
            q.add(new Node(gate, 0));
        }

        while (!q.isEmpty()) {
            Node cur = q.poll();
            if (intensities[cur.curPos] < cur.intensity) {
                continue;
            }
            if (summitSet.contains(cur.curPos)) {
                candidates.add(new int[]{cur.curPos, cur.intensity});
                continue;
            }

            List<Edge> possibleEdges = map[cur.curPos];
            for (Edge e : possibleEdges) {
                int newIntensity = Math.max(intensities[cur.curPos], e.cost);
                Node nextNode = new Node(e.end, newIntensity);

                if (intensities[e.end] > newIntensity) {
                    q.add(nextNode);
                    intensities[e.end] = newIntensity;
                }
            }
        }

        candidates.sort(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                int intensityCmp = Integer.compare(o1[1], o2[1]);
                if (intensityCmp == 0) {
                    return o1[0] - o2[0];
                }
                return intensityCmp;
            }
        });
        return candidates.get(0);
    }

    static void init(int n, int[][] paths, int[] gates, int[] summits) {
        intensities = new int[n + 1];
        Arrays.fill(intensities, INF);

        for (int gate : gates) {
            gateSet.add(gate);
            intensities[gate] = 0;
        }
        for (int summit : summits) {
            summitSet.add(summit);
        }

        map = new List[n + 1];
        for (int i = 1; i <= n; i++) {
            map[i] = new ArrayList<>();
        }

        for (int[] path : paths) {
            int start = path[0];
            int end = path[1];
            int cost = path[2];

            if (gateSet.contains(start) || summitSet.contains(end)) {
                map[start].add(new Edge(end, cost));
            } else if (summitSet.contains(start) || gateSet.contains(end)) {
                map[end].add(new Edge(start, cost));
            } else {
                map[start].add(new Edge(end, cost));
                map[end].add(new Edge(start, cost));
            }
        }
    }

    static class Node {
        int curPos;
        int intensity;

        Node(int curPos, int intensity) {
            this.curPos = curPos;
            this.intensity = intensity;
        }
    }

    static class Edge {
        int end;
        int cost;

        Edge(int end, int cost) {
            this.end = end;
            this.cost = cost;
        }
    }

    public static void main(String[] args) {
        int n = 6;
        int[][] paths = new int[][]{{1, 2, 3}, {2, 3, 5}, {2, 4, 2}, {2, 5, 4}, {3, 4, 4}, {4, 5, 3}, {4, 6, 1},
                {5, 6, 1}};
        int[] gates = new int[]{1, 3};
        int[] summits = new int[]{5};
        int[] result = solution(n, paths, gates, summits);
        System.out.println(result[0] + " " + result[1]);
    }
}