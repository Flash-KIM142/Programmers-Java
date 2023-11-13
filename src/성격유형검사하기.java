// 2023.11.14 2022 카카오 테크 인턴십

import java.util.HashMap;
import java.util.Map;

public class 성격유형검사하기 {

    static Map<Character, Integer> typeScore = new HashMap<>();

    public static String solution(String[] survey, int[] choices) {
        initTypeIdx();
        int surveyLen = survey.length;
        for (int i = 0; i < surveyLen; i++) {
            int choice = choices[i];
            char target;
            if (choice == 4) {
                continue;
            } else if (choice < 4) {
                target = survey[i].charAt(0);
                typeScore.put(target, typeScore.get(target) + 4 - choice);
            } else {
                target = survey[i].charAt(1);
                typeScore.put(target, typeScore.get(target) + choice - 4);
            }
        }

        return decideType();
    }

    static void initTypeIdx() {
        typeScore.put('R', 0);
        typeScore.put('T', 0);
        typeScore.put('C', 0);
        typeScore.put('F', 0);
        typeScore.put('J', 0);
        typeScore.put('M', 0);
        typeScore.put('A', 0);
        typeScore.put('N', 0);
    }

    static String decideType() {
        StringBuilder sb = new StringBuilder();

        if (typeScore.get('R') >= typeScore.get('T')) {
            sb.append('R');
        } else {
            sb.append('T');
        }

        if (typeScore.get('C') >= typeScore.get('F')) {
            sb.append('C');
        } else {
            sb.append('F');
        }

        if (typeScore.get('J') >= typeScore.get('M')) {
            sb.append('J');
        } else {
            sb.append('M');
        }

        if (typeScore.get('A') >= typeScore.get('N')) {
            sb.append('A');
        } else {
            sb.append('N');
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        String[] survey = new String[]{"AN", "CF", "MJ", "RT", "NA"};
        int[] choices = new int[]{5, 3, 2, 7, 5};
        String result = solution(survey, choices);
        System.out.println(result);
    }
}