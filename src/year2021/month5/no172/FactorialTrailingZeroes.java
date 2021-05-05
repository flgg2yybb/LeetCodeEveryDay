package year2021.month5.no172;

import java.math.BigInteger;

public class FactorialTrailingZeroes {

    public static void main(String[] args) {
        int n1 = 13;
        int n2 = 25;
        int n3 = 11231;
        System.out.println(trailingZeroes(n1));
        System.out.println(trailingZeroes(n2));
        System.out.println(trailingZeroes(n3));
        System.out.println(count(n1));
        System.out.println(count(n2));
        System.out.println(count(n3));
    }

    private static int count(int n) {
        BigInteger temp = BigInteger.ONE;
        for (int i = 1; i <= n; i++) {
            temp = temp.multiply(BigInteger.valueOf(i));
        }
        int ans = 0;
        while (temp.mod(BigInteger.TEN).intValue() == 0) {
            ans++;
            temp = temp.divide(BigInteger.TEN);
        }
        return ans;
    }

    public static int trailingZeroes(int n) {
        // 尾数中零的数量，就等于 n! 可以分解成多少个 5 相乘的数量
        // 而能分解除 5 的数必是 5 的倍数
        // 对于 5, 10, 15, 20, 30, 35 等都只能分出一个 5
        // 而对于 25, 125 这种 5 的幂，则可以分出多个 5
        // 故 n! = a*5 + b*5^2 + c*5^3 + ... + p*5^q + X, X 为不能被 5 整除的整数
        // 则零的数量即为 a + b + c + ... + p
        int count = 0;
        for (int i = 5; i <= n; i *= 5) {
            count += n / i;
        }
        return count;
    }

}

/*
* 给定一个整数 n，返回 n! 结果尾数中零的数量。

示例 1:

输入: 3
输出: 0
解释: 3! = 6, 尾数中没有零。
示例 2:

输入: 5
输出: 1
解释: 5! = 120, 尾数中有 1 个零.
说明: 你算法的时间复杂度应为 O(log n) 。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/factorial-trailing-zeroes
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */
