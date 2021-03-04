package year2021.month2.no494;

public class TargetSum {
    public static void main(String[] args) {
        int[] nums1 = {1, 1, 1, 1, 1};
        int target1 = 3;
        System.out.println(findTargetSumWays1(nums1, target1));
    }

    private static int findTargetSumWays1(int[] nums, int S) {
        /*DP，背包问题
         * 状态定义：
         * dp[i][j]表示数组中[0, i]的子数组能组成和为 j的数量
         * 状态转移方程：
         * dp[i][j] = dp[i-1][j - nums[i]] + dp[i-1][j + nums[i]]
         * 递推形式：
         * dp[i][j + nums[i]] += dp[i-1][j]
         * dp[i][j - nums[i]] += dp[i-1][j]
         * 由于初始数组的和不超过1000，故 j的范围为[-1000, 1000]，
         * 则需将 j的预加上1000，以防止数组下标为负
         * 初始值
         * dp[0][nums[0]] = 1
         * dp[0][-nums[0]] = 1
         * */
        int[][] dp = new int[nums.length][2001];
        dp[0][1000 + nums[0]] = 1;
//        这里使用 +=是防止 nums[0]恰好为 0
        dp[0][1000 - nums[0]] += 1;
        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j <= 2000; j++) {
//                数组从中心向两边扩散，使用上一层有值的点才能扩散，不然会越界
                if (dp[i - 1][j] > 0) {
                    dp[i][j + nums[i]] += dp[i - 1][j];
                    dp[i][j - nums[i]] += dp[i - 1][j];
                }
            }
        }
        return S > 1000 ? 0 : dp[nums.length - 1][1000 + S];
    }

    public static int findTargetSumWays(int[] nums, int S) {
//        DFS
        return dfs(nums, S, 0, 0);
    }

    private static int dfs(int[] nums, int target, int level, int currSum) {
        if (level == nums.length) {
            return currSum == target ? 1 : 0;
        }
        int count = 0;
        count += dfs(nums, target, level + 1, currSum + nums[level]);
        count += dfs(nums, target, level + 1, currSum - nums[level]);
        return count;
    }
}

/*
* 给定一个非负整数数组，a1, a2, ..., an, 和一个目标数，S。现在你有两个符号 + 和 -。对于数组中的任意一个整数，你都可以从 + 或 -中选择一个符号添加在前面。

返回可以使最终数组和为目标数 S 的所有添加符号的方法数。

 

示例：

输入：nums: [1, 1, 1, 1, 1], S: 3
输出：5
解释：

-1+1+1+1+1 = 3
+1-1+1+1+1 = 3
+1+1-1+1+1 = 3
+1+1+1-1+1 = 3
+1+1+1+1-1 = 3

一共有5种方法让最终目标和为3。
 

提示：

数组非空，且长度不会超过 20 。
初始的数组的和不会超过 1000 。
保证返回的最终结果能被 32 位整数存下。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/target-sum
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */
