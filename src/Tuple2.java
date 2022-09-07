import java.util.*;

public class Tuple2 {

    public static void main(String[] args) {
        for (int a: solution("{{20,111},{111}}")) {
            System.out.println(a);
        }
        solution("{{20,111},{111}}");
    }

    static int[] solution(String s) {
        String[] arr = s.replaceAll("[{}]", " ").trim().split(" , ");
        Arrays.sort(arr, (o1, o2) -> { return o1.length() - o2.length(); });

        int[] answer = new int[arr.length];
        int idx = 0;

        HashSet<String> set = new HashSet<>();
        for (String s1 : arr) {
            String[] tmp = s1.split(",");
            for (String s2 : tmp) {
                if(set.add(s2)){
                    answer[idx++] = Integer.parseInt(s2);
                }
            }
        }

        return answer;
    }
}
