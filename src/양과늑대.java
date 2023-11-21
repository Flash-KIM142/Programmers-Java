// 2023.11.22 2022 카카오 블라인드, 소요시간: 55분 - tc 5번 fail

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class 양과늑대 {

    static List[] map;

    public static int solution(int[] info, int[][] edges) {
        int answer = 0;
        init(info, edges);

        Queue<Node> q = new LinkedList<>();
        q.add(new Node(0, 1, 0, 0, new HashSet<>() {{
            add(0);
        }}));

        while (!q.isEmpty()) {
            Node cur = q.poll();
            answer = Math.max(answer, cur.sheep);
            if (cur.move > cur.visited.size() * 2) {
                continue;
            }

            List<Integer> nexts = map[cur.pos];
            for (Integer next : nexts) {
                int nextSheep = cur.sheep;
                int nextWolf = cur.wolf;
                int nextMove = cur.move + 1;
                Set<Integer> nextVisited = new HashSet<>(cur.visited) {{
                    add(next);
                }};

                if (!cur.visited.contains(next)) {
                    if (info[next] == 0) {
                        nextSheep++;
                    } else {
                        nextWolf++;
                    }
                }
                if (nextWolf >= nextSheep) {
                    continue;
                }

                q.add(new Node(next, nextSheep, nextWolf, nextMove, nextVisited));
            }
        }

        return answer;
    }

    static void init(int[] info, int[][] edges) {
        map = new List[info.length];
        for (int i = 0; i < map.length; i++) {
            map[i] = new ArrayList<Integer>();
        }

        for (int[] edge : edges) {
            int start = edge[0];
            int end = edge[1];

            map[start].add(end);
            map[end].add(start);
        }
    }

    static class Node {
        int pos;
        int sheep;
        int wolf;
        int move;
        Set<Integer> visited;

        Node(int pos, int sheep, int wolf, int move, Set<Integer> visited) {
            this.pos = pos;
            this.sheep = sheep;
            this.wolf = wolf;
            this.move = move;
            this.visited = visited;
        }
    }

    public static void main(String[] args) {
        int[] info = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        int[][] edges = new int[][]{{0, 1}, {0, 2}, {1, 3}, {1, 4}, {2, 5}, {2, 6}, {3, 7}, {3, 8}, {4, 9}, {4, 10},
                {5, 11}, {5, 12}, {6, 13}, {6, 14}, {7, 15}, {7, 16}};
        int result = solution(info, edges);
        System.out.println(result);
    }
}