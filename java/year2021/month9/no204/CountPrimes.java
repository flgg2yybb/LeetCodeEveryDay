package year2021.month9.no204;

import java.util.Arrays;

public class CountPrimes {

    public static void main(String[] args) {
        int n1 = 10;
        int n2 = 0;
        int n3 = 1;
        System.out.println(countPrimes(n1));
        System.out.println(countPrimes(n2));
        System.out.println(countPrimes(n3));
    }

    public static int countPrimes(int n) {
        /*
         * 判断 y 是否为质数，则需要从 2 遍历到 sqrt(x)，
         * 看看是否存在 x，使得 y % x == 0
         * 而若对于每一个数都这么判断，会有很多重复计算。
         * 然而：质数的倍数也不为质数，因此可以自底向上计算
         * */
        boolean[] primes = new boolean[n];
        Arrays.fill(primes, true);
        int count = 0;
        for (int i = 2; i < n; i++) {
            if (primes[i]) {
                count++;
                // i * i 可能溢出
                if ((long) i * i < n) {
                    // 令 j 为 i^2 开始，步长为 i，则 j 必不为 质数
                    for (int j = i * i; j < n; j += i) {
                        primes[j] = false;
                    }
                }
            }
        }
        return count;
    }

}

/*
* 统计所有小于非负整数 n 的质数的数量。

 

示例 1：

输入：n = 10
输出：4
解释：小于 10 的质数一共有 4 个, 它们是 2, 3, 5, 7 。
示例 2：

输入：n = 0
输出：0
示例 3：

输入：n = 1
输出：0
 

提示：

0 <= n <= 5 * 106

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/count-primes
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */
