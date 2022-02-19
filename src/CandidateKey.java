import java.io.*;
import java.util.*;

public class CandidateKey {
    static int stuNum, atrNum;

    static int solution(String[][] relation) {
        int answer = 0;
        stuNum = relation.length;
        atrNum = relation[0].length;
        List<Integer> candidates = new LinkedList<>();

        for (int i = 1; i < (1 << atrNum); i++)
            if (check(relation, i)) candidates.add(i);

        Collections.sort(candidates,comp);
        while (candidates.size() != 0) {
            int n = candidates.remove(0);
            answer++;

            Iterator<Integer> itr = candidates.iterator();
            while (itr.hasNext()) {
                int cur = itr.next();
                if ((n & cur) == n) itr.remove();
            }
        }

        return answer;
    }

    static Boolean check(String[][] relation, int subsetBit) {
        for (int i = 0; i < stuNum - 1; i++) {
            for (int j = i+1; j < stuNum; j++) {
                boolean isSame = true;
                for (int k = 0; k < atrNum; k++) {
                    if ((subsetBit & (1 << k)) != 0) {
                        if (!relation[i][k].equals(relation[j][k])) {
                            isSame = false;
                            break;
                        }
                    }
                }
                if (isSame) return false;
            }
        }
        return true;
    }

    static Comparator<Integer> comp = new Comparator<Integer>() {
        Integer countBits(int n) {
            int res = 0;
            while (n != 0) {
                if ((n & 1) != 0) res++;
                n = n >> 1;
            }
            return res;
        }

        @Override
        public int compare(Integer o1, Integer o2) {
            int a = countBits(o1);
            int b = countBits(o2);

            if (a > b) return 1;
            else if (a < b) return -1;
            else return 0;
        }
    };

    public static void main(String args[]) throws IOException {
        BufferedWriter bfw = new BufferedWriter(new OutputStreamWriter(System.out));
        String[][] relation = {{"100", "ryan", "music", "2"}, {"200", "apeach", "math", "2"}, {"300", "tube", "computer", "3"}, {"400", "con", "computer", "4"}, {"500", "muzi", "music", "3"}, {"600", "apeach", "music", "2"}};
        int result = solution(relation);
        bfw.write(result + "");
        bfw.close();
    }
}