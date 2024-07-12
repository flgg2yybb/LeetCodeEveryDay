package year2021.month2.no238;

import java.util.Arrays;
import java.util.stream.Collectors;

public class ProductOfArrayExceptSelf {
    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3, 4};
        disp(productExceptSelf1(nums));
    }

    private static int[] productExceptSelf1(int[] nums) {
        /*求出每个元素的前缀乘积以及后缀乘积，
         * 再将每个元素的前缀乘积乘与后缀乘积，即为结果
         * 空间优化：
         * 先用answer数组存储前缀乘积
         * 再用suffix滚动变量依次获取后缀乘积并与answer对应位相乘
         * 节省了前缀乘积和后缀乘积的数组空间开销
         * time is O(n)，两轮for循环
         * space is O(1)
         * */
        int[] answer = new int[nums.length];
        answer[0] = 1;
        for (int i = 1; i < nums.length; i++) {
            answer[i] = answer[i - 1] * nums[i - 1];
        }
        int suffix = 1;
        for (int i = nums.length - 1; i >= 0; i--) {
            answer[i] = answer[i] * suffix;
            suffix = suffix * nums[i];
        }
        return answer;
    }

    public static int[] productExceptSelf(int[] nums) {
        /*求出每个元素的前缀乘积以及后缀乘积，
         * 再将每个元素的前缀乘积乘与后缀乘积，即为结果
         * time is O(n)，三轮for循环
         * space is O(n)，开辟两个长度为n的数组分别存储前缀乘积和后缀乘积
         * */
        int[] answer = new int[nums.length];
        int[] prefix = new int[nums.length];
        prefix[0] = 1;
        for (int i = 1; i < nums.length; i++) {
            prefix[i] = prefix[i - 1] * nums[i - 1];
        }
        int[] suffix = new int[nums.length];
        suffix[nums.length - 1] = 1;
        for (int i = nums.length - 2; i >= 0; i--) {
            suffix[i] = suffix[i + 1] * nums[i + 1];
        }
        for (int i = 0; i < nums.length; i++) {
            answer[i] = prefix[i] * suffix[i];
        }
        return answer;
    }

    private static void disp(int[] nums) {
        System.out.println(Arrays.stream(nums).boxed().collect(Collectors.toList()));
    }
}

/*
 * 给你一个长度为 n 的整数数组 nums，其中 n > 1，返回输出数组 output ，其中 output[i] 等于 nums 中除 nums[i] 之外其余各元素的乘积。

 

示例:

输入: [1,2,3,4]
输出: [24,12,8,6]
 

提示：题目数据保证数组之中任意元素的全部前缀元素和后缀（甚至是整个数组）的乘积都在 32 位整数范围内。

说明: 请不要使用除法，且在 O(n) 时间复杂度内完成此题。

进阶：
你可以在常数空间复杂度内完成这个题目吗？（ 出于对空间复杂度分析的目的，输出数组不被视为额外空间。）

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/product-of-array-except-self
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */
