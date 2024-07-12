package year2021.month1.no39;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CombinationSum {
    public static void main(String[] args) {
        int[] candidates1 = new int[]{2, 3, 6, 7};
        int target1 = 7;
        int[] candidates2 = new int[]{2, 3, 5};
        int target2 = 8;
        int[] candidates3 = new int[]{2, 7, 6, 3, 5, 1};
        int target3 = 9;
        System.out.println(combinationSum1(candidates1, target1));
        System.out.println(combinationSum1(candidates2, target2));
        System.out.println(combinationSum1(candidates3, target3));
    }

    public static List<List<Integer>> combinationSum1(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        if (candidates == null || candidates.length == 0) {
            return result;
        }
        backtrack1(candidates, 0, target, result, new LinkedList<>());
        return result;
    }

    private static void backtrack1(int[] candidates, int pos, int target, List<List<Integer>> result, Deque<Integer> list) {
        if (target == 0) {
            result.add(new ArrayList<>(list));
            return;
        }
        if (pos >= candidates.length) {
            return;
        }
        int loop = target / candidates[pos];
        for (int i = 0; i <= loop; i++) {
            int num = i * candidates[pos];
            for (int j = 0; j < i; j++) {
                list.offerLast(candidates[pos]);
            }
            backtrack1(candidates, pos + 1, target - num, result, list);
            for (int j = 0; j < i; j++) {
                list.pollLast();
            }
        }
    }

    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        if (candidates == null || candidates.length == 0) {
            return result;
        }
        Arrays.sort(candidates);
        backtrack1(candidates, 0, target, result, new LinkedList<>());
        return result;
    }

    private static void backtrack(int[] candidates, int pos, int target, List<List<Integer>> result, int[] array, int index) {
        if (target == 0) {
            result.add(IntStream.of(array).boxed().filter(num -> num > 0).collect(Collectors.toList()));
            return;
        }
        if (pos >= candidates.length) {
            return;
        }
        if (candidates[pos] > target) {
            return;
        }
        int loop = target / candidates[pos];
        for (int i = 0; i <= loop; i++) {
            int num = i * candidates[pos];
            for (int j = 0; j < i; j++) {
                array[index++] = candidates[pos];
            }
            backtrack(candidates, pos + 1, target - num, result, array, index);
            for (int j = 0; j < i; j++) {
                array[--index] = 0;
            }
        }
    }
}

/*
* 给定一个无重复元素的数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。

candidates 中的数字可以无限制重复被选取。

说明：

所有数字（包括 target）都是正整数。
解集不能包含重复的组合。 
示例 1：

输入：candidates = [2,3,6,7], target = 7,
所求解集为：
[
  [7],
  [2,2,3]
]
示例 2：

输入：candidates = [2,3,5], target = 8,
所求解集为：
[
  [2,2,2,2],
  [2,3,3],
  [3,5]
]
 

提示：

1 <= candidates.length <= 30
1 <= candidates[i] <= 200
candidate 中的每个元素都是独一无二的。
1 <= target <= 500

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/combination-sum
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */
