public class AdPlacement {
    static String solution(String play_time, String adv_time, String[] logs){
        if(play_time.equals(adv_time))  return "00:00:00";

        int playSecs = fromTimeToSec(play_time);
        int advSecs = fromTimeToSec(adv_time);

        int[] visited = new int[playSecs+1];
        for(String log: logs){
            String[] split = log.split("-");
            int startSecs = fromTimeToSec(split[0]);
            int endSecs = fromTimeToSec(split[1]);

            for(int i=startSecs; i<endSecs; i++)
                visited[i]++;
        }

        long tmpOverlap = 0;
        int start = 0;
        int end = advSecs;
        for(int i=start; i<end; i++)
            tmpOverlap += visited[i];

        long realOverlap = tmpOverlap;
        int maxStart = start;
        while(end<=playSecs){
            tmpOverlap -= visited[start];
            tmpOverlap += visited[end];

            if(tmpOverlap>realOverlap){
                realOverlap = tmpOverlap;
                maxStart = start+1;
            }
            start++;
            end++;
        }

        return fromSecToTime(maxStart);
    }

    static Integer fromTimeToSec(String time){
        String[] split = time.split(":");
        int hrs = Integer.parseInt(split[0]);
        int mins = Integer.parseInt(split[1]);
        int secs = Integer.parseInt(split[2]);
        return hrs*3600 + mins*60 + secs;
    }

    static String fromSecToTime(int startSecs){
        int secs = startSecs % 60;
        String secsStr = String.format("%02d", secs);
        startSecs /= 60;
        int mins = startSecs % 60;
        String minsStr = String.format("%02d", mins);
        startSecs /= 60;
        String hrsStr = String.format("%02d", startSecs);
        return hrsStr + ":" + minsStr + ":" + secsStr;
    }

    public static void main(String[] args) {
        String play_time = "02:03:55";
        String adv_time = "00:14:15";
        String[] logs = { "01:20:15-01:45:14", "00:40:31-01:00:00", "00:25:50-00:48:29", "01:30:59-01:53:29", "01:37:44-02:02:30" };
        String answer = solution(play_time, adv_time, logs);
        System.out.println(answer);
    }
}