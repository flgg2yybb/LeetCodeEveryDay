package twenty.december.no51;

import java.util.*;

public class NQueens {
    public static void main(String[] args) {
        int n1 = 4;
        int n2 = 8;
        int n3 = 1;
        List<List<String>> resultList = solveNQueens(n1);
        disp(resultList);
    }

    public static List<List<String>> solveNQueens(int n) {
        List<List<String>> resultList = new ArrayList<>();
        if (n == 0) {
            return resultList;
        }
        Set<Integer> col = new HashSet<>();
        Set<Integer> diff = new HashSet<>();
        Set<Integer> sum = new HashSet<>();
        int[] queens = new int[n];
//        -1 stand for nothing
        Arrays.fill(queens, -1);
        backtrack(n, 0, resultList, queens, col, diff, sum);
        return resultList;
    }

    //    dfs 按层递归，level即行下标
    private static void backtrack(int n, int level,
                                  List<List<String>> resultList, int[] queens,
                                  Set<Integer> col, Set<Integer> diff, Set<Integer> sum) {
        if (level == n) {
            resultList.add(getBoard(queens));
        }
        int y = level;
        for (int x = 0; x < n; x++) {
            if (col.contains(x) || diff.contains(y - x) || sum.contains(y + x)) {
                continue;
            }
            queens[level] = x;
            col.add(x);
            diff.add(y - x);
            sum.add(y + x);
            backtrack(n, level + 1, resultList, queens, col, diff, sum);
//            recover
            queens[level] = -1;
            col.remove(x);
            diff.remove(y - x);
            sum.remove(y + x);
        }
    }

    private static List<String> getBoard(int[] queens) {
        List<String> result = new ArrayList<>();
        Arrays.stream(queens).forEach(queneCol -> {
            char[] chars = new char[queens.length];
            Arrays.fill(chars, '.');
            chars[queneCol] = 'Q';
            result.add(new String(chars));
        });
        return result;
    }

    private static void disp(List<List<String>> resultList) {
        resultList.forEach(list -> {
            list.forEach(System.out::println);
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
