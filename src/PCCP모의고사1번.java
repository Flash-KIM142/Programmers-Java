// 2023.11.23 PCCP 모의고사, 소요시간: 10분

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PCCP모의고사1번 {

    public static String solution(String input_string) {
        String answer = "";
        StringBuilder sb = new StringBuilder();
        char prev = input_string.charAt(0);
        sb.append(prev);

        for (int i = 1; i < input_string.length(); i++) {
            char cur = input_string.charAt(i);
            if (prev == cur) {
                continue;
            }

            sb.append(cur);
            prev = cur;
        }

        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < sb.length(); i++) {
            char cur = sb.charAt(i);
            map.putIfAbsent(cur, 0);
            map.put(cur, map.get(cur) + 1);
        }

        List<Character> list = new ArrayList<>();
        for (Character key : map.keySet()) {
            if (map.get(key) > 1) {
                list.add(key);
            }
        }
        Collections.sort(list);

        for (Character c : list) {
            answer += c;
        }
        if (answer.isEmpty()) {
            return "N";
        }
        return answer;
    }

    public static void main(String[] args) {
        String result = solution("edeaaabbccd");
        System.out.println(result);
    }
}