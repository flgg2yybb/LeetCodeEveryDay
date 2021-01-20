package year2021.month1.no76;

public class MinimumWindowSubstring {
    public static void main(String[] args) {
        String s1 = "ADOBECODEBANC";
        String t1 = "ABC";
        String s2 = "a";
        String t2 = "a";
        String s3 = "axasioboASDMCPOSDAMPOCcuiFAMPIOFMPAWEMPFasndfiohwZCBZXCBquondlsanckSADGMOPSDAMGPOAMFDAMASDGFAasdpfonaaaiaaaasodnfiosna";
        String t3 = "PAWAMPF";
        String s4 = "a";
        String t4 = "aa";
        System.out.println(minWindow(s1, t1));  //BANC
        System.out.println(minWindow(s2, t2));  //a
        System.out.println(minWindow(s3, t3));  //FMPAWE
        System.out.println(minWindow(s4, t4));  //""
    }

    public static String minWindow(String s, String t) {
//        sliding widows
        int sLen = s.length();
        int tLen = t.length();
        if (sLen == 0 || tLen == 0 || sLen < tLen) {
            return "";
        }
        char[] sCharArray = s.toCharArray();
        char[] tCharArray = t.toCharArray();
        int[] sCharCount = new int[128];
        int[] tCharCount = new int[128];
        for (int i = 0; i < tLen; i++) {
            tCharCount[tCharArray[i]]++;
        }
//        表示窗口内包含t中多少个元素
        int include = 0;
        int start = 0;
        int end = Integer.MAX_VALUE;
        int left = 0;
        int right = 0;
//        滑动窗口为：[left, right)
        while (right < sLen) {
            char rightChar = sCharArray[right];
            if (tCharCount[rightChar] == 0) {
                right++;
                continue;
            }
            if (sCharCount[rightChar] < tCharCount[rightChar]) {
                include++;
            }
            sCharCount[rightChar]++;
            right++;
            while (include == tLen) {
                if (right - left < end - start) {
                    start = left;
                    end = right;
                }
                char leftChar = sCharArray[left];
                if (sCharCount[leftChar] == 0) {
                    left++;
                    continue;
                }
                if (sCharCount[leftChar] <= tCharCount[leftChar]) {
                    include--;
                }
                sCharCount[leftChar]--;
                left++;
            }
        }
        return end == Integer.MAX_VALUE ? "" : s.substring(start, end);
    }
}

/*
* 给你一个字符串 s 、一个字符串 t 。返回 s 中涵盖 t 所有字符的最小子串。如果 s 中不存在涵盖 t 所有字符的子串，则返回空字符串 "" 。

注意：如果 s 中存在这样的子串，我们保证它是唯一的答案。

 

示例 1：

输入：s = "ADOBECODEBANC", t = "ABC"
输出："BANC"
示例 2：

输入：s = "a", t = "a"
输出："a"
 

提示：

1 <= s.length, t.length <= 105
s 和 t 由英文字母组成
 

进阶：你能设计一个在 o(n) 时间内解决此问题的算法吗？

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/minimum-window-substring
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */
