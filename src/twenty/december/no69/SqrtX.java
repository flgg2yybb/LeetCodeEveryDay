package twenty.december.no69;

public class SqrtX {

    public static void main(String[] args) {
        int x1 = 4;
        int x2 = 8;
        int x3 = 0;
        int x4 = Integer.MAX_VALUE;
        int x5 = 1;
        System.out.println(mySqrt(x1));
        System.out.println(mySqrt(x2));
        System.out.println(mySqrt(x3));
        System.out.println(mySqrt(x4));
        System.out.println(mySqrt(x5));
    }

    public static int mySqrt(int x) {
        int low = -1;
        int high = x;
        while (low < high) {
//            low起始值为-1，与+1抵消，可防止整形溢出
            int mid = (low + high + 1) / 2;
            if ((long) mid * mid <= x) {
                low = mid;
            } else {
                high = mid - 1;
            }
        }
        return low;
    }
}

/*
* 实现int sqrt(int x)函数。

计算并返回x的平方根，其中x 是非负整数。

由于返回类型是整数，结果只保留整数的部分，小数部分将被舍去。

示例 1:

输入: 4
输出: 2
示例 2:

输入: 8
输出: 2
说明: 8 的平方根是 2.82842..., 
    由于返回类型是整数，小数部分将被舍去。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/sqrtx
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */