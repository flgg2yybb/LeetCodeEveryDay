package year2021.month4.no204;

import java.util.Arrays;

public class CountPrimes {
    public static void main(String[] args) {
        int n = 10;
        System.out.println(countPrimes1(n));
    }

    private static int countPrimes1(int n) {
        // 埃氏筛, time is O(n loglogn), space is O(n)
        boolean[] isPrimes = new boolean[n];
        Arrays.fill(isPrimes, true);
        int count = 0;
        for (int i = 2; i < n; i++) {
            if (isPrimes[i]) {
                count++;
                // 当前质数的倍数一定不是质数，从 i^2 开始，
                // 不需要从 2*i 开始，因为 2*i 必被之前的 2 所计算过
                if ((long) i * i < n) {
                    for (int j = i * i; j < n; j = j + i) {
                        isPrimes[j] = false;
                    }
                }
            }
        }
        return count;
    }

    public static int countPrimes(int n) {
        int count = 0;
        for (int i = 2; i < n; i++) {
            // 质数只能被 1 和 本身 整除
            boolean isPrime = true;
            for (int j = 2; j * j <= i; j++) {
                if (i % j == 0) {
                    isPrime = false;
                    break;
                }
            }
            if (isPrime) {
                count++;
            }
        }
        return count;
    }
}

/*
 * 统计所有小于非负整数 n 的质数的数量。

 

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
