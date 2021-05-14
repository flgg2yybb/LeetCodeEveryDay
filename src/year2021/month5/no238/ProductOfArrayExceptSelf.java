package year2021.month5.no238;

import java.util.Arrays;

public class ProductOfArrayExceptSelf {

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4};
        System.out.println(Arrays.toString(productExceptSelf1(nums)));
    }

    private static int[] productExceptSelf1(int[] nums) {
        int len = nums.length;
        int[] output = new int[len];
        // 计算前缀乘积
        output[0] = 1;
        for (int i = 1; i < len; i++) {
            output[i] = output[i - 1] * nums[i - 1];
        }
        // 计算后缀乘积
        int suffixMul = 1;
        for (int i = len - 1; i >= 0; i--) {
            output[i] *= suffixMul;
            suffixMul *= nums[i];
        }
        return output;
    }

    public static int[] productExceptSelf(int[] nums) {
        /*分别用两个数组 prefixMul 和 suffixMul 来计算
         * nums[i] 的前缀乘积、后缀乘积
         * 在将前缀乘积 乘于 后缀乘积 即为所求
         * */
        int len = nums.length;
        int[] prefixMul = new int[len];
        prefixMul[0] = 1;
        for (int i = 1; i < len; i++) {
            prefixMul[i] = prefixMul[i - 1] * nums[i - 1];
        }
        int[] suffixMul = new int[len];
        suffixMul[len - 1] = 1;
        for (int i = len - 2; i >= 0; i--) {
            suffixMul[i] = suffixMul[i + 1] * nums[i + 1];
        }
        int[] output = new int[len];
        for (int i = 0; i < len; i++) {
            output[i] = prefixMul[i] * suffixMul[i];
        }
        return output;
    }

}

/*
* 给你一个长度为 n 的整数数组 nums，其中 n > 1，返回输出数组 output ，其中 output[i] 等于 nums 中除 nums[i] 之外其余各元素的乘积。

 

示例:

输入: [1,2,3,4]
输出: [24,12,8,6]
 

提示：题目数据保证数组之中任意元素的全部前缀元素和后缀（甚至是整个数组）的乘积都在 32 位整数范围内。

说明: 请不要使用除法，且在 O(n) 时间复杂度内完成此题。

进阶：
你可以在常数空间复杂度内完成这个题目吗？（ 出于对空间复杂度分析的目的，输出数组不被视为额外空间。）

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/product-of-array-except-self
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */
