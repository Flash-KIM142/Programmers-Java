import java.util.Arrays;
import java.util.HashMap;

public class 가장많이받은선물2 {

    public static void main(String[] args) {
        String[] friends = {"muzi", "ryan", "frodo", "neo"};
        String[] gifts = {"muzi frodo", "muzi frodo", "ryan muzi", "ryan muzi", "ryan muzi", "frodo muzi", "frodo ryan", "neo muzi"};
        int res = solution(friends, gifts);
        System.out.println(res);
    }

    static int solution(String[] friends, String[] gifts) {
        int friends_num = friends.length;
        HashMap<String, Integer> friends_idx = new HashMap<>();
        for (int i = 0; i < friends_num; i++) {
            friends_idx.put(friends[i], i);
        }

        int[][] last_month = new int[friends_num][friends_num];
        int[] next_month = new int[friends_num];
        int[] present_score = new int[friends_num];

        for(int i=0; i< gifts.length; i++){
            String[] giver_getter = gifts[i].split(" ");
            int giver = friends_idx.get(giver_getter[0]);
            int getter = friends_idx.get(giver_getter[1]);

            last_month[giver][getter]++;
            present_score[giver]++;
            present_score[getter]--;
        }

        for(int i=0; i<friends_num; i++){
            for(int j=0; j<friends_num; j++){
                if(i==j)    continue;
                if(last_month[i][j]>last_month[j][i]){
                    next_month[i]++;
                }
                else if(last_month[i][j]==last_month[j][i]){
                    if(present_score[i] == present_score[j])    continue;
                    if(present_score[i] > present_score[j]){
                        next_month[i]++;
                    }
                }
            }
        }

        Arrays.sort(next_month);

        return next_month[friends_num-1];
    }
}
