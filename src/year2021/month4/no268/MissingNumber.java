package year2021.month4.no268;

import java.util.Arrays;

public class MissingNumber {

    public static void main(String[] args) {
        int[] nums1 = {3, 0, 1};
        int[] nums2 = {0, 1};
        int[] nums3 = {9, 6, 4, 2, 3, 5, 7, 0, 1};
        int[] nums4 = {0};
        System.out.println(missingNumber1(nums1));
        System.out.println(missingNumber1(nums2));
        System.out.println(missingNumber1(nums3));
        System.out.println(missingNumber1(nums4));
    }

    private static int missingNumber1(int[] nums) {
        // 异或运算，因 x ^ x = 0，故可以将nums数组的元素
        // 以及 0 -> n 的元素一起做异或运算，剩下的值只出现一次，即为缺失值
        int n = nums.length;
        int missingNumber = n;
        for (int i = 0; i < n; i++) {
            missingNumber ^= i ^ nums[i];
        }
        return missingNumber;
    }

    public static int missingNumber(int[] nums) {
        int n = nums.length;
        int expectedSum = (1 + n) * n / 2;
        int arraySum = Arrays.stream(nums).sum();
        return expectedSum - arraySum;
    }

}

/*
* 给定一个包含 [0, n]  中  n  个数的数组 nums ，找出 [0, n] 这个范围内没有出现在数组中的那个数。

  

进阶：

你能否实现线性时间复杂度、仅使用额外常数空间的算法解决此问题?
  

示例 1：

输入：nums = [3,0,1]
输出：2
解释：n = 3，因为有 3 个数字，所以所有的数字都在范围 [0,3] 内。2 是丢失的数字，因为它没有出现在 nums 中。
示例 2：

输入：nums = [0,1]
输出：2
解释：n = 2，因为有 2 个数字，所以所有的数字都在范围 [0,2] 内。2 是丢失的数字，因为它没有出现在 nums 中。
示例 3：

输入：nums = [9,6,4,2,3,5,7,0,1]
输出：8
解释：n = 9，因为有 9 个数字，所以所有的数字都在范围 [0,9] 内。8 是丢失的数字，因为它没有出现在 nums 中。
示例 4：

输入：nums = [0]
输出：1
解释：n = 1，因为有 1 个数字，所以所有的数字都在范围 [0,1] 内。1 是丢失的数字，因为它没有出现在 nums 中。
  

提示：

n == nums.length
1 <= n <= 104
0 <= nums[i] <= n
nums 中的所有数字都 独一无二

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/missing-number
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。*/
