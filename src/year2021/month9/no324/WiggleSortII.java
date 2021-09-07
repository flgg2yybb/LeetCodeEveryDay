package year2021.month9.no324;

import java.util.Arrays;
import java.util.Random;

public class WiggleSortII {

    public static void main(String[] args) {
        int[] nums1 = {1, 5, 1, 1, 6, 4};
        int[] nums2 = {1, 3, 2, 2, 3, 1};
        wiggleSort(nums1);
        wiggleSort(nums2);
        System.out.println(Arrays.toString(nums1));
        System.out.println(Arrays.toString(nums2));
    }

    private static void wiggleSort(int[] nums) {
        /*
         * 快速选择(partition) + 三分法 + 逆序插入
         * time is O(n), space is O(n)
         * 只需要将 nums 数组拆成两部分 A、B，使得 A 的最大值 <= B 的最小值
         * 先使用快速选择寻找中位数(top kth)
         * 再使用三分法，将数组排序为 [小于中位数的元素，中位数，大于中位数的元素]
         * 后将数组拆为两部分，
         * 最后，为了防止中位数相邻，则需要用逆序插入
         * */
        int median = findMedian(nums);
        threeWayPartition(nums, median);
        reverseInsert(nums);
    }

    private static int findMedian(int[] nums) {
        int len = nums.length;
        int kth = len / 2;
        int left = 0;
        int right = len - 1;
        while (left < right) {
            int pivot = partition(nums, left, right);
            if (pivot == kth) {
                return nums[pivot];
            } else if (pivot < kth) {
                left = pivot + 1;
            } else {    // pivot > kth
                right = pivot - 1;
            }
        }
        return nums[left];
    }

    private static int partition(int[] nums, int left, int right) {
        Random random = new Random();
        int rand = random.nextInt(right - left) + left;
        swap(nums, left, rand);
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

    private static void threeWayPartition(int[] nums, int median) {
        /*
         * 三分法
         * [0, i) => 小于 median 的数
         * [i, j) => 等于 median 的数
         * [k, len) => 大于 medain 的数
         * */
        int i = 0;
        int j = 0;
        int k = nums.length;
        while (j < k) {
            if (nums[j] == median) {
                j++;
            } else if (nums[j] > median) {
                k--;
                swap(nums, j, k);
            } else {    // nums[j] < median
                swap(nums, i, j);
                i++;
                j++;
            }
        }
    }

    private static void swap(int[] nums, int x, int y) {
        int temp = nums[x];
        nums[x] = nums[y];
        nums[y] = temp;
    }

    private static void reverseInsert(int[] nums) {
        /*
         * 为了防止中位数相邻，需要使用倒序穿插
         * PS：中位数相邻例子，对于数组 nums = [1,2,2,3]
         * 分为子数组 A = [1,2]、B = [2,3]
         * 若不使用倒序穿插，则得到 [1, 2, 2, 3]
         * 若使用倒序穿插，则得到 [2, 3, 1, 2]
         * */
        int len = nums.length;
        int leftSize = len / 2 + len % 2;
        int[] temp = Arrays.copyOf(nums, len);
        int pos = 0;
        for (int i = leftSize - 1; i >= 0; i--) {
            nums[pos] = temp[i];
            pos += 2;
        }
        pos = 1;
        for (int i = len - 1; i >= leftSize; i--) {
            nums[pos] = temp[i];
            pos += 2;
        }
    }

    public static void wiggleSort1(int[] nums) {
        int[] temp = Arrays.copyOf(nums, nums.length);
        Arrays.sort(temp);
        int pos = temp.length - 1;
        // 逆序穿插，防止中位数相邻
        for (int i = 1; i < nums.length; i += 2) {
            nums[i] = temp[pos--];
        }
        for (int i = 0; i < nums.length; i += 2) {
            nums[i] = temp[pos--];
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
