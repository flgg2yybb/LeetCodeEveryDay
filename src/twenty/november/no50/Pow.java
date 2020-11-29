package twenty.november.no50;

public class Pow {
    public static void main(String[] args) {
        double x1 = 2d;
        int n1 = -2147483648;
        double x2 = 0.00001;
        int n2 = 2147483647;
        System.out.println(myPow2(x1, n1));
    }

    private static double myPow2(double x, int n) {
//        n 取值为 -2^31 -> 2^31 - 1, 若 n == -2^32, 直接取反会有整型溢出，因此用 long来记录n
        long longN = n;
        if (longN < 0) {
            x = 1d / x;
            longN = -longN;
        }
        double result = 1d;
        while (longN > 0) {
            if (longN % 2 == 1) {
                result *= x;
            }
            x *= x;
//            longN /= 2
            longN >>= 1;
        }
        return result;
    }

    private static double myPow(double x, int n) {
//        n 取值为 -2^31 -> 2^31 - 1, 若 n == -2^32, 直接取反会有整型溢出，因此用 long来记录n
        long longN = n;
        return longN > 0 ? quickPow(x, longN) : 1 / quickPow(x, -longN);
    }

    private static double quickPow(double x, long n) {
        if (n == 0) {
            return 1d;
        }
        double half = quickPow(x, n / 2);
        double result = half * half;
        if (n % 2 == 1) {
            result *= x;
        }
        return result;
    }
}

/*
* 实现 pow(x, n) ，即计算 x 的 n 次幂函数。

示例 1:

输入: 2.00000, 10
输出: 1024.00000
示例 2:

输入: 2.10000, 3
输出: 9.26100
示例 3:

输入: 2.00000, -2
输出: 0.25000
解释: 2-2 = 1/22 = 1/4 = 0.25
说明:

-100.0 < x < 100.0
n 是 32 位有符号整数，其数值范围是 [−231, 231 − 1] 。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/powx-n
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */
