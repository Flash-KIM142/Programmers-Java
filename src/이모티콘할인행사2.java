import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class 이모티콘할인행사2 {

    public static void main(String[] args) {

        int[][] users = new int[][]{{40, 10000}, {25, 10000}};
        int[] emoticons = new int[]{7000, 9000};
        System.out.println(Arrays.toString(solution(users, emoticons)));
    }

    static List<int[]> combs = new ArrayList<>();
    static final int[] discountType = {10,20,30,40};

    public static int[] solution(int[][] users, int[] emoticons) {
        List<int[]> possibleResults = new ArrayList<>();

        int[] ary = new int[emoticons.length];
        combination(emoticons.length, 0, ary);

        for(int[] c: combs){
            int buyPass = 0;
            int totalSales = 0;

            for(int[] user: users){
                int willBuyOverDiscount = user[0];
                int willBuyPassOver = user[1];
                int total = 0;

                for(int i=0; i<emoticons.length; i++){
                    int discount = c[i];
                    if(discount>=willBuyOverDiscount){
                        total += (emoticons[i] * (100 - discount)) / 100;
                    }
                }

                if(total>=willBuyPassOver){
                    buyPass++;
                    total = 0;
                }
                totalSales += total;
            }

            possibleResults.add(new int[]{buyPass, totalSales});
        }

        possibleResults.sort(new Comparator<int[]>() {

            @Override
            public int compare(int[] o1, int[] o2){
                if(o1[0]==o2[0])    return o2[1] - o1[1];
                else return o2[0] - o1[0];
            }
        });
        return possibleResults.get(0);
    }

    static void combination(int size, int idx, int[] ary){
        if(idx==size){
            int[] tmp = new int[size];
            for(int i=0; i<size; i++)   tmp[i] = ary[i];

            combs.add(tmp);
            return;
        }

        for(int i=0; i<4; i++){
            ary[idx] = discountType[i];
            combination(size, idx+1, ary);
        }
    }
}
