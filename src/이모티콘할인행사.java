import java.util.*;

public class 이모티콘할인행사 {

    public static void main(String[] args) {

//        System.out.println(Arrays.toString(solution(new int[][]{{40, 10000}, {25,10000}}, new int[]{7000,9000})));
        System.out.println(Arrays.toString(solution(new int[][]{{40, 2900}, {23, 10000}, {11, 5200}, {5, 5900}, {40, 3100}, {27, 9200}, {32, 6900}}, new int[]{1300, 1500, 1600, 4900})));
    }

    static int[] saleType = {10,20,30,40};
    static ArrayList<int[]> saleCases = new ArrayList<>();

    static int[] solution(int[][] users, int[] emoticons) {

        /* 이모티콘별 할인율 경우의 수 리스트 만들어주기 */
        dfs(0, emoticons.length, new int[emoticons.length]);

        /* 이용자별 구매 케이스 구하기 */
        ArrayList<int[]> resList = new ArrayList<>();
        int[][] userInfoAry = new int[users.length][2]; // 0번이 0이면 이용권 X, 1이면 이용권 O. 1번은 구매금액. 0번이 1이 되는 순간 1번은 자동으로 0으로 변환.

        for(int[] saleInfo: saleCases){
            for(int i=0; i<emoticons.length; i++){
                int thisEmojiSale = saleInfo[i];
                int thisEmojiPrice = emoticons[i] * (100-thisEmojiSale) / 100;
                for(int j=0; j<users.length; j++){
                    if(users[j][0]<=thisEmojiSale){ // 기준 넘어서 돈 주고 삼
                        userInfoAry[j][1] += thisEmojiPrice;
                    }
                }
            }

            /* 하나의 result 경우가 종합되었고 정산해서 resList 에 하나 추가해야 함. */
            int signups = 0;
            int totalSales = 0;
            for(int i=0; i<users.length; i++){
                if(users[i][1]<=userInfoAry[i][1]){
                    signups++;
                }
                else{
                    totalSales += userInfoAry[i][1];
                }
            }
            resList.add(new int[]{signups, totalSales});

            userInfoAry = new int[users.length][2]; // 초기화
        }

        /* resList 문제 조건대로 정렬해주기 */
        resList.sort(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0] != o2[0]) return o2[0] - o1[0];
                else return o2[1] - o1[1];
            }
        });

        return resList.get(0);
    }

    static void dfs(int curEmoji, int n, int[] saleCase){
        if(curEmoji==n){
            int[] tmp = new int[saleCase.length];
            for(int i=0; i<saleCase.length; i++)
                tmp[i] = saleCase[i];

            saleCases.add(tmp);
            return;
        }

        for(int i=0; i<4; i++){
            saleCase[curEmoji] = saleType[i];
            dfs(curEmoji+1, n, saleCase);
        }
    }

    // 1. 할인을 너무 짜게 때리면 비율 기준을 넘기는 사람이 없을테고, 그러면 아무도 구매를 하지 않을 것이기 때문에 구매액도, 구매액의 증가에 따른 서비스 가입자도 없을 것. 따라서 닝겐들 할인 기준 중 최솟값부터 시작.
    // 2. 경우의 수들을 계산한 후에 자료구조에 넣어두어 구매액과 서비스 가입자수에 대한 가중치를 넣어 정렬시키고 맨 앞 녀석을 뽑는 방식?
    // 3. 최악의 경우 200만이기 때문에 for문은 경우의 수 초과는 걱정 안해도 됨.
    // 4. 완전 탐색을 해야할까?
}
