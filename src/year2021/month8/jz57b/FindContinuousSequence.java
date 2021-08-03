package year2021.month8.jz57b;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

public class FindContinuousSequence {

    public static void main(String[] args) {
        int target1 = 9;
        int target2 = 15;
        int target3 = 100000;
        System.out.println(Arrays.deepToString(findContinuousSequence(target1)));
        System.out.println(Arrays.deepToString(findContinuousSequence(target2)));
        System.out.println(Arrays.deepToString(findContinuousSequence(target3)));
    }

    public static int[][] findContinuousSequence(int target) {
        // Prefix Sum
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 0);
        List<int[]> lists = new ArrayList<>();
        int sum = 0;
        for (int i = 1; i < target; i++) {
            sum += i;
            if (map.containsKey(sum - target)) {
                Integer num = map.get(sum - target);
                lists.add(IntStream.rangeClosed(num + 1, i).toArray());
            }
            map.put(sum, i);
        }
        int[][] ans = new int[lists.size()][];
        for (int i = 0; i < lists.size(); i++) {
            ans[i] = lists.get(i);
        }
        return ans;
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