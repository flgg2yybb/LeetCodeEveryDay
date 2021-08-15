package year2021.month8.jz67;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StrToInt {

    public static void main(String[] args) {
        String s1 = "42";
        String s2 = "   -42";
        String s3 = "4193 with words";
        String s4 = "words and 987";
        String s5 = "-91283472332";
        String s6 = "91283472332";
        String s7 = String.valueOf(Integer.MAX_VALUE);
        String s8 = String.valueOf(Integer.MIN_VALUE);
        String s9 = "    -34c123";
        String s10 = " +-123";
        String s11 = "9223372036854775808";
        String s12 = "00000";
        System.out.println(strToInt(s1));
        System.out.println(strToInt(s2));
        System.out.println(strToInt(s3));
        System.out.println(strToInt(s4));
        System.out.println(strToInt(s5));
        System.out.println(strToInt(s6));
        System.out.println(strToInt(s7));
        System.out.println(strToInt(s8));
        System.out.println(strToInt(s9));
        System.out.println(strToInt(s10));
        System.out.println(strToInt(s11));
        System.out.println(strToInt(s12));
    }

    public static int strToInt(String str) {
        Automate automate = new Automate();
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            automate.add(c);
        }
        return automate.getResult();
    }

}

class Automate {

    private static final Map<String, List<String>> statusMap;

    /*
     *           SPACE       SIGN        NUMBER      CHAR
     * SPACE     SPACE       SIGN        NUMBER      END
     * SIGN       END        END         NUMBER      END
     * NUMBER     END        END         NUMBER      END
     * END        END        END           END       END
     * */
    static {
        statusMap = new HashMap<>();
        statusMap.put("SPACE", Arrays.asList("SPACE", "SIGN", "NUMBER", "END"));
        statusMap.put("SIGN", Arrays.asList("END", "END", "NUMBER", "END"));
        statusMap.put("NUMBER", Arrays.asList("END", "END", "NUMBER", "END"));
        statusMap.put("END", Arrays.asList("END", "END", "END", "END"));
    }

    private long number;
    private String status;
    private boolean positive;

    public Automate() {
        this.status = "SPACE";
        this.positive = true;
        this.number = 0;
    }

    public void add(char c) {
        this.status = statusMap.get(this.status).get(getCol(c));
        if ("SIGN".equals(status)) {
            positive = c == '+';
        }
        if ("NUMBER".equals(status)) {
            number = number * 10 + (c - '0');
            if (positive && number >= Integer.MAX_VALUE) {
                number = Integer.MAX_VALUE;
                status = "END";
            }
            if (!positive && number - 1 >= Integer.MAX_VALUE) {
                number = 1L + Integer.MAX_VALUE;
                status = "END";
            }
        }
    }

    public int getResult() {
        return (int) (positive ? number : -1 * number);
    }

    private int getCol(char c) {
        if (c == ' ') {
            return 0;
        }
        if (c == '+' || c == '-') {
            return 1;
        }
        if (Character.isDigit(c)) {
            return 2;
        }
        return 3;
    }
}

class StringAutomate {

    private static final Map<String, List<String>> statusMap;

    /*
     *           SPACE       SIGN        NUMBER      CHAR
     * SPACE     SPACE       SIGN        NUMBER      END
     * SIGN       END        END         NUMBER      END
     * NUMBER     END        END         NUMBER      END
     * END        END        END           END       END
     * */
    static {
        statusMap = new HashMap<>();
        statusMap.put("SPACE", Arrays.asList("SPACE", "SIGN", "NUMBER", "END"));
        statusMap.put("SIGN", Arrays.asList("END", "END", "NUMBER", "END"));
        statusMap.put("NUMBER", Arrays.asList("END", "END", "NUMBER", "END"));
        statusMap.put("END", Arrays.asList("END", "END", "END", "END"));
    }

    private final StringBuilder number;
    private String status;
    private boolean positive;

    public StringAutomate() {
        this.status = "SPACE";
        this.positive = true;
        this.number = new StringBuilder();
    }

    public void add(char c) {
        this.status = statusMap.get(this.status).get(getCol(c));
        if ("SIGN".equals(status)) {
            positive = c == '+';
        }
        if ("NUMBER".equals(status)) {
            number.append(c);
        }
    }

    public int getResult() {
        String rowNumberStr = number.toString();
        int nonZeroIndex = 0;
        for (int i = 0; i < rowNumberStr.length(); i++) {
            if (rowNumberStr.charAt(i) != '0') {
                break;
            }
            nonZeroIndex++;
        }
        String numberStr = rowNumberStr.substring(nonZeroIndex);
        if (numberStr.isEmpty()) {
            return 0;
        }
        if (numberStr.length() > 10) {
            return positive ? Integer.MAX_VALUE : Integer.MIN_VALUE;
        }
        if (positive && (numberStr.length() == 10 && numberStr.compareTo("2147483647") >= 0)) {
            return Integer.MAX_VALUE;
        }
        if (!positive && (numberStr.length() == 10 && numberStr.compareTo("2147483648") >= 0)) {
            return Integer.MIN_VALUE;
        }
        return positive ? Integer.parseInt(numberStr) : -1 * Integer.parseInt(numberStr);
    }

    private int getCol(char c) {
        if (c == ' ') {
            return 0;
        }
        if (c == '+' || c == '-') {
            return 1;
        }
        if (Character.isDigit(c)) {
            return 2;
        }
        return 3;
    }
}

/*
* 写一个函数 StrToInt，实现把字符串转换成整数这个功能。不能使用 atoi 或者其他类似的库函数。

 

首先，该函数会根据需要丢弃无用的开头空格字符，直到寻找到第一个非空格的字符为止。

当我们寻找到的第一个非空字符为正或者负号时，则将该符号与之后面尽可能多的连续数字组合起来，作为该整数的正负号；假如第一个非空字符是数字，则直接将其与之后连续的数字字符组合起来，形成整数。

该字符串除了有效的整数部分之后也可能会存在多余的字符，这些字符可以被忽略，它们对于函数不应该造成影响。

注意：假如该字符串中的第一个非空格字符不是一个有效整数字符、字符串为空或字符串仅包含空白字符时，则你的函数不需要进行转换。

在任何情况下，若函数不能进行有效的转换时，请返回 0。

说明：

假设我们的环境只能存储 32 位大小的有符号整数，那么其数值范围为 [−231,  231 − 1]。如果数值超过这个范围，请返回  INT_MAX (231 − 1) 或 INT_MIN (−231) 。

示例 1:

输入: "42"
输出: 42
示例 2:

输入: "   -42"
输出: -42
解释: 第一个非空白字符为 '-', 它是一个负号。
     我们尽可能将负号与后面所有连续出现的数字组合起来，最后得到 -42 。
示例 3:

输入: "4193 with words"
输出: 4193
解释: 转换截止于数字 '3' ，因为它的下一个字符不为数字。
示例 4:

输入: "words and 987"
输出: 0
解释: 第一个非空字符是 'w', 但它不是数字或正、负号。
     因此无法执行有效的转换。
示例 5:

输入: "-91283472332"
输出: -2147483648
解释: 数字 "-91283472332" 超过 32 位有符号整数范围。 
     因此返回 INT_MIN (−231) 。
 

注意：本题与主站 8 题相同：https://leetcode-cn.com/problems/string-to-integer-atoi/

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/ba-zi-fu-chuan-zhuan-huan-cheng-zheng-shu-lcof
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */