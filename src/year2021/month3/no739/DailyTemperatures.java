package year2021.month3.no739;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.stream.Collectors;

public class DailyTemperatures {
    public static void main(String[] args) {
        int[] T1 = {73, 74, 75, 71, 69, 72, 76, 73};
        disp(dailyTemperatures1(T1));
    }

    private static int[] dailyTemperatures1(int[] T) {
        // 单调不增栈，保存元素索引, time is O(n), space is O(n)
        int len = T.length;
        Deque<Integer> stack = new LinkedList<>();
        int[] res = new int[len];
        for (int i = 0; i < len; i++) {
            while (!stack.isEmpty() && T[i] > T[stack.peekLast()]) {
                Integer index = stack.pollLast();
                res[index] = i - index;
            }
            stack.offerLast(i);
        }
        return res;
    }

    public static int[] dailyTemperatures(int[] T) {
        // Brute Force, time is O(n^2), space is O(1)
        int len = T.length;
        int[] res = new int[len];
        for (int i = 0; i < len; i++) {
            int value = 0;
            for (int j = i + 1; j < len; j++) {
                if (T[j] > T[i]) {
                    value = j - i;
                    break;
                }
            }
            res[i] = value;
        }
        return res;
    }

    private static void disp(int[] nums) {
        System.out.println(Arrays.stream(nums).boxed().collect(Collectors.toList()));
    }
}

/*
* 请根据每日 气温 列表，重新生成一个列表。对应位置的输出为：要想观测到更高的气温，至少需要等待的天数。如果气温在这之后都不会升高，请在该位置用 0 来代替。

例如，给定一个列表 temperatures = [73, 74, 75, 71, 69, 72, 76, 73]，你的输出应该是 [1, 1, 4, 2, 1, 1, 0, 0]。

提示：气温 列表长度的范围是 [1, 30000]。每个气温的值的均为华氏度，都是在 [30, 100] 范围内的整数。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/daily-temperatures
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */
