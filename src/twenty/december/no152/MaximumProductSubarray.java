package twenty.december.no152;

import java.util.stream.IntStream;

public class MaximumProductSubarray {
    public static void main(String[] args) {
        int[] num1 = new int[]{2, 3, -2, 4};
        int[] num2 = new int[]{-2, 0, -1};
        int[] num3 = new int[]{2, 3, -2, -4};
        System.out.println(maxProduct2(num1));
        System.out.println(maxProduct2(num2));
        System.out.println(maxProduct2(num3));
    }

    private static int maxProduct2(int[] nums) {
//        DP, fmax[i]为nums数组中从下标为0的元素到下标为i元素中的最大子序列乘积
//            fmax[i]为nums数组中从下标为0的元素到下标为i元素中的最小子序列乘积
//            fmax[i] = fmax{fmax[i - 1] * nums[i], fmin[i - 1] * nums[i], nums[i]}
//            fmin[i] = fmin{fmax[i - 1] * nums[i], fmin[i - 1] * nums[i], nums[i]}
        int[] fmax = new int[nums.length];
        int[] fmin = new int[nums.length];
        fmax[0] = nums[0];
        fmin[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            int element1 = fmax[i - 1] * nums[i];
            int element2 = fmin[i - 1] * nums[i];
            fmax[i] = Math.max(nums[i], Math.max(element1, element2));
            fmin[i] = Math.min(nums[i], Math.min(element1, element2));
        }
        return IntStream.of(fmax).max().getAsInt();
    }

    private static int maxProduct(int[] nums) {
//        DP, dp[i][j]为nums数组中从 i下标的元素到 j下标的元素的乘积，则
//        dp[i][i] = nums[i], j = i
//        dp[i][j] = dp[i][j - 1] * nums[j], j > i
        int[][] dp = new int[nums.length][nums.length];
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            dp[i][i] = nums[i];
            if (max < dp[i][i]) {
                max = dp[i][i];
            }
        }
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                dp[i][j] = dp[i][j - 1] * nums[j];
                if (max < dp[i][j]) {
                    max = dp[i][j];
                }
            }
        }
        return max;
    }

    private static void disp(int[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
    }
}

/**
 * 给你一个整数数组 nums ，请你找出数组中乘积最大的连续子数组（该子数组中至少包含一个数字），并返回该子数组所对应的乘积。
 * <p>
 *  
 * <p>
 * 示例 1:
 * <p>
 * 输入: [2,3,-2,4]
 * 输出: 6
 * 解释: 子数组 [2,3] 有最大乘积 6。
 * 示例 2:
 * <p>
 * 输入: [-2,0,-1]
 * 输出: 0
 * 解释: 结果不能为 2, 因为 [-2,-1] 不是子数组。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/maximum-product-subarray
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */