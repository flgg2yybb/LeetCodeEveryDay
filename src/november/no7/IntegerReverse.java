package november.no7;

import java.util.ArrayList;
import java.util.List;

public class IntegerReverse {
    public static void main(String[] args) {
        int test1 = 1534236469;
        int test2 = -753159;
        int test3 = -1230;
        int currentTest = test1;
        int absTest = Math.abs(currentTest);
        boolean isNegative = currentTest < 0;
        int expectedResult = 987456321;
        List<Integer> list = new ArrayList<>();
        while (absTest > 0) {
            list.add(absTest % 10);
            absTest /= 10;
        }
        long result = 0;
        for (int i = 0; i < list.size(); i++) {
            result = result * 10 + list.get(i);
        }
        if (isNegative) {
            result *= -1;
        }
        int intResult = (int) result;
        if (intResult != result) {
            System.out.println(0);
        }
        System.out.println(result);
    }
}

/*
* 给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转。

示例 1:

输入: 123
输出: 321

示例 2:

输入: -123
输出: -321

示例 3:

输入: 120
输出: 21
注意:

假设我们的环境只能存储得下 32 位的有符号整数，则其数值范围为 [−2^31,  2^31 − 1]。请根据这个假设，如果反转后整数溢出那么就返回 0。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/reverse-integer
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */
