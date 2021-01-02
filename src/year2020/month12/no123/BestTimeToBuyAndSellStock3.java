package year2020.month12.no123;

public class BestTimeToBuyAndSellStock3 {
    public static void main(String[] args) {
        int[] prices1 = new int[]{3, 3, 5, 0, 0, 3, 1, 4};
        int[] prices2 = new int[]{1, 2, 3, 4, 5};
        int[] prices3 = new int[]{7, 6, 4, 3, 1};
        System.out.println(maxProfit(prices1));
        System.out.println(maxProfit(prices2));
        System.out.println(maxProfit(prices3));
    }

    public static int maxProfit(int[] prices) {
        /*DP,
         * 状态定义：
         * mp[i][k][j]代表第i天，当前已经交易了k次，还持有j股的最大利益，(k也可理解为卖出的次数)
         * 其中，i取值[0,n-1], 本题k取值[0,2](K为最大交易笔数),
         * j取值为：0 - 手上无股票，1 - 有一股股票
         * 注：买卖完成一次称为一笔交易，卖完才算一笔交易完成
         * 状态转移方程：
         * mp[i][k][0] = max{mp[i-1][k-1][1] + prices[i], mp[i-1][k][0]}
         * mp[i][k][1] = max{mp[i-1][k][0] - prices[i], mp[i-1][k][1]}
         * */
        if (prices == null || prices.length <= 1) {
            return 0;
        }
        final int N = prices.length;    //有n天
        final int K = 2;                //最多两笔交易
        int[][][] mp = new int[N][K + 1][2];
        mp[0][0][0] = 0;
        mp[0][0][1] = -prices[0];
        mp[0][1][0] = 0;
        mp[0][1][1] = -prices[0];
        mp[0][2][0] = 0;
        mp[0][2][1] = -prices[0];

        for (int i = 1; i < N; i++) {
            mp[i][0][0] = mp[i - 1][0][0];
            mp[i][0][1] = Math.max(mp[i - 1][0][0] - prices[i], mp[i - 1][0][1]);

            mp[i][1][0] = Math.max(mp[i - 1][0][1] + prices[i], mp[i - 1][1][0]);
            mp[i][1][1] = Math.max(mp[i - 1][1][0] - prices[i], mp[i - 1][1][1]);

            mp[i][2][0] = Math.max(mp[i - 1][1][1] + prices[i], mp[i - 1][2][0]);
        }
        return Math.max(mp[N - 1][0][0], Math.max(mp[N - 1][1][0], mp[N - 1][2][0]));
    }

}

/**
 * 给定一个数组，它的第 i 个元素是一支给定的股票在第 i 天的价格。
 * <p>
 * 设计一个算法来计算你所能获取的最大利润。你最多可以完成 两笔 交易。
 * <p>
 * 注意: 你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [3,3,5,0,0,3,1,4]
 * 输出: 6
 * 解释: 在第 4 天（股票价格 = 0）的时候买入，在第 6 天（股票价格 = 3）的时候卖出，这笔交易所能获得利润 = 3-0 = 3 。
 *      随后，在第 7 天（股票价格 = 1）的时候买入，在第 8 天 （股票价格 = 4）的时候卖出，这笔交易所能获得利润 = 4-1 = 3 。
 * 示例 2:
 * <p>
 * 输入: [1,2,3,4,5]
 * 输出: 4
 * 解释: 在第 1 天（股票价格 = 1）的时候买入，在第 5 天 （股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。  
 *      注意你不能在第 1 天和第 2 天接连购买股票，之后再将它们卖出。  
 *      因为这样属于同时参与了多笔交易，你必须在再次购买前出售掉之前的股票。
 * 示例 3:
 * <p>
 * 输入: [7,6,4,3,1]
 * 输出: 0
 * 解释: 在这个情况下, 没有交易完成, 所以最大利润为 0。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-iii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

