package year2021.month5.no15;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ThreeSum {

    public static void main(String[] args) {
        int[] nums1 = new int[]{-1, 0, 1, 2, -1, -1, -4};
        int[] nums2 = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
        System.out.println(threeSum(nums1));
        System.out.println(threeSum(nums2));
    }

    private static List<List<Integer>> threeSum(int[] nums) {
        // sort & two pointers, time is O(n^2), space is O(n)
        // 先排序，再遍历 nums 固定 a，同时保证每次 a 不同
        // 再对 a 之后的元素进行双指针查找
        // 头指针指向 a 的下一个元素，尾指针指向末尾元素
        // 找出满足等式 b + c = -a 的集合
        // 若 b + c > -a，需要减小，则尾指针前移
        // 若 b + c < -a，则需要增大，头指针后移
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < nums.length - 2; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                // 保证每次选取的 a 不同
                continue;
            }
            int a = nums[i];
            int left = i + 1;
            int right = nums.length - 1;
            while (left < right) {
                int b = nums[left];
                int c = nums[right];
                if (b + c == -a) {
                    result.add(Arrays.asList(a, b, c));
                    // 保证下个 b 不同
                    do {
                        left++;
                    } while (left < right && nums[left] == nums[left - 1]);
                } else if (b + c > -a) {
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
* 给你一个包含 n 个整数的数组nums，判断nums中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有满足条件且不重复的三元组。

注意：答案中不可以包含重复的三元组。



示例：

给定数组 nums = [-1, 0, 1, 2, -1, -4]，

满足要求的三元组集合为：
[
  [-1, 0, 1],
  [-1, -1, 2]
]

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/3sum
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */
