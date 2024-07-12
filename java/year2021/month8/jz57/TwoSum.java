package year2021.month8.jz57;

import java.util.Arrays;

public class TwoSum {

    public static void main(String[] args) {
        int[] nums1 = {2, 7, 11, 15};
        int target1 = 9;
        int[] nums2 = {10, 26, 30, 31, 47, 60};
        int target2 = 40;
        System.out.println(Arrays.toString(twoSum(nums1, target1)));
        System.out.println(Arrays.toString(twoSum(nums2, target2)));
    }

    public static int[] twoSum(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int cur = nums[left] + nums[right];
            if (cur == target) {
                return new int[]{nums[left], nums[right]};
            } else if (cur > target) {
                right--;
            } else {
                left++;
            }
        }
        return new int[]{-1, -1};
    }

}

/*
* 输入一个递增排序的数组和一个数字s，在数组中查找两个数，使得它们的和正好是s。如果有多对数字的和等于s，则输出任意一对即可。

  

示例 1：

输入：nums = [2,7,11,15], target = 9
输出：[2,7] 或者 [7,2]
示例 2：

输入：nums = [10,26,30,31,47,60], target = 40
输出：[10,30] 或者 [30,10]
  

限制：

1 <= nums.length <= 10^5
1 <= nums[i]  <= 10^6

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/he-wei-sde-liang-ge-shu-zi-lcof
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */
