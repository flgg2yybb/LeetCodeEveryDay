package year2021.month4.no34;

import java.util.Arrays;

public class FindFirstAndLastPositionOfElementInSortedArray {

    public static void main(String[] args) {
        int[] nums1 = {5, 7, 7, 8, 8, 10};
        int target1 = 8;
        int[] nums2 = {5, 7, 7, 8, 8, 10};
        int target2 = 10;
        int[] nums3 = {};
        int target3 = 0;
        System.out.println(Arrays.toString(searchRange(nums1, target1)));
        System.out.println(Arrays.toString(searchRange(nums2, target2)));
        System.out.println(Arrays.toString(searchRange(nums3, target3)));
    }

    public static int[] searchRange(int[] nums, int target) {
        int firstPosition = searchFirstPosition(nums, target);
        if (firstPosition == -1) {
            return new int[]{-1, -1};
        }
        int lastPosition = searchLastPosition(nums, target);
        return new int[]{firstPosition, lastPosition};
    }

    private static int searchLastPosition(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        int ans = -1;
        while (left <= right) {
            int mid = left + (right - left + 1) / 2;
            if (nums[mid] == target) {
                left = mid + 1;
                ans = mid;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return ans;
    }

    private static int searchFirstPosition(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        int ans = -1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                right = mid - 1;
                ans = mid;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return ans;
    }

}

/*
* 给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。

如果数组中不存在目标值 target，返回 [-1, -1]。

进阶：

你可以设计并实现时间复杂度为 O(log n) 的算法解决此问题吗？
 

示例 1：

输入：nums = [5,7,7,8,8,10], target = 8
输出：[3,4]
示例 2：

输入：nums = [5,7,7,8,8,10], target = 6
输出：[-1,-1]
示例 3：

输入：nums = [], target = 0
输出：[-1,-1]
 

提示：

0 <= nums.length <= 105
-109 <= nums[i] <= 109
nums 是一个非递减数组
-109 <= target <= 109

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/find-first-and-last-position-of-element-in-sorted-array
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */
