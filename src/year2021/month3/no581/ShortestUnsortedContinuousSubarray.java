package year2021.month3.no581;

public class ShortestUnsortedContinuousSubarray {
    public static void main(String[] args) {
        int[] nums1 = {2, 6, 4, 8, 10, 9, 15};
        int[] nums2 = {1, 2, 3, 4};
        int[] nums3 = {1};
        int[] nums4 = {1, 2, 6, 4};
        int[] nums5 = {1, 4, 7, 5, 6, 8};
        int[] nums6 = {1, 4, 0, 5, 6, 8};
        int[] nums7 = {1, 3, 2, 3, 3};
        System.out.println(findUnsortedSubarray(nums1));
        System.out.println(findUnsortedSubarray(nums2));
        System.out.println(findUnsortedSubarray(nums3));
        System.out.println(findUnsortedSubarray(nums4));
        System.out.println(findUnsortedSubarray(nums5));
        System.out.println(findUnsortedSubarray(nums6));
        System.out.println(findUnsortedSubarray(nums7));
    }

    public static int findUnsortedSubarray(int[] nums) {
        /*思路：
         * 先找到数组中不是升序遍历的最大子数组，记为[left, right]
         * 然后求出该区间内的最小值和最大值
         * 再向外扩充，长度即为结果
         * */
        int left = 0;
        while (left < nums.length - 1 && nums[left] <= nums[left + 1]) {
            left++;
        }
        if (left == nums.length - 1) {
            return 0;
        }
        int right = nums.length - 1;
        while (right > 0 && nums[right - 1] <= nums[right]) {
            right--;
        }
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int i = left; i <= right; i++) {
            min = Math.min(min, nums[i]);
            max = Math.max(max, nums[i]);
        }
        while (left > 0 && nums[left - 1] > min) {
            left--;
        }
        while (right < nums.length - 1 && nums[right + 1] < max) {
            right++;
        }
        return right - left + 1;
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
-10^5 <= nums[i] <= 10^5
 

进阶：你可以设计一个时间复杂度为 O(n) 的解决方案吗？

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/shortest-unsorted-continuous-subarray
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */
