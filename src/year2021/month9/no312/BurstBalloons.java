package year2021.month9.no312;

public class BurstBalloons {
    public static void main(String[] args) {
        int[] nums1 = {3, 1, 5, 8};
        int[] nums2 = {1, 5};
        System.out.println(maxCoins(nums1));
        System.out.println(maxCoins(nums2));
    }

    public static int maxCoins(int[] nums) {
        /*
         * 区间 DP, time is O(n^3), sapce is O(n^2)
         * 为 nums 数组两边加上 1 方便计算
         * 状态定义：
         * dp[i][j] 表示戳破 (i,j) 区间所能获得的最大收益，j >= i + 2
         * 状态转移方程
         * dp[i][j] = max{dp[i][k] + nums[i] * nums[k] * nums[j] + dp[k][j]}, i < k < j,
         * 假设 k 为区间 (i,j) 上最后一个被戳爆的气球
         * */
        int[] balloons = new int[nums.length + 2];
        int length = balloons.length;
        balloons[0] = 1;
        balloons[length - 1] = 1;
        System.arraycopy(nums, 0, balloons, 1, nums.length);
        int[][] dp = new int[length][length];
        for (int len = 3; len <= length; len++) {       // 区间长度
            for (int i = 0; i <= length - len; i++) {   // 区间左边界
                int j = i + len - 1;                    // 区间右边界
                for (int k = i + 1; k < j; k++) {
                    dp[i][j] = Math.max(dp[i][j], dp[i][k] + balloons[i] * balloons[k] * balloons[j] + dp[k][j]);
                }
            }
        }
        return dp[0][length - 1];
    }
}

/*
* 有 n 个气球，编号为0 到 n - 1，每个气球上都标有一个数字，这些数字存在数组 nums 中。

现在要求你戳破所有的气球。戳破第 i 个气球，你可以获得 nums[i - 1] * nums[i] * nums[i + 1] 枚硬币。 这里的 i - 1 和 i + 1 代表和 i 相邻的两个气球的序号。如果 i - 1或 i + 1 超出了数组的边界，那么就当它是一个数字为 1 的气球。

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
* */
