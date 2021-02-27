package year2021.month2.no416;

public class PartitionEqualSubsetSum {
    public static void main(String[] args) {
        int[] nums1 = {1, 5, 11, 5};
        int[] nums2 = {1, 2, 3, 5};
        int[] nums3 = {100, 100, 100, 100, 100, 100, 100};
        int[] nums4 = {100, 100, 100, 100, 100, 100, 100, 100};
        System.out.println(canPartition(nums1));
        System.out.println(canPartition(nums2));
        System.out.println(canPartition(nums3));
        System.out.println(canPartition(nums4));
    }

    public static boolean canPartition(int[] nums) {
        /*思路：【0-1背包问题】
         * 一个数组若能拆分为两个等和子数组，则对于拆分后的两个子数组
         * 它们的数组元素和等于大数组的元素和的一半
         * 即可将问题转换为：
         * 是否可在数组中挑选出若干元素（不能是整个数组的元素），使挑选出的元素和为整个数组元素和的一半
         * DP：
         * 状态定义
         * dp[i][j]定义为能否在数组中[0,i]中选取子数组，使其和为j
         * 状态转移方程
         * 若不选取nums[i]:
         *      dp[i][j] = dp[i-1][j]
         * 若选取nums[i]:
         *      dp[i][j] = dp[i-1][j] || dp[i-1][j-nums[i]]
         * 初始值：
         * dp[i][0] = true
         * dp[0][nums[0]] = true
         * */
        int sum = 0;
        int maxElement = 0;
        for (int num : nums) {
            sum += num;
            maxElement = Math.max(maxElement, num);
        }
        if ((sum & 1) == 1) {
            return false;
        }
        int target = sum / 2;
        if (maxElement > target) {
            return false;
        }
        boolean[][] dp = new boolean[nums.length][target + 1];
//        初始值，赋值第一列
        for (int i = 0; i < nums.length; i++) {
            dp[i][0] = true;
        }
//        初始值，赋值第一行
        dp[0][nums[0]] = true;
//        dp方程转移
        for (int i = 1; i < nums.length; i++) {
            for (int j = 1; j <= target; j++) {
                if (dp[i - 1][j]) {
//                    若之前的已有子数组的和为j，则当前理应也可以
                    dp[i][j] = true;
                    continue;
                }
                if (nums[i] <= j) {
                    dp[i][j] = dp[i - 1][j - nums[i]];
                }
            }
        }
        return dp[nums.length - 1][target];
    }
}

/*
* 给定一个只包含正整数的非空数组。是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。

注意:

每个数组中的元素不会超过 100
数组的大小不会超过 200
示例 1:

输入: [1, 5, 11, 5]

输出: true

解释: 数组可以分割成 [1, 5, 5] 和 [11].
 

示例 2:

输入: [1, 2, 3, 5]

输出: false

解释: 数组不能分割成两个元素和相等的子集.

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/partition-equal-subset-sum
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */