package year2024.month12.no11;

public class ContainerWithMostWater {
    public static int maxArea(int[] height) {
        int left = 0, right = height.length - 1;
        int area = 0;
        while (left < right) {
            int wid = right - left;
            int high = 0;
            if (height[left] <= height[right]) {
                high = height[left];
                left++;
            } else { // height[left] > height[right]
                high = height[right];
                right--;
            }
            area = Math.max(area, wid * high);
        }
        return area;
    }

    public static void main(String[] args) {
        int[] height1 = {1, 8, 6, 2, 5, 4, 8, 3, 7};
        int[] height2 = {1, 1};
        int[] height3 = {4, 3, 2, 1, 4};
        int[] height4 = {1, 2, 1};
        System.out.println(maxArea(height1));
        System.out.println(maxArea(height2));
        System.out.println(maxArea(height3));
        System.out.println(maxArea(height4));
    }
}

/*
* 给定一个长度为 n 的整数数组 height 。有 n 条垂线，第 i 条线的两个端点是 (i, 0) 和 (i, height[i]) 。

找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。

返回容器可以储存的最大水量。

说明：你不能倾斜容器。


输入：[1,8,6,2,5,4,8,3,7]
输出：49
解释：图中垂直线代表输入数组 [1,8,6,2,5,4,8,3,7]。在此情况下，容器能够容纳水（表示为蓝色部分）的最大值为 49。
示例 2：

输入：height = [1,1]
输出：1


提示：

n == height.length
2 <= n <= 105
0 <= height[i] <= 104

* */
