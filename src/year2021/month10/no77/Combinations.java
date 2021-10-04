package year2021.month10.no77;

import java.util.ArrayList;
import java.util.List;

public class Combinations {
    public static void main(String[] args) {
        int n1 = 4, k1 = 2;
        int n2 = 1, k2 = 1;
        System.out.println(combine(n1, k1));
        System.out.println(combine(n2, k2));
    }

    public static List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        backtrack(n, 1, k, ans, list);
        return ans;
    }

    private static void backtrack(int n, int num, int remain, List<List<Integer>> ans, List<Integer> list) {
        if (remain == 0) {
            ans.add(new ArrayList<>(list));
            return;
        }
        if (remain < n - num + 1) { // 不选
            backtrack(n, num + 1, remain, ans, list);
        }
        list.add(num);
        backtrack(n, num + 1, remain - 1, ans, list);
        list.remove(list.size() - 1);
    }
}

/*
 * 给定两个整数 n 和 k，返回范围 [1, n] 中所有可能的 k 个数的组合。

你可以按 任何顺序 返回答案。

 

示例 1：

输入：n = 4, k = 2
输出：
[
  [2,4],
  [3,4],
  [2,3],
  [1,2],
  [1,3],
  [1,4],
]
示例 2：

输入：n = 1, k = 1
输出：[[1]]
 

提示：

1 <= n <= 20
1 <= k <= n

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/combinations
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */
