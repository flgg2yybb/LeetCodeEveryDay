package year2021.month9.no47;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PermutationsII {

    public static void main(String[] args) {
        int[] nums1 = {1, 1, 3};
        System.out.println(permuteUnique(nums1));
    }

    public static List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        Arrays.sort(nums);
        boolean[] used = new boolean[nums.length];
        backtrack(nums, used, 0, new ArrayList<>(), ans);
        return ans;
    }

    private static void backtrack(int[] nums, boolean[] used, int pos, List<Integer> list, List<List<Integer>> ans) {
        if (pos == nums.length) {
            ans.add(new ArrayList<>(list));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (used[i] || i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) {
                continue;
            }
            list.add(nums[i]);
            used[i] = true;
            backtrack(nums, used, pos + 1, list, ans);
            used[i] = false;
            list.remove(list.size() - 1);
        }
    }

    public static List<List<Integer>> permuteUnique1(int[] nums) {
        Set<List<Integer>> ans = new HashSet<>();
        backtrack1(nums, 0, ans);
        return new ArrayList<>(ans);
    }

    private static void backtrack1(int[] nums, int pos, Set<List<Integer>> ans) {
        if (pos == nums.length) {
            ans.add(IntStream.of(nums).boxed().collect(Collectors.toList()));

            return;
        }
        for (int i = pos; i < nums.length; i++) {
            sawp(nums, i, pos);
            backtrack1(nums, pos + 1, ans);
            sawp(nums, i, pos);
        }
    }

    private static void sawp(int[] nums, int x, int y) {
        int temp = nums[x];
        nums[x] = nums[y];
        nums[y] = temp;
    }

}

/*
* 给定一个可包含重复数字的序列 nums ，按任意顺序 返回所有不重复的全排列。

 

示例 1：

输入：nums = [1,1,2]
输出：
[[1,1,2],
 [1,2,1],
 [2,1,1]]
示例 2：

输入：nums = [1,2,3]
输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
 

提示：

1 <= nums.length <= 8
-10 <= nums[i] <= 10

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/permutations-ii
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */
