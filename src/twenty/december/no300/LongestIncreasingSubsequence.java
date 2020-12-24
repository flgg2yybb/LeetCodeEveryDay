package twenty.december.no300;

import java.util.stream.IntStream;

public class LongestIncreasingSubsequence {

    public static void main(String[] args) {
        int[] nums1 = new int[]{10, 9, 2, 5, 3, 7, 101, 18};
        int[] nums2 = new int[]{0, 1, 0, 3, 2, 3};
        int[] nums3 = new int[]{7, 7, 7, 7, 7, 7, 7};
        System.out.println(lengthOfLIS(nums1));
        System.out.println(lengthOfLIS(nums2));
        System.out.println(lengthOfLIS(nums3));

    }

    public static int lengthOfLIS(int[] nums) {
        /*DP
         * 状态定义：
         * dp[i]为包含下标为i的元素的最大递增子序列的长度
         * 状态转移方程：
         * dp[i] = max{dp[j]} + 1, j = 0, 1, ... , i - 1 且 nums[j] < nums[i]
         * 结果：
         * max length = max{dp[i]}, i = 0, 1, ...
         * */
        int[] dp = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            int max = 0;
            for (int j = 0; j < i; j++) {
                if (dp[j] > max && nums[j] < nums[i]) {
                    max = dp[j];
                }
            }
            dp[i] = max + 1;
        }
        return IntStream.of(dp).max().orElse(-1);
    }
}

/*
* 给你一个整数数组 nums ，找到其中最长严格递增子序列的长度。

子序列是由数组派生而来的序列，删除（或不删除）数组中的元素而不改变其余元素的顺序。例如，[3,6,2,7] 是数组 [0,3,1,6,2,2,7] 的子序列。

 
示例 1：

输入：nums = [10,9,2,5,3,7,101,18]
输出：4
解释：最长递增子序列是 [2,3,7,101]，因此长度为 4 。
示例 2：

输入：nums = [0,1,0,3,2,3]
输出：4
示例 3：

输入：nums = [7,7,7,7,7,7,7]
输出：1
 

提示：

1 <= nums.length <= 2500
-10^4 <= nums[i] <= 10^4
 

进阶：

你可以设计时间复杂度为 O(n^2) 的解决方案吗？
你能将算法的时间复杂度降低到 O(n log(n)) 吗?

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/longest-increasing-subsequence
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */