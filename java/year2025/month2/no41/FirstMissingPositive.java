package year2025.month2.no41;

import java.util.HashSet;
import java.util.Set;

public class FirstMissingPositive {
    public static void main(String[] args) {
        int[] nums1 = {1, 2, 0};
        int[] nums2 = {3, 4, -1, 1};
        int[] nums3 = {7, 8, 9, 11, 12};
        System.out.println(firstMissingPositive(nums1));
        System.out.println(firstMissingPositive(nums2));
        System.out.println(firstMissingPositive(nums3));
    }

    // Hash In-Place, times: O(n), space: O(1)
    // 正常逻辑是：新建一个哈希表，把所有的正整数放进去，然后从1开始遍历，找到第一个不在哈希表中的正整数即可。
    // 【因为考虑到空间复杂度为O(1)，所以不能新建哈希表，只能在原数组上进行操作】。
    // 1. 遍历数组，把每个元素 i 放到对应的位置 i-1 上，比如1放到nums[0]，2放到nums[1]，3放到nums[2]，以此类推。
    // 2. 再次遍历数组，找到第一个不在对应位置上的正整数即可。
    // PS：对于不在1~n范围内的正整数，可以直接忽略，因为不在这个范围内的正整数不会影响到结果。
    public static int firstMissingPositive(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            // 注意：此处是for循环，而不是if条件判断，因为交换后的nums[i]可能还是不在对应位置上
            while (nums[i] > 0 && nums[i] <= nums.length && nums[nums[i] - 1] != nums[i]) {
                int temp = nums[nums[i] - 1];
                nums[nums[i] - 1] = nums[i];
                nums[i] = temp;
            }
        }
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != i + 1) {
                return i + 1;
            }
        }
        return nums.length + 1;
    }

    // Hash Table, times: O(n), space: O(n)
    public static int firstMissingPositive1(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        int ans = 1;
        while (set.contains(ans)) {
            ans++;
        }
        return ans;
    }
}


/*
给你一个未排序的整数数组 nums ，请你找出其中没有出现的最小的正整数。

请你实现时间复杂度为 O(n) 并且只使用常数级别额外空间的解决方案。

示例 1：

输入：nums = [1,2,0]
输出：3
解释：范围 [1,2] 中的数字都在数组中。
示例 2：

输入：nums = [3,4,-1,1]
输出：2
解释：1 在数组中，但 2 没有。
示例 3：

输入：nums = [7,8,9,11,12]
输出：1
解释：最小的正数 1 没有出现。


提示：

1 <= nums.length <= 105
-231 <= nums[i] <= 231 - 1
*/

