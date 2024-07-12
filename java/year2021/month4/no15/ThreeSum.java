package year2021.month4.no15;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ThreeSum {

    public static void main(String[] args) {
        int[] nums1 = {-1, 0, 1, 2, -1, -4};
        int[] nums2 = {0, 0, 0, 0, 0, 0, 0, 0, 0};
        int[] nums3 = {};
        int[] nums4 = {0};
        System.out.println(threeSum(nums1));
        System.out.println(threeSum(nums2));
        System.out.println(threeSum(nums3));
        System.out.println(threeSum(nums4));
    }

    public static List<List<Integer>> threeSum(int[] nums) {
        int length = nums.length;
        if (length < 3) {
            return Collections.emptyList();
        }
        // sort
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < length - 2; i++) {
            // skip repeated element
            if (i > 0 && nums[i - 1] == nums[i]) {
                continue;
            }
            int a = nums[i];
            // two pointers scan
            int left = i + 1;
            int right = length - 1;
            while (left < right) {
                int b = nums[left];
                int c = nums[right];
                if (a + b + c == 0) {
                    result.add(Arrays.asList(a, b, c));
                    left++;
                    right--;
                    // skip repeated element
                    while (left < right && nums[left - 1] == nums[left]) {
                        left++;
                    }
                    // skip repeated element
                    while (left < right && nums[right + 1] == nums[right]) {
                        right--;
                    }
                } else if (a + b + c > 0) {
                    right--;
                } else {
                    left++;
                }
            }
        }
        return result;
    }

}

/*
* 给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有和为 0 且不重复的三元组。

注意：答案中不可以包含重复的三元组。

 

示例 1：

输入：nums = [-1,0,1,2,-1,-4]
输出：[[-1,-1,2],[-1,0,1]]
示例 2：

输入：nums = []
输出：[]
示例 3：

输入：nums = [0]
输出：[]
 

提示：

0 <= nums.length <= 3000
-105 <= nums[i] <= 105

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/3sum
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */
