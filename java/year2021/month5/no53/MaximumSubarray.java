package year2021.month5.no53;

public class MaximumSubarray {

    public static void main(String[] args) {
        int[] nums1 = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        int[] nums2 = {1};
        int[] nums3 = {0};
        int[] nums4 = {-1};
        int[] nums5 = {-100000};
        System.out.println(maxSubArray1(nums1));
        System.out.println(maxSubArray1(nums2));
        System.out.println(maxSubArray1(nums3));
        System.out.println(maxSubArray1(nums4));
        System.out.println(maxSubArray1(nums5));
    }

    private static int maxSubArray1(int[] nums) {
        final int MIN = -1000000;
        int dp = MIN;
        int max = MIN;
        for (int num : nums) {
            dp = Math.max(num, dp + num);
            max = Math.max(max, dp);
        }
        return max;
    }

    public static int maxSubArray(int[] nums) {
        /*DP
         * 令 dp[i] 为包含下标 i 元素的连续子数组的和，则
         * dp[i] = max{nums[i], dp[i-1] + nums[i]}
         * 最后返回 max{dp[i]}, i = 0, 1, ... ,n 即可
         * */
        int[] dp = new int[nums.length + 1];
        final int MIN = -1000000;
        dp[0] = MIN;
        int max = MIN;
        for (int i = 0; i < nums.length; i++) {
            dp[i + 1] = Math.max(nums[i], dp[i] + nums[i]);
            max = Math.max(max, dp[i + 1]);
        }
        return max;
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
