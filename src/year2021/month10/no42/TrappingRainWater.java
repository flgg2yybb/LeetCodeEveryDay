package year2021.month10.no42;

public class TrappingRainWater {

    public static void main(String[] args) {
        int[] height1 = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        int[] height2 = {4, 2, 0, 3, 2, 5};
        System.out.println(trap(height1));
        System.out.println(trap(height2));
    }

    private static int trap(int[] height) {
        int len = height.length;
        int ans = 0;
        int left = 0;
        int right = len - 1;
        int minBound = 0;
        while (left < right) {
            if (height[left] <= height[right]) {
                minBound = Math.max(minBound, height[left]);
                ans += minBound - height[left];
                left++;
            } else {
                minBound = Math.max(minBound, height[right]);
                ans += minBound - height[right];
                right--;
            }
        }
        return ans;
    }

    public static int trap1(int[] height) {
        int len = height.length;
        int[] leftMax = new int[len];
        int[] rightMax = new int[len];
        leftMax[0] = height[0];
        for (int i = 1; i < len; i++) {
            leftMax[i] = Math.max(height[i], leftMax[i - 1]);
        }
        rightMax[len - 1] = height[len - 1];
        for (int i = len - 2; i >= 0; i--) {
            rightMax[i] = Math.max(height[i], rightMax[i + 1]);
        }
        int ans = 0;
        for (int i = 0; i < len; i++) {
            int minBound = Math.min(leftMax[i], rightMax[i]);
            ans += minBound - height[i];
        }
        return ans;
    }

}

/*
* 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。

 

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

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/trapping-rain-water
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */
