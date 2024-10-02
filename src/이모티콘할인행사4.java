import java.util.*;

public class 이모티콘할인행사4 {

    public static void main(String[] args) {
        int[][] users = {{40, 10000}, {25, 10000}};
        int[] emoticons = {7000, 9000};
        int[] res = solution(users, emoticons);
        System.out.println(res[0] + " " + res[1]);
    }

    static List<int[]> discount_combination = new ArrayList<>();

    static int[] solution(int[][] users, int[] emoticons) {
        int[] answer = new int[2];
        List<Integer[]> candidates = new ArrayList<>();

        /* 모든 이모티콘의 할인율 설정해서 케이스 하나당 <가입자수 + 판매액> 조합을 초기화해야함 */
        get_discount_combination(emoticons.length, 0, new int[emoticons.length]);

        for(int[] cur_discount_comb: discount_combination){
            int total_describer = 0;
            int total_buy_sum = 0;
            /* 각 사용자 돌아가며 가입여부와 판매액 계산 */
            for(int[] user: users){
                int wanted_discount_rate = user[0];
                int buy_limit = user[1];

                int user_buy_sum = 0;
                for(int i=0; i<emoticons.length; i++){
                    if(wanted_discount_rate>cur_discount_comb[i]){ // 할인율 못 미쳐서 안 사는 경우 스킵
                        continue;
                    }

                    user_buy_sum += get_discounted_price(emoticons[i], cur_discount_comb[i]);
                }

                /* 현재 사용자의 구매금액이 임계점 찍었는지 */
                if(user_buy_sum >= buy_limit){
                    total_describer++;
                }
                else{
                    total_buy_sum += user_buy_sum;
                }
            }

            candidates.add(new Integer[]{total_describer, total_buy_sum});
        }

        Collections.sort(candidates, new Comparator<Integer[]>(){
            @Override
            public int compare(Integer[] o1, Integer[] o2){
                if(o1[0]==o2[0]){
                    return o1[1] - o2[1];
                }
                return o1[0] - o2[0];
            }
        });

        answer[0] = candidates.get(candidates.size()-1)[0];
        answer[1] = candidates.get(candidates.size()-1)[1];
        return answer;
    }

    static void get_discount_combination(int n, int idx, int[] cur){
        if(idx == n){
            int[] tmp = new int[n];
            for(int i=0; i<n; i++){
                tmp[i] = cur[i];
            }

            discount_combination.add(tmp);
            return;
        }

        for(int discount_type=1; discount_type<=4; discount_type++){
            cur[idx] = discount_type * 10;
            get_discount_combination(n, idx+1, cur);
        }
    }

    static int get_discounted_price(int price, int discount_rate){
        price = price * (100 - discount_rate);
        return price / 100;
    }
}
