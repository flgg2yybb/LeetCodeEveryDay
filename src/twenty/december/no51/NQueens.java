package twenty.december.no51;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class NQueens {
    public static void main(String[] args) {
        int n = 4;
        List<List<String>> resultList = solveNQueens(n);
        disp(resultList);
    }

    public static List<List<String>> solveNQueens(int n) {
        List<List<String>> resultList = new ArrayList<>();
        if (n < 3) {
            return resultList;
        }
        Set<Integer> col = new HashSet<>();
        Set<Integer> diff = new HashSet<>();
        Set<Integer> sum = new HashSet<>();
        dfs(n, 0, resultList, new ArrayList<>(), col, diff, sum);
        return resultList;
    }

    //    dfs 按层递归，level即行下标
    private static void dfs(int n, int level,
                            List<List<String>> resultList, List<String> result,
                            Set<Integer> col, Set<Integer> diff, Set<Integer> sum) {
        if (level == n) {
            resultList.add(result);
        }
        int y = level;
        for (int x = 0; x < n; x++) {
            if (col.contains(x) || diff.contains(y - x) || sum.contains(y + x)) {
                continue;
            }
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < x; i++) {
                sb.append(".");
            }
            sb.append("Q");
            for (int i = x + 1; i < n; i++) {
                sb.append(".");
            }
            result.add(sb.toString());
            col.add(x);
            diff.add(y - x);
            sum.add(y + x);
            dfs(n, level + 1, resultList, result, col, diff, sum);
            col.remove(x);
            diff.remove(y - x);
            sum.remove(y + x);
        }
    }

    private static void disp(List<List<String>> resultList) {
        resultList.forEach(list -> {
            list.forEach(str -> {
                System.out.println(str);
            });
            System.out.println();
        });
    }
}

/*
* n 皇后问题研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。



上图为 8 皇后问题的一种解法。

给定一个整数 n，返回所有不同的 n 皇后问题的解决方案。

每一种解法包含一个明确的 n 皇后问题的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位。

 

示例：

输入：4
输出：[
 [".Q..",  // 解法 1
  "...Q",
  "Q...",
  "..Q."],

 ["..Q.",  // 解法 2
  "Q...",
  "...Q",
  ".Q.."]
]
解释: 4 皇后问题存在两个不同的解法。
 

提示：

皇后彼此不能相互攻击，也就是说：任何两个皇后都不能处于同一条横行、纵行或斜线上。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/n-queens
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */
