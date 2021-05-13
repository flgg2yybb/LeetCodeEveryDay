package year2021.month5.no43;

public class MultiplyStrings {

    public static void main(String[] args) {
        String nums11 = "2";
        String nums12 = "3";
        String nums21 = "123";
        String nums22 = "456";
        System.out.println(multiply(nums11, nums12));
        System.out.println(multiply(nums21, nums22));
    }

    public static String multiply(String num1, String num2) {
        if ("0".equals(num1) || "0".equals(num2)) {
            return "0";
        }
        int m = num1.length();
        int n = num2.length();
        // m 位数乘 n 位数结果的位数一定满足: <= m+n && >= m+n-1
        // 故 arr[0] 的值有可能有，也有可能没有
        int[] arr = new int[m + n];
        for (int i = m - 1; i >= 0; i--) {
            int a = num1.charAt(i) - '0';
            for (int j = n - 1; j >= 0; j--) {
                int b = num2.charAt(j) - '0';
                arr[i + j + 1] += a * b;
            }
        }
        for (int i = arr.length - 1; i > 0; i--) {
            arr[i - 1] += arr[i] / 10;
            arr[i] %= 10;
        }
        int pos = arr[0] == 0 ? 1 : 0;
        StringBuilder ans = new StringBuilder();
        while (pos < arr.length) {
            ans.append(arr[pos]);
            pos++;
        }
        return ans.toString();
    }


}

/*
* 给定两个以字符串形式表示的非负整数 num1 和 num2，返回 num1 和 num2 的乘积，它们的乘积也表示为字符串形式。

示例 1:

输入: num1 = "2", num2 = "3"
输出: "6"
示例 2:

输入: num1 = "123", num2 = "456"
输出: "56088"
说明：

num1 和 num2 的长度小于110。
num1 和 num2 只包含数字 0-9。
num1 和 num2 均不以零开头，除非是数字 0 本身。
不能使用任何标准库的大数类型（比如 BigInteger）或直接将输入转换为整数来处理。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/multiply-strings
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */
