package november.no6;

import java.util.ArrayList;
import java.util.List;

public class ZigZagConversion {
    public static void main(String[] args) {
        String s = "LEETCODEISHIRING";
        int numRows = 4;
        System.out.println(convert1(s, numRows));
    }

    private static String convert1(String s, int numRows) {
//        time = n = O(n), space = numRows * n = O(n)
        if (numRows == 1) {
            return s;
        }
//        初始化 numRows * n数组
        List<StringBuilder> lists = new ArrayList<>();
        for (int i = 0; i < numRows; i++) {
            lists.add(new StringBuilder());
        }
//        遍历s，并将对应的char放进对应行的数组
        int direction = 1;
        int index = 0;
        for (int j = 0; j < s.length(); j++) {
            index += direction;
            lists.get(index - 1).append(s.charAt(j));
            if (index == numRows) {
                direction = -1;
            } else if (index == 1) {
                direction = 1;
            }
        }
        StringBuilder sb = new StringBuilder();
        lists.forEach(row -> sb.append(row));
//        将数组中装好的元素拼起来
        return new String(sb);
    }

    private static String convert(String s, int numRows) {
//        模拟, time = numRows * n = O(n), space = O(1)
        if (numRows == 1) {
            return s;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= numRows; i++) {
            int direction = 1;
            int index = 0;
            for (int j = 0; j < s.length(); j++) {
                index += direction;
                if (index == i) {
                    sb.append(s.charAt(j));
                }
                if (index == numRows) {
                    direction = -1;
                } else if (index == 1) {
                    direction = 1;
                }
            }
        }
        return new String(sb);
    }
}

/*
* 将一个给定字符串根据给定的行数，以从上往下、从左到右进行 Z 字形排列。

比如输入字符串为 "LEETCODEISHIRING" 行数为 3 时，排列如下：

L   C   I   R
E T O E S I I G
E   D   H   N
之后，你的输出需要从左往右逐行读取，产生出一个新的字符串，比如："LCIRETOESIIGEDHN"。

请你实现这个将字符串进行指定行数变换的函数：

string convert(string s, int numRows);
示例 1:

输入: s = "LEETCODEISHIRING", numRows = 3
输出: "LCIRETOESIIGEDHN"
示例 2:

输入: s = "LEETCODEISHIRING", numRows = 4
输出: "LDREOEIIECIHNTSG"
解释:

L     D     R
E   O E   I I
E C   I H   N
T     S     G

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/zigzag-conversion
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */