package twenty.december.no714;

public class BestTimeToBuyAndSellStockWithTransactionFee {
    public static void main(String[] args) {
        int[] prices = new int[]{1, 3, 2, 8, 4, 9};
        int fee = 2;
        System.out.println(maxProfit2(prices, fee));
    }

    private static int maxProfit2(int[] prices, int fee) {
        if (prices == null || prices.length < 2) {
            return 0;
        }
//        当前无股票的最大利益
        int dp0 = 0;
//        当前有股票的最大利益
        int dp1 = -prices[0];
        for (int i = 1; i < prices.length; i++) {
            int newDp0 = Math.max(dp0, dp1 + prices[i] - fee);
            int newDp1 = Math.max(dp1, dp0 - prices[i]);
            dp0 = newDp0;
            dp1 = newDp1;
        }
        return dp0;
    }

    public static int maxProfit(int[] prices, int fee) {
        /*DP
         * 状态定义：
         * mp[i]代表第i天后的最大利益
         * mp[i][0]代表第i天后没有股票
         * mp[i][1]代表第i天后持有股票
         * 状态转移方程：
         * mp[i][0] = max{mp[i-1][0], mp[i-1][1] + prices[i] - fee}
         * mp[i][1] = max{mp[i-1][1], mp[i-1][0] - prices[i]}
         * 初始值：
         * mp[0][0] = 0
         * mp[0][1] = -prices[0]
         * */
        if (prices == null || prices.length < 2) {
            return 0;
        }
        final int N = prices.length;
        int[][] mp = new int[N][2];
        mp[0][0] = 0;
        mp[0][1] = -prices[0];
        for (int i = 1; i < N; i++) {
            mp[i][0] = Math.max(mp[i - 1][0], mp[i - 1][1] + prices[i] - fee);
            mp[i][1] = Math.max(mp[i - 1][1], mp[i - 1][0] - prices[i]);
        }
        return mp[N - 1][0];
    }
}
/*
* 给定一个整数数组 prices，其中第 i 个元素代表了第 i 天的股票价格 ；非负整数 fee 代表了交易股票的手续费用。

你可以无限次地完成交易，但是你每笔交易都需要付手续费。如果你已经购买了一个股票，在卖出它之前你就不能再继续购买股票了。

返回获得利润的最大值。

注意：这里的一笔交易指买入持有并卖出股票的整个过程，每笔交易你只需要为支付一次手续费。

示例 1:

输入: prices = [1, 3, 2, 8, 4, 9], fee = 2
输出: 8
解释: 能够达到的最大利润:  
在此处买入 prices[0] = 1
在此处卖出 prices[3] = 8
在此处买入 prices[4] = 4
在此处卖出 prices[5] = 9
总利润: ((8 - 1) - 2) + ((9 - 4) - 2) = 8.
注意:

0 < prices.length <= 50000.
0 < prices[i] < 50000.
0 <= fee < 50000.

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-with-transaction-fee
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */
