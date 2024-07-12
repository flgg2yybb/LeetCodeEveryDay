package year2021.month4.no215;

import java.util.Random;

public class KthLargestElementInAnArray {
    public static void main(String[] args) {
        int[] nums1 = {3, 2, 1, 5, 6, 4};
        int k1 = 2;
        int[] nums2 = {3, 2, 3, 1, 2, 4, 5, 5, 6};
        int k2 = 4;
        int[] nums3 = {1};
        int k3 = 1;
        int[] nums4 = {3, 2, 1, 5, 6, 4};
        int k4 = 2;
        System.out.println(findKthLargest(nums1, k1));
        System.out.println(findKthLargest(nums2, k2));
        System.out.println(findKthLargest(nums3, k3));
        System.out.println(findKthLargest(nums4, k4));
    }

    public static int findKthLargest(int[] nums, int k) {
        int index = nums.length - k;
        return findKthMininum(nums, 0, nums.length - 1, index);
    }

    private static int findKthMininum(int[] nums, int start, int end, int k) {
        if (k < start || k > end) {
            throw new IllegalArgumentException();
        }
        // two pointers
        while (start < end) {
            // quick sort's partition to find pivot
            int pivot = partition(nums, start, end);
            if (pivot == k) {
                return nums[k];
            } else if (pivot < k) {
                start = pivot + 1;
            } else {
                end = pivot - 1;
            }
        }
        return nums[start];
    }

    private static int partition(int[] nums, int start, int end) {
        int random = new Random().nextInt(end - start);
        swap(nums, start, random + start);
        int key = nums[start];
        while (start < end) {
            while (start < end && nums[end] >= key) {
                end--;
            }
            nums[start] = nums[end];
            while (start < end && nums[start] <= key) {
                start++;
            }
            nums[end] = nums[start];
        }
        nums[start] = key;
        return start;
    }

    private static void swap(int[] nums, int x, int y) {
        int temp = nums[x];
        nums[x] = nums[y];
        nums[y] = temp;
    }
}

/*
 * 在未排序的数组中找到第 k 个最大的元素。请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。

示例 1:

输入: [3,2,1,5,6,4] 和 k = 2
输出: 5

示例 2:

输入: [3,2,3,1,2,4,5,5,6] 和 k = 4
输出: 4

说明:

你可以假设 k 总是有效的，且 1 ≤ k ≤ 数组的长度。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/kth-largest-element-in-an-array
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */
