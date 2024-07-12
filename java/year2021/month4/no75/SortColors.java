package year2021.month4.no75;

import java.util.Arrays;

public class SortColors {

    public static void main(String[] args) {
        int[] nums1 = {2, 0, 2, 1, 1, 0};
        int[] nums2 = {2, 0, 1};
        int[] nums3 = {0};
        int[] nums4 = {1};
        int[] nums5 = {2, 2, 2, 1, 1, 1, 0, 0, 0};
        int[] nums6 = {0, 1, 2, 1, 0, 1, 2, 1, 0, 1, 2};
        int[] nums7 = {1, 2, 0};
        sortColors(nums1);
        sortColors(nums2);
        sortColors(nums3);
        sortColors(nums4);
        sortColors(nums5);
        sortColors(nums6);
        sortColors(nums7);
        System.out.println(Arrays.toString(nums1));
        System.out.println(Arrays.toString(nums2));
        System.out.println(Arrays.toString(nums3));
        System.out.println(Arrays.toString(nums4));
        System.out.println(Arrays.toString(nums5));
        System.out.println(Arrays.toString(nums6));
        System.out.println(Arrays.toString(nums7));
    }

    public static void sortColors(int[] nums) {
        /* Two Pointers, time is O(n), space is O(1)
         * [0, p)        => 0
         * [p, i)        => 1
         * [q, length)   => 2
         * */
        int p = 0;
        int i = 0;
        int q = nums.length;
        while (i < q) {
            int cur = nums[i];
            if (cur == 0) {
                swap(nums, i, p);
                p++;
                i++;
            } else if (cur == 1) {
                i++;
            } else {
                // cur == 2
                // Note: after swap, need to check the nums[i] again
                q--;
                swap(nums, i, q);
            }
        }
    }

    private static void swap(int[] nums, int x, int y) {
        int temp = nums[x];
        nums[x] = nums[y];
        nums[y] = temp;
    }

}

/*
* 给定一个包含红色、白色和蓝色，一共 n 个元素的数组，原地对它们进行排序，使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。

此题中，我们使用整数 0、 1 和 2 分别表示红色、白色和蓝色。

 

示例 1：

输入：nums = [2,0,2,1,1,0]
输出：[0,0,1,1,2,2]
示例 2：

输入：nums = [2,0,1]
输出：[0,1,2]
示例 3：

输入：nums = [0]
输出：[0]
示例 4：

输入：nums = [1]
输出：[1]
 

提示：

n == nums.length
1 <= n <= 300
nums[i] 为 0、1 或 2
 

进阶：

你可以不使用代码库中的排序函数来解决这道题吗？
你能想出一个仅使用常数空间的一趟扫描算法吗？

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/sort-colors
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */
