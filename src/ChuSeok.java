import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ChuSeok {
    static int solution(String[] lines) {
        ArrayList<Info> list = new ArrayList<>();
        ArrayList<Integer> overlap = new ArrayList<>();

        for(String line: lines)
            list.add(new Info(line));
        list.sort(Comparator.comparing(Info::getStart));

        for(Info cur: list){
//            Info target1 = new Info(cur.start, cur.start+999);
            Info target2 = new Info(cur.end, cur.end+999);
//            int target1OverLapNum = 0;
            int target2OverLapNum = 0;
            for(Info test: list){
//                if(test.isOverLap(target1)) target1OverLapNum++;
                if(test.isOverLap(target2)) target2OverLapNum++;
            }
//            if(overlap.indexOf(target1OverLapNum)<0)    overlap.add(target1OverLapNum);
            if(overlap.indexOf(target2OverLapNum)<0)    overlap.add(target2OverLapNum);
        }
        Collections.sort(overlap, Collections.reverseOrder());
        return overlap.get(0);
    }

    static class Info{
        int start;
        int end;

        Info(String line){
            String[] split = line.split("\\s");
            String endStr = split[1];
            String resStr = split[2];

            String[] endTimeSplit = endStr.split(":");
            int hrs = Integer.parseInt(endTimeSplit[0])*60*60*1000;
            int mins = Integer.parseInt(endTimeSplit[1])*60*1000;
            int secs = (int)(Double.parseDouble(endTimeSplit[2])*1000);
            this.end = hrs + mins + secs;

            resStr = resStr.replaceAll("s","");
            int resTime = (int)(Double.parseDouble(resStr)*1000);

            this.start = this.end - resTime + 1;
        }

        Info(int start, int end){
            this.start = start;
            this.end = end;
        }

        Boolean isOverLap(Info target){
            if(target.end < this.start || this.end < target.start)  return false;
            return true;
        }

        Integer getStart(){ return this.start; }
    }

    public static void main(String args[]){
        String[] lines = { "2016-09-15 20:59:57.421 0.351s",
                "2016-09-15 20:59:58.233 1.181s",
                "2016-09-15 20:59:58.299 0.8s",
                "2016-09-15 20:59:58.688 1.041s",
                "2016-09-15 20:59:59.591 1.412s",
                "2016-09-15 21:00:00.464 1.466s",
                "2016-09-15 21:00:00.741 1.581s",
                "2016-09-15 21:00:00.748 2.31s",
                "2016-09-15 21:00:00.966 0.381s",
                "2016-09-15 21:00:02.066 2.62s" };
        int res = solution(lines);
        System.out.println(res);
    }
}