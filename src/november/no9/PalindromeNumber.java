package november.no9;

public class PalindromeNumber {
    public static void main(String[] args) {
        int x1 = 121;
        int x2 = -121;
        int x3 = 1000000001;
        System.out.println(isPalindrome(x1));
        System.out.println(isPalindrome(x2));
        System.out.println(isPalindrome(x3));
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