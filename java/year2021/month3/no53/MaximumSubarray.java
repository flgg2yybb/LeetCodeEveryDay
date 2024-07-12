package year2021.month3.no53;

public class MaximumSubarray {

    public static void main(String[] args) {
        int[] nums1 = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        int[] nums2 = {1};
        System.out.println(maxSubArray2(nums1));     //6
        System.out.println(maxSubArray2(nums2));     //1
    }

    private static int maxSubArray2(int[] nums) {
        // DP，空间压缩
        int pre = nums[0];
        int ans = pre;
        for (int i = 1; i < nums.length; i++) {
            pre = pre > 0 ? pre + nums[i] : nums[i];
            ans = Math.max(ans, pre);
        }
        return ans;
    }

    public static int maxSubArray1(int[] nums) {
        /*DP
        * 状态定义：
        * dp[i]表示 nums 中 [0,i] 区间上包含下标 i 元素的最大子序和
        * 状态转移方程：
        * dp[i] = dp[i-1] > 0 ? dp[i-1] + nums[i] : nums[i]
        * */
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        int ans = nums[0];
        for (int i = 1; i < nums.length; i++) {
            dp[i] = dp[i - 1] > 0 ? dp[i - 1] + nums[i] : nums[i];
            ans = Math.max(ans, dp[i]);
        }
        return ans;
    }

}

/*
* 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。

 

示例 1：

输入：nums = [-2,1,-3,4,-1,2,1,-5,4]
输出：6
解释：连续子数组 [4,-1,2,1] 的和最大，为 6 。
示例 2：

输入：nums = [1]
输出：1
示例 3：

输入：nums = [0]
输出：0
示例 4：

输入：nums = [-1]
输出：-1
示例 5：

输入：nums = [-100000]
输出：-100000
 

提示：

1 <= nums.length <= 3 * 104
-105 <= nums[i] <= 105
 

进阶：如果你已经实现复杂度为 O(n) 的解法，尝试使用更为精妙的 分治法 求解。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/maximum-subarray
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */
