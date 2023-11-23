// 2023.11.22 2021 카카오 블라인드, 소요시간: 23분

import java.util.Locale;

public class 신규아이디추천 {

    public static String solution(String new_id) {
        String answer = "";
        answer = new_id.toLowerCase(Locale.ROOT);
        answer = answer.replaceAll("[^a-z0-9-_\\.]", "");
        while (answer.contains("..")) {
            answer = answer.replace("..", ".");
        }
        if (answer.length() != 0 && answer.charAt(0) == '.') {
            answer = answer.substring(1);
        }
        if (answer.length() != 0 && answer.charAt(answer.length() - 1) == '.') {
            answer = answer.substring(0, answer.length() - 1);
        }
        if (answer.isEmpty()) {
            answer = "a";
        }
        if (answer.length() >= 16) {
            answer = answer.substring(0, 15);
            if (answer.charAt(answer.length() - 1) == '.') {
                answer = answer.substring(0, answer.length() - 1);
            }
        }
        if (answer.length() <= 2) {
            char end = answer.charAt(answer.length() - 1);
            while (answer.length() < 3) {
                answer += end;
            }
        }
        return answer;
    }

    public static void main(String[] args) {
        String result = solution("abcdefg.");
        System.out.println(result);
    }
}