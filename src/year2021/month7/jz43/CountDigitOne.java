package year2021.month7.jz43;

public class CountDigitOne {

    public static void main(String[] args) {
        int n1 = 1;
        int n2 = 12;
        int n3 = 13;
        int n5 = 2304;
        int n6 = 2314;
        int n7 = 2324;
        int n9 = 2394;
        int n10 = Integer.MAX_VALUE;
        System.out.println(countDigitOne(n1));
        System.out.println(countDigitOne(n2));
        System.out.println(countDigitOne(n3));
        System.out.println(countDigitOne(n5));
        System.out.println(countDigitOne(n6));
        System.out.println(countDigitOne(n7));
        System.out.println(countDigitOne(n9));
        System.out.println(countDigitOne(n10));
    }

    public static int countDigitOne(int n) {
        /*
         * 数学、找规律
         * 一个数字按十进制划分为不同位，可用
         * n -> n[m]n[m-2]...n[i]...n[1]，m 为 n 的位数
         * 则 1~ n这 n 个整数的十进制表示中 1出现的次数，即为
         * n 这个数字中每一位出现 1 的次数的和
         *
         * 假设 cur 为当前位，high 为 cur 前的数字，记为高位
         * low 为 cur 后的数字，记为低位，
         * digit 为当前位的基位，即 10^i，cur 为第 i 位
         * 则当前位出现 1 的次数计算如下
         *
         * 前提：n = high cur low
         * 1. 当 cur 为 0 时，假设 n = 2304 = 23    0    4
         *                                 high  cur  low
         *   则 digit = 10，有
         *   * cur位出现 1 的数的范围为 0010 -> 2219
         *   * 仅用高低位表示，即为：    000  -> 229
         *   * 则 1 出现次数为：229 - 0 + 1 = 230
         *   ** 公式为：high * digit
         *
         * 2. 当 cur 为 1 时，假设 n = 2314 = 23    1    4
         *                                 high  cur  low
         *   则 digit = 10，有
         *   * cur 位出现 1 的数的范围为 0010 -> 2314
         *   * 仅用高低位表示，即为：     000  -> 234
         *   * 则 1 出现次数为：234 - 0 + 1 = 235
         *   ** 公式为：high * digit + low + 1
         *
         * 3. 当 cur 为 [2,9]时，假设 n = 2324 = 23    2    4
         *                                    high  cur  low
         *   则 digit = 10，有
         *   * cur 位出现 1 的数的范围为 0010 -> 2319
         *   * 仅用高低位表示，即为：     000  -> 239
         *   * 则 1 出现次数为：239 - 0 + 1 = 240
         *   ** 公式为：(high + 1) * digit
         * */
        int count = 0;
        int digit = 1;
        int high = n / 10;
        int cur = n % 10;
        int low = 0;
        while (high != 0 || cur != 0) {
            if (cur == 0) {
                count += high * digit;
            } else if (cur == 1) {
                count += high * digit + low + 1;
            } else {
                count += (high + 1) * digit;
            }
            low += cur * digit;
            cur = high % 10;
            high /= 10;
            digit *= 10;
        }
        return count;
    }

}

/*
* 输入一个整数 n ，求1～n这n个整数的十进制表示中1出现的次数。

例如，输入12，1～12这些整数中包含1 的数字有1、10、11和12，1一共出现了5次。

 

示例 1：

输入：n = 12
输出：5
示例 2：

输入：n = 13
输出：6
 

限制：

1 <= n < 2^31
注意：本题与主站 233 题相同：https://leetcode-cn.com/problems/number-of-digit-one/

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/1nzheng-shu-zhong-1chu-xian-de-ci-shu-lcof
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */
