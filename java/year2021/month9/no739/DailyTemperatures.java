package year2021.month9.no739;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

public class DailyTemperatures {
    public static void main(String[] args) {
        int[] t1 = {73, 74, 75, 71, 69, 72, 76, 73};
        int[] t2 = {30, 40, 50, 60};
        int[] t3 = {30, 60, 90};
        System.out.println(Arrays.toString(dailyTemperatures(t1)));
        System.out.println(Arrays.toString(dailyTemperatures(t2)));
        System.out.println(Arrays.toString(dailyTemperatures(t3)));
    }

    public static int[] dailyTemperatures(int[] temperatures) {
        // 单调不增栈
        Deque<Integer> stack = new LinkedList<>();
        int len = temperatures.length;
        int[] ans = new int[len];
        for (int i = 0; i < len; i++) {
            while (!stack.isEmpty() && temperatures[stack.peek()] < temperatures[i]) {
                Integer index = stack.pop();
                ans[index] = i - index;
            }
            stack.push(i);
        }
        // 剩下的元素为 0，则不用处理
        return ans;
    }
}

/*
* 请根据每日 气温 列表 temperatures ，请计算在每一天需要等几天才会有更高的温度。如果气温在这之后都不会升高，请在该位置用 0 来代替。

示例 1:

输入: temperatures = [73,74,75,71,69,72,76,73]
输出: [1,1,4,2,1,1,0,0]
示例 2:

输入: temperatures = [30,40,50,60]
输出: [1,1,1,0]
示例 3:

输入: temperatures = [30,60,90]
输出: [1,1,0]
 

提示：

1 <= temperatures.length <= 105
30 <= temperatures[i] <= 100

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/daily-temperatures
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */
