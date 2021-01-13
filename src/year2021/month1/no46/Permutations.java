package year2021.month1.no46;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Permutations {
    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3};
        System.out.println(permute(nums));
    }

    public static List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return result;
        }
        backtrack(nums, new boolean[nums.length], new int[nums.length], result, 0);
        return result;
    }

    private static void backtrack(int[] nums, boolean[] visited, int[] permutation, List<List<Integer>> result, int level) {
        if (level == nums.length) {
            result.add(IntStream.of(permutation).boxed().collect(Collectors.toList()));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (visited[i]) {
                continue;
            }
            visited[i] = true;
            permutation[level] = nums[i];
            backtrack(nums, visited, permutation, result, level + 1);
            permutation[level] = -1;
            visited[i] = false;
        }
    }
}

/*
* 给定一个 没有重复 数字的序列，返回其所有可能的全排列。

示例:

输入: [1,2,3]
输出:
[
  [1,2,3],
  [1,3,2],
  [2,1,3],
  [2,3,1],
  [3,1,2],
  [3,2,1]
]

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/permutations
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */
