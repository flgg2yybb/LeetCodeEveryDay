package year2021.month3.no189;

import java.util.Arrays;
import java.util.stream.Collectors;

public class RotateArray {
    public static void main(String[] args) {
        int[] nums1 = {1, 2, 3, 4, 5, 6, 7};
        int k1 = 3;
        int[] nums2 = {-1, -100, 3, 99};
        int k2 = 2;
        int[] nums3 = {1, 2, 3, 4, 5, 6, 7};
        int k3 = 7;
        rotate(nums1, k1);
        disp(nums1);
        rotate(nums2, k2);
        disp(nums2);
        rotate(nums3, k3);
        disp(nums3);
    }

    public static void rotate(int[] nums, int k) {
        //原数组 -> 1, 2, 3, 4, 5, 6, 7
        //翻转数组[0, n-1] -> 7, 6, 5, 4, 3, 2, 1
        //翻转 [0, (k%n)-1] -> 5, 6, 7, 4, 3, 2, 1
        //翻转[(k%n), n-1] -> 5, 6, 7, 1, 2, 3, 4
        int n = nums.length;
        k %= n;
        reverse(nums, 0, n - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, n - 1);
    }

    private static void reverse(int[] nums, int start, int end) {
        int left = start;
        int right = end;
        while (left < right) {
            int temp = nums[left];
            nums[left] = nums[right];
            nums[right] = temp;
            left++;
            right--;
        }
    }

    private static void disp(int[] nums) {
        System.out.println(Arrays.stream(nums).boxed().collect(Collectors.toList()));
    }
}

/*
* 给定一个数组，将数组中的元素向右移动   k   个位置，其中   k   是非负数。

   

进阶：

尽可能想出更多的解决方案，至少有三种不同的方法可以解决这个问题。
你可以使用空间复杂度为   O(1) 的   原地   算法解决这个问题吗？
   

示例 1:

输入: nums = [1,2,3,4,5,6,7], k = 3
输出: [5,6,7,1,2,3,4]
解释:
向右旋转 1 步: [7,1,2,3,4,5,6]
向右旋转 2 步: [6,7,1,2,3,4,5]
向右旋转 3 步: [5,6,7,1,2,3,4]
示例   2:

输入：nums = [-1,-100,3,99], k = 2
输出：[3,99,-1,-100]
解释: 
向右旋转 1 步: [99,-1,-100,3]
向右旋转 2 步: [3,99,-1,-100]
   

提示：

1 <= nums.length <= 2 * 104
-231 <= nums[i] <= 231 - 1
0 <= k <= 105

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/rotate-array
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */
