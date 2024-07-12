package year2021.month8.jz49;

public class NthUglyNumber {

    public static void main(String[] args) {
        int n1 = 10;
        System.out.println(nthUglyNumber(n1));
    }

    public static int nthUglyNumber(int n) {
        /*
         * 算法：由于丑数只包含质因子 2、3、5
         * 则对于待求的第n大丑数 xn，可以从较小的某个丑数转移过来，有
         * xn = min{xa*2, xb*3, xc*5}
         * 相当对于每个小的丑数，都乘于 2、3、5，则结果必为丑数，
         * 但不能保证顺序。
         * 故可以设索引 a、b、c 用来表示丑数是否已经乘过 2、3、5，
         * 同时在每次筛选时选最小的丑数，并将相应索引 +1
         * 注：可能出现 xa*2、xb*3、xc*5 有相同的情况，
         * 故在对索引 +1判断时，每个索引都应该独立判断（即不能if else）
         * DP
         * 状态定义
         * dp[i]为从小到大排序的第 i-1 个丑数
         * 状态转移方程
         * dp[i] = min{dp[a]*2, dp[b]*3, dp[c]*5}, 0 <= a,b,c < i
         * 若dp[i] = dp[a]*2，a++
         * 若dp[i] = dp[b]*3，b++
         * 若dp[i] = dp[c]*5，c++
         * 初始值
         * dp[0] = 1
         * a = 0
         * b = 0
         * c = 0
         * */
        int[] dp = new int[n];
        dp[0] = 1;
        int a = 0;
        int b = 0;
        int c = 0;
        for (int i = 1; i < n; i++) {
            int xa = dp[a] * 2;
            int xb = dp[b] * 3;
            int xc = dp[c] * 5;
            dp[i] = Math.min(xa, Math.min(xb, xc));
            if (dp[i] == xa) {
                a++;
            }
            if (dp[i] == xb) {
                b++;
            }
            if (dp[i] == xc) {
                c++;
            }
        }
        return dp[n - 1];
    }

}

/*
* 我们把只包含质因子 2、3 和 5 的数称作丑数（Ugly Number）。求按从小到大的顺序的第 n 个丑数。

 

示例:

输入: n = 10
输出: 12
解释: 1, 2, 3, 4, 5, 6, 8, 9, 10, 12 是前 10 个丑数。
说明:  

1 是丑数。
n 不超过1690。
注意：本题与主站 264 题相同：https://leetcode-cn.com/problems/ugly-number-ii/

通过次数77,774提交次数119,518

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/chou-shu-lcof
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */
