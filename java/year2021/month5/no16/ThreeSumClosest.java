package year2021.month5.no16;

import java.util.Arrays;

public class ThreeSumClosest {

    public static void main(String[] args) {
        int[] nums1 = {-1, 2, 1, -4};
        int target = 1;
        System.out.println(threeSumClosest(nums1, target));
    }

    public static int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int closest = 100000;
        for (int i = 0; i < nums.length - 2; i++) {
            // 移动到下一个不同元素
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int left = i + 1;
            int right = nums.length - 1;
            while (left < right) {
                int a = nums[i];
                int b = nums[left];
                int c = nums[right];
                if (a + b + c == target) {
                    return a + b + c;
                }
                if (Math.abs(a + b + c - target) < Math.abs(closest - target)) {
                    closest = a + b + c;
                }
                if (a + b + c > target) {
                    // 移动到下一个不同元素
                    do {
                        right--;
                    } while (left < right && nums[right] == nums[right + 1]);
                } else {
                    // 移动到下一个不同元素
                    do {
                        left++;
                    } while (left < right && nums[left] == nums[left - 1]);
                }
            }
        }
        return closest;
    }

}

/*
给定一个包括 n 个整数的数组 nums 和 一个目标值 target。找出 nums 中的三个整数，使得它们的和与 target 最接近。返回这三个数的和。假定每组输入只存在唯一答案。

 

示例：

输入：nums = [-1,2,1,-4], target = 1
输出：2
解释：与 target 最接近的和是 2 (-1 + 2 + 1 = 2) 。
 

提示：

3 <= nums.length <= 10^3
-10^3 <= nums[i] <= 10^3
-10^4 <= target <= 10^4

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/3sum-closest
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
*/
