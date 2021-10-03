package year2021.month10.no90;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SubsetsII {
    public static void main(String[] args) {
        int[] nums1 = {1, 2, 2};
        int[] nums2 = {0};
        System.out.println(subsetsWithDup(nums1));
        System.out.println(subsetsWithDup(nums2));
    }

    public static List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        boolean[] visited = new boolean[nums.length];
        Arrays.sort(nums);
        backtrack(nums, visited, ans, list, 0);
        return ans;
    }

    private static void backtrack(int[] nums, boolean[] visited, List<List<Integer>> ans, List<Integer> list, int pos) {
        if (pos == nums.length) {
            ans.add(new ArrayList<>(list));
            return;
        }
        backtrack(nums, visited, ans, list, pos + 1);
        if (pos > 0 && nums[pos] == nums[pos - 1] && !visited[pos - 1]) {
            return;
        }
        visited[pos] = true;
        list.add(nums[pos]);
        backtrack(nums, visited, ans, list, pos + 1);
        list.remove(list.size() - 1);
        visited[pos] = false;
    }
}

/*
 * 给你一个整数数组 nums ，其中可能包含重复元素，请你返回该数组所有可能的子集（幂集）。

解集 不能 包含重复的子集。返回的解集中，子集可以按 任意顺序 排列。

 

示例 1：

输入：nums = [1,2,2]
输出：[[],[1],[1,2],[1,2,2],[2],[2,2]]
示例 2：

输入：nums = [0]
输出：[[],[0]]
 

提示：

1 <= nums.length <= 10
-10 <= nums[i] <= 10

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/subsets-ii
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */
