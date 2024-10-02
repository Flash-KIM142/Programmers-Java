import java.util.*;

public class 성격유형검사하기2 {

    public String solution(String[] survey, int[] choices) {
        String answer = "";
        /* 유형별 점수 초기화 */
        Map<Character, Integer> score_board = new HashMap<>();
        score_board.put('R', 0);
        score_board.put('T', 0);
        score_board.put('C', 0);
        score_board.put('F', 0);
        score_board.put('J', 0);
        score_board.put('M', 0);
        score_board.put('A', 0);
        score_board.put('N', 0);

        /* survey 돌며 유형별 점수 기록 */
        for(int i=0; i<survey.length; i++){
            if(choices[i] == 4){
                continue;
            }
            else if(choices[i]<4){ // survey[0]에 점수 부과
                score_board.put(survey[i].charAt(0), score_board.get(survey[i].charAt(0)) + 4 - choices[i]);
            }
            else{ // survey[1] 에 점수 부과
                score_board.put(survey[i].charAt(1), score_board.get(survey[i].charAt(1)) + choices[i] - 4);
            }
        }

        answer += score_board.get('R') >= score_board.get('T') ? "R" : "T";
        answer += score_board.get('C') >= score_board.get('F') ? "C": "F";
        answer += score_board.get('J') >= score_board.get('M') ? "J": "M";
        answer += score_board.get('A') >= score_board.get('N') ? "A": "N";

        return answer;
    }
}
