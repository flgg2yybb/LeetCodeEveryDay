package year2021.month6.no84;

import java.util.Deque;
import java.util.LinkedList;

public class LargestRectangleInHistogram {

    public static void main(String[] args) {
        int[] heights1 = {2, 1, 5, 6, 2, 3};
        int[] heights2 = {5, 5, 1, 7, 1, 1, 5, 2, 7, 6};
        System.out.println(largestRectangleArea(heights1));
        System.out.println(largestRectangleArea(heights2));
    }

    /*
     * 单调递增栈
     * 暴力法中，我们需要找到任意一个柱子的左右边界，其实就是在左右方
     * 寻找是否存在比当前柱子高度严格要小的柱子，有则为边界
     * 因此，若我们遍历 heights 时，保持栈内元素高度递增
     * 如果当前柱子高度比栈顶元素高度大，则将其入栈，
     *   且可知新栈顶元素的左边界即为原栈顶元素
     * 若果当前柱子高度比栈顶元素高度小，则连续出栈
     *   并在每次出栈时更新出栈元素高度所能勾勒出的面积
     * 栈内元素为索引，以便宽度计算
     * 同时在数组前后引入两个高度为 0 的哨兵避免重复判断
     * 每个元素只入栈、出栈各一次，故复杂度为
     * time is O(n), space is O(n)
     * */
    private static int largestRectangleArea(int[] heights) {
        int[] newHeights = new int[heights.length + 2];
        // 哨兵
        newHeights[0] = 0;
        newHeights[newHeights.length - 1] = 0;
        System.arraycopy(heights, 0, newHeights, 1, heights.length);
        Deque<Integer> stack = new LinkedList<>();
        int maxArea = 0;
        for (int i = 0; i < newHeights.length; i++) {
            while (!stack.isEmpty() && newHeights[i] < newHeights[stack.peekLast()]) {
                int index = stack.pollLast();
                int currHeight = newHeights[index];
                assert !stack.isEmpty();    // 由于栈底有高度为 0 的哨兵，且该哨兵不可能出栈，则此处一定有元素
                int currWidth = i - stack.peekLast() - 1;
                maxArea = Math.max(maxArea, currWidth * currHeight);

            }
            stack.offerLast(i);
        }
        return maxArea;
    }

    public static int largestRectangleArea1(int[] heights) {
        // 暴力法（中心扩散），对于每一根柱子，我们以当前柱子高度尽量向两方扩散
        // 扩散到顶时即可计算当前柱子高度所能勾勒成的最大面积
        int max = 0;
        for (int i = 0; i < heights.length; i++) {
            int left = i;
            int right = i;
            int currHeight = heights[i];
            while (left >= 0 && heights[left] >= currHeight) {
                left--;
            }
            while (right < heights.length && heights[right] >= currHeight) {
                right++;
            }
            int currWidth = right - left - 1;
            max = Math.max(max, currWidth * currHeight);
        }
        return max;
    }

}

/*
* 给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。

求在该柱状图中，能够勾勒出来的矩形的最大面积。

 



以上是柱状图的示例，其中每个柱子的宽度为 1，给定的高度为 [2,1,5,6,2,3]。

 



图中阴影部分为所能勾勒出的最大矩形面积，其面积为 10 个单位。

 

示例:

输入: [2,1,5,6,2,3]
输出: 10

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/largest-rectangle-in-histogram
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */
