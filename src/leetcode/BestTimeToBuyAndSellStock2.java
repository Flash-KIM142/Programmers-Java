package leetcode;

public class BestTimeToBuyAndSellStock2 {

    public int maxProfit(int[] prices) {
        int total_profit = 0;
        for(int i=1; i<prices.length; i++) {
            int profit_per_day = prices[i] - prices[i-1];
            if(profit_per_day > 0) {
                total_profit += profit_per_day;
            }
        }
        return total_profit;
    }
}
