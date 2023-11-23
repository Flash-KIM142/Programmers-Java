// 2023.11.22 2021 카카오 블라인드, 실패

public class 광고삽입 {

    static int[] playedCnt = new int[360000];
    static int videoLen;
    static int adLen;

    public static String solution(String play_time, String adv_time, String[] logs) {
        if (play_time.equals(adv_time)) {
            return "00:00:00";
        }

        videoLen = timeToSeconds(play_time);
        adLen = timeToSeconds(adv_time);

        /* 초당 누적 재생수 초기화 */
        for (String log : logs) {
            String[] logSplit = log.split("-");
            int start = timeToSeconds(logSplit[0]);
            int end = timeToSeconds(logSplit[1]);

            for (int i = start; i < end; i++) {
                playedCnt[i]++;
            }
        }

        /* 누적 재생시간 가장 긴 구간 찾기 */
        long max = 0;
        int resultInSeconds = 0;
        for (int i = 0; i < adLen; i++) {
            max += playedCnt[i];
        }
        long tmpMax = max;
        for (int i = 0, j = adLen; j < videoLen; i++, j++) {
            tmpMax += playedCnt[j] - playedCnt[i];

            if (tmpMax > max) {
                max = tmpMax;
                resultInSeconds = i + 1;
            }
        }

        return secondsToTime(resultInSeconds);
    }

    static int timeToSeconds(String time) {
        String[] splits = time.split(":");
        return Integer.parseInt(splits[0]) * 3600 + Integer.parseInt(splits[1]) * 60 + Integer.parseInt(splits[2]);
    }

    static String secondsToTime(int seconds) {
        int hrs = seconds / 3600;
        seconds %= 3600;
        int mins = seconds / 60;
        seconds %= 60;

        String hrsStr = hrs + "";
        String minsStr = mins + "";
        String secondsStr = seconds + "";

        if (hrsStr.length() != 2) {
            hrsStr = "0" + hrsStr;
        }
        if (minsStr.length() != 2) {
            minsStr = "0" + minsStr;
        }
        if (secondsStr.length() != 2) {
            secondsStr = "0" + secondsStr;
        }

        return hrsStr + ":" + minsStr + ":" + secondsStr;
    }


    public static void main(String[] args) {
        String play_time = "00:00:10";
        String adv_time = "00:00:04";
        String[] logs = new String[]{"00:00:01-00:00:03", "00:00:02-00:00:03", "00:00:02-00:00:04",
                "00:00:06-00:00:08", "00:00:07-00:00:08", "00:00:07-00:00:09", "00:00:08-00:00:10"};
        String result = solution(play_time, adv_time, logs);
        System.out.println(result);
    }
}