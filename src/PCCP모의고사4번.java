// 2023.11.23 PCCP 모의고사간, 실패

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class PCCP모의고사4번 {

    public static long[] solution(int[][] program) {
        long[] answer = new long[11];
        Arrays.sort(program, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1] - o2[1];
            }
        });
        long time = 0;

        PriorityQueue<Program> pq = new PriorityQueue<>();
        int operatedCnt = 0;
        int programIdx = 0;
        while (operatedCnt < program.length) {
            while (programIdx < program.length && program[programIdx][1] <= time) {
                pq.add(new Program(program[programIdx][1], program[programIdx][0], program[programIdx][2]));
                programIdx++;
            }

            if (pq.isEmpty()) {
                time = program[programIdx][1];
            } else {
                Program cur = pq.poll();
                if (time > cur.calledAt) {
                    answer[cur.priority] += time - cur.calledAt;
                }
                time += cur.executionTime;
                operatedCnt++;
            }
        }

        answer[0] = time;
        return answer;
    }

    static class Program implements Comparable<Program> {
        int calledAt;
        int priority;
        int executionTime;

        Program(int calledAt, int priority, int executionTime) {
            this.calledAt = calledAt;
            this.priority = priority;
            this.executionTime = executionTime;
        }

        @Override
        public int compareTo(Program cmp) {
            int priorityCmp = priority - cmp.priority;
            if (priorityCmp == 0) {
                return calledAt - cmp.calledAt;
            }
            return priorityCmp;
        }
    }

    public static void main(String[] args) {
        long[] result = solution(new int[][]{{3, 6, 4}, {4, 2, 5}, {1, 0, 5}, {5, 0, 5}});
        for (long n : result) {
            System.out.println(n);
        }
    }
}