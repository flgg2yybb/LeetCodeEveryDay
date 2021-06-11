package year2021.month6.no324;

import java.util.Arrays;

public class WiggleSortII {

    public static void main(String[] args) {
        int[] nums1 = {1, 5, 1, 1, 6, 4};
        int[] nums2 = {1, 3, 2, 2, 3, 1};
        wiggleSort(nums1);
        wiggleSort(nums2);
        System.out.println(Arrays.toString(nums1));
        System.out.println(Arrays.toString(nums2));
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
