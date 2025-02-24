package year2025.month2.no189;

import java.util.Arrays;

public class RotateArray {

    public static void main(String[] args) {
        int[] nums1 = new int[]{1, 2, 3, 4, 5, 6, 7};
        int k1 = 3;
        int[] nums2 = new int[]{-1, -100, 3, 99};
        int k2 = 2;
        rotate(nums1, k1);
        rotate(nums2, k2);
        System.out.println(Arrays.toString(nums1));
        System.out.println(Arrays.toString(nums2));
    }

    // 翻转数组
    public static void rotate(int[] nums, int k) {
        int n = nums.length;
        k %= n;
        reverse(nums, 0, n - 1);    // 翻转整个数组
        reverse(nums, 0, k - 1);    // 翻转前 k 个元素
        reverse(nums, k, n - 1);    // 翻转后 n-k 个元素
    }

    private static void reverse(int[] nums, int start, int end) {
        int left = start;
        int right = end;
        while (left < right) {
            int temp = nums[left];
            nums[left] = nums[right];
            nums[right] = temp;
            left++;
            right--;
        }
    }

    // copy
    public static void rotate1(int[] nums, int k) {
        int[] copy = new int[nums.length];
        int i = 0;
        int j = i + k % nums.length;
        while (i < nums.length) {
            if (j == nums.length) {
                j = 0;
            }
            copy[j++] = nums[i++];
        }
        System.arraycopy(copy, 0, nums, 0, nums.length);
    }

}

/*
*
* 给定一个整数数组 nums，将数组中的元素向右轮转 k 个位置，其中 k 是非负数。

示例 1:

输入: nums = [1,2,3,4,5,6,7], k = 3
输出: [5,6,7,1,2,3,4]
解释:
向右轮转 1 步: [7,1,2,3,4,5,6]
向右轮转 2 步: [6,7,1,2,3,4,5]
向右轮转 3 步: [5,6,7,1,2,3,4]
示例 2:

输入：nums = [-1,-100,3,99], k = 2
输出：[3,99,-1,-100]
解释:
向右轮转 1 步: [99,-1,-100,3]
向右轮转 2 步: [3,99,-1,-100]

提示：

1 <= nums.length <= 105
-231 <= nums[i] <= 231 - 1
0 <= k <= 105

进阶：

尽可能想出更多的解决方案，至少有 三种 不同的方法可以解决这个问题。
你可以使用空间复杂度为 O(1) 的 原地 算法解决这个问题吗？
* */
