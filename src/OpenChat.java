import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

public class OpenChat {
    static HashMap<String, String> map = new HashMap<>();
    static ArrayList<String[]> msg = new ArrayList<>();

    static String[] solution(String[] record){
        for(String r: record){
            StringTokenizer stk = new StringTokenizer(r);
            String cmd = stk.nextToken();
            String uid = stk.nextToken();
            String nickname = "";

            if(cmd.equals("Enter")){ // 입장
                nickname = stk.nextToken();
                if(!map.containsKey(uid))
                    map.put(uid, nickname);
                else
                    map.replace(uid, nickname);

                String[] tmp = { cmd, uid };
                msg.add(tmp);
            }
            else if(cmd.equals("Change")){ // 변경
                nickname = stk.nextToken();
                map.replace(uid, nickname);
            }else{ // 퇴장
                String[] tmp = { cmd, uid };
                msg.add(tmp);
            }
        }

        String[] answer = new String[msg.size()];
        int idx = 0;
        for(String[] tmp: msg){
            String cmd = tmp[0];
            String uid = tmp[1];

            if(cmd.equals("Enter"))
                answer[idx++] = map.get(uid) + "님이 들어왔습니다.";
            else
                answer[idx++] = map.get(uid) + "님이 나갔습니다.";
        }
        return answer;
    }

    public static void main(String args[]) {
        String[] record = { "Enter uid1234 Muzi", "Enter uid4567 Prodo","Leave uid1234","Enter uid1234 Prodo","Change uid4567 Ryan" };
        String[] result = solution(record);
        for(String line: result) System.out.println(line);
    }
}