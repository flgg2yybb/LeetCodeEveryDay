package year2021.month6.no279;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class PerfectSquares {

    public static void main(String[] args) {
        int n1 = 12;
        int n2 = 13;
        System.out.println(numSquares(n1));
        System.out.println(numSquares(n2));
    }

    public static int numSquares(int n) {
        /*
         * DP
         * 思路：首先求出小于等于 n 的所有完全平方数，放入容器中
         * 之后，便是 dp 完全问题，从容器中拿出若干元素使其和等于 n
         * 并元素个数尽可能的少
         * 假设容器数组为 element
         * 状态定义：
         * dp[i] 为从容器中拿出若干元素组成和为 n 的最小个数
         * 初始值：
         * dp[0] = 0
         * dp[i*i] = 1, i * i <= n
         * 状态转移方程：
         * dp[i] = min{dp[i-j]} + 1, j 属于容器中的元素
         * */
        final int MAX = 100000;
        int[] dp = new int[n + 1];
        Arrays.fill(dp, MAX);
        dp[0] = 0;
        Set<Integer> elements = new HashSet<>();
        for (int i = 1; i * i <= n; i++) {
            int perfectNumber = i * i;
            elements.add(perfectNumber);
        }
        for (int i = 1; i <= n; i++) {
            for (Integer j : elements) {
                if (i - j >= 0) {
                    dp[i] = Math.min(dp[i], dp[i - j] + 1);
                }
            }
        }
        return dp[n];
    }

}

/*
* 给定正整数 n，找到若干个完全平方数（比如 1, 4, 9, 16, ...）使得它们的和等于 n。你需要让组成和的完全平方数的个数最少。

给你一个整数 n ，返回和为 n 的完全平方数的 最少数量 。

完全平方数 是一个整数，其值等于另一个整数的平方；换句话说，其值等于一个整数自乘的积。例如，1、4、9 和 16 都是完全平方数，而 3 和 11 不是。

 

示例 1：

输入：n = 12
输出：3 
解释：12 = 4 + 4 + 4
示例 2：

输入：n = 13
输出：2
解释：13 = 4 + 9
 
提示：

1 <= n <= 104

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/perfect-squares
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */
