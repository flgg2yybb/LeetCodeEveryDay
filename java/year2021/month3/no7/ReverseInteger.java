package year2021.month3.no7;

public class ReverseInteger {
    public static void main(String[] args) {
        int x1 = 123;
        int x2 = -123;
        int x3 = 120;
        int x4 = 0;
        int x5 = Integer.MAX_VALUE;
        int x6 = Integer.MIN_VALUE;
        System.out.println(reverse(x1));
        System.out.println(reverse(x2));
        System.out.println(reverse(x3));
        System.out.println(reverse(x4));
        System.out.println(reverse(x5));
        System.out.println(reverse(x6));
    }

    public static int reverse(int x) {
        boolean isNegative = x < 0;
        x = Math.abs(x);
        int res = 0;
        while (x > 0) {
            int mod = x % 10;
            if ((Integer.MAX_VALUE - mod) / 10 < res) {
                // res * 10 + mod > Integer.MAX_VALUE ==> 越界
                return 0;
            }
            res = res * 10 + mod;
            x /= 10;
        }
        return isNegative ? res * -1 : res;
    }
}

/*
 * 给你一个 32 位的有符号整数 x ，返回将 x 中的数字部分反转后的结果。

如果反转后整数超过 32 位的有符号整数的范围 [−2^31,  2^31 − 1] ，就返回 0。

假设环境不允许存储 64 位整数（有符号或无符号）。
 

示例 1：

输入：x = 123
输出：321
示例 2：

输入：x = -123
输出：-321
示例 3：

输入：x = 120
输出：21
示例 4：

输入：x = 0
输出：0
 

提示：

-2^31 <= x <= 2^31 - 1

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/reverse-integer
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */
