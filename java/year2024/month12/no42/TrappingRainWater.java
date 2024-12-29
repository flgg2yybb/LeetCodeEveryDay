package year2024.month12.no42;

public class TrappingRainWater {

    // Two Pointers, time: O(n), space: O(1)
    // 思路：如果可以知道每一个点左右两边的最长板，即可知道当前点可以积累多少雨水
    // 因此可以使用双指针，分别从左右两边向中间靠拢，维护当前区间内的左右两边的最长板
    // 又因为雨水量只和较短的长版有关，所以仅需要维护左右两边的最长板中的短版即可
    // 每次移动双指针较短的那一边，逐步积累每一个点的雨水，即可得到答案
    public static int trap(int[] height) {
        int sum = 0;
        int left = 0;
        int right = height.length - 1;
        int minBound = 0;
        while (left < right) {
            if (height[left] <= height[right]) {
                minBound = Math.max(minBound, height[left]);
                sum += minBound - height[left];
                left++;
            } else {    // height[left] > height[right]
                minBound = Math.max(minBound, height[right]);
                sum += minBound - height[right];
                right--;
            }
        }
        return sum;
    }

    public static void main(String[] args) {
        int[] height1 = new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        int[] height2 = new int[]{4, 2, 0, 3, 2, 5};
        System.out.println(trap(height1));
        System.out.println(trap(height2));
    }
}

/*
给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。

示例 1：

输入：height = [0,1,0,2,1,0,1,3,2,1,2,1]
输出：6
解释：上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。
示例 2：

输入：height = [4,2,0,3,2,5]
输出：9


提示：

n == height.length
1 <= n <= 2 * 104
0 <= height[i] <= 105
*/
