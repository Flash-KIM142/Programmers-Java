public class NewIdRecommend2 {

    static String solution(String new_id) {
        String result = "";

        // 1단계
        result = new_id.toLowerCase();

        // 2단계
        result = result.replaceAll("[^\\w\\-_.]*", "");

        // 3단계
        result = result.replaceAll("\\.{2,}", ".");

        // 4단계
        result = result.replaceAll("^\\.|\\.$", "");

        // 5단계
        if(result.length()==0){
            result = "a";
        }

        // 6단계
        if(result.length()>=16){
            result = result.substring(0, 15);
            result = result.replaceAll("\\.$", "");
        }

        // 7단계
        if(result.length()<=2){
            char last = result.charAt(result.length()-1);
            while(result.length()!=3){
                result += last;
            }
        }

        return result;
    }
}
