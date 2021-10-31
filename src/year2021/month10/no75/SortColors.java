package year2021.month10.no75;

import java.util.Arrays;

public class SortColors {

    public static void main(String[] args) {
        int[] nums1 = {2, 0, 2, 1, 1, 0};
        int[] nums2 = {2, 0, 1};
        int[] nums3 = {0};
        int[] nums4 = {1};
        sortColors(nums1);
        sortColors(nums2);
        sortColors(nums3);
        sortColors(nums4);
        System.out.println(Arrays.toString(nums1));
        System.out.println(Arrays.toString(nums2));
        System.out.println(Arrays.toString(nums3));
        System.out.println(Arrays.toString(nums4));
    }

    public static void sortColors(int[] nums) {
        /*
         * 三分法
         * [0, i]   => 0
         * (i, j)   => 1
         * [k, len) => 2
         * */
        int i = -1;
        int j = 0;
        int k = nums.length;
        while (j < k) {
            switch (nums[j]) {
                case 0:
                    i++;
                    swap(nums, i, j);
                    j++;
                    break;
                case 1:
                    j++;
                    break;
                case 2:
                    k--;
                    swap(nums, j, k);
                    break;
                default:
                    throw new IllegalArgumentException();
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
