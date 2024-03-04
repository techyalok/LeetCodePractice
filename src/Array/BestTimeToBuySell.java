package Array;

public class BestTimeToBuySell {
    public static int maxProfit(int[] prices) {
        int buy=prices[0];
        int maxProfit=0;

        for(int i=1; i<prices.length; i++){
            maxProfit = Math.max(maxProfit, prices[i]-buy);
            buy = Math.min(buy, prices[i]);
        }
        return maxProfit;

    }

    public static void main(String[] args){
        int profit = maxProfit(new int[]{7,6,4,3,1});
        System.out.println(profit);
    }
}