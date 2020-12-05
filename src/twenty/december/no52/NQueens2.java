package twenty.december.no52;

import java.util.HashSet;
import java.util.Set;

public class NQueens2 {
    public static void main(String[] args) {
        int n1 = 1;
        int n2 = 2;
        int n3 = 3;
        int n4 = 4;
        int n5 = 8;
        System.out.println(totalNQueens(n1));
        System.out.println(totalNQueens(n2));
        System.out.println(totalNQueens(n3));
        System.out.println(totalNQueens(n4));
        System.out.println(totalNQueens(n5));
    }

    private static int totalNQueens(int n) {
        if (n == 0) {
            return 0;
        }
        Set<Integer> col = new HashSet<>();
        Set<Integer> diff = new HashSet<>();
        Set<Integer> sum = new HashSet<>();
        return dfs(n, 0, col, diff, sum);
    }

    private static int dfs(int n, int level, Set<Integer> col, Set<Integer> diff, Set<Integer> sum) {
        if (n == level) {
            return 1;
        }
        int count = 0;
        int y = level;
        for (int x = 0; x < n; x++) {
            if (col.contains(x) || diff.contains(y - x) || sum.contains(y + x)) {
                continue;
            }
            col.add(x);
            diff.add(y - x);
            sum.add(y + x);
            count += dfs(n, level + 1, col, diff, sum);
            col.remove(x);
            diff.remove(y - x);
            sum.remove(y + x);
        }
        return count;
    }
}

/*
* n 皇后问题研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。

上图为 8 皇后问题的一种解法。

给定一个整数 n，返回 n 皇后不同的解决方案的数量。

示例:

输入: 4
输出: 2
解释: 4 皇后问题存在如下两个不同的解法。
[
 [".Q..",  // 解法 1
  "...Q",
  "Q...",
  "..Q."],

 ["..Q.",  // 解法 2
  "Q...",
  "...Q",
  ".Q.."]
]



提示：

    皇后，是国际象棋中的棋子，意味着国王的妻子。皇后只做一件事，那就是“吃子”。当她遇见可以吃的棋子时，就迅速冲上去吃掉棋子。当然，她横、竖、斜都可走一或 N-1 步，可进可退。（引用自 百度百科 - 皇后 ）

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/n-queens-ii
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。*/
