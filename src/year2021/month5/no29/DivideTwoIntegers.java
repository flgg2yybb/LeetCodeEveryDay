package year2021.month5.no29;

public class DivideTwoIntegers {

    public static void main(String[] args) {
        int dividend1 = 10;
        int divisor1 = 3;
        int dividend2 = 7;
        int divisor2 = -3;
        int dividend3 = Integer.MAX_VALUE;
        int divisor3 = 1;
        int dividend4 = Integer.MIN_VALUE;
        int divisor4 = 1;
        int dividend5 = Integer.MAX_VALUE;
        int divisor5 = -1;
        int dividend6 = Integer.MIN_VALUE;
        int divisor6 = -1;
        System.out.println(divide(dividend1, divisor1));
        System.out.println(divide(dividend2, divisor2));
        System.out.println(divide(dividend3, divisor3));
        System.out.println(divide(dividend4, divisor4));
        System.out.println(divide(dividend5, divisor5));
        System.out.println(divide(dividend6, divisor6));
    }

    public static int divide(int dividend, int divisor) {
        boolean isNegative = false;
        if (dividend < 0 && divisor > 0 || dividend > 0 && divisor < 0) {
            isNegative = true;
        }
        long absDividend = dividend < 0 ? dividend * -1L : dividend;
        long absDivisor = divisor < 0 ? divisor * -1L : divisor;
        long left = 0;
        long right = absDividend;
        while (left < right) {
            long mid = left + ((right - left + 1) >> 1);
            if (quickMul(mid, absDivisor) <= absDividend) {
                left = mid;
            } else {
                right = mid - 1;
            }
        }
        long ans = isNegative ? -1 * left : left;
        if (ans > Integer.MAX_VALUE || ans < Integer.MIN_VALUE) {
            return Integer.MAX_VALUE;
        }
        return (int) ans;
    }

    private static long quickMul(long x, long y) {
        /*
         * 乘法的本质是加法，快速乘法思想如下，对于 13 乘 27
         * 将 27 转换成二进制：
         * 27 = 11011(二进制) = 1 * 2^4 + 1 * 2^3 + 0 * 2^2 + 1 * 2^1 + 1
         *    = 16 + 8 + 2 + 1
         * 则
         * 13 * 27 = 13 * (16 + 8 + 2 + 1)
         *         = 13 * 16 + 13 * 8 + 13 * 2 + 13 * 1
         * */
        long ans = 0;
        while (y > 0) {
            if ((y & 1) == 1) {
                ans += x;
            }
            y >>= 1;
            x <<= 1;
        }
        return ans;
    }

}

/*
* 给定两个整数，被除数 dividend 和除数 divisor。将两数相除，要求不使用乘法、除法和 mod 运算符。

返回被除数 dividend 除以除数 divisor 得到的商。

整数除法的结果应当截去（truncate）其小数部分，例如：truncate(8.345) = 8 以及 truncate(-2.7335) = -2

 

示例 1:

输入: dividend = 10, divisor = 3
输出: 3
解释: 10/3 = truncate(3.33333..) = truncate(3) = 3
示例 2:

输入: dividend = 7, divisor = -3
输出: -2
解释: 7/-3 = truncate(-2.33333..) = -2
 

提示：

被除数和除数均为 32 位有符号整数。
除数不为 0。
假设我们的环境只能存储 32 位有符号整数，其数值范围是 [−231,  231 − 1]。本题中，如果除法结果溢出，则返回 231 − 1。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/divide-two-integers
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */
