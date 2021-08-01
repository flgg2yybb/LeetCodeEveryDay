package year2021.month7.jz56a;

import java.util.Arrays;

public class SingleNumbers {

    public static void main(String[] args) {
        int[] nums1 = {4, 1, 4, 6};
        int[] nums2 = {1, 2, 10, 4, 1, 4, 3, 3};
        System.out.println(Arrays.toString(singleNumbers(nums1)));
        System.out.println(Arrays.toString(singleNumbers(nums2)));
    }

    public static int[] singleNumbers(int[] nums) {
        /*
         * 因有两个数字出现了一次，其他数字都出现了两次，
         * 则对 nums 所有元素做 *异或操作* ，可得结果为 x^y (x、y分别是两个只出现一次的数字)
         * 因 x != y，则 x^y != 0，故 x^y 至少有一个二进制位为 1，则得到改二进制位，记为 z
         * 用 z 去对 nums 各元素做 *与运算*，则可将 nums 分为两个子数组
         * 易知，x、y 分别位于两个子数组，且两个子数组仅包含一个出现一次的元素，其他元素都出现两次
         * （因 nums 保证了除了 x、y 其他元素都出现两次，意味着和 z 做与操作时，同一元素结果相同）
         * 再对两个子数组的所有元素做异或操作，即可得到 x、y
         * */
        int xor = 0;
        for (int num : nums) {
            xor ^= num;
        }
        int z = 1;
        while ((z & xor) == 0) {
            z <<= 1;
        }
        int x = 0;
        int y = 0;
        for (int num : nums) {
            if ((num & z) == 0) {
                x ^= num;
            } else {
                y ^= num;
            }
        }
        return new int[]{x, y};
    }

}

/*
* 一个整型数组 nums 里除两个数字之外，其他数字都出现了两次。请写程序找出这两个只出现一次的数字。要求时间复杂度是O(n)，空间复杂度是O(1)。

 

示例 1：

输入：nums = [4,1,4,6]
输出：[1,6] 或 [6,1]
示例 2：

输入：nums = [1,2,10,4,1,4,3,3]
输出：[2,10] 或 [10,2]
 

限制：

2 <= nums.length <= 10000

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/shu-zu-zhong-shu-zi-chu-xian-de-ci-shu-lcof
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */
