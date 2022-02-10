import java.io.*;
import java.util.*;

public class ParkingBill {
    static int[] solution(int[] fees, String[] records) {
        int defaultTime = fees[0];  int defaultBill = fees[1];
        int unitTime = fees[2]; int unitBill = fees[3];
        HashMap<String, Info> map = new HashMap<>();

        for(String record: records){
            StringTokenizer stk = new StringTokenizer(record);
            String[] time = stk.nextToken().split(":");
            String carNum = stk.nextToken();
            int hrs = Integer.parseInt(time[0]);
            int mins = Integer.parseInt(time[1]);
            Time tmp = new Time(hrs, mins);

            if(map.get(carNum)==null)
                map.put(carNum, new Info(tmp));
            else
                map.get(carNum).record.add(tmp);
        }

        for(Info a: map.values()){
            a.getTotalBill(defaultTime,defaultBill,unitTime,unitBill);
        }
        Object[] mapKey = map.keySet().toArray();
        Arrays.sort(mapKey);

        int[] answer = new int[map.size()];
        int i = 0;
        for(Object key: mapKey){
            answer[i] = map.get(key).totalBill;
            i++;
        }
        return answer;
    }

    static class Info{
        Queue<Time> record = new LinkedList<>();
        int totalBill;

        Info(Time a){ record.add(a); }

        void getTotalBill(int defaultTime, int defaultBill, int unitTime, int unitBill){
            int result = 0;
            int totalMins = 0;
            Time before = new Time(0,0);
            Time after = new Time(0,0);
            while(!this.record.isEmpty()){
                if(this.record.size()!=1)   before = this.record.poll();
                else    break;
                after = this.record.poll();
                Time lap = after.getLap(before);
                totalMins += lap.hrs*60 + lap.mins;
            }

            if(!this.record.isEmpty()){
                Time last = this.record.poll();
                Time lap = new Time(23, 59).getLap(last);
                totalMins += lap.hrs*60 + lap.mins;
            }

            if(totalMins<=defaultTime)  result += defaultBill;
            else {
                result += defaultBill;
                totalMins -= defaultTime;

                int unitCnt = (totalMins % unitTime == 0 ? totalMins / unitTime : totalMins / unitTime + 1);
                result += unitCnt * unitBill;
            }
            this.totalBill = result;
        }
    }

    static class Time{
        int hrs;
        int mins;

        Time(int hrs, int mins){
            this.hrs = hrs;
            this.mins = mins;
        }

        Time getLap(Time before){
            if(this.mins - before.mins >=0)    return new Time(this.hrs-before.hrs, this.mins- before.mins);
            else    return new Time(this.hrs-before.hrs-1, this.mins- before.mins+60);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedWriter bfw = new BufferedWriter(new OutputStreamWriter(System.out));
        int[] fees = { 180, 5000, 10, 600 };
        String[] records = { "05:34 5961 IN", "06:00 0000 IN", "06:34 0000 OUT", "07:59 5961 OUT", "07:59 0148 IN", "18:59 0000 IN", "19:09 0148 OUT", "22:59 5961 IN", "23:00 5961 OUT" };
        int[] answer = solution(fees, records);
        for(int bill: answer)   bfw.write(bill + " ");
        bfw.close();
    }
}