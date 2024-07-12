package year2021.month7.jz65;

public class Add {

    public static void main(String[] args) {
        int a1 = 1;
        int b1 = 1;
        int a2 = 6;
        int b2 = -3;
        System.out.println(add(a1, b1));
        System.out.println(add(a2, b2));
    }

    private static int add(int a, int b) {
        if (b == 0) {
            return a;
        }
        int n = a ^ b;
        int c = (a & b) << 1;
        return add(n, c);
    }

    public static int add1(int a, int b) {
        /*
         * 求和 s = a + b，设 a(i) 代表 a 的二进制的第 i 位，则分为以下四种情况
         *
         *   a(i)    b(i)    无进位和 n(i)   进位 c(i+1)
         *    0       0          0               0
         *    0       1          1               0
         *    1       0          1               0
         *    1       1          0               1
         *
         * 可见， 无进位和 与 异或 运算相同，进位 与 与运算再左移一位 相同，故有
         *   n = a ^ b
         *   c = (a & b) <<1
         * 则，s = a + b = n + c，循环递归至 进位 c 为 0 时， n 即为答案
         * */
        while (b != 0) {
            int n = a ^ b;
            int c = (a & b) << 1;
            a = n;
            b = c;
        }
        return a;
    }

}

/*
* 写一个函数，求两个整数之和，要求在函数体内不得使用 “+”、“-”、“*”、“/” 四则运算符号。

示例:

输入: a = 1, b = 1
输出: 2
 

提示：

a, b 均可能是负数或 0
结果不会溢出 32 位整数

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/bu-yong-jia-jian-cheng-chu-zuo-jia-fa-lcof
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */
