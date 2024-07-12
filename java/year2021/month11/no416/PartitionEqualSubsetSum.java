package year2021.month11.no416;

public class PartitionEqualSubsetSum {

    public static void main(String[] args) {
        int[] nums1 = {1, 5, 11, 5};
        int[] nums2 = {2, 2, 3, 5};
        System.out.println(canPartition(nums1));
        System.out.println(canPartition(nums2));
    }

    public static boolean canPartition(int[] nums) {
//        状态压缩
        int sum = 0;
        int maxElement = Integer.MIN_VALUE;
        for (int num : nums) {
            sum += num;
            maxElement = Math.max(maxElement, num);
        }
        if ((sum & 1) == 1) {   // 为奇数
            return false;
        }
        int target = sum / 2;
        if (maxElement > target) {
            return false;
        }
        boolean[] dp = new boolean[target + 1];
        dp[0] = true;
        dp[nums[0]] = true;
        for (int i = 1; i < nums.length; i++) {
            int num = nums[i];
            for (int j = target; j >= num; j--) {
                if (dp[j - num]) {
                    dp[j] = true;
                }
            }
        }
        return dp[target];
    }

    public static boolean canPartition1(int[] nums) {
        /*
         * 问题可转化为在 nums 中是否存在某些元素，使其和为 nums 所有元素和的一半，假设和为 target
         * DP, 【0-1背包】
         * 状态定义
         * dp[i][j] 表示 nums 数组中 [0,i] 的元素是否存在能凑成和为 j 的元素
         * 状态转移方程
         * if dp[i-1][j] == true || dp[i-1][j-nums[i]] = true
         *   dp[i][j] = true
         * j = 1, 2, ..., target, 需保证 j-nums[i] 不越界
         * 初始值
         * dp[0][0] = true
         * dp[0][nums[0]] = true
         * */
        int sum = 0;
        int maxElement = Integer.MIN_VALUE;
        for (int num : nums) {
            sum += num;
            maxElement = Math.max(maxElement, num);
        }
        if ((sum & 1) == 1) {   // 为奇数
            return false;
        }
        int target = sum / 2;
        if (maxElement > target) {
            return false;
        }
        boolean[][] dp = new boolean[nums.length][target + 1];
        dp[0][0] = true;
        dp[0][nums[0]] = true;
        for (int i = 1; i < nums.length; i++) {
            int num = nums[i];
            for (int j = 0; j <= target; j++) {
                if (dp[i - 1][j] || (j >= num && dp[i - 1][j - num])) {
                    dp[i][j] = true;
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
