// 2023.11.22 2022 카카오 블라인드, 소요시간: 57분

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class 양궁대회 {

    static int[] appeach;
    static List<int[]> ryanCandidates = new ArrayList<>();
    static int maxDiff = -1;

    public static int[] solution(int n, int[] info) {
        appeach = info;
        backtracking(new int[11], 0, 0, n);

        if (maxDiff == -1) {
            return new int[]{-1};
        }
        if (ryanCandidates.size() == 1) {
            return ryanCandidates.get(0);
        } else {
            ryanCandidates.sort(new Comparator<int[]>() {
                @Override
                public int compare(int[] o1, int[] o2) {
                    for (int i = 10; i >= 0; i--) {
                        if (o1[i] == o2[i]) {
                            continue;
                        }
                        return o2[i] - o1[i];
                    }
                    return -1;
                }
            });

            return ryanCandidates.get(0);
        }
    }

    static void backtracking(int[] cur, int idx, int cnt, int n) {
        if (cnt == n) {
            int tmpDiff = getDiff(cur);
            if (tmpDiff <= 0) {
                return;
            }

            int[] candidate = new int[11];
            for (int i = 0; i < 11; i++) {
                candidate[i] = cur[i];
            }

            if (tmpDiff > maxDiff) {
                ryanCandidates = new ArrayList<>();
                ryanCandidates.add(candidate);
                maxDiff = tmpDiff;
            } else if (tmpDiff == maxDiff) {
                ryanCandidates.add(candidate);
            }

            return;
        }

        for (int i = idx; i < 11; i++) {
            int appeachRecord = appeach[i];
            if (cnt + appeachRecord + 1 > n) {
                cur[i] = n - cnt;
                cnt += cur[i];
                backtracking(cur, i + 1, cnt, n);
                cnt -= cur[i];
                cur[i] = 0;
                continue;
            }

            cur[i] = appeachRecord + 1;
            cnt += cur[i];
            backtracking(cur, i + 1, cnt, n);
            cnt -= cur[i];
            cur[i] = 0;
        }
    }

    static int getDiff(int[] ryan) {
        int appeachPoint = 0;
        int ryanPoint = 0;

        for (int i = 0; i < 11; i++) {
            if (appeach[i] == 0 && ryan[i] == 0) {
                continue;
            }
            if (appeach[i] >= ryan[i]) {
                appeachPoint += (10 - i);
            } else {
                ryanPoint += (10 - i);
            }
        }

        return ryanPoint - appeachPoint;
    }

    public static void main(String[] args) {
        int[] result = solution(9, new int[]{0, 0, 1, 2, 0, 1, 1, 1, 1, 1, 1});
        for (int n : result) {
            System.out.println(n);
        }
    }
}