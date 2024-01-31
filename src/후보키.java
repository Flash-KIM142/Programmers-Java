// 2023.11.24 2019 카카오 블라인드, 소요시간: 62분

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class 후보키 {

    static int row, col;
    static List<Integer> notUnique = new ArrayList<>();
    static List<int[]> possibleCombinationCandidateKeys = new ArrayList<>();

    public static int solution(String[][] relation) {
        row = relation.length;
        col = relation[0].length;

        /* 단독 후보키 찾는 과정 */
        Set[] findUnique = new Set[col];
        for (int i = 0; i < col; i++) {
            findUnique[i] = new HashSet<String>();
        }
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                findUnique[j].add(relation[i][j]);
            }
        }

        boolean[] isUnique = new boolean[col];
        for (int i = 0; i < col; i++) {
            if (findUnique[i].size() == row) {
                isUnique[i] = true;
            }
        }

        /* 조합 후보키 찾는 과정 */
        for (int i = 0; i < col; i++) {
            if (!isUnique[i]) {
                notUnique.add(i);
            }
        }
        for (int i = 2; i <= notUnique.size(); i++) {
            combination(new int[i], 0, 0, i, relation);
        }

        if (possibleCombinationCandidateKeys.size() == 0 || possibleCombinationCandidateKeys.size() == 1) {
            return col - notUnique.size() + possibleCombinationCandidateKeys.size();
        }

        /* 유일성 못 지키는 후보키 제외 */
        L:
        for (int i = possibleCombinationCandidateKeys.size() - 1; i > 0; i--) {
            int[] target = possibleCombinationCandidateKeys.get(i);
            Set<Integer> targetSet = new HashSet<>();
            for (int t : target) {
                targetSet.add(t);
            }

            for (int j = 0; j < i; j++) {
                int[] validate = possibleCombinationCandidateKeys.get(j);
                Set<Integer> validateSet = new HashSet<>();
                for (int v : validate) {
                    validateSet.add(v);
                }

                if (targetSet.containsAll(validateSet)) {
                    possibleCombinationCandidateKeys.remove(i);
                    continue L;
                }
            }
        }

        return col - notUnique.size() + possibleCombinationCandidateKeys.size();
    }

    static void combination(int[] targets, int idx, int cnt, int size, String[][] relation) {
        if (cnt == size) {
            Set<String> composites = new HashSet<>();
            for (int i = 0; i < row; i++) {
                StringBuilder sb = new StringBuilder();
                for (int t : targets) {
                    sb.append(relation[i][t]);
                }
                composites.add(sb.toString());
            }

            if (composites.size() == row) {
                int[] tmp = targets.clone();
                possibleCombinationCandidateKeys.add(tmp);
            }
            return;
        }

        for (int i = idx; i < notUnique.size(); i++) {
            targets[cnt] = notUnique.get(i);
            combination(targets, i + 1, cnt + 1, size, relation);
        }
    }

    public static void main(String[] args) {
        String[][] relation = new String[][]{{"100", "ryan", "music", "2"}, {"200", "apeach", "math", "2"},
                {"300", "tube", "computer", "3"}, {"400", "con", "computer", "4"}, {"500", "muzi", "music", "3"},
                {"600", "apeach", "music", "2"}};
        int result = solution(relation);
        System.out.println(result);
    }
}