package year2021.month5.no371;

public class SumOfTwoIntegers {

    public static void main(String[] args) {
        int a1 = 1;
        int b1 = 2;
        int a2 = -2;
        int b2 = 3;
        int a3 = 2;
        int b3 = 3;
        System.out.println(getSum(a1, b1));
        System.out.println(getSum(a2, b2));
        System.out.println(getSum(a3, b3));
    }

    public static int getSum(int a, int b) {
        int forward = a & b;
        int sum = a ^ b;
        while (forward != 0) {
            forward <<= 1;
            int nextForward = forward & sum;
            sum ^= forward;
            forward = nextForward;
        }
        return sum;
    }

}

/*
* 不使用运算符 + 和 -  ，计算两整数  a 、b  之和。

示例 1:

输入: a = 1, b = 2
输出: 3
示例 2:

输入: a = -2, b = 3
输出: 1

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/sum-of-two-integers
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */
