package year2021.month7.jz14a;

public class CuttingRope {

    public static void main(String[] args) {
        int n1 = 2;
        int n2 = 10;
        System.out.println(cuttingRope1(n1));
        System.out.println(cuttingRope1(n2));
    }

    private static int cuttingRope1(int n) {
        /*
         * DP, time is O(n^2), space is O(n)
         * dp[i]：长度为 i 绳子剪成 m 段之后的最大乘积
         * 思路：
         * 先看绳子被剪断的第一段，如果只剪掉长度为 1，则没有任何增益，
         * 因此得从 2 开始剪
         * 假设第一段剪的长度为 j，剪了一段之后，剩下的 (i - j) 可以减，
         * 也可以不减
         *   * 剪的话，最大乘积为 j * dp[i-j]
         *   * 不剪的话，最大乘积为 j * (i-j)
         *   * 两者取最大即可
         * 转移方程：
         * dp[i] = max{dp[i], j * (i-j), j * dp[i-j]}, j = 2, 3, ... , i - 1
         * */
        if (n < 3) {
            return 1;
        }
        int[] dp = new int[n + 1];
        dp[1] = 1;
        dp[2] = 1;
        for (int i = 3; i <= n; i++) {
            for (int j = 2; j < i; j++) {
                dp[i] = Math.max(dp[i], Math.max(j * (i - j), j * dp[i - j]));
            }
        }
        return dp[n];
    }

    public static int cuttingRope(int n) {
        /*
         * 切分规则：
         * 最优： 3 。把绳子尽可能切为多个长度为 3 的片段，留下的最后一段绳子的长度可能为 0,1,2 三种情况。
         * 次优： 2 。若最后一段绳子长度为 2 ；则保留，不再拆为 1+1 。
         * 最差： 1 。若最后一段绳子长度为 1 ；则应把一份 3 + 1 替换为 2 + 2，因为 2 ×2 > 3 × 1。
         * */
        if (n <= 3) {
            return n - 1;
        }
        // n = 3a + b
        int a = n / 3;
        int b = n % 3;
        if (b == 0) {
            return (int) Math.pow(3, a);
        }
        if (b == 2) {
            return (int) Math.pow(3, a) * 2;
        }
        return (int) Math.pow(3, a - 1) * 4;
    }

}

/*
* 给你一根长度为 n 的绳子，请把绳子剪成整数长度的 m 段（m、n都是整数，n>1并且m>1），每段绳子的长度记为 k[0],k[1]...k[m-1] 。请问 k[0]*k[1]*...*k[m-1] 可能的最大乘积是多少？例如，当绳子的长度是8时，我们把它剪成长度分别为2、3、3的三段，此时得到的最大乘积是18。

示例 1：

输入: 2
输出: 1
解释: 2 = 1 + 1, 1 × 1 = 1
示例 2:

输入: 10
输出: 36
解释: 10 = 3 + 3 + 4, 3 × 3 × 4 = 36
提示：

2 <= n <= 58
注意：本题与主站 343 题相同：https://leetcode-cn.com/problems/integer-break/

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/jian-sheng-zi-lcof
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */
