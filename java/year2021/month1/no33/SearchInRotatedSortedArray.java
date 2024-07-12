package year2021.month1.no33;

import java.util.stream.IntStream;

public class SearchInRotatedSortedArray {
    public static void main(String[] args) {
        int[] nums1 = new int[]{4, 5, 6, 7, 0, 1, 2};
        int target1 = 0;
        int[] nums2 = new int[]{4, 5, 6, 7, 0, 1, 2};
        int target2 = 3;
        int[] nums3 = new int[]{1};
        int target3 = 0;
        int[] nums4 = new int[]{0, 1, 2, 4, 5, 6, 7};
        int target4 = 2;
        System.out.println(search2(nums1, target1));
        System.out.println(search2(nums2, target2));
        System.out.println(search2(nums3, target3));
        System.out.println(search2(nums4, target4));
    }

    private static int search2(int[] nums, int target) {
        /*二分查找
         * 直接对整个数组进行二分查找，根据mid可分为左子数组和友子数组，可知左右子数组至少有一个是有序的
         * 对于有序的子数组，可以知道上下界，根据上下界与target的大小关系可知应该在哪一个子数组进行再次查找
         * 若 nums[mid] == target
         *   return mid
         * 若 nums[left] < nums[mid]，则左边有序
         *   若 nums[left] <= target < nums[mid]
         *       则 right = mid - 1
         *   否则，
         *       left = mid + 1
         * 否则，右边有序
         *   若 nums[mid] < target <= nums[right]
         *       则 left = mid + 1
         *   否则，
         *       right = mid - 1
         * */
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (nums[mid] == target) {
                return mid;
            }
            if (nums[left] <= nums[mid]) {
//                左边有序
                if (nums[left] <= target && target < nums[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else {
//                右边有序
                if (nums[mid] < target && target <= nums[right]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }
        return -1;
    }

    private static int search(int[] nums, int target) {
        /*二分查找
         * 先找出分界点 i, 有 nums[i - 1] > nums[i]，则分界点两边的子数组均递增
         * 再根据target的值选择相应的一边进行二分查找
         * */
        int left = 0;
        int right = nums.length - 1;
        int bound = 0;
        while (left < right) {
            int mid = (left + right + 1) / 2;
            if (nums[mid - 1] > nums[mid]) {
                bound = mid;
                break;
            }
            if (nums[left] < nums[mid]) {
                left = mid;
            } else {
                right = mid - 1;
            }
        }
        if (bound == 0) {
            left = 0;
            right = nums.length - 1;
        } else {
            left = target >= nums[0] ? 0 : bound;
            right = target >= nums[0] ? bound - 1 : nums.length - 1;
        }
        while (left <= right) {
            int mid = (left + right) / 2;
            if (nums[mid] == target) {
                return mid;
            }
            if (nums[mid] > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return -1;
    }

    public static int linearSearch(int[] nums, int target) {
        return IntStream.range(0, nums.length).filter(i -> nums[i] == target).findAny().orElse(-1);
    }
}

/*
* 升序排列的整数数组 nums 在预先未知的某个点上进行了旋转（例如， [0,1,2,4,5,6,7] 经旋转后可能变为[4,5,6,7,0,1,2] ）。

请你在数组中搜索target ，如果数组中存在这个目标值，则返回它的索引，否则返回-1。



示例 1：

输入：nums = [4,5,6,7,0,1,2], target = 0
输出：4
示例2：

输入：nums = [4,5,6,7,0,1,2], target = 3
输出：-1
示例 3：

输入：nums = [1], target = 0
输出：-1


提示：

1 <= nums.length <= 5000
-10^4 <= nums[i] <= 10^4
nums 中的每个值都 独一无二
nums 肯定会在某个点上旋转
-10^4 <= target <= 10^4

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/search-in-rotated-sorted-array
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */
