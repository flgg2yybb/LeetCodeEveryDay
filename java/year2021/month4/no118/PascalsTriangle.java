package year2021.month4.no118;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PascalsTriangle {
    public static void main(String[] args) {
        int numRows = 5;
        System.out.println(generate1(5));
    }

    private static List<List<Integer>> generate1(int numRows) {
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < numRows; i++) {
            List<Integer> currList = new ArrayList<>();
            for (int j = 0; j <= i; j++) {
                if (j == 0 || j == i) {
                    currList.add(1);
                } else {
                    List<Integer> prevList = res.get(i - 1);
                    currList.add(prevList.get(j - 1) + prevList.get(j));
                }
            }
            res.add(currList);
        }
        return res;
    }

    public static List<List<Integer>> generate(int numRows) {
        if (numRows == 0) {
            return Collections.emptyList();
        }
        List<List<Integer>> res = new ArrayList<>();
        res.add(Collections.singletonList(1));
        for (int i = 1; i < numRows; i++) {
            List<Integer> prevList = res.get(i - 1);
            List<Integer> currList = new ArrayList<>();
            for (int j = 0; j < prevList.size() + 1; j++) {
                int num = 0;
                if (j - 1 >= 0) {
                    num += prevList.get(j - 1);
                }
                if (j < prevList.size()) {
                    num += prevList.get(j);
                }
                currList.add(num);
            }
            res.add(currList);
        }
        return res;
    }
}

/*
 * 给定一个非负整数 numRows，生成杨辉三角的前 numRows 行。



在杨辉三角中，每个数是它左上方和右上方的数的和。

示例:

输入: 5
输出:
[
     [1],
    [1,1],
   [1,2,1],
  [1,3,3,1],
 [1,4,6,4,1]
]

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/pascals-triangle
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */
