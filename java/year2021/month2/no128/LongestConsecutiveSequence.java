package year2021.month2.no128;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class LongestConsecutiveSequence {
    public static void main(String[] args) {
        int[] nums1 = new int[]{100, 4, 200, 1, 3, 2};
        int[] nums2 = new int[]{0, 3, 7, 2, 5, 8, 4, 6, 0, 1};
        int[] nums3 = new int[]{1};
        int[] nums4 = new int[0];
        int[] nums5 = new int[]{0, 2, 1, 1};
        System.out.println(longestConsecutive1(nums1));  //4
        System.out.println(longestConsecutive1(nums2));  //9
        System.out.println(longestConsecutive1(nums3));  //1
        System.out.println(longestConsecutive1(nums4));  //0
        System.out.println(longestConsecutive1(nums5));  //3
    }

    private static int longestConsecutive1(int[] nums) {
        if (nums.length < 2) {
            return nums.length;
        }
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            set.add(nums[i]);
        }
        int max = 0;
        for (Integer num : set) {
            if (set.contains(num - 1)) {
                continue;
            }
            int count = 1;
            int next = num + 1;
            while (set.contains(next)) {
                next++;
                count++;
            }
            max = Math.max(max, count);
        }
        return max;
    }

    public static int longestConsecutive(int[] nums) {
        if (nums.length < 2) {
            return nums.length;
        }
        Arrays.sort(nums);
        int max = 0;
        int count = 1;
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] == nums[i + 1]) {
                continue;
            }
            if (nums[i] + 1 != nums[i + 1]) {
                max = Math.max(max, count);
                count = 1;
                continue;
            }
            count++;
        }
        return Math.max(max, count);
    }
}

/*
* 给定一个未排序的整数数组 nums ，找出数字连续的最长序列（不要求序列元素在原数组中连续）的长度。

 

进阶：你可以设计并实现时间复杂度为 O(n) 的解决方案吗？

 

示例 1：

输入：nums = [100,4,200,1,3,2]
输出：4
解释：最长数字连续序列是 [1, 2, 3, 4]。它的长度为 4。
示例 2：

输入：nums = [0,3,7,2,5,8,4,6,0,1]
输出：9
 

提示：

0 <= nums.length <= 104
-109 <= nums[i] <= 109

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/longest-consecutive-sequence
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */
