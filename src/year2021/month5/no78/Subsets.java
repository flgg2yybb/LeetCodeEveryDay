package year2021.month5.no78;

import java.util.ArrayList;
import java.util.List;

public class Subsets {

    public static void main(String[] args) {
        int[] nums1 = {1, 2, 3};
        int[] nums2 = {0};
        System.out.println(subsets(nums1));
        System.out.println(subsets(nums2));
    }

    public static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        backtrack(ans, new ArrayList<>(), nums, 0);
        return ans;
    }

    private static void backtrack(List<List<Integer>> ans, List<Integer> collect, int[] nums, int pos) {
        if (pos == nums.length) {
            ans.add(new ArrayList<>(collect));
            return;
        }
        backtrack(ans, collect, nums, pos + 1);
        collect.add(nums[pos]);
        backtrack(ans, collect, nums, pos + 1);
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
