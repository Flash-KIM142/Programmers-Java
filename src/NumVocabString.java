import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.HashMap;

public class NumVocabString {
    static HashMap<String, String> map = new HashMap<>();

    static int solution(String s) {
        String answer = "";
        mapInit();

        String cur ="";
        for(int i=0; i<s.length(); i++){
            char ch = s.charAt(i);
            if(!Character.isDigit(ch)){ // 문자면
                cur += ch;
                if(map.containsKey(cur)){ // cur 이 하나의 온전한 단어로 완성됐으면
                    answer += map.get(cur);
                    cur = "";
                }
                else    continue;
            }
            else{ // 숫자면
                answer += ch;
            }
        }

        return Integer.parseInt(answer);
    }

    static void mapInit(){
        map.put("zero", "0"); map.put("one", "1");  map.put("two", "2");  map.put("three", "3");
        map.put("four", "4"); map.put("five", "5"); map.put("six", "6");  map.put("seven", "7");
        map.put("eight", "8");    map.put("nine", "9");
    }

    public static void main(String args[]) throws IOException {
        BufferedWriter bfw = new BufferedWriter(new OutputStreamWriter(System.out));
        String s = "one4seveneight";
        bfw.write(String.valueOf(solution(s)));
        bfw.close();
    }
}