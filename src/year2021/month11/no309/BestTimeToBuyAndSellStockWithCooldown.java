package year2021.month11.no309;

public class BestTimeToBuyAndSellStockWithCooldown {
    public static void main(String[] args) {
        int[] prices1 = {1, 2, 3, 0, 2};
        System.out.println(maxProfit1(prices1));
    }

    private static int maxProfit1(int[] prices) {
        // 状态压缩
        int len = prices.length;
        int noStockInForzen = 0;
        int noStock = 0;
        int holdStock = -prices[0];
        for (int i = 1; i < len; i++) {
            int prevNoStockInForzen = noStockInForzen;
            int prevNoStock = noStock;
            int prevHoldStock = holdStock;
            noStockInForzen = prevHoldStock + prices[i];
            noStock = Math.max(prevNoStockInForzen, prevNoStock);
            holdStock = Math.max(prevNoStock - prices[i], prevHoldStock);
        }
        return Math.max(noStockInForzen, noStock);
    }

    public static int maxProfit(int[] prices) {
        /*
         * DP
         * 状态定义
         * dp[i][j] 表示第 i 天条件为 j 的最大利润
         * （j = 0 没股票处于冷冻期，1 没股票不处于冷冻期，2 持有股票）
         * 状态转移方程
         * dp[i][0] = dp[i-1][2] + prices[i]
         * dp[i][1] = max{dp[i-1][0], dp[i-1][1]}
         * dp[i][2] = max{dp[i-1][1] - prices[i], dp[i-1][2]}
         * */
        int len = prices.length;
        int[][] dp = new int[len][3];
        dp[0][0] = 0;
        dp[0][1] = 0;
        dp[0][2] = -prices[0];
        for (int i = 1; i < len; i++) {
            dp[i][0] = dp[i - 1][2] + prices[i];
            dp[i][1] = Math.max(dp[i - 1][0], dp[i - 1][1]);
            dp[i][2] = Math.max(dp[i - 1][1] - prices[i], dp[i - 1][2]);
        }
        return Math.max(dp[len - 1][0], dp[len - 1][1]);
    }
}

/*
 * 给定一个整数数组，其中第 i 个元素代表了第 i 天的股票价格 。​

设计一个算法计算出最大利润。在满足以下约束条件下，你可以尽可能地完成更多的交易（多次买卖一支股票）:

你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
卖出股票后，你无法在第二天买入股票 (即冷冻期为 1 天)。
示例:

输入: [1,2,3,0,2]
输出: 3
解释: 对应的交易状态为: [买入, 卖出, 冷冻期, 买入, 卖出]

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-with-cooldown
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */