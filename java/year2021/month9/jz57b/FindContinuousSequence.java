package year2021.month9.jz57b;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class FindContinuousSequence {
    public static void main(String[] args) {
        int target1 = 9;
        int target2 = 15;
        int target3 = 3;
        System.out.println(Arrays.deepToString(findContinuousSequence(target1)));
        System.out.println(Arrays.deepToString(findContinuousSequence(target2)));
        System.out.println(Arrays.deepToString(findContinuousSequence(target3)));
    }

    public static int[][] findContinuousSequence(int target) {
        // 滑动窗口 [left, right]
        List<int[]> list = new ArrayList<>();
        int left = 1;
        int sum = 1;
        for (int right = 2; right <= (target + 1) / 2; right++) {
            sum += right;
            while (sum > target) {
                sum -= left;
                left++;
            }
            if (sum == target) {
                list.add(IntStream.rangeClosed(left, right).toArray());
            }
        }
        return list.toArray(new int[0][]);
    }
}

/*
* 输入一个正整数 target ，输出所有和为 target 的连续正整数序列（至少含有两个数）。

序列内的数字由小到大排列，不同序列按照首个数字从小到大排列。

 

示例 1：

输入：target = 9
输出：[[2,3,4],[4,5]]
示例 2：

输入：target = 15
输出：[[1,2,3,4,5],[4,5,6],[7,8]]
 

限制：

1 <= target <= 10^5

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/he-wei-sde-lian-xu-zheng-shu-xu-lie-lcof
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */
