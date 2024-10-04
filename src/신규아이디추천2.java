public class 신규아이디추천2 {

    public static void main(String[] args) {
        String new_id = "=.=";
        String res = solution(new_id);
        System.out.println(res);
    }

    static String solution(String new_id) {
        String answer = "";
        answer = new_id.toLowerCase();
        answer = answer.replaceAll("[^a-z0-9\\-\\_.]", "");
        answer = answer.replaceAll("\\.{2,}", ".");
        if(answer.charAt(0) == '.') {
            answer = answer.substring(1);
        }
        if(answer.length() > 0 && answer.charAt(answer.length() - 1) == '.') {
            answer = answer.substring(0, answer.length() - 1);
        }
        if(answer.equals("")) {
            answer += "a";
        }
        if(answer.length() >= 16) {
            answer = answer.substring(0, 15);
            if(answer.charAt(14) == '.') {
                answer = answer.substring(0, 14);
            }
        }
        if(answer.length() <= 2) {
            char last = answer.charAt(answer.length() - 1);
            while(answer.length()!=3) {
                answer += "" + last;
            }
        }
        return answer;
    }
}
