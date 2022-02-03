import java.io.*;
import java.util.HashMap;

public class HotelRoom {
    static HashMap<Long, Long> map = new HashMap<>();

    static long[] solution(long k, long[] room_number) {
        long[] answer = new long[room_number.length];
        for (int i = 0; i < room_number.length; i++)
            answer[i] = findEmptyRoom(room_number[i]);
        return answer;
    }

    static Long findEmptyRoom(long room_num) {
        if (!map.containsKey(room_num)) { // 빈 방이면 map에 다음 방 번호까지 함께 추가해주자
            map.put(room_num, room_num + 1);
            return room_num;
        }
        long empty = findEmptyRoom(map.get(room_num));
        map.put(room_num, empty);
        return empty;
    }

    public static void main(String args[]) throws IOException {
        BufferedWriter bfw = new BufferedWriter(new OutputStreamWriter(System.out));
        long k = 10;
        long[] room_number = {1, 3, 4, 1, 3, 1};
        long[] result = solution(k, room_number);
        for (long num : result) bfw.write(num + " ");
        bfw.close();
    }
}