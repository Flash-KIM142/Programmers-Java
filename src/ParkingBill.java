import java.util.*;

public class ParkingBill {
    static HashMap<Integer, Queue<String>> map = new HashMap<>();
    static ArrayList<Integer> carList = new ArrayList<>();

    static int[] solution(int[] fees, String[] records){
        for(String record: records){
            StringTokenizer stk = new StringTokenizer(record);
            String time = stk.nextToken();
            int carNo = Integer.parseInt(stk.nextToken());

            if(!map.containsKey(carNo)){
                Queue<String> tmp = new LinkedList<>();
                map.put(carNo, tmp);
            }
            map.get(carNo).add(time);
            if(!carList.contains(carNo))    carList.add(carNo);
        }
        Collections.sort(carList);

        int[] answer = new int[carList.size()];
        int idx = 0;
        for(int num: carList){
            Queue<String> tmp = map.get(num);
            if(tmp.size()%2!=0) tmp.add("23:59");
            int total = 0;

            while(!tmp.isEmpty()){
                String in = tmp.poll();
                String out = tmp.poll();
                total += timeLap(in, out);
            }

            if(total<=fees[0])  answer[idx++] = fees[1];
            else{
                int units = ((total - fees[0]) / fees[2]);
                if(((total - fees[0]) % fees[2])!=0)    units += 1;
                answer[idx++] = fees[1] + units * fees[3];
            }
        }
        return answer;
    }

    static Integer timeLap(String in, String out){
        String[] split = in.split(":");
        int inMins = Integer.parseInt(split[0])*60 + Integer.parseInt(split[1]);

        split = out.split(":");
        int outMins = Integer.parseInt(split[0])*60 + Integer.parseInt(split[1]);

        return outMins - inMins;
    }

    public static void main(String[] args) {
        int[] fees = { 180, 5000, 10, 600 };
        String[] records = { "05:34 5961 IN", "06:00 0000 IN", "06:34 0000 OUT", "07:59 5961 OUT", "07:59 0148 IN", "18:59 0000 IN", "19:09 0148 OUT", "22:59 5961 IN", "23:00 5961 OUT" };
        int[] answer = solution(fees, records);
        for(int bill: answer) System.out.println(bill + " ");
    }
}