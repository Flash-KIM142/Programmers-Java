// 2023.11.24 2019 카카오 블라인드, 소요시간: 25분

import java.util.HashMap;
import java.util.Map;

public class 오픈채팅방 {

    static int messageIdx = 0;
    static Map<String, String> nameMap = new HashMap<>(); // <아이디, 이름>
    static Map<String, Map<Integer, Integer>> messageMap = new HashMap<>(); // <아이디, <메시지인덱스, 메지지 타입>>
    static String[] messageType = {"님이 들어왔습니다.", "님이 나갔습니다."};

    public static String[] solution(String[] record) {
        for (String r : record) {
            String[] split = r.split(" ");
            Map<Integer, Integer> curMessageState;

            switch (split[0]) {
                case "Enter":
                    nameMap.put(split[1], split[2]);
                    messageMap.putIfAbsent(split[1], new HashMap<>());
                    curMessageState = messageMap.get(split[1]);
                    curMessageState.put(messageIdx++, 0);
                    messageMap.put(split[1], curMessageState);
                    break;
                case "Leave":
                    curMessageState = messageMap.get(split[1]);
                    curMessageState.put(messageIdx++, 1);
                    messageMap.put(split[1], curMessageState);
                    break;
                case "Change":
                    nameMap.put(split[1], split[2]);
                    break;
            }
        }

        String[] answer = new String[messageIdx];
        for (String id : messageMap.keySet()) {
            Map<Integer, Integer> messages = messageMap.get(id);
            String name = nameMap.get(id);
            for (int i : messages.keySet()) {
                answer[i] = name + messageType[messages.get(i)];
            }
        }
        return answer;
    }

    public static void main(String[] args) {
        String[] record = new String[]{"Enter uid1234 Muzi", "Enter uid4567 Prodo", "Leave uid1234",
                "Enter uid1234 Prodo", "Change uid4567 Ryan"};
        String[] result = solution(record);
        for (String s : result) {
            System.out.println(s);
        }
    }
}