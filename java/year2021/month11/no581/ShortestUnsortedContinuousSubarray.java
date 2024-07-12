package year2021.month11.no581;

public class ShortestUnsortedContinuousSubarray {
    public static void main(String[] args) {
        int[] nums1 = {2, 6, 4, 8, 10, 9, 15};
        int[] nums2 = {1, 2, 3, 4};
        int[] nums3 = {1};
        System.out.println(findUnsortedSubarray(nums1));
        System.out.println(findUnsortedSubarray(nums2));
        System.out.println(findUnsortedSubarray(nums3));
    }

    public static int findUnsortedSubarray(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        while (left < right && nums[left] <= nums[left + 1]) {
            left++;
        }
        if (left == nums.length - 1) {
            return 0;
        }
        while (left < right && nums[right - 1] <= nums[right]) {
            right--;
        }
        int unsortedMin = Integer.MAX_VALUE;
        int unsortedMax = Integer.MIN_VALUE;
        for (int i = left; i <= right; i++) {
            unsortedMin = Math.min(unsortedMin, nums[i]);
            unsortedMax = Math.max(unsortedMax, nums[i]);
        }
        int start = left;
        while (start > 0 && nums[start - 1] > unsortedMin) {
            start--;
        }
        int end = right;
        while (end < nums.length - 1 && nums[end + 1] < unsortedMax) {
            end++;
        }
        return end - start + 1;
    }
}

/*
 * 给你一个整数数组 nums ，你需要找出一个 连续子数组 ，如果对这个子数组进行升序排序，那么整个数组都会变为升序排序。

请你找出符合题意的 最短 子数组，并输出它的长度。

 

示例 1：

输入：nums = [2,6,4,8,10,9,15]
输出：5
解释：你只需要对 [6, 4, 8, 10, 9] 进行升序排序，那么整个表都会变为升序排序。
示例 2：

输入：nums = [1,2,3,4]
输出：0
示例 3：

输入：nums = [1]
输出：0
 

提示：

1 <= nums.length <= 104
-105 <= nums[i] <= 105
 

进阶：你可以设计一个时间复杂度为 O(n) 的解决方案吗？

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/shortest-unsorted-continuous-subarray
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */
