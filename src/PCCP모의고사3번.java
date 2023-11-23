// 2023.11.23 PCCP 모의고사, 소요시간: 1시간

import java.util.Stack;

public class PCCP모의고사3번 {

    static int[] childrenOfRr = {0, 1, 1, 2};

    public static String[] solution(int[][] queries) {
        String[] answer = new String[queries.length];
        int answerIdx = 0;
        L:
        for (int[] query : queries) {
            int gen = query[0] - 1;
            int place = query[1] - 1;

            if (gen == 0) {
                answer[answerIdx++] = "Rr";
                continue;
            } else if (gen == 1) {
                if (place == 0) {
                    answer[answerIdx++] = "RR";
                } else if (place == 1 || place == 2) {
                    answer[answerIdx++] = "Rr";
                } else {
                    answer[answerIdx++] = "rr";
                }
                continue;
            }

            Stack<Node> stk = new Stack<>();
            stk.add(new Node(gen - 1, place / 4, place % 4));
            while (stk.peek().gen > 1) {
                stk.add(new Node(stk.peek().gen - 1, stk.peek().place / 4, stk.peek().place % 4));
            }

            if (stk.peek().place == 0) {
                answer[answerIdx++] = "RR";
            } else if (stk.peek().place == 3) {
                answer[answerIdx++] = "rr";
            } else {
                int curType = 1;
                while (!stk.isEmpty()) {
                    Node cur = stk.pop();
                    curType = childrenOfRr[cur.idx];
                    if (curType == 0) {
                        answer[answerIdx++] = "RR";
                        continue L;
                    } else if (curType == 2) {
                        answer[answerIdx++] = "rr";
                        continue L;
                    }
                }
                answer[answerIdx++] = "Rr";
            }
        }

        return answer;
    }

    static class Node {
        int gen;
        int place;
        int idx;

        Node(int gen, int place, int idx) {
            this.gen = gen;
            this.place = place;
            this.idx = idx;
        }
    }

    public static void main(String[] args) {
        int[][] queries = new int[][]{{2, 3}, {3, 9}};
        String[] result = solution(queries);
        for (String s : result) {
            System.out.println(s);
        }
    }
}