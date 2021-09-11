package year2021.month9.no416;

public class PartitionEqualSubsetSum {
    public static void main(String[] args) {
        int[] nums1 = {1, 5, 11, 5};
        int[] nums2 = {1, 2, 3, 5};
        int[] nums3 = {3, 3, 3, 4, 5};
        System.out.println(canPartition(nums1));
        System.out.println(canPartition(nums2));
        System.out.println(canPartition(nums3));
    }

    public static boolean canPartition(int[] nums) {
        /*
         * 思路：一个数组若能分割成两个等和子集，则说明原数组和必须为偶数
         * 且若从数组中能取出若干元素，使其等于元原数组和的一半，则说明可以分割
         * 故为【0-1背包问题】
         * 对于每一个元素，我们有选或者不选的可能
         * DP，
         * 状态定义：
         * dp[i][j] 表示能否在数组[0,i]选取若干元素使其和为 j
         * 状态转移方程：
         * dp[i][j] = dp[i-1][j] || dp[i-1][j-nums[i]]
         *              不选              选
         * 初始值：
         * 行：dp[0][nums[0]] = true
         * 列：dp[i][0] = true
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
        dp[0][nums[0]] = true;
        for (int i = 0; i < nums.length; i++) {
            dp[i][0] = true;
        }
        for (int i = 1; i < nums.length; i++) {
            for (int j = 1; j <= target; j++) {
                if (dp[i - 1][j]) {
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
* 给你一个 只包含正整数 的 非空 数组 nums 。请你判断是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。

 

示例 1：

输入：nums = [1,5,11,5]
输出：true
解释：数组可以分割成 [1, 5, 5] 和 [11] 。
示例 2：

输入：nums = [1,2,3,5]
输出：false
解释：数组不能分割成两个元素和相等的子集。
 

提示：

1 <= nums.length <= 200
1 <= nums[i] <= 100

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/partition-equal-subset-sum
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */
