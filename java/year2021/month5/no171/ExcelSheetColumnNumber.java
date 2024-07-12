package year2021.month5.no171;

public class ExcelSheetColumnNumber {

    public static void main(String[] args) {
        String columnTitle1 = "A";
        String columnTitle2 = "AB";
        String columnTitle3 = "ZY";
        System.out.println(titleToNumber(columnTitle1));
        System.out.println(titleToNumber(columnTitle2));
        System.out.println(titleToNumber(columnTitle3));
    }

    public static int titleToNumber(String columnTitle) {
        int num = 0;
        for (int i = 0; i < columnTitle.length(); i++) {
            num = num * 26 + getNum(columnTitle.charAt(i));
        }
        return num;
    }

    private static int getNum(char c) {
        return c - 'A' + 1;
    }

}

/*
* 给定一个Excel表格中的列名称，返回其相应的列序号。

例如，

    A -> 1
    B -> 2
    C -> 3
    ...
    Z -> 26
    AA -> 27
    AB -> 28 
    ...
示例 1:

输入: "A"
输出: 1
示例 2:

输入: "AB"
输出: 28
示例 3:

输入: "ZY"
输出: 701

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/excel-sheet-column-number
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */
