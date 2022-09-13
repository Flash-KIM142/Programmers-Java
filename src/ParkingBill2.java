import java.util.*;

public class ParkingBill2 {
    static HashMap<String, Queue<Integer>> recordMap = new HashMap<>();

    static int[] solution(int[] fees, String[] records) {
        List<Info> list = new ArrayList<>();
        init(records);

        for(String key: recordMap.keySet()){
            Queue<Integer> q = recordMap.get(key);
            int sum = 0;

            int in = -1;
            int out = -1;
            int cnt = 0;

            while(!q.isEmpty()){
                if(cnt%2==0){
                    in = q.poll();
                }
                else{
                    out = q.poll();
                }

                if(in!=-1 && out!=-1){
                    sum += (out-in);
                    in = 0;
                    out = 0;
                }
                cnt++;
            }

            Info cur = new Info(Integer.parseInt(key), sum);
            list.add(cur);
        }

        list.sort((o1, o2) -> o1.getCarNum() - o2.getCarNum());


        int[] answer = new int[list.size()];
        int idx = 0;
        for(Info cur: list){
            answer[idx++] = getFee(cur.getFee(), fees);
        }

        return answer;
    }

    static int getFee(int mins, int[] fees){
        if(mins<=fees[0])   return fees[1];

        int sum = fees[1];
        int additionalMins = (mins - fees[0]);
        int units;

        if(additionalMins%fees[2]!=0){
            units = (additionalMins/fees[2]) + 1;
        }
        else{
            units = additionalMins/fees[2];
        }

        sum += (units * fees[3]);

        return sum;
    }

    static void init(String[] records){
        for(String r: records){
            String[] split = r.split(" ");
            String timeStr = split[0];
            String numStr = split[1];
            String inOrOut = split[2];

            int timeInt = timeStrToInt(timeStr);

            if(!recordMap.containsKey(numStr)){
                Queue<Integer> tmp = new LinkedList<>();
                recordMap.put(numStr, tmp);
            }
            recordMap.get(numStr).add(timeInt);
        }

        for(String key: recordMap.keySet()){
            if(recordMap.get(key).size()%2 != 0){
                recordMap.get(key).add(23*60 + 59);
            }
        }
    }

    static int timeStrToInt(String timeStr){
        String[] split = timeStr.split(":");
        int hrs = Integer.parseInt(split[0]);
        int mins = Integer.parseInt(split[1]);

        return hrs * 60 + mins;
    }

    static class Info{
        int carNum;
        int fee;

        Info(int carNum, int fee){
            this.carNum = carNum;
            this.fee = fee;
        }

        int getCarNum(){ return this.carNum; }
        int getFee(){ return this.fee; }
    }

    public static void main(String[] args) {
        int[] fees = {1, 461, 1, 10};
        String[] records = {"00:00 1234 IN"};

        for(int a: solution(fees, records)){
            System.out.println(a);
        }
    }
}
