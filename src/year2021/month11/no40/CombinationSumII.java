package year2021.month11.no40;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CombinationSumII {

    public static void main(String[] args) {
        int[] candidates1 = {10, 1, 2, 7, 6, 1, 5};
        int target1 = 8;
        int[] candidates2 = {2, 5, 2, 1, 2};
        int target2 = 5;
        System.out.println(combinationSum2(candidates1, target1));
        System.out.println(combinationSum2(candidates2, target2));
    }

    public static List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        Arrays.sort(candidates);
        boolean[] used = new boolean[candidates.length];
        backtrack(candidates, target, 0, used, 0, ans, new ArrayList<>());
        return new ArrayList<>(ans);
    }

    private static void backtrack(int[] candidates, int target, int pos, boolean[] used, int sum, List<List<Integer>> ans, List<Integer> temps) {
        if (sum == target) {
            ans.add(new ArrayList<>(temps));
            return;
        }
        if (sum > target || pos == candidates.length) {
            return;
        }
        backtrack(candidates, target, pos + 1, used, sum, ans, temps);
        if (pos > 0 && candidates[pos - 1] == candidates[pos] && !used[pos - 1]) {
            return;
        }
        int num = candidates[pos];
        temps.add(num);
        used[pos] = true;
        backtrack(candidates, target, pos + 1, used, sum + num, ans, temps);
        used[pos] = false;
        temps.remove(temps.size() - 1);
    }

    public static List<List<Integer>> combinationSum1(int[] candidates, int target) {
        Set<List<Integer>> ans = new HashSet<>();
        Arrays.sort(candidates);
        backtrack1(candidates, target, 0, 0, ans, new ArrayList<>());
        return new ArrayList<>(ans);
    }

    private static void backtrack1(int[] candidates, int target, int pos, int sum, Set<List<Integer>> ans, List<Integer> temps) {
        if (sum == target) {
            ans.add(new ArrayList<>(temps));
            return;
        }
        if (sum > target || pos == candidates.length) {
            return;
        }
        backtrack1(candidates, target, pos + 1, sum, ans, temps);
        int num = candidates[pos];
        temps.add(num);
        backtrack1(candidates, target, pos + 1, sum + num, ans, temps);
        temps.remove(temps.size() - 1);
    }

}

/*
* 给定一个数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。

candidates 中的每个数字在每个组合中只能使用一次。

注意：解集不能包含重复的组合。 

 

示例 1:

输入: candidates = [10,1,2,7,6,1,5], target = 8,
输出:
[
[1,1,6],
[1,2,5],
[1,7],
[2,6]
]
示例 2:

输入: candidates = [2,5,2,1,2], target = 5,
输出:
[
[1,2,2],
[5]
]
 

提示:

1 <= candidates.length <= 100
1 <= candidates[i] <= 50
1 <= target <= 30

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/combination-sum-ii
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */