import java.util.HashMap;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class OpenChat {
    static HashMap<String, String> uidName = new HashMap<>();
    static LinkedList<String[]> uidAction = new LinkedList<>(); // Enter:1, Leave:2, Change:3

    static String[] solution(String[] record) {
        operateOrder(record);
        String[] answer = new String[uidAction.size()];
        int msgIdx = 0;

        for(String[] msg: uidAction){
            String uid = msg[0];
            int actionNum = Integer.parseInt(msg[1]);
            switch(actionNum){
                case 1: // 입장
                    answer[msgIdx] = uidName.get(uid) + "님이 들어왔습니다.";
                    break;
                case 2: // 퇴장
                    answer[msgIdx] = uidName.get(uid) + "님이 나갔습니다.";
                    break;
            }
            msgIdx++;
        }
        return answer;
    }

    static void operateOrder(String[] record){
        for(String order: record){
            StringTokenizer stk = new StringTokenizer(order);
            String action = stk.nextToken();
            String uid = stk.nextToken();

            switch(action){
                case "Enter": // action1
                    uidName.put(uid, stk.nextToken());
                    uidAction.add(new String[]{uid, "1"});
                    break;
                case "Leave": // action2
                    uidAction.add(new String[]{uid, "2"});
                    break;
                case "Change": // action3
                    uidName.put(uid, stk.nextToken());
                    break;
            }
        }
    }

    public static void main(String args[]) {
        String[] record = { "Enter uid1234 Muzi", "Enter uid4567 Prodo","Leave uid1234","Enter uid1234 Prodo","Change uid4567 Ryan" };
        String[] result = solution(record);
        for(String line: result) System.out.println(line);
    }
}