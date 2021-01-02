package year2020.month12.no231;

public class PowerOfTwo {

    public static void main(String[] args) {
        int n1 = 1;
        int n2 = 16;
        int n3 = 218;
        int n4 = Integer.MIN_VALUE;
        int n5 = 0;
        System.out.println(isPowerOfTwo2(n1));
        System.out.println(isPowerOfTwo2(n2));
        System.out.println(isPowerOfTwo2(n3));
        System.out.println(isPowerOfTwo2(n4));
        System.out.println(isPowerOfTwo2(n5));
        System.out.println(Math.pow(2, 31));

    }

    private static boolean isPowerOfTwo2(int n) {
        return n > 0 && (n & (-n)) == n;
    }

    private static boolean isPowerOfTwo(int n) {
        return n > 0 && (n & (n - 1)) == 0;
    }
}

/*
* 给定一个整数，编写一个函数来判断它是否是 2 的幂次方。

示例 1:

输入: 1
输出: true
解释: 20 = 1
示例 2:

输入: 16
输出: true
解释: 24 = 16
示例 3:

输入: 218
输出: false

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/power-of-two
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */
