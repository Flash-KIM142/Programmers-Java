// 2023.11.13 2021 카카오 채용연계형 인턴십

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class 표편집 {

    public static void main(String[] args) {
        String result = solution(8, 2, new String[]{"D 2", "C", "U 3", "C", "D 4", "C", "U 2", "Z", "Z"});
        System.out.println(result);
    }

    public static String solution(int n, int k, String[] cmd) {
        List<Cell> table = new ArrayList<>();
        init(table, n);
        Stack<Integer> removed = new Stack<>();

        for (String s : cmd) {
            char cmdType = s.charAt(0);
            int cnt;
            switch (cmdType) {
                case 'U':
                    cnt = Integer.parseInt(s.substring(2));
                    while (cnt > 0) {
                        k = table.get(k).prev;
                        cnt--;
                    }
                    break;
                case 'D':
                    cnt = Integer.parseInt(s.substring(2));
                    while (cnt > 0) {
                        k = table.get(k).next;
                        cnt--;
                    }
                    break;
                case 'C':
                    table.get(k).status = 0;
                    removed.add(k);

                    if (table.get(k).prev != -1) {
                        table.get(table.get(k).prev).next = table.get(k).next;
                    }

                    if (table.get(k).next != -1) {
                        table.get(table.get(k).next).prev = table.get(k).prev;
                    }

                    if (table.get(k).next == -1) {
                        k = table.get(k).prev;
                    } else {
                        k = table.get(k).next;
                    }
                    break;
                case 'Z':
                    int target = removed.pop();
                    table.get(target).status = 1;
                    if (table.get(target).prev != -1) {
                        table.get(table.get(target).prev).next = target;
                    }
                    if (table.get(target).next != -1) {
                        table.get(table.get(target).next).prev = target;
                    }
                    break;
            }
        }

        StringBuilder sb = new StringBuilder();
        for (Cell cell : table) {
            if (cell.status == 1) {
                sb.append("O");
            } else {
                sb.append("X");
            }
        }
        return sb.toString();
    }

    static void init(List<Cell> table, int n) {
        for (int i = 0; i < n - 1; i++) {
            table.add(new Cell(i - 1, i + 1, 1));
        }
        table.add(new Cell(n - 2, -1, 1));
    }

    static class Cell {
        int prev;
        int next;
        int status;

        Cell(int prev, int next, int status) {
            this.prev = prev;
            this.next = next;
            this.status = status;
        }
    }
}