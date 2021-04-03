package year2021.month4.no412;

import java.util.ArrayList;
import java.util.List;

public class FizzBuzz {
    public static void main(String[] args) {
        int n = 15;
        System.out.println(fizzBuzz(15));
    }

    public static List<String> fizzBuzz(int n) {
        List<String> res = new ArrayList<>();
        dfsFizzBuzz(1, n, res);
        return res;
    }

    private static void dfsFizzBuzz(int cur, int end, List<String> res) {
        if (cur > end) {
            return;
        }
        StringBuilder sb = new StringBuilder();
        if (cur % 3 == 0) {
            sb.append("Fizz");
        }
        if (cur % 5 == 0) {
            sb.append("Buzz");
        }
        res.add(sb.length() == 0 ? String.valueOf(cur) : sb.toString());
        dfsFizzBuzz(cur + 1, end, res);
    }
}

/*
 * 写一个程序，输出从 1 到 n 数字的字符串表示。

1. 如果 n 是3的倍数，输出“Fizz”；

2. 如果 n 是5的倍数，输出“Buzz”；

3.如果 n 同时是3和5的倍数，输出 “FizzBuzz”。

示例：

n = 15,

返回:
[
    "1",
    "2",
    "Fizz",
    "4",
    "Buzz",
    "Fizz",
    "7",
    "8",
    "Fizz",
    "Buzz",
    "11",
    "Fizz",
    "13",
    "14",
    "FizzBuzz"
]

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/fizz-buzz
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */
