package year2021.month5.no166;

import java.util.HashMap;
import java.util.Map;

public class FractionToRecurringDecimal {

    public static void main(String[] args) {
        int numerator1 = 1;
        int denominator1 = 2;
        int numerator2 = 2;
        int denominator2 = 1;
        int numerator3 = 2;
        int denominator3 = 3;
        int numerator4 = 4;
        int denominator4 = 333;
        int numerator5 = 1;
        int denominator5 = 5;
        System.out.println(fractionToDecimal(numerator1, denominator1));
        System.out.println(fractionToDecimal(numerator2, denominator2));
        System.out.println(fractionToDecimal(numerator3, denominator3));
        System.out.println(fractionToDecimal(numerator4, denominator4));
        System.out.println(fractionToDecimal(numerator5, denominator5));
    }

    public static String fractionToDecimal(int numerator, int denominator) {
        boolean isNegative = false;
        if (numerator < 0 && denominator > 0 || numerator > 0 && denominator < 0) {
            isNegative = true;
        }
        long absNumerator = numerator < 0 ? numerator * -1L : numerator;
        long absDenominator = denominator < 0 ? denominator * -1L : denominator;
        StringBuilder sb = new StringBuilder();
        if (isNegative) {
            sb.append("-");
        }
        sb.append(absNumerator / absDenominator);
        long remainder = absNumerator % absDenominator;
        if (remainder == 0) {
            return sb.toString();
        }
        sb.append(".");
        Map<Long, Integer> map = new HashMap<>();
        while (remainder != 0) {
            if (map.containsKey(remainder)) {
                sb.insert(map.get(remainder), "(");
                sb.append(")");
                break;
            }
            map.put(remainder, sb.length());
            remainder *= 10;
            sb.append(remainder / absDenominator);
            remainder = remainder % absDenominator;
        }
        return sb.toString();
    }

}

/*
* 给定两个整数，分别表示分数的分子 numerator 和分母 denominator，以 字符串形式返回小数 。

如果小数部分为循环小数，则将循环的部分括在括号内。

如果存在多个答案，只需返回 任意一个 。

对于所有给定的输入，保证 答案字符串的长度小于 104 。

 

示例 1：

输入：numerator = 1, denominator = 2
输出："0.5"
示例 2：

输入：numerator = 2, denominator = 1
输出："2"
示例 3：

输入：numerator = 2, denominator = 3
输出："0.(6)"
示例 4：

输入：numerator = 4, denominator = 333
输出："0.(012)"
示例 5：

输入：numerator = 1, denominator = 5
输出："0.2"
 

提示：

-231 <= numerator, denominator <= 231 - 1
denominator != 0

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/fraction-to-recurring-decimal
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */
