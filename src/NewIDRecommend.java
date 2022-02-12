public class NewIDRecommend {
    static String solution(String new_id) {
        String answer = "";
        /** 1번 작업 */
        answer = new_id.toLowerCase();

        /** 2번 작업 */
        String match = "[^a-z\\d\\-_.]*";
        answer = answer.replaceAll(match, "");

        /** 3번 작업 */
        match = "\\.{2,}";
        answer = answer.replaceAll(match, ".");

        /** 4번 작업 */
        match = "^[.]|[.]$";
        answer = answer.replaceAll(match, "");

        /** 5번 작업 */
        if(answer.isEmpty())    answer += "a";

        /** 6번 작업 */
        if(answer.length()>15)  answer = answer.substring(0, 15);
        answer = answer.replaceAll("[.]$", "");

        /** 7번 작업 */
        if(answer.length()<=2){
            char last = answer.charAt(answer.length()-1);
            while(answer.length()!=3)   answer += last;
        }

        return answer;
    }

    public static void main(String[] args) {
        String new_id = "abcdefghijklmn.p";
        System.out.println(solution(new_id));
    }
}