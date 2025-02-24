package year2025.month2.no238;

import java.util.Arrays;

public class ProductOfArrayExceptSelf {

    public static void main(String[] args) {
        int[] nums1 = new int[]{1, 2, 3, 4};
        int[] nums2 = new int[]{-1, 1, 0, -3, 3};
        System.out.println(Arrays.toString(productExceptSelf(nums1)));
        System.out.println(Arrays.toString(productExceptSelf(nums2)));
    }

    // Prefix Sum, times: O(n), space: O(n)
    public static int[] productExceptSelf(int[] nums) {
        int[] prefix = new int[nums.length];
        for (int i = 0; i < prefix.length; i++) {
            if (i == 0) {
                prefix[i] = 1;
                continue;
            }
            prefix[i] = prefix[i - 1] * nums[i - 1];
        }
        int[] subfix = new int[nums.length];
        for (int i = subfix.length - 1; i >= 0; i--) {
            if (i == subfix.length - 1) {
                subfix[i] = 1;
                continue;
            }
            subfix[i] = subfix[i + 1] * nums[i + 1];
        }
        int[] res = new int[nums.length];
        for (int i = 0; i < res.length; i++) {
            res[i] = prefix[i] * subfix[i];
        }
        return res;
    }
}

/*
* 给你一个整数数组 nums，返回 数组 answer ，其中 answer[i] 等于 nums 中除 nums[i] 之外其余各元素的乘积 。

题目数据 保证 数组 nums之中任意元素的全部前缀元素和后缀的乘积都在  32 位 整数范围内。

请 不要使用除法，且在 O(n) 时间复杂度内完成此题。



示例 1:

输入: nums = [1,2,3,4]
输出: [24,12,8,6]
示例 2:

输入: nums = [-1,1,0,-3,3]
输出: [0,0,9,0,0]


提示：

2 <= nums.length <= 105
-30 <= nums[i] <= 30
输入 保证 数组 answer[i] 在  32 位 整数范围内


进阶：你可以在 O(1) 的额外空间复杂度内完成这个题目吗？（ 出于对空间复杂度分析的目的，输出数组 不被视为 额外空间。）
* */
