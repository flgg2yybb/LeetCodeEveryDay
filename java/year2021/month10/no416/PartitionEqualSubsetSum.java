package year2021.month10.no416;

public class PartitionEqualSubsetSum {

    public static void main(String[] args) {
        int[] nums1 = {1, 5, 11, 5};
        int[] nums2 = {1, 2, 3, 5};
        System.out.println(canPartition(nums1));
        System.out.println(canPartition(nums2));
    }

    private static boolean canPartition(int[] nums) {
        int sum = 0;
        int max = Integer.MIN_VALUE;
        for (int num : nums) {
            max = Math.max(max, num);
            sum += num;
        }
        if (sum % 2 != 0) {
            return false;
        }
        int half = sum / 2;
        if (max > half) {
            return false;
        }
        boolean[] dp = new boolean[half + 1];
        dp[0] = true;
        dp[nums[0]] = true;
        for (int i = 1; i < nums.length; i++) {
            int num = nums[i];
            // 需要从大到小遍历，否则：dp[j-num] 不为上一行的值，而是已经更新过的
            for (int j = half; j - num >= 0; j--) {
                dp[j] |= dp[j - num];
            }
        }
        return dp[half];
    }

    public static boolean canPartition1(int[] nums) {
        int sum = 0;
        int max = Integer.MIN_VALUE;
        for (int num : nums) {
            max = Math.max(max, num);
            sum += num;
        }
        if (sum % 2 != 0) {
            return false;
        }
        int half = sum / 2;
        if (max > half) {
            return false;
        }
        /*
         * 思路：一个数组若能分割成两个等和子集，则说明原数组和必须为偶数
         * 且若从数组中能取出若干元素，使其等于元原数组和的一半，则说明可以分割
         * 故为【0-1背包问题】
         * 状态定义：
         * dp[i][j] 代表子数组[0,i]中选取若干元素是否能凑成 j
         * 状态转移方程：
         * dp[i][j] = dp[i-1][j] || dp[i-1][j-nums[i]]
         *              不选              选
         * */
        boolean[][] dp = new boolean[nums.length][half + 1];
        dp[0][nums[0]] = true;
        for (int i = 0; i < nums.length; i++) {
            dp[i][0] = true;
        }
        // 填表格，从第二行开始填
        for (int i = 1; i < nums.length; i++) {
            for (int j = 1; j <= half; j++) {
                dp[i][j] = dp[i - 1][j];
                if (!dp[i][j] && j - nums[i] >= 0) {
                    dp[i][j] = dp[i - 1][j - nums[i]];
                }
            }
        }
        return dp[nums.length - 1][half];
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
