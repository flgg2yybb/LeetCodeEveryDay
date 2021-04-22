package year2021.month4.no78;

import java.util.ArrayList;
import java.util.List;

public class Subsets {

    public static void main(String[] args) {
        int[] nums1 = {0};
        int[] nums2 = {1, 2, 3};
        System.out.println(subsets1(nums1));
        System.out.println(subsets1(nums2));
    }

    private static List<List<Integer>> subsets1(int[] nums) {
        // time is O(n * 2^n), space is O(n), not including the return list space
        List<List<Integer>> result = new ArrayList<>();
        // the total amount of subsets of nums
        int total = 1 << nums.length;
        List<Integer> subSet = new ArrayList<>();
        for (int i = 0; i < total; i++) {
            // 每次循环的 i 的二进制位中，为 1 的位的集合即为当前子集
            subSet.clear();
            for (int j = 0; j < nums.length; j++) {
                // 若 (i & (1 << j)) != 0，代表第 j 位需加入到当前子集中
                if ((i & (1 << j)) != 0) {
                    subSet.add(nums[j]);
                }
            }
            result.add(new ArrayList<>(subSet));
        }
        return result;
    }

    public static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(nums, result, new ArrayList<>(), 0);
        return result;
    }

    private static void backtrack(int[] nums, List<List<Integer>> result, List<Integer> collect, int pos) {
        if (pos == nums.length) {
            // O(n)
            result.add(new ArrayList<>(collect));
            return;
        }
        backtrack(nums, result, collect, pos + 1);
        collect.add(nums[pos]);
        backtrack(nums, result, collect, pos + 1);
        collect.remove(collect.size() - 1);
    }

}

/*
* 给你一个整数数组 nums ，数组中的元素 互不相同 。返回该数组所有可能的子集（幂集）。

解集 不能 包含重复的子集。你可以按 任意顺序 返回解集。

 

示例 1：

输入：nums = [1,2,3]
输出：[[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
示例 2：

输入：nums = [0]
输出：[[],[0]]
 

提示：

1 <= nums.length <= 10
-10 <= nums[i] <= 10
nums 中的所有元素 互不相同

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/subsets
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */
