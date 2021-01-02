package year2020.month11.no9;

public class PalindromeNumber {
    public static void main(String[] args) {
        int x1 = 121;
        int x2 = -121;
        int x3 = 1000000001;
        int x4 = 0;
        int x5 = 3;
        System.out.println(isPalindrome2(x1));
        System.out.println(isPalindrome2(x2));
        System.out.println(isPalindrome2(x3));
        System.out.println(isPalindrome2(x4));
        System.out.println(isPalindrome2(x5));
    }

    private static boolean isPalindrome2(int x) {
//        time is O(log n), space is O(1)
//        所有负数、以及个位数为0的正整数均不为回文数（正整数高位不可能为0）
        if (x < 0 || x > 0 && x % 10 == 0) {
            return false;
        }
//        取出整数后半部分反转与前半部分比较
        int reversedNum = 0;
//        考虑奇偶情况，当原整数小于等于反转数时，此时已到一半
        while (x > reversedNum) {
            reversedNum = reversedNum * 10 + x % 10;
            x /= 10;
        }
//        若x有奇数位，则reversedNum会比x多一位（原x的中间位），需将reversedNum除10后与x比较
//        若x为偶数位，则reversedNum和x位数相同，直接比较即可
        return x == reversedNum || x == reversedNum / 10;
    }

    private static boolean isPalindrome(int x) {
//        O(n)
        String num = String.valueOf(x);
        int left = 0;
        int right = num.length() - 1;
        while (left <= right) {
            if (num.charAt(left) != num.charAt(right)) {
                break;
            }
            left++;
            right--;
        }
        return right < left;
    }
}

/*
* 判断一个整数是否是回文数。回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。

示例 1:

输入: 121
输出: true
示例 2:

输入: -121
输出: false
解释: 从左向右读, 为 -121 。 从右向左读, 为 121- 。因此它不是一个回文数。
示例 3:

输入: 10
输出: false
解释: 从右向左读, 为 01 。因此它不是一个回文数。
进阶:

你能不将整数转为字符串来解决这个问题吗？

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/palindrome-number
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */