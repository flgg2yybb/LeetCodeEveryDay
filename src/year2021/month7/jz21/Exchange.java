package year2021.month7.jz21;

import java.util.Arrays;

public class Exchange {

    public static void main(String[] args) {
        int[] nums1 = {1, 2, 3, 4};
        int[] nums2 = {2, 4, 7, 3, 56, 9, 5, 2, 5, 8, 5, 2, 5, 7, 3, 5, 2, 5, 2};
        System.out.println(Arrays.toString(exchange1(nums1)));
        System.out.println(Arrays.toString(exchange1(nums2)));
    }

    private static int[] exchange1(int[] nums) {
        // slow/fast pointers
        // [0, slow) -> odd
        // [slow, fast) -> even
        if (nums == null || nums.length == 0) {
            return nums;
        }
        int slow = 0;
        int fast = 0;
        while (fast < nums.length) {
            if (nums[fast] % 2 == 1) {
                swap(nums, fast, slow);
                slow++;
            }
            fast++;
        }
        return nums;
    }

    private static void swap(int[] nums, int x, int y) {
        int temp = nums[x];
        nums[x] = nums[y];
        nums[y] = temp;
    }

    public static int[] exchange(int[] nums) {
        // partition
        if (nums == null || nums.length == 0) {
            return nums;
        }
        int left = 0;
        int right = nums.length - 1;
        int key = nums[left];
        while (left < right) {
            while (left < right && (nums[right] % 2 == 0)) {
                right--;
            }
            nums[left] = nums[right];
            while (left < right && (nums[left] % 2 == 1)) {
                left++;
            }
            nums[right] = nums[left];
        }
        nums[left] = key;
        return nums;
    }

}

/*
* 输入一个整数数组，实现一个函数来调整该数组中数字的顺序，使得所有奇数位于数组的前半部分，所有偶数位于数组的后半部分。

 

示例：

输入：nums = [1,2,3,4]
输出：[1,3,2,4] 
注：[3,1,2,4] 也是正确的答案之一。
 

提示：

0 <= nums.length <= 50000
1 <= nums[i] <= 10000

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/diao-zheng-shu-zu-shun-xu-shi-qi-shu-wei-yu-ou-shu-qian-mian-lcof
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */
