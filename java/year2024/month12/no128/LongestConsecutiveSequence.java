package year2024.month12.no128;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class LongestConsecutiveSequence {

    // time: O(n), space: O(n)
    public static int longestConsecutive(int[] nums) {
        Set<Integer> set = Arrays.stream(nums).boxed().collect(Collectors.toSet());
        int longest = 0;
        for (Integer num : set) {
            if (set.contains(num - 1)) {
                // 如果 num-1 存在，则 num 必不是最长连续序列的第一个元素，因此可以跳过
                continue;
            }
            int cur = 1;
            int nextNum = num + 1;
            while (set.contains(nextNum)) {
                cur++;
                nextNum++;
            }
            if (cur > longest) {
                longest = cur;
            }
        }
        return longest;
    }

    public static void main(String[] args) {
        int[] nums1 = {100, 4, 200, 1, 3, 2};
        int[] nums2 = {0, 3, 7, 2, 5, 8, 4, 6, 0, 1};
        int[] nums3 = {0};
        System.out.println(longestConsecutive(nums1));
        System.out.println(longestConsecutive(nums2));
        System.out.println(longestConsecutive(nums3));
    }
}

/*
给定一个未排序的整数数组 nums ，找出数字连续的最长序列（不要求序列元素在原数组中连续）的长度。

请你设计并实现时间复杂度为 O(n) 的算法解决此问题。



示例 1：

输入：nums = [100,4,200,1,3,2]
输出：4
解释：最长数字连续序列是 [1, 2, 3, 4]。它的长度为 4。
示例 2：

输入：nums = [0,3,7,2,5,8,4,6,0,1]
输出：9


提示：

0 <= nums.length <= 105
-109 <= nums[i] <= 109
*/