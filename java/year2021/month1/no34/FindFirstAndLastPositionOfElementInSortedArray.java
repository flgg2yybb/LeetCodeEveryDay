package year2021.month1.no34;

import java.util.stream.IntStream;

public class FindFirstAndLastPositionOfElementInSortedArray {
    public static void main(String[] args) {
        int[] nums1 = new int[]{5, 7, 7, 8, 8, 10};
        int target1 = 8;
        int[] nums2 = new int[]{5, 7, 7, 8, 8, 10};
        int target2 = 5;
        int[] nums3 = new int[]{};
        int target3 = 0;
        disp(searchRange(nums1, target1));
        disp(searchRange(nums2, target2));
        disp(searchRange(nums3, target3));
    }

    public static int[] searchRange(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return new int[]{-1, -1};
        }
//        找左边界
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int mid = (left + right) / 2;
            if (nums[mid] >= target) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        if (nums[left] != target) {
            return new int[]{-1, -1};
        }
        int leftBound = left;
//        找右边界
        right = nums.length - 1;
        while (left < right) {
            int mid = (left + right + 1) / 2;
            if (nums[mid] <= target) {
                left = mid;
            } else {
                right = mid - 1;
            }
        }
        int rightBound = left;
        return new int[]{leftBound, rightBound};
    }

    private static void disp(int[] nums) {
        if (nums == null || nums.length == 0) {
            System.out.println("EMPTY");
            return;
        }
        IntStream.of(nums).forEach(num -> System.out.print(num + " "));
        System.out.println();
    }
}

/*
* 给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。

如果数组中不存在目标值 target，返回 [-1, -1]。

进阶：

你可以设计并实现时间复杂度为 O(log n) 的算法解决此问题吗？
 

示例 1：

输入：nums = [5,7,7,8,8,10], target = 8
输出：[3,4]
示例 2：

输入：nums = [5,7,7,8,8,10], target = 6
输出：[-1,-1]
示例 3：

输入：nums = [], target = 0
输出：[-1,-1]
 

提示：

0 <= nums.length <= 105
-109 <= nums[i] <= 109
nums 是一个非递减数组
-109 <= target <= 109

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/find-first-and-last-position-of-element-in-sorted-array
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */
