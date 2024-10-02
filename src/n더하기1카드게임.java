import java.util.*;

public class n더하기1카드게임 {

    public int solution(int coin, int[] cards) {
        int answer = 0;
        int n = cards.length;

        /* 이미 소유한 카드 목록 */
        Set<Integer> own_cards = new HashSet<>();
        for(int i=0; i<n/3; i++){
            own_cards.add(cards[i]);
        }

        /* 라운드를 돌며 2장의 카드를 킵하고, 후에 필요하면 구매. 시간과 상관없이 구매 */
        Set<Integer> keep_cards = new HashSet<>();
        for(int i=0; i<=n/3; i++){
            // 현재 라운드까지는 도달한 것
            answer = i + 1;
            // 끝까지 도달했을 경우 처리
            if(i == n/3){
                break;
            }

            /* 현재 라운드 두 장 카드 킵하기 */
            int idx = n/3 + i*2;
            keep_cards.add(cards[idx]);
            keep_cards.add(cards[idx+1]);

            boolean go_to_next_round = false;
            // own_cards에서 2장 존재하는지 확인
            Iterator<Integer> own_cards_it = own_cards.iterator();
            while(own_cards_it.hasNext()){
                int cur = own_cards_it.next();
                int target = n + 1 - cur;
                // 소유한 카드 중 n+1 조합이 존재하면 사용
                if(own_cards.contains(target)){
                    own_cards.remove(cur);
                    own_cards.remove(target);
                    go_to_next_round = true;
                    break;
                }
            }
            if(go_to_next_round){ // 2장 조합 있으면 다음 라운드 진행
                continue;
            }

            // own_cards에서 1장, keep_cards에서 1장 시도
            own_cards_it = own_cards.iterator();
            while(own_cards_it.hasNext()){
                int cur = own_cards_it.next();
                int target = n + 1 - cur;
                // keep_cards에서 매치 찾을 수 있으면 사용
                if(keep_cards.contains(target) && coin >= 1){
                    own_cards.remove(cur);
                    keep_cards.remove(target);
                    coin--;
                    go_to_next_round = true;
                    break;
                }
            }
            if(go_to_next_round){
                continue;
            }

            // keep_cards에서만 2장이 필요한 경우
            Iterator<Integer> keep_cards_it = keep_cards.iterator();
            while(keep_cards_it.hasNext()){
                int cur = keep_cards_it.next();
                int target = n + 1 - cur;
                // keep_cards 안에 매치가 있을 경우
                if(keep_cards.contains(target) && coin >=2 ){
                    keep_cards.remove(cur);
                    keep_cards.remove(target);
                    coin -= 2;
                    go_to_next_round = true;
                    break;
                }
            }
            if(!go_to_next_round){
                return i + 1;
            }
        }

        return answer;
    }
}
