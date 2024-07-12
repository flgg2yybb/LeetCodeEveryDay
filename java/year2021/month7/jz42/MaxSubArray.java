package year2021.month7.jz42;

public class MaxSubArray {

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
        int n = nums.length;
        int it = nums[0];
        int max = it;
        for (int i = 1; i < n; i++) {
            it = Math.max(it, 0) + nums[i];
            max = Math.max(max, it);
        }
        return max;
    }

    public static int maxSubArray(int[] nums) {
        /*
         * DP, time is O(n), space is O(n)
         * 状态定义
         * dp[i] 表示从 [0,i] 子数组中，其中一个包含下标 i 元素的子数组的最大值
         * 状态转移方程
         * dp[i] = max{dp[i-1] + nums[i], nums[i]}
         * ans = max{dp[i]}, i = 0, 1, ..., n-1
         * */
        int n = nums.length;
        int[] dp = new int[n];
        dp[0] = nums[0];
        int max = dp[0];
        for (int i = 1; i < n; i++) {
            dp[i] = Math.max(dp[i - 1], 0) + nums[i];
            max = Math.max(max, dp[i]);
        }
        return max;
    }

}

/*
* 输入一个整型数组，数组中的一个或连续多个整数组成一个子数组。求所有子数组的和的最大值。

要求时间复杂度为O(n)。

 

示例1:

输入: nums = [-2,1,-3,4,-1,2,1,-5,4]
输出: 6
解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
 

提示：

1 <= arr.length <= 10^5
-100 <= arr[i] <= 100
注意：本题与主站 53 题相同：https://leetcode-cn.com/problems/maximum-subarray/

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/lian-xu-zi-shu-zu-de-zui-da-he-lcof
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */
