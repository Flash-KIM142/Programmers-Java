// 2023.11.13 2021 카카오 채용연계형 인턴십

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

public class 미로탈출 {

    public static void main(String[] args) {
        int result = solution(4, 1, 4, new int[][]{{1, 2, 1}, {3, 2, 1}, {2, 4, 1}}, new int[]{2, 3});
        System.out.println(result);
    }

    static final int INF = Integer.MAX_VALUE;
    static int[][] map;

    public static int solution(int n, int start, int end, int[][] roads, int[] traps) {
        int answer = 0;
        /* 지도 초기화 */
        map = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (i == j) {
                    map[i][j] = 0;
                } else {
                    map[i][j] = INF;
                }
            }
        }
        for (int[] road : roads) {
            if (map[road[0]][road[1]] > road[2]) {
                map[road[0]][road[1]] = road[2];
            }
        }

        /* 함정 초기화 */
        Set<Integer> trap = new HashSet<>();
        for (int t : traps) {
            trap.add(t);
        }

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(start, 0L));
        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            int curPos = cur.pos;

            if (curPos == end) {
                answer = (int) cur.journey;
                break;
            }

            if (trap.contains(curPos)) {
                activateTrap(curPos, n);
            }

            for (int next = 1; next <= n; next++) {
                if (map[curPos][next] == 0) {
                    continue;
                }

                pq.add(new Node(next, cur.journey + map[curPos][next]));
            }
        }

        return answer;
    }

    static void activateTrap(int targetPos, int n) {
        for (int i = 1; i <= n; i++) {
            if (map[targetPos][i] == 0 && map[i][targetPos] == 0) {
                continue;
            }

            int tmp = map[targetPos][i];
            map[targetPos][i] = map[i][targetPos]; // 함정에서 나가는 길 뒤집기
            map[i][targetPos] = tmp; // 함정으로 들어오는 길 뒤집기
        }
    }

    static class Node implements Comparable<Node> {
        int pos;
        long journey;

        Node(int pos, long journey) {
            this.pos = pos;
            this.journey = journey;
        }

        @Override
        public int compareTo(Node cmp) {
            return (int) (this.journey - cmp.journey);
        }
    }
}