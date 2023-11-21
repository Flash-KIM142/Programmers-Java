// 2023.11.21 2022 카카오 블라인드, 소요시간: 26분

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class 주차요금계산 {

    static int baseTime;
    static int baseFee;
    static int unitTime;
    static int unitFee;
    static Map<String, Stack<String>> parkingRecord = new HashMap<>();
    static List<int[]> result = new ArrayList<>(); // [0]은 자동차 번호, [1]은 요금

    public static int[] solution(int[] fees, String[] records) {
        /* 요금 정보 초기화 */
        baseTime = fees[0];
        baseFee = fees[1];
        unitTime = fees[2];
        unitFee = fees[3];

        /* 주차 기록 초기화 */
        for (String record : records) {
            String[] split = record.split(" ");

            if (parkingRecord.containsKey(split[1])) {
                parkingRecord.get(split[1]).add(split[0]);
            } else {
                parkingRecord.put(split[1], new Stack<>());
                parkingRecord.get(split[1]).add(split[0]);
            }
        }

        /* 마지막 출차 기록 없는 차들 23:59 출차 처리 */
        for (String key : parkingRecord.keySet()) {
            if (parkingRecord.get(key).size() % 2 == 0) {
                continue;
            }
            parkingRecord.get(key).add("23:59");
        }

        /* 자동차별 요금 계산 */
        for (String key : parkingRecord.keySet()) {
            Stack<String> inAndOut = parkingRecord.get(key);
            int totalMinutes = 0;

            while (!inAndOut.isEmpty()) {
                String out = inAndOut.pop();
                String in = inAndOut.pop();

                totalMinutes += getMinutesBetweenInAndOut(in, out);
            }

            int totalFee = getTotalFee(totalMinutes);
            result.add(new int[]{Integer.parseInt(key), totalFee});
        }

        /* 자동차 번호 순서 오름차순 정렬 */
        result.sort(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });

        /* 정답 초기화 */
        int[] answer = new int[result.size()];
        for (int i = 0; i < answer.length; i++) {
            answer[i] = result.get(i)[1];
        }

        return answer;
    }

    static int getTotalFee(int totalMinutes) {
        if (totalMinutes <= baseTime) {
            return baseFee;
        }

        int extraTime = totalMinutes - baseTime;
        int units = extraTime / unitTime;
        if (extraTime % unitTime != 0) {
            units++;
        }

        return baseFee + units * unitFee;
    }

    static int getMinutesBetweenInAndOut(String in, String out) {
        String[] inSplit = in.split(":");
        String[] outSplit = out.split(":");
        int inHrs = Integer.parseInt(inSplit[0]);
        int inMins = Integer.parseInt(inSplit[1]);
        int outHrs = Integer.parseInt(outSplit[0]);
        int outMins = Integer.parseInt(outSplit[1]);

        return (outHrs - inHrs) * 60 + (outMins - inMins);
    }

    public static void main(String[] args) {
        int[] result = solution(new int[]{180, 5000, 10, 600},
                new String[]{"05:34 5961 IN", "06:00 0000 IN", "06:34 0000 OUT", "07:59 5961 OUT", "07:59 0148 IN",
                        "18:59 0000 IN", "19:09 0148 OUT", "22:59 5961 IN", "23:00 5961 OUT"});
        for (int n : result) {
            System.out.println(n);
        }
    }
}