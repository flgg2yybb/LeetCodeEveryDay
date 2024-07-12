package year2021.month6.no152;

public class MaximumProductSubarray {

    public static void main(String[] args) {
        int[] nums1 = {2, 3, -2, 4};
        int[] nums2 = {-2, 0, -1};
        System.out.println(maxProduct1(nums1));
        System.out.println(maxProduct1(nums2));
    }

    private static int maxProduct1(int[] nums) {
        // DP，状态压缩
        int max = nums[0];
        int min = nums[0];
        int ans = max;
        for (int i = 1; i < nums.length; i++) {
            int prevMax = max;
            int prevMin = min;
            max = Math.max(Math.max(prevMax * nums[i], prevMin * nums[i]), nums[i]);
            min = Math.min(Math.min(prevMax * nums[i], prevMin * nums[i]), nums[i]);
            ans = Math.max(ans, max);
        }
        return ans;
    }

    public static int maxProduct(int[] nums) {
        // DP, time is O(n), space is O(n)
        // 状态定义
        // maxDp[i] 为包含数组下标 i 元素的最大连续子数组的乘积
        // 由于有负数存在，且负数可能再乘以一个负数之后变为最大数，故还需定义最小dp
        // minDp[i] 为包含数组下标 i 元素的最小连续子数组的乘积
        int[] maxDp = new int[nums.length];
        int[] minDp = new int[nums.length];
        maxDp[0] = nums[0];
        minDp[0] = nums[0];
        int ans = maxDp[0];
        for (int i = 1; i < nums.length; i++) {
            maxDp[i] = Math.max(Math.max(maxDp[i - 1] * nums[i], minDp[i - 1] * nums[i]), nums[i]);
            minDp[i] = Math.min(Math.min(minDp[i - 1] * nums[i], maxDp[i - 1] * nums[i]), nums[i]);
            ans = Math.max(ans, maxDp[i]);
        }
        return ans;
    }

}

/*
* 给你一个整数数组 nums ，请你找出数组中乘积最大的连续子数组（该子数组中至少包含一个数字），并返回该子数组所对应的乘积。

 

示例 1:

输入: [2,3,-2,4]
输出: 6
解释: 子数组 [2,3] 有最大乘积 6。
示例 2:

输入: [-2,0,-1]
输出: 0
解释: 结果不能为 2, 因为 [-2,-1] 不是子数组。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/maximum-product-subarray
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */
