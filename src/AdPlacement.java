public class AdPlacement {
    static String solution(String play_time, String adv_time, String[] logs) {
        int playTime = timeToSeconds(play_time);
        int advTime = timeToSeconds(adv_time);
        if(playTime==advTime)   return "00:00:00";
        int[] visited = new int[playTime+1];

        for(String log: logs){
            String[] times = log.split("-");
            int start = timeToSeconds(times[0]);
            int end = timeToSeconds(times[1]);

            for(int i=start; i<end; i++)
                visited[i]++;
        }
        /** 초기값 세팅 */
        int start = 0;
        int end = advTime;
        long sum = 0;
        for(int i=start; i<end; i++)
            sum += visited[i];

        /** 최대 누적 구간의 시작점 찾기 */
        long max = sum;
        long maxStart = start;
        while(end<=playTime){
            sum -= visited[start];
            sum += visited[end];

            if(sum>max){
                max = sum;
                maxStart = start + 1;
            }

            start++;
            end++;
        }

        return secondsToTime(maxStart);
    }

    static Integer timeToSeconds(String time){
        String[] split = time.split(":");
        int hrs = Integer.parseInt(split[0]);
        int mins = Integer.parseInt(split[1]);
        int secs = Integer.parseInt(split[2]);

        return hrs*3600 + mins*60 + secs;
    }

    static String secondsToTime(long seconds){
        int hrs = (int)seconds / 3600;
        int rest = (int)seconds % 3600;
        int mins = rest / 60;
        rest %= 60;
        int secs = rest;

        return String.format("%02d:%02d:%02d", hrs, mins, secs);
    }

    public static void main(String[] args) {
        String play_time = "02:03:55";
        String adv_time = "00:14:15";
        String[] logs = { "01:20:15-01:45:14", "00:40:31-01:00:00", "00:25:50-00:48:29", "01:30:59-01:53:29", "01:37:44-02:02:30" };
        String answer = solution(play_time, adv_time, logs);
        System.out.println(answer);
    }
}