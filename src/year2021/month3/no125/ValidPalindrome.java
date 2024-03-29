package year2021.month3.no125;

public class ValidPalindrome {
    public static void main(String[] args) {
        String s1 = "A man, a plan, a canal: Panama";
        String s2 = "race a car";
        String s3 = " ";
        String s4 = " , : ? ,";
        String s5 = "a.";
        System.out.println(isPalindrome(s1));
        System.out.println(isPalindrome(s2));
        System.out.println(isPalindrome(s3));
        System.out.println(isPalindrome(s4));
        System.out.println(isPalindrome(s5));
    }

    public static boolean isPalindrome(String s) {
        int left = 0;
        int right = s.length() - 1;
        while (left < right) {
            while (left < right && !Character.isLetterOrDigit(s.charAt(right))) {
                right--;
            }
            while (left < right && !Character.isLetterOrDigit(s.charAt(left))) {
                left++;
            }
            if (!isSameLetter(s, left, right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    private static boolean isSameLetter(String s, int left, int right) {
        if (s.charAt(left) == s.charAt(right)) {
            return true;
        }
        int leftChar = s.charAt(left) >= 'a' ? s.charAt(left) - 'a' : s.charAt(left) - 'A';
        int rightChar = s.charAt(right) >= 'a' ? s.charAt(right) - 'a' : s.charAt(right) - 'A';
        return leftChar == rightChar;
    }
}

/*
* 给定一个字符串，验证它是否是回文串，只考虑字母和数字字符，可以忽略字母的大小写。

说明：本题中，我们将空字符串定义为有效的回文串。

示例 1:

输入: "A man, a plan, a canal: Panama"
输出: true
示例 2:

输入: "race a car"
输出: false

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/valid-palindrome
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */
