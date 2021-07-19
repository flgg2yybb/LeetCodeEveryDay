package year2021.month7.jz3;

import java.util.HashSet;
import java.util.Set;

public class FindRepeatNumber {

    public static void main(String[] args) {
        int[] nums1 = {2, 3, 1, 0, 2, 5, 3};
        int[] nums2 = {1, 2, 3, 4, 5, 0};
        int[] nums3 = {2, 2, 2, 2, 2};
        int[] nums4 = {1, 1, 2, 2, 2};
        System.out.println(findRepeatNumber1(nums1));
        System.out.println(findRepeatNumber1(nums2));
        System.out.println(findRepeatNumber1(nums3));
        System.out.println(findRepeatNumber1(nums4));
    }

    private static int findRepeatNumber1(int[] nums) {
        int pos = 0;
        while (pos < nums.length) {
            while (nums[pos] != pos) {
                if (nums[pos] == nums[nums[pos]]) {
                    return nums[pos];
                }
                swap(nums, pos, nums[pos]);
            }
            pos++;
        }
        return -1;
    }

    private static void swap(int[] nums, int x, int y) {
        int temp = nums[x];
        nums[x] = nums[y];
        nums[y] = temp;
    }

    public static int findRepeatNumber(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            if (set.contains(num)) {
                return num;
            }
            set.add(num);
        }
        return -1;
    }

}

/*
* 找出数组中重复的数字。


在一个长度为 n 的数组 nums 里的所有数字都在 0～n-1 的范围内。数组中某些数字是重复的，但不知道有几个数字重复了，也不知道每个数字重复了几次。请找出数组中任意一个重复的数字。

示例 1：

输入：
[2, 3, 1, 0, 2, 5, 3]
输出：2 或 3 
 

限制：

2 <= n <= 100000

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/shu-zu-zhong-zhong-fu-de-shu-zi-lcof
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */
