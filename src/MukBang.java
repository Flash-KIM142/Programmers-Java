public class MukBang {
    static int answer;

    static int solution(int[] food_times, long k) {
        int foodNum = food_times.length;
        long round = k / foodNum; // 온전한 바퀴수

        dfs(food_times, foodNum, round, k);
        return answer;
    }

    static void dfs(int[] food_times, int foodNum, long round, long idx){
        if(idx<=foodNum){ // 인덱스가 음식의 개수 이하일 때 재귀를 종료해주자.
            while(idx>=0){
                int flag = 0;
                for (int i = 0; i < foodNum; i++) {
                    if (food_times[i] == 0) { // 다 먹은 음식 개수 세고 바로 넘기자
                        flag++;
                        continue;
                    }
                    if (idx == 0) {
                        answer = i+1;
                        return;
                    }

                    food_times[i] -= 1;
                    idx--;
                }
                if(flag==foodNum)   break; // 싹 다 먹어서 남은 거 없으면 while문 종료
            }
            answer = -1; // while문 종료돼서 여기로 왔다는 건 결국 더이상 먹을 게 없다는 뜻
            return;
        }

        idx %= foodNum; // 온전한 바퀴수를 돌고나서 몇 번째 음식에 도달했는지
        for(int i=0; i<food_times.length; i++) {
            if (food_times[i] < round) // 뛰어 넘어야 할 녀석들이 있었다면
                idx += (round - food_times[i]); // 몇 번이나 뛰어넘었는지 idx에 추가해주자
            food_times[i] = (food_times[i]-round<0 ? 0 : food_times[i]-(int)round); // 다 먹었으면 0, 아직 남아있으면 남은 양으로 갱신
        }
        dfs(food_times, foodNum, idx/foodNum, idx);
    }

    public static void main(String[] args) {
        int[] food_times = {3,1,1,3};
        int k = 7;
        int answer = solution(food_times, k);
        System.out.println(answer);
    }
}