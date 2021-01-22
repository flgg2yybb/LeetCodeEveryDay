package year2021.month1.no84;

import java.util.Deque;
import java.util.LinkedList;

public class LargestRectangleInHistogram {
    public static void main(String[] args) {
        int[] heights1 = new int[]{2, 1, 5, 6, 2, 3};
        int[] heights2 = new int[]{2, 1, 0, 5, 2, 3};
        int[] heights3 = new int[]{1, 1};
        System.out.println(largestRectangleArea(heights1));
        System.out.println(largestRectangleArea(heights2));
        System.out.println(largestRectangleArea(heights3));
    }

    /*
     * 单调栈（Monotone Stack）
     * 单调栈首先是栈，是栈的应用
     * 栈内元素维持了【单调性】的应用场景
     *   1. 单调递增（不减）栈可以找到左边第一个比当前出栈元素小（包含等于）的元素
     *   2. 单调递减（不增）栈可以找到左边第一个比当前出栈元素大（包含等于）的元素
     * */

    public static int largestRectangleArea(int[] heights) {
        Deque<Integer> stack = new LinkedList<>();
        int[] leftBoundaries = new int[heights.length];
        int[] rightBoundaries = new int[heights.length];
//        更新各个柱子的左边界
        for (int i = 0; i < heights.length; i++) {
            while (!stack.isEmpty() && heights[stack.peekLast()] >= heights[i]) {
                Integer pop = stack.pollLast();
                leftBoundaries[pop] = stack.isEmpty() ? -1 : stack.peekLast();
            }
            stack.offerLast(i);
        }
        while (!stack.isEmpty()) {
            Integer pop = stack.pollLast();
            leftBoundaries[pop] = stack.isEmpty() ? -1 : stack.peekLast();
        }
//        更新各个柱子的右边界
        for (int i = heights.length - 1; i >= 0; i--) {
            while (!stack.isEmpty() && heights[stack.peekLast()] >= heights[i]) {
                Integer pop = stack.pollLast();
                rightBoundaries[pop] = stack.isEmpty() ? heights.length : stack.peekLast();
            }
            stack.offerLast(i);
        }
        while (!stack.isEmpty()) {
            Integer pop = stack.pollLast();
            rightBoundaries[pop] = stack.isEmpty() ? heights.length : stack.peekLast();
        }
//        根据每个柱子的左右边界计算出以当前柱子为高的面积，并更新最大面积
        int max = 0;
        for (int i = 0; i < heights.length; i++) {
            int width = rightBoundaries[i] - leftBoundaries[i] - 1;
            max = Math.max(max, heights[i] * width);
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
