// 2023.10.6 이분탐색

public class 입국심사 {

    public long solution(int n, int[] times) {
        long answer = 0;
        int len = times.length;
        long min = 1;
        long max = (long) times[len-1] * n;

        while(min<=max){
            long mid = (min + max) / 2;
            long cnt = 0;

            for(int i=0; i<len; i++)    cnt += mid / times[i];

            if(cnt<n){
                min = mid + 1;
            }
            else{
                answer = mid;
                max = mid - 1;
            }
        }

        return answer;
    }
}
