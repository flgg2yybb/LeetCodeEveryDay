package year2021.month8.jz14b;

import java.math.BigInteger;
import java.util.Arrays;

public class CuttingRope {

    public static void main(String[] args) {
        int n1 = 2;
        int n2 = 10;
        int n3 = 1000;
        System.out.println(cuttingRope1(n1));
        System.out.println(cuttingRope1(n2));
        System.out.println(cuttingRope1(n3));
    }

    private static int cuttingRope1(int n) {
        /*
         * 切分规则：
         * 最优： 3 。把绳子尽可能切为多个长度为 3 的片段，留下的最后一段绳子的长度可能为 0,1,2 三种情况。
         * 次优： 2 。若最后一段绳子长度为 2 ；则保留，不再拆为 1+1 。
         * 最差： 1 。若最后一段绳子长度为 1 ；则应把一份 3 + 1 替换为 2 + 2，因为 2 ×2 > 3 × 1。
         * */
        if (n <= 3) {
            return n - 1;
        }
        final int MOD = 1000000007;
        // n = 3a + b，
        int a = n / 3;
        int b = n % 3;
        if (b == 0) {
            return (int) quickPowerMod(3, a, MOD);
        }
        if (b == 2) {
            return (int) (quickPowerMod(3, a, MOD) * 2 % MOD);
        }
        return (int) (quickPowerMod(3, a - 1, MOD) * 4 % MOD);
    }

    private static long quickPowerMod(long x, int a, int p) {
        // 快速幂求余 => 求 (x^a) % p，其中 x < p
        long ans = 1;
        while (a > 0) {
            if ((a & 1) == 1) {
                ans = (ans * x) % p;
            }
            x = (x * x) % p;
            a >>>= 1;
        }
        return ans;
    }

    private static long powerMod(int x, int a, int p) {
        long ans = 1;
        while (a > 0) {
            ans = (ans * x) % p;
            a--;
        }
        return ans;
    }

    public static int cuttingRope(int n) {
        /*
         * DP
         * 状态定义
         * dp[i] 表示长度为 n 的绳子所能剪成的最大乘积
         * 状态转移方程
         * 对长度为 i 的绳子剪下 j 长度一段，则
         * 剩下的 i - j 可以不减
         * 剩下的 i - j 剪
         * 两者取其大
         * dp[i] =  max{j * (i-j), j * dp[i-j]}, j = 2, 3, ... , i-1
         * */
        if (n < 3) {
            return 1;
        }
        BigInteger[] dp = new BigInteger[n + 1];
        Arrays.fill(dp, BigInteger.valueOf(1));
        for (int i = 3; i <= n; i++) {
            for (int j = 2; j < i; j++) {
                // 注意，dp过程不能取余，需要结果取余，故需使用 BitInteger
                dp[i] = dp[i]
                        .max(BigInteger.valueOf((long) j * (i - j)))
                        .max(dp[i - j].multiply(BigInteger.valueOf(j)));
            }
        }
        return dp[n].mod(BigInteger.valueOf(1000000007)).intValue();
    }

}

/*
* 给你一根长度为 n 的绳子，请把绳子剪成整数长度的 m 段（m、n都是整数，n>1并且m>1），每段绳子的长度记为 k[0],k[1]...k[m - 1] 。请问 k[0]*k[1]*...*k[m - 1] 可能的最大乘积是多少？例如，当绳子的长度是8时，我们把它剪成长度分别为2、3、3的三段，此时得到的最大乘积是18。

答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。

 

示例 1：

输入: 2
输出: 1
解释: 2 = 1 + 1, 1 × 1 = 1
示例 2:

输入: 10
输出: 36
解释: 10 = 3 + 3 + 4, 3 × 3 × 4 = 36
 

提示：

2 <= n <= 1000
注意：本题与主站 343 题相同：https://leetcode-cn.com/problems/integer-break/

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/jian-sheng-zi-ii-lcof
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */
