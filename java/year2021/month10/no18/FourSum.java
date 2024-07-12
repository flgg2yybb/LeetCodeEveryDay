package year2021.month10.no18;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class FourSum {

    public static void main(String[] args) {
        int[] nums1 = {1, 0, -1, 0, -2, 2};
        int target1 = 0;
        int[] nums2 = {2, 2, 2, 2, 2};
        int target2 = 8;
        System.out.println(fourSum(nums1, target1));
        System.out.println(fourSum(nums2, target2));
    }

    public static List<List<Integer>> fourSum(int[] nums, int target) {
        if (nums.length < 4) {
            return Collections.emptyList();
        }
        List<List<Integer>> ans = new ArrayList<>();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 3; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int a = nums[i];
            for (int j = i + 1; j < nums.length - 2; j++) {
                if (j > i + 1 && nums[j] == nums[j - 1]) {
                    continue;
                }
                int b = nums[j];
                int left = j + 1;
                int right = nums.length - 1;
                while (left < right) {
                    int c = nums[left];
                    int d = nums[right];
                    int sum = a + b + c + d;
                    if (sum == target) {
                        ans.add(Arrays.asList(a, b, c, d));
                        left++;
                        while (left < right && nums[left] == nums[left - 1]) {
                            left++;
                        }
                        right--;
                        while (left < right && nums[right] == nums[right + 1]) {
                            right--;
                        }
                    } else if (sum < target) {
                        left++;
                    } else {
                        right--;
                    }
                }
            }
        }
        return ans;
    }

}

/*
* 给你一个由 n 个整数组成的数组 nums ，和一个目标值 target 。请你找出并返回满足下述全部条件且不重复的四元组 [nums[a], nums[b], nums[c], nums[d]] ：

0 <= a, b, c, d < n
a、b、c 和 d 互不相同
nums[a] + nums[b] + nums[c] + nums[d] == target
你可以按 任意顺序 返回答案 。

 

示例 1：

输入：nums = [1,0,-1,0,-2,2], target = 0
输出：[[-2,-1,1,2],[-2,0,0,2],[-1,0,0,1]]
示例 2：

输入：nums = [2,2,2,2,2], target = 8
输出：[[2,2,2,2]]
 

提示：

1 <= nums.length <= 200
-109 <= nums[i] <= 109
-109 <= target <= 109

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/4sum
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */
