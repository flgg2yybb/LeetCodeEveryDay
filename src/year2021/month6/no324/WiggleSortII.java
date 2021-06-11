package year2021.month6.no324;

import java.util.Arrays;
import java.util.Random;

public class WiggleSortII {

    public static void main(String[] args) {
        int[] nums1 = {1, 5, 1, 1, 6, 4};
        int[] nums2 = {1, 3, 2, 2, 3, 1};
        int[] nums3 = {1, 5, 1, 1, 6, 4, 4, 4, 4, 4};
        wiggleSort1(nums1);
        wiggleSort1(nums2);
        wiggleSort1(nums3);
        System.out.println(Arrays.toString(nums1));
        System.out.println(Arrays.toString(nums2));
        System.out.println(Arrays.toString(nums3));
    }

    private static void wiggleSort1(int[] nums) {
        /* 快速选择 + 三分法 + 倒序穿插, time is O(n), space is O(n)
         * 只需要将 nums 数组分为两个数组A、B，其中 A 数组的最大值 <= B 数组的最小值即可
         * 不要求子数组 A、B 内部有序，故无需排序，只需找到中位数，而后将数组分为两部分
         * 使用快速选择找到中位数（top k 算法）
         * 使用三分法，将数组分为 [小于中位数的部分，等于中位数的部分，大于中位数的部分]
         * 为了防止中位数相邻，需要使用倒序穿插
         * PS：中位数相邻例子，对于数组 nums = [1,2,2,3]
         * 分为子数组 A = [1,2]、B = [2,3]
         * 若不使用倒序穿插，则得到 [1, 2, 2, 3]
         * 若使用倒序穿插，则得到 [2, 3, 1, 2]
         * */
        int median = findMedian(nums);
        threeWayPartition(nums, median);
        int smallArraySize = nums.length / 2 + nums.length % 2;
        int largeArraySize = nums.length / 2;
        int[] smallArr = new int[smallArraySize];
        System.arraycopy(nums, 0, smallArr, 0, smallArraySize);
        int[] largeArr = new int[largeArraySize];
        System.arraycopy(nums, smallArraySize, largeArr, 0, largeArraySize);
        int n = largeArraySize - 1;
        for (int i = 1; i < nums.length; i += 2) {
            nums[i] = largeArr[n];
            n--;
        }
        n = smallArraySize - 1;
        for (int i = 0; i < nums.length; i += 2) {
            nums[i] = smallArr[n];
            n--;
        }
    }

    private static void threeWayPartition(int[] nums, int median) {
        int length = nums.length;
        int i = 0;
        int j = 0;
        int k = length;
        /*
         * 循环不变量
         * [0,i) < median
         * [i, j) = median
         * [k, length) > median
         * */
        while (j < k) {
            if (nums[j] < median) {
                swap(nums, i, j);
                i++;
                j++;
            } else if (nums[j] == median) {
                j++;
            } else {
                k--;
                swap(nums, j, k);
            }
        }
    }

    private static int findMedian(int[] nums) {
        int k = nums.length / 2;
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int pivot = partition(nums, left, right);
            if (pivot == k) {
                return nums[pivot];
            } else if (pivot < k) {
                left = pivot + 1;
            } else {
                right = pivot - 1;
            }
        }
        return nums[left];
    }

    private static int partition(int[] nums, int start, int end) {
        int rand = new Random().nextInt(end - start) + start;
        swap(nums, start, rand);
        int left = start;
        int right = end;
        int key = nums[left];
        while (left < right) {
            while (left < right && nums[right] >= key) {
                right--;
            }
            nums[left] = nums[right];
            while (left < right && nums[left] <= key) {
                left++;
            }
            nums[right] = nums[left];
        }
        nums[left] = key;
        return left;
    }

    private static void swap(int[] nums, int x, int y) {
        int temp = nums[x];
        nums[x] = nums[y];
        nums[y] = temp;
    }

    public static void wiggleSort(int[] nums) {
        // 排序 + 逆序穿插，time is O(nlogn), space is O(n)
        int length = nums.length;
        int[] temp = nums.clone();
        Arrays.sort(temp);
        int n = length - 1;
        for (int i = 1; i < length; i += 2) {
            nums[i] = temp[n];
            n--;
        }
        for (int i = 0; i < length; i += 2) {
            nums[i] = temp[n];
            n--;
        }
    }

}

/*
* 给你一个整数数组 nums，将它重新排列成 nums[0] < nums[1] > nums[2] < nums[3]... 的顺序。

你可以假设所有输入数组都可以得到满足题目要求的结果。

 

示例 1：

输入：nums = [1,5,1,1,6,4]
输出：[1,6,1,5,1,4]
解释：[1,4,1,5,1,6] 同样是符合题目要求的结果，可以被判题程序接受。
示例 2：

输入：nums = [1,3,2,2,3,1]
输出：[2,3,1,3,1,2]
 

提示：

1 <= nums.length <= 5 * 104
0 <= nums[i] <= 5000
题目数据保证，对于给定的输入 nums ，总能产生满足题目要求的结果
 

进阶：你能用 O(n) 时间复杂度和 / 或原地 O(1) 额外空间来实现吗？

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/wiggle-sort-ii
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */
