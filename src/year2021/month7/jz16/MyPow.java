package year2021.month7.jz16;

public class MyPow {

    public static void main(String[] args) {
        double x1 = 2.0d;
        int n1 = 10;
        double x2 = 2.1d;
        int n2 = 3;
        double x3 = 2.0d;
        int n3 = -2;
        double x4 = 1.0d;
        int n4 = Integer.MIN_VALUE;
        System.out.println(myPow(x1, n1));
        System.out.println(myPow(x2, n2));
        System.out.println(myPow(x3, n3));
        System.out.println(myPow(x4, n4));
    }

    public static double myPow(double x, int n) {
        if (n < 0) {
            return 1 / (myPow(x, -(n + 1)) * x);    // 防止整型负数越界
        }
        if (n == 0) {
            return 1;
        }
        double halfAns = myPow(x, n / 2);
        double ans = halfAns * halfAns;
        if (n % 2 == 1) {
            ans *= x;
        }
        return ans;
    }

}

/*
* 实现 pow(x, n) ，即计算 x 的 n 次幂函数（即，xn）。不得使用库函数，同时不需要考虑大数问题。

 

示例 1：

输入：x = 2.00000, n = 10
输出：1024.00000
示例 2：

输入：x = 2.10000, n = 3
输出：9.26100
示例 3：

输入：x = 2.00000, n = -2
输出：0.25000
解释：2-2 = 1/22 = 1/4 = 0.25
 

提示：

-100.0 < x < 100.0
-231 <= n <= 231-1
-104 <= xn <= 104
 

注意：本题与主站 50 题相同：https://leetcode-cn.com/problems/powx-n/

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/shu-zhi-de-zheng-shu-ci-fang-lcof
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */
