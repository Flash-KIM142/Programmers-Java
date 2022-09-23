public class Bridge2 {

    static int solution(int[] stones, int k) {
        int min =1, max = 200000000;
        int answer = 0;

        while(min<=max){
            int mid = (min+max)/2;

            if(isPossible(stones, k, mid)){
                answer = (answer>=mid ? answer: mid);
                min = mid + 1;
            }
            else    max = mid - 1;
        }

        return answer;
    }

    static boolean isPossible(int[] stones, int k, int mid){
        int n = 0;
        for(int stone: stones){
            if(stone-mid<0) n++;
            else    n = 0;

            if(n==k)    return false;
        }

        return true;
    }
}
