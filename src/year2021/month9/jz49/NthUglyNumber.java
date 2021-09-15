package year2021.month9.jz49;

public class NthUglyNumber {
    public static void main(String[] args) {
        int n1 = 10;
        System.out.println(nthUglyNumber(n1));
    }

    public static int nthUglyNumber(int n) {
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
            if (xa == dp[i]) {
                a++;
            }
            if (xb == dp[i]) {
                b++;
            }
            if (xc == dp[i]) {
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

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/chou-shu-lcof
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */
