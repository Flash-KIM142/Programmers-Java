// 2023.11.20 2023 카카오 블라인드

import java.util.ArrayList;
import java.util.List;

public class 표병합 {

    static String[][] map = new String[51][51];
    static Pos[][] merged = new Pos[51][51];

    public static String[] solution(String[] commands) {
        init();
        List<String> answer = new ArrayList<>();
        for (String cmd : commands) {
            String[] split = cmd.split(" ");
            String cmdType = split[0];

            switch (cmdType) {
                case "UPDATE":
                    update(split);
                    break;
                case "MERGE":
                    merge(split);
                    break;
                case "UNMERGE":
                    unmerge(split);
                    break;
                case "PRINT":
                    int r = Integer.parseInt(split[1]);
                    int c = Integer.parseInt(split[2]);

                    int mergedR = merged[r][c].r;
                    int mergedC = merged[r][c].c;
                    String value = map[mergedR][mergedC];

                    if (value.isEmpty()) {
                        answer.add("EMPTY");
                    } else {
                        answer.add(value);
                    }
                    break;
            }
        }

        return answer.toArray(new String[answer.size()]);
    }

    static void update(String[] args) {
        if (args.length == 4) {
            int r = Integer.parseInt(args[1]);
            int c = Integer.parseInt(args[2]);
            String value = args[3];

            int mergedR = merged[r][c].r;
            int mergedC = merged[r][c].c;

            map[mergedR][mergedC] = value;
        } else {
            String value1 = args[1];
            String value2 = args[2];

            for (int r = 1; r < 51; r++) {
                for (int c = 1; c < 51; c++) {
                    if (map[r][c].equals(value1)) {
                        map[r][c] = value2;
                    }
                }
            }
        }
    }

    static void merge(String[] args) {
        int r1 = Integer.parseInt(args[1]);
        int c1 = Integer.parseInt(args[2]);
        int r2 = Integer.parseInt(args[3]);
        int c2 = Integer.parseInt(args[4]);

        if (r1 == r2 && c1 == c2) {
            return;
        }

        int mergedR1 = merged[r1][c1].r;
        int mergedC1 = merged[r1][c1].c;
        int mergedR2 = merged[r2][c2].r;
        int mergedC2 = merged[r2][c2].c;

        if (mergedR1 == mergedR2 && mergedC1 == mergedC2) {
            return;
        }

        for (int r = 1; r < 51; r++) {
            for (int c = 1; c < 51; c++) {
                if (merged[r][c].r == mergedR2 && merged[r][c].c == mergedC2) {
                    merged[r][c] = new Pos(mergedR1, mergedC1);
                }
            }
        }

        if (map[mergedR1][mergedC1].isEmpty() && !map[mergedR2][mergedC2].isEmpty()) {
            map[mergedR1][mergedC1] = map[mergedR2][mergedC2];
        } else {
            map[mergedR2][mergedC2] = map[mergedR1][mergedC1];
        }
    }

    static void unmerge(String[] args) {
        int r = Integer.parseInt(args[1]);
        int c = Integer.parseInt(args[2]);

        int mergedR = merged[r][c].r;
        int mergedC = merged[r][c].c;
        String value = map[mergedR][mergedC];

        for (int i = 1; i < 51; i++) {
            for (int j = 1; j < 51; j++) {
                if (merged[i][j].r == mergedR && merged[i][j].c == mergedC) {
                    merged[i][j] = new Pos(i, j);
                    map[i][j] = "";
                }
            }
        }
        map[r][c] = value;
    }

    static void init() {
        for (int r = 1; r < 51; r++) {
            for (int c = 1; c < 51; c++) {
                merged[r][c] = new Pos(r, c);
                map[r][c] = "";
            }
        }
    }

    static class Pos {
        int r;
        int c;

        Pos(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    public static void main(String[] args) {
        String[] result = solution(
                new String[]{"UPDATE 1 1 menu", "MERGE 1 1 1 2", "MERGE 1 1 1 3", "MERGE 1 1 1 4", "MERGE 1 2 1 3",
                        "UPDATE 1 1 hansik", "PRINT 1 1", "PRINT 1 2", "PRINT 1 3", "PRINT 1 4"});
        for (String s : result) {
            System.out.println(s);
        }
    }
}