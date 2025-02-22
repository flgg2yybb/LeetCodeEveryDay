package year2025.month2.no53;

public class MaximumSubarray {
    public static void main(String[] args) {
        int[] nums1 = new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4};
        int[] nums2 = new int[]{1};
        int[] nums3 = new int[]{5, 4, -1, 7, 8};
        System.out.println((maxSubArray(nums1)));
        System.out.println((maxSubArray(nums2)));
        System.out.println((maxSubArray(nums3)));
    }


    /* DP, time: O(n), space: O(n)
    状态定义：dp[i] 为包含 nums[i] 组成的最大连续子数组和
    状态转移方程：dp[i] = max(dp[i-1] + nums[i], nums[i])
    初始状态：dp[0] = nums[0]
    返回值：max(dp)
    * */
    public static int maxSubArray(int[] nums) {
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        int res = dp[0];
        for (int i = 1; i < nums.length; i++) {
            dp[i] = Math.max(dp[i - 1] + nums[i], nums[i]);
            res = Math.max(res, dp[i]);
        }
        return res;
    }
}

/*
给你一个整数数组 nums ，请你找出一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。

子数组是数组中的一个连续部分。



示例 1：

输入：nums = [-2,1,-3,4,-1,2,1,-5,4]
输出：6
解释：连续子数组 [4,-1,2,1] 的和最大，为 6 。
示例 2：

输入：nums = [1]
输出：1
示例 3：

输入：nums = [5,4,-1,7,8]
输出：23


提示：

1 <= nums.length <= 105
-104 <= nums[i] <= 104


进阶：如果你已经实现复杂度为 O(n) 的解法，尝试使用更为精妙的 分治法 求解。
*/

