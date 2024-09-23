import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class 주사위고르기2 {

    static List<boolean[]> dice_comb_list;
    static List<Integer> a_sum;
    static List<Integer> b_sum;

    public static void main(String[] args) {
        int[][] dice = {{1, 2, 3, 4, 5, 6}, {3, 3, 3, 3, 4, 4}, {1, 3, 3, 4, 4, 4}, {1, 1, 4, 4, 5, 5}};
//        int[][] dice = {{1, 2, 3, 4, 5, 6}, {2, 2, 4, 4, 6, 6}};
        int[] res = solution(dice);
        for(int n: res){
            System.out.println(n);
        }
    }

    static int[] solution(int[][] dice) {
        int n = dice.length;
        int half_n = n/2;
        dice_comb_list = new ArrayList<>();

        /* 주사위 조합 따져보기 */
        get_dice_comb(n, half_n, 0, 0, new boolean[n]);

        /* 각 주사위 조합별로 승률 계산 */
        int max_win = 0;
        int[] answer = new int[half_n];
        List<Integer> answer_list = new ArrayList<>();
        for(boolean[] cur: dice_comb_list){
            List<Integer> a_dice_num_list = new ArrayList<>();
            List<Integer> b_dice_num_list = new ArrayList<>();
            for(int i=0; i<cur.length; i++){
                if(cur[i]){
                    a_dice_num_list.add(i);
                }
                else{
                    b_dice_num_list.add(i);
                }
            }

            a_sum = get_sum(new ArrayList<Integer>(), a_dice_num_list, dice);
            b_sum = get_sum(new ArrayList<Integer>(), b_dice_num_list, dice);

            Collections.sort(a_sum);
            Collections.sort(b_sum);

            /* 이분탐색 통해서 승리 개수 구하기 */
            int win_cnt = 0;
            for(int num: a_sum){
                win_cnt += binary_search(num, b_sum);
            }

            /* 최대 승리 개수 갱신 */
            if(win_cnt > max_win){
                max_win = win_cnt;
                answer_list.clear();
                for(int i=0; i<n; i++){
                    if(cur[i]){
                        answer_list.add(i);
                    }
                }
            }
        }

        for(int i=0; i<half_n; i++){
            answer[i] = answer_list.get(i) + 1;
        }
        return answer;
    }

    /* 주사위 조합 구하기 */
    static void get_dice_comb(int n, int half_n, int idx, int cnt, boolean[] cur_comb){
        if(cnt == half_n){
            boolean[] tmp = new boolean[n];
            for(int i=0; i<n; i++){
                if(cur_comb[i]){
                    tmp[i] = true;
                }
            }
            dice_comb_list.add(tmp);
            return;
        }

        for(int i=idx; i<n; i++){
            cur_comb[i] = true;
            get_dice_comb(n, half_n, i+1, cnt+1, cur_comb);
            cur_comb[i] = false;
        }
    }

    /* 주사위 합 구하기 */
    static List<Integer> get_sum(List<Integer> sum, List<Integer> dice_num_list, int[][] dice){
        get_sum_comb(0, 0, sum, dice_num_list, dice);
        return sum;
    }

    static void get_sum_comb(int cnt, int cur, List<Integer> sum, List<Integer> dice_num_list, int[][] dice){
        if(cnt == dice_num_list.size()){
            sum.add(cur);
            return;
        }

        for(int i=0; i<6; i++){
            cur += dice[dice_num_list.get(cnt)][i];
            get_sum_comb(cnt+1, cur, sum, dice_num_list, dice);
            cur -= dice[dice_num_list.get(cnt)][i];
        }
    }

    static int binary_search(int num, List<Integer> list){
        int l = 0;
        int r = list.size()-1;
        while (l <= r){
            int mid = (l+r)/2;
            if(list.get(mid) < num){
                l = mid+1;
            }
            else{
                r = mid-1;
            }
        }
        return l;
    }
}
