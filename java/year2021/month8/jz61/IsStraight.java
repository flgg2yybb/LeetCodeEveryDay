package year2021.month8.jz61;

import java.util.Arrays;

public class IsStraight {

    public static void main(String[] args) {
        int[] nums1 = {1, 2, 3, 4, 5};
        int[] nums2 = {0, 1, 0, 2, 5};
        int[] nums3 = {1, 2, 4, 5, 0};
        int[] nums4 = {1, 2, 6, 5, 0};
        int[] nums5 = {0, 0, 2, 2, 5};
        System.out.println(isStraight1(nums1));
        System.out.println(isStraight1(nums2));
        System.out.println(isStraight1(nums3));
        System.out.println(isStraight1(nums4));
        System.out.println(isStraight1(nums5));

    }

    private static boolean isStraight1(int[] nums) {
        /*
         * 用 Set 遍历 nums 中非 0 元素，存在重复元素则说明不存在顺子
         * 同时记录最大 max 、最小 min 元素，遍历完成之后，
         * 说明不存在重复元素，若此时满足 max - min < 5，说明是顺子
         * 前提：不存在重复元素，且满足 max - min < 5，举例：
         * 0 个 0 元素，牌为 1 2 3 4 5
         * 1 个 0 元素，牌为 1 0 3 4 5
         * 2 个 0 元素，牌为 1 0 0 4 5
         * 3 个 0 元素，牌为 1 0 0 0 5
         * ...
         * */
        int[] set = new int[14];
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for (int num : nums) {
            if (num == 0) {
                continue;
            }
            if (set[num] == 1) {
                return false;
            }
            set[num] = 1;
            max = Math.max(max, num);
            min = Math.min(min, num);
        }
        return max - min < 5;
    }

    public static boolean isStraight(int[] nums) {
        /*
         * 排序，模拟顺子，遇到非顺子的牌，用 0 补齐
         * */
        Arrays.sort(nums);
        int i = 0;
        int zeroCount = 0;
        while (i < nums.length && nums[i] == 0) {
            i++;
            zeroCount++;
        }
        int prev = nums[i++];
        while (i < nums.length) {
            if (nums[i] <= prev) {
                return false;
            }
            if (nums[i] - prev - 1 > zeroCount) {
                return false;
            }
            zeroCount -= nums[i] - prev - 1;
            prev = nums[i];
            i++;
        }
        return true;
    }

}

/*
* 从扑克牌中随机抽5张牌，判断是不是一个顺子，即这5张牌是不是连续的。2～10为数字本身，A为1，J为11，Q为12，K为13，而大、小王为 0 ，可以看成任意数字。A 不能视为 14。

 

示例 1:

输入: [1,2,3,4,5]
输出: True
 

示例 2:

输入: [0,0,1,2,5]
输出: True
 

限制：

数组长度为 5 

数组的数取值为 [0, 13] .

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/bu-ke-pai-zhong-de-shun-zi-lcof
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */
