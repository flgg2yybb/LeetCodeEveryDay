package year2021.month2.no215;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Random;
import java.util.stream.IntStream;

public class KthLargestElementInArray {
    public static void main(String[] args) {
        int[] nums1 = new int[]{3, 2, 1, 5, 6, 4};
        int k1 = 2;
        int[] nums2 = new int[]{3, 2, 3, 1, 2, 4, 5, 5, 6};
        int k2 = 4;
        System.out.println(findKthLargest3(nums1, k1));
        System.out.println(findKthLargest3(nums2, k2));
    }

    private static int findKthLargest3(int[] nums, int k) {
        int kSmallestIndex = nums.length - k;
        return quickSelectKSmallest1(nums, 0, nums.length - 1, kSmallestIndex);
    }

    private static int quickSelectKSmallest1(int[] nums, int left, int right, int kSmallestIndex) {
        if (kSmallestIndex < left || kSmallestIndex > right) {
            throw new IllegalArgumentException();
        }
        while (true) {
            int pivotIndex = randomPartition(nums, left, right);
            if (kSmallestIndex == pivotIndex) {
                return nums[pivotIndex];
            } else if (kSmallestIndex < pivotIndex) {
                right = pivotIndex - 1;
            } else {
                left = pivotIndex + 1;
            }
        }
    }

    private static int quickSelectKSmallest(int[] nums, int left, int right, int kSmallestIndex) {
        if (kSmallestIndex < left || kSmallestIndex > right) {
            throw new IllegalArgumentException();
        }
        int pivotIndex = randomPartition(nums, left, right);
        if (kSmallestIndex == pivotIndex) {
            return nums[pivotIndex];
        }
        return kSmallestIndex < pivotIndex ? quickSelectKSmallest(nums, left, pivotIndex - 1, kSmallestIndex) :
                quickSelectKSmallest(nums, pivotIndex + 1, right, kSmallestIndex);
    }

    private static int randomPartition(int[] nums, int left, int right) {
        if (left < right) {
            Random random = new Random();
            int randomIndex = left + random.nextInt(right - left);
            swap(nums, left, randomIndex);
        }
        int pivot = nums[left];
        int l = left;
        int r = right;
        while (l < r) {
            while (l < r && nums[r] >= pivot) {
                r--;
            }
            nums[l] = nums[r];
            while (l < r && nums[l] <= pivot) {
                l++;
            }
            nums[r] = nums[l];
        }
        nums[l] = pivot;
        return l;
    }

    private static int partition(int[] nums, int left, int right) {
        int pivot = nums[left];
        int l = left;
        int r = right;
        while (l < r) {
            while (l < r && nums[r] >= pivot) {
                r--;
            }
            nums[l] = nums[r];
            while (l < r && nums[l] <= pivot) {
                l++;
            }
            nums[r] = nums[l];
        }
        nums[l] = pivot;
        return l;
    }

    private static void swap(int[] nums, int x, int y) {
        int temp = nums[x];
        nums[x] = nums[y];
        nums[y] = temp;
    }

    private static int findKthLargest2(int[] nums, int k) {
//        用一个最小堆维护最大的K个数字，适用于动态数据流中查找Top Kth元素
        PriorityQueue<Integer> minHeap = new PriorityQueue<>(k);
//        先向最小堆中装入k个元素
        IntStream.range(0, k).forEach(index -> minHeap.offer(nums[index]));
        IntStream.range(k, nums.length).forEach(index -> {
            if (nums[index] > minHeap.peek()) {
                minHeap.poll();
                minHeap.offer(nums[index]);
            }
        });
        return minHeap.peek();
    }

    private static int findKthLargest1(int[] nums, int k) {
//        用一个最小堆维护最大的K个数字，适用于动态数据流中查找Top Kth元素
        PriorityQueue<Integer> minHeap = new PriorityQueue<>(k);
        Arrays.stream(nums).boxed().forEach(num -> {
            if (minHeap.size() < k) {
                minHeap.offer(num);
                return;
            }
            if (num > minHeap.peek()) {
                minHeap.poll();
                minHeap.offer(num);
            }
        });
        return minHeap.peek();
    }

    public static int findKthLargest(int[] nums, int k) {
        Arrays.sort(nums);
        return nums[nums.length - k];
    }
}

/*
 * 在未排序的数组中找到第 k 个最大的元素。请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。

示例 1:

输入: [3,2,1,5,6,4] 和 k = 2
输出: 5
示例 2:

输入: [3,2,3,1,2,4,5,5,6] 和 k = 4
输出: 4
说明:

你可以假设 k 总是有效的，且 1 ≤ k ≤ 数组的长度。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/kth-largest-element-in-an-array
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */
