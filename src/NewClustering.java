import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class NewClustering {
    static ArrayList<String> str1Set = new ArrayList<>();
    static ArrayList<String> str2Set = new ArrayList<>();

    static int solution(String str1, String str2) {
        makeStrSet(1, str1);
        makeStrSet(2, str2);
        if (str1Set.size() == 0 && str2Set.size() == 0) return 65536;

        int intersec = getIntersection();
        double tmpAnswer = (double) intersec / (str1Set.size() + str2Set.size() - intersec);
        double answer = Math.floor(tmpAnswer * 65536);

        return (int) answer;
    }

    static void makeStrSet(int num, String str) {
        ArrayList<String> set = new ArrayList<>();
        Pattern p = Pattern.compile("^[a-zA-Z]*$");

        for (int i = 0; i < str.length() - 1; i++) {
            StringBuilder sb = new StringBuilder();
            sb.append(str.charAt(i));
            sb.append(str.charAt(i + 1));
            Matcher m = p.matcher(sb.toString());
            if (m.find()) set.add(sb.toString());
            sb.append("");
        }

        if (num == 1) {
            str1Set = new ArrayList<>(set);
            Collections.copy(str1Set, set);
        } else {
            str2Set = new ArrayList<>(set);
            Collections.copy(str2Set, set);
        }
    }

    static Integer getIntersection() {
        int res = 0;
        boolean[] visited = new boolean[str2Set.size()];

        L1:
        for (String s1 : str1Set) {
            for (int i = 0; i < str2Set.size(); i++) {
                if (visited[i]) continue;
                String s2 = str2Set.get(i);
                boolean flag = s1.equalsIgnoreCase(s2);
                if (flag) {
                    visited[i] = true;
                    continue L1;
                }
            }
        }

        for (boolean flag : visited)
            if (flag) res++;

        return res;
    }

    public static void main(String args[]) throws IOException {
        BufferedWriter bfw = new BufferedWriter(new OutputStreamWriter(System.out));
        String str1 = "aa1+aa2";
        String str2 = "AAAA12";
        int result = solution(str1, str2);
        bfw.write(result + "");
        bfw.close();
    }
}