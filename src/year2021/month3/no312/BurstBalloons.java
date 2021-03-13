package year2021.month3.no312;

public class BurstBalloons {
    public static void main(String[] args) {
        int[] nums1 = {3, 1, 5, 8};
        int[] nums2 = {1, 5};
        System.out.println(maxCoins(nums1));
        System.out.println(maxCoins(nums2));
    }

    public static int maxCoins(int[] nums) {
        /* 区间DP, time is O(n^3), space is O(n^2)
         * 为防止数组越界,给nums数组头尾加上值为 1的元素，代表nums[-1]以及nums[n]，防止越界处理
         * 状态定义
         * dp[i][j]为戳破开区间(i,j)中所有气球所获得的硬币最大数量，(i < j - 1)
         * 状态转移方程：
         * dp[i][j] = max{dp[i][k] + nums[i] * nums[k] * nums[j] + dp[k][j]}, k = i + 1, i + 2, ... , j - 1
         * i < k < j，dp[i][j]的计算依赖于dp[i][k]和dp[k][j]，
         * 故状态转移需往右上方向，i: n - 1 -> 0; j: i + 2 -> n + 1
         * 最终结果保存在dp[0][n+1], n 为nums元素个数
         * */
        if (nums == null) {
            return 0;
        }
        int n = nums.length;
        int[] values = new int[n + 2];
        values[0] = 1;
        values[n + 1] = 1;
        System.arraycopy(nums, 0, values, 1, n);
        int[][] dp = new int[n + 2][n + 2];
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i + 2; j < n + 2; j++) {
                int max = 0;
                for (int k = i + 1; k < j; k++) {
                    max = Math.max(max, dp[i][k] + values[i] * values[k] * values[j] + dp[k][j]);
                }
                dp[i][j] = max;
            }
        }
        return dp[0][n + 1];
    }
}

/*有 n 个气球，编号为0 到 n - 1，每个气球上都标有一个数字，这些数字存在数组 nums 中。

现在要求你戳破所有的气球。戳破第 i 个气球，你可以获得 nums[i - 1] * nums[i] * nums[i + 1] 枚硬币。 这里的 i - 1 和 i + 1 代表和 i 相邻的两个气球的序号。如果 i - 1或 i + 1 超出了数组的边界，那么就当它是一个数字为 1 的气球。

求所能获得硬币的最大数量。

 

示例 1：
输入：nums = [3,1,5,8]
输出：167
解释：
nums = [3,1,5,8] --> [3,5,8] --> [3,8] --> [8] --> []
coins =  3*1*5    +   3*5*8   +  1*3*8  + 1*8*1 = 167
示例 2：

输入：nums = [1,5]
输出：10
 

提示：

n == nums.length
1 <= n <= 500
0 <= nums[i] <= 100

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/burst-balloons
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
*/
