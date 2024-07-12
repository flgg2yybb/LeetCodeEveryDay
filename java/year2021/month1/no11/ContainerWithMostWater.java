package year2021.month1.no11;

public class ContainerWithMostWater {
    public static void main(String[] args) {
        int[] heights1 = new int[]{1, 8, 6, 2, 5, 4, 8, 3, 7};
        int[] heights2 = new int[]{1, 1};
        int[] heights3 = new int[]{4, 3, 2, 1, 4};
        int[] heights4 = new int[]{1, 2, 1};
        System.out.println(maxArea(heights1));
        System.out.println(maxArea(heights2));
        System.out.println(maxArea(heights3));
        System.out.println(maxArea(heights4));
    }

    public static int maxArea(int[] height) {
        /*双指针
         * 定义左指针left指向头部，右指针right指向尾部，双指针向中间扫描
         * 由于容量 = (right - left) * Math.min(height[right], height[left])
         * 在指针向中间移动的过程中，(right - left)会减小
         * 则 Math.min(height[right], height[left])需要增大才能找出更大的容量
         * 即：增大短板
         * 所以每次循环开始时，找出left和right较短的那一个
         * 使其朝着中间的方向找到下一个更大的高度
         * 每次比较容量即可找到最大容量
         * */
        if (height == null || height.length < 2) {
            return 0;
        }
        int left = 0;
        int right = height.length - 1;
        int maxVolume = 0;
        while (left < right) {
            maxVolume = Math.max(maxVolume, (right - left) * Math.min(height[left], height[right]));
            if (height[left] < height[right]) {
                int origin = height[left];
                while (++left < right) {
                    if (height[left] > origin) {
                        break;
                    }
                }
            } else {
                int origin = height[right];
                while (--right > left) {
                    if (height[right] > origin) {
                        break;
                    }
                }
            }
        }
        return maxVolume;
    }
}

/*
* 给你 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i, ai) 和 (i, 0) 。找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。

说明：你不能倾斜容器。

 

示例 1：



输入：[1,8,6,2,5,4,8,3,7]
输出：49
解释：图中垂直线代表输入数组 [1,8,6,2,5,4,8,3,7]。在此情况下，容器能够容纳水（表示为蓝色部分）的最大值为 49。
示例 2：

输入：height = [1,1]
输出：1
示例 3：

输入：height = [4,3,2,1,4]
输出：16
示例 4：

输入：height = [1,2,1]
输出：2
 

提示：

n = height.length
2 <= n <= 3 * 104
0 <= height[i] <= 3 * 104

* */
