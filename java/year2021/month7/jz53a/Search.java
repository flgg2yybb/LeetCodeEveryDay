package year2021.month7.jz53a;

public class Search {

    public static void main(String[] args) {
        int[] nums1 = {5, 7, 7, 8, 8, 10};
        int target1 = 8;
        int target2 = 6;
        int target3 = 4;
        System.out.println(search(nums1, target1));
        System.out.println(search(nums1, target2));
        System.out.println(search(nums1, target3));
    }

    public static int search(int[] nums, int target) {
        int first = binarySearchFirstTargetIndex(nums, target);
        if (first == -1) {
            return 0;
        }
        int last = binarySearchLastTargetIndex(nums, target);
        return last - first + 1;
    }

    private static int binarySearchLastTargetIndex(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        int ans = -1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] > target) {
                right = mid - 1;
            } else if (nums[mid] == target) {
                ans = mid;
                left = mid + 1;
            } else {
                left = mid + 1;
            }
        }
        return ans;
    }

    private static int binarySearchFirstTargetIndex(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        int ans = -1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] == target) {
                ans = mid;
                right = mid - 1;
            } else {
                right = mid - 1;
            }
        }
        return ans;
    }

}

/*
* 统计一个数字在排序数组中出现的次数。

 

示例 1:

输入: nums = [5,7,7,8,8,10], target = 8
输出: 2
示例 2:

输入: nums = [5,7,7,8,8,10], target = 6
输出: 0
 

限制：

0 <= 数组长度 <= 50000

 

注意：本题与主站 34 题相同（仅返回值不同）：https://leetcode-cn.com/problems/find-first-and-last-position-of-element-in-sorted-array/

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/zai-pai-xu-shu-zu-zhong-cha-zhao-shu-zi-lcof
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */
