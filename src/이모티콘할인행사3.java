// 2023.11.20 2023 카카오 블라인드

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class 이모티콘할인행사3 {

    static List<Info> candidates = new ArrayList<>();

    public static int[] solution(int[][] users, int[] emoticons) {
        combination(users, emoticons, new int[emoticons.length], 0);
        Collections.sort(candidates);
        Info result = candidates.get(0);
        return new int[]{result.buyers, result.totalSales};
    }

    static void combination(int[][] users, int[] emoticons, int[] emoticonsSaleType, int idx) {
        if (idx == emoticons.length) {
            handleSaleType(users, emoticons, emoticonsSaleType);
            return;
        }

        for (int saleType = 4; saleType >= 1; saleType--) {
            emoticonsSaleType[idx] = saleType;
            combination(users, emoticons, emoticonsSaleType, idx + 1);
        }
    }

    static void handleSaleType(int[][] users, int[] emoticons, int[] emoticonsSaleType) {
        int buyers = 0;
        int totalSalePrice = 0;

        for (int[] user : users) {
            int standardSaleType = user[0];
            int standardDescribePrice = user[1];
            int tmpTotalPrice = 0;

            for (int i = 0; i < emoticons.length; i++) {
                if (standardSaleType <= emoticonsSaleType[i] * 10) {
                    tmpTotalPrice += emoticons[i] * (100 - emoticonsSaleType[i] * 10) / 100;
                }
            }

            if (tmpTotalPrice >= standardDescribePrice) {
                buyers++;
            } else {
                totalSalePrice += tmpTotalPrice;
            }
        }

        candidates.add(new Info(buyers, totalSalePrice));
    }

    static class Info implements Comparable<Info> {
        int buyers;
        int totalSales;

        Info(int buyers, int totalSales) {
            this.buyers = buyers;
            this.totalSales = totalSales;
        }

        @Override
        public int compareTo(Info cmp) {
            int buyersCmp = cmp.buyers - buyers;

            if (buyersCmp == 0) {
                return cmp.totalSales - totalSales;
            }
            return buyersCmp;
        }
    }

    public static void main(String[] args) {
        int[][] users = new int[][]{{40, 10000}, {25, 10000}};
        int[] emoticons = new int[]{7000, 9000};
        int[] result = solution(users, emoticons);
        for (int n : result) {
            System.out.println(n);
        }
    }
}