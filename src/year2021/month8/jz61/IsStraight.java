package year2021.month8.jz61;

import java.util.Arrays;

public class IsStraight {

    public static void main(String[] args) {
        int[] nums1 = {1, 2, 3, 4, 5};
        int[] nums2 = {0, 1, 0, 2, 5};
        int[] nums3 = {1, 2, 4, 5, 0};
        int[] nums4 = {1, 2, 6, 5, 0};
        int[] nums5 = {0, 0, 2, 2, 5};
        System.out.println(isStraight(nums1));
        System.out.println(isStraight(nums2));
        System.out.println(isStraight(nums3));
        System.out.println(isStraight(nums4));
        System.out.println(isStraight(nums5));

    }

    public static boolean isStraight(int[] nums) {
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
