package year2021.month8.jz46;

public class TranslateNum {

    public static void main(String[] args) {
        int num1 = 12258;
        int num2 = 0;
        int num3 = Integer.MAX_VALUE;
        System.out.println(translateNum1(num1));
        System.out.println(translateNum1(num2));
        System.out.println(translateNum1(num3));
    }

    private static int translateNum1(int num) {
        String numStr = String.valueOf(num);
        int len = numStr.length();
        int first = 1;
        int second = 1;
        int third = 1;
        for (int i = 2; i <= len; i++) {
            int pos = i - 1;
            third = second;
            int pre = numStr.charAt(pos - 1) - '0';
            int cur = numStr.charAt(pos) - '0';
            if (pre == 1 || pre == 2 && cur <= 5) {
                third += first;
            }
            first = second;
            second = third;
        }
        return third;
    }

    public static int translateNum(int num) {
        String numStr = String.valueOf(num);
        int len = numStr.length();
        int[] dp = new int[len + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= len; i++) {
            int pos = i - 1;
            dp[i] = dp[i - 1];
            int pre = numStr.charAt(pos - 1) - '0';
            int cur = numStr.charAt(pos) - '0';
            if (pre == 1 || pre == 2 && cur <= 5) {
                dp[i] += dp[i - 2];
            }
        }
        return dp[len];
    }

}

/*
* 给定一个数字，我们按照如下规则把它翻译为字符串：0 翻译成 “a” ，1 翻译成 “b”，……，11 翻译成 “l”，……，25 翻译成 “z”。一个数字可能有多个翻译。请编程实现一个函数，用来计算一个数字有多少种不同的翻译方法。

  

示例 1:

输入: 12258
输出: 5
解释: 12258有5种不同的翻译，分别是"bccfi", "bwfi", "bczi", "mcfi"和"mzi"
  

提示：

0 <= num < 2^31

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/ba-shu-zi-fan-yi-cheng-zi-fu-chuan-lcof
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */
