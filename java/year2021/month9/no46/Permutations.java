package year2021.month9.no46;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Permutations {

    public static void main(String[] args) {
        int[] nums1 = {1, 2, 3};
        System.out.println(permute(nums1));
    }

    public static List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        backtrack(nums, 0, ans);
        return ans;
    }

    private static void backtrack(int[] nums, int pos, List<List<Integer>> ans) {
        if (pos == nums.length) {
            ans.add(IntStream.of(nums).boxed().collect(Collectors.toList()));
            return;
        }
        for (int i = pos; i < nums.length; i++) {
            sawp(nums, i, pos);
            backtrack(nums, pos + 1, ans);
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
* 给定一个不含重复数字的数组 nums ，返回其 所有可能的全排列 。你可以 按任意顺序 返回答案。

 

示例 1：

输入：nums = [1,2,3]
输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
示例 2：

输入：nums = [0,1]
输出：[[0,1],[1,0]]
示例 3：

输入：nums = [1]
输出：[[1]]
 

提示：

1 <= nums.length <= 6
-10 <= nums[i] <= 10
nums 中的所有整数 互不相同

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/permutations
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */
