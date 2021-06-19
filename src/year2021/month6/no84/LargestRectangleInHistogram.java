package year2021.month6.no84;

public class LargestRectangleInHistogram {

    public static void main(String[] args) {
        int[] heights1 = {2, 1, 5, 6, 2, 3};
        int[] heights2 = {5, 5, 1, 7, 1, 1, 5, 2, 7, 6};
        System.out.println(largestRectangleArea(heights1));
        System.out.println(largestRectangleArea(heights2));
    }

    public static int largestRectangleArea(int[] heights) {
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
