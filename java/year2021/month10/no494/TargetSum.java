package year2021.month10.no494;

public class TargetSum {

    public static void main(String[] args) {
        int[] nums1 = {1, 1, 1, 1, 1};
        int target1 = 3;
        int[] nums2 = {1};
        int target2 = 1;
        System.out.println(findTargetSumWays(nums1, target1));
        System.out.println(findTargetSumWays(nums2, target2));
    }

    public static int findTargetSumWays(int[] nums, int target) {
        final int MARGIN = 1000;
        int m = nums.length;
        int n = 2 * MARGIN + 1;
        int[][] dp = new int[m][n];
        // 有可能 nums[0] 为 0，故需要 +=
        dp[0][MARGIN + nums[0]] += 1;
        dp[0][MARGIN - nums[0]] += 1;
        for (int i = 1; i < m; i++) {
            int num = Math.abs(nums[i]);
            for (int j = 0; j < n; j++) {
                if (j >= num) {
                    dp[i][j] += dp[i - 1][j - num];
                }
                if (j + num < n) {
                    dp[i][j] += dp[i - 1][j + num];
                }
            }
        }
        return dp[m - 1][MARGIN + target];
    }

}

/*
* 给你一个整数数组 nums 和一个整数 target 。

向数组中的每个整数前添加 '+' 或 '-' ，然后串联起所有整数，可以构造一个 表达式 ：

例如，nums = [2, 1] ，可以在 2 之前添加 '+' ，在 1 之前添加 '-' ，然后串联起来得到表达式 "+2-1" 。
返回可以通过上述方法构造的、运算结果等于 target 的不同 表达式 的数目。

 

示例 1：

输入：nums = [1,1,1,1,1], target = 3
输出：5
解释：一共有 5 种方法让最终目标和为 3 。
-1 + 1 + 1 + 1 + 1 = 3
+1 - 1 + 1 + 1 + 1 = 3
+1 + 1 - 1 + 1 + 1 = 3
+1 + 1 + 1 - 1 + 1 = 3
+1 + 1 + 1 + 1 - 1 = 3
示例 2：

输入：nums = [1], target = 1
输出：1
 

提示：

1 <= nums.length <= 20
0 <= nums[i] <= 1000
0 <= sum(nums[i]) <= 1000
-1000 <= target <= 1000

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/target-sum
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */
