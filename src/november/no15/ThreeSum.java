package november.no15;

import java.util.*;
import java.util.stream.Collectors;

public class ThreeSum {
    public static void main(String[] args) {
        int[] nums1 = new int[]{-1, 0, 1, 2, -1, -1, -4};
        int[] nums2 = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
        disp(threeSum(nums2));
    }

    private static List<List<Integer>> threeSum(int[] nums) {
        if (nums.length < 3) {
            return Collections.emptyList();
        }
//        排序，以使用双指针，且可方便去重
        Arrays.sort(nums);
        List<List<Integer>> resultList = new ArrayList<>();
        int i = 0;
        while (i < nums.length - 2) {
//            保证每次的i都不同
            if (i > 0 && nums[i] == nums[i - 1]) {
                i++;
                continue;
            }
            int x = nums[i];
            int left = i + 1;
            int right = nums.length - 1;
            while (left < right) {
                int y = nums[left];
                int z = nums[right];
                if (x + y + z == 0) {
                    List<Integer> list = Arrays.asList(x, y, z);
                    resultList.add(list);
                    while (left < right && nums[left] == nums[left + 1]) {
//                        保证下一个left不同
                        left++;
                    }
                    left++;
                    while (left < right && nums[right] == nums[right - 1]) {
//                        保证下一个right不同
                        right--;
                    }
                    right--;
                } else if (x + y + z > 0) {
                    right--;
                } else {
                    left++;
                }
            }
            i++;
        }
        return resultList;
    }

    private static void disp(List<List<Integer>> lists) {
        lists.forEach(System.out::println);
    }
}

/*
* 给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有满足条件且不重复的三元组。

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
