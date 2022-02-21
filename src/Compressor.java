import java.util.ArrayList;
import java.util.HashMap;

public class Compressor {
    static HashMap<String, Integer> map = new HashMap<>();

    static int[] solution(String msg) {
        ArrayList<Integer> list = new ArrayList<>();
        dictInit();
        while(msg.length()!=0){
            if(map.containsKey(msg)) {
                list.add(map.get(msg));
                break;
            }
            String tmp = "";
            int idx = 0;
            for(; idx<msg.length(); idx++){
                tmp += msg.charAt(idx);
                if(!map.containsKey(tmp)){ // 사전에 없는 단어면
                    map.put(tmp, map.size()+1);
                    tmp = tmp.substring(0, tmp.length()-1);
                    list.add(map.get(tmp));
                    break;
                }
            }
            msg = msg.substring(idx);
        }
        int[] answer = list.stream().mapToInt(i -> i).toArray();
        return answer;
    }

    static void dictInit(){
        char cur = 'A';
        for(int i=0; i<26; i++){
            String s = Character.toString(cur);
            cur++;
            map.put(s, i+1);
        }
    }

    public static void main(String[] args) {
        String msg = "TOBEORNOTTOBEORTOBEORNOT";
        int[] answer = solution(msg);
        for(int a: answer) System.out.println(a);
    }
}