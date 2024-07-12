package year2021.month7.jz44;

public class FindNthDigit {

    public static void main(String[] args) {
        int n1 = 3;
        int n2 = 11;
        int n3 = Integer.MAX_VALUE;
        System.out.println(findNthDigit(n1));
        System.out.println(findNthDigit(n2));
        System.out.println(findNthDigit(n3));
    }

    public static int findNthDigit(int n) {
        /*
         * 1. 将 101112... 中的每一位数称为 数位，记为 n
         * 2. 将 10 11 12 ... 称为 数字，记为 num
         * 3. 数字 10 是一个两位数，称此数字的 位数 为 2，记为 digit
         * 4. 每 digit 位数的起始数字（1，10，100），记为 start
         *       数字范围        位数      数字数量        数位数量
         *       1-9             1       9              9
         *       10-99           2       90             180
         *       100-999         3       900            2700
         *       start-end      digit    9*start        9*start*digit
         * 算法：
         * 1. 确定 n 所在数字的位数，记为 digit
         * 2. 确定 n 所在的数字，记为 num
         * 3. 确定 n 是 num 中的哪一数位
         * */
        int digit = 1;
        long start = 1;
        long count = 9;
        while (n > count) {     //确定 n 所在数字的位数，记为 digit
            n -= count;
            digit += 1;
            start *= 10;
            count = 9 * start * digit;
        }
        long num = start + ((n - 1) / digit);   //确定 n 所在的数字，记为 num
        return String.valueOf(num).charAt((n - 1) % digit) - '0';   //确定 n 是 num 中的哪一数位
    }

}

/*
* 数字以0123456789101112131415…的格式序列化到一个字符序列中。在这个序列中，第5位（从下标0开始计数）是5，第13位是1，第19位是4，等等。

请写一个函数，求任意第n位对应的数字。

 

示例 1：

输入：n = 3
输出：3
示例 2：

输入：n = 11
输出：0
 

限制：

0 <= n < 2^31
注意：本题与主站 400 题相同：https://leetcode-cn.com/problems/nth-digit/

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/shu-zi-xu-lie-zhong-mou-yi-wei-de-shu-zi-lcof
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */
