package year2024.month12.no283;

import java.util.Arrays;

public class MoveZeroes {

    // Quick Soft - Partition, tims: O(n), space: O(1)
    public static void moveZeroes(int[] nums) {
        int slow = 0;
        int fast = 0;
        while (fast < nums.length) {
            if (nums[fast] != 0) {
                int temp = nums[slow];
                nums[slow] = nums[fast];
                nums[fast] = temp;
                slow++;
            }
            fast++;
        }
    }

    // Two Pointers, tims: O(n), space: O(1)
    public static void moveZeroes1(int[] nums) {
        if (nums.length == 0) {
            return;
        }
        int slow = 0;
        while (slow < nums.length && nums[slow] != 0) {
            slow++;
        }
        int fast = slow + 1;
        while (fast < nums.length) {
            // find first zero in list
            while (slow < nums.length && nums[slow] != 0) {
                slow++;
            }
            // find first non-zero after slow pointer
            while (fast < nums.length && nums[fast] == 0) {
                fast++;
            }
            if (fast < nums.length) {
                nums[slow] = nums[fast];
                nums[fast] = 0;
            }
        }
    }

    public static void main(String[] args) {
        int[] nums1 = {0, 1, 0, 3, 12};
        int[] nums2 = {0};
        moveZeroes(nums1);
        moveZeroes(nums2);
        System.out.println(Arrays.toString(nums1));
        System.out.println(Arrays.toString(nums2));
    }
}

/*
* 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。

请注意 ，必须在不复制数组的情况下原地对数组进行操作。



示例 1:

输入: nums = [0,1,0,3,12]
输出: [1,3,12,0,0]
示例 2:

输入: nums = [0]
输出: [0]


提示:

1 <= nums.length <= 104
-231 <= nums[i] <= 231 - 1


进阶：你能尽量减少完成的操作次数吗？
* */
