package year2021.month5.no300;

public class LongestIncreasingSubsequence {
    public static void main(String[] args) {
        int[] nums1 = {10, 9, 2, 5, 3, 7, 101, 18};
        int[] nums2 = {0, 1, 0, 3, 2, 3};
        int[] nums3 = {7, 7, 7, 7, 7, 7, 7};
        System.out.println(lengthOfLIS1(nums1));
        System.out.println(lengthOfLIS1(nums2));
        System.out.println(lengthOfLIS1(nums3));
    }

    private static int lengthOfLIS1(int[] nums) {
        /*贪心 + 二分
         * 要使得严格递增子序列尽可能长，那么我们需要子序列尽可能增长得缓慢
         * 用一个数组来记录当前最长且末位最小的严格递增子序列
         * 若新元素大于数组末尾元素，则将新元素 append 到数组末尾
         * 若新元素小于等于数组末尾元素，
         * 则用二分法找到 数组中最小且大于等于新元素的元素下标
         * 将其替换为新元素
         * （可以覆盖到原最长子序列的元素，若替换后的元素属于新的最长子序列
         * 那么替换元素的下标后的元素也必会被替换）
         * 结果为数组长度
         * */
        // lis 单调递增（不减）
        int[] lis = new int[nums.length];
        lis[0] = nums[0];
        int size = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > lis[size - 1]) {
                lis[size] = nums[i];
                size++;
                continue;
            }
            // 当前元素不大于 lis 数组末尾元素
            int left = 0;
            int right = size - 1;
            int target = nums[i];
            while (left < right) {
                int mid = left + (right - left) / 2;
                if (lis[mid] >= target) {
                    right = mid;
                } else {
                    left = mid + 1;
                }
            }
            lis[left] = target;
        }
        return size;
    }

    public static int lengthOfLIS(int[] nums) {
        /*DP
         * dp[i]为从[0,i]数组中包含下标 i 的元素的最长严格递增子序列的长度
         * dp[i] = max{dp[j]} + 1, j < i && nums[j] < nums[i]
         * */
        int[] dp = new int[nums.length];
        int ans = 1;
        dp[0] = 1;
        for (int i = 1; i < nums.length; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[j] >= nums[i]) {
                    continue;
                }
                dp[i] = Math.max(dp[i], dp[j] + 1);
            }
            ans = Math.max(ans, dp[i]);
        }
        return ans;
    }
}

/*
给你一个整数数组 nums ，找到其中最长严格递增子序列的长度。

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
-104 <= nums[i] <= 104
 

进阶：

你可以设计时间复杂度为 O(n2) 的解决方案吗？
你能将算法的时间复杂度降低到 O(n log(n)) 吗?

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/longest-increasing-subsequence
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
*/
