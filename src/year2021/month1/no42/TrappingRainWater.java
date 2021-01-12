package year2021.month1.no42;

import java.util.Deque;
import java.util.LinkedList;
import java.util.stream.IntStream;

public class TrappingRainWater {
    public static void main(String[] args) {
        int[] height1 = new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        int[] height2 = new int[]{4, 2, 0, 3, 2, 5};
        int[] height3 = new int[]{0, 2, 4, 0, 8, 5};
        System.out.println(trap2(height1));
        System.out.println(trap2(height2));
        System.out.println(trap2(height3));

    }

    private static int trap2(int[] height) {
        if (height == null || height.length < 3) {
            return 0;
        }
        Deque<Integer> stack = new LinkedList<>();
        int i = 0;
        int volume = 0;
        while (i < height.length) {
            while (!stack.isEmpty() && height[stack.peekLast()] < height[i]) {
                int top = stack.pollLast();
                if (stack.isEmpty()) {
                    break;
                }
                int width = i - stack.peekLast() - 1;
                int high = Math.min(height[stack.peekLast()], height[i]) - height[top];
                volume += high * width;
            }
            stack.offerLast(i++);
        }
        return volume;
    }

    private static int trap1(int[] height) {
        if (height == null || height.length < 3) {
            return 0;
        }
        int[] leftBound = new int[height.length];
        leftBound[0] = height[0];
        for (int i = 1; i < height.length; i++) {
            leftBound[i] = Math.max(leftBound[i - 1], height[i]);
        }
        int[] rightBound = new int[height.length];
        rightBound[height.length - 1] = height[height.length - 1];
        for (int i = height.length - 2; i >= 0; i--) {
            rightBound[i] = Math.max(rightBound[i + 1], height[i]);
        }
        return IntStream.range(0, height.length)
                .map(i -> Math.min(leftBound[i], rightBound[i]) - height[i])
                .sum();
    }

    public static int trap(int[] height) {
        if (height == null || height.length < 3) {
            return 0;
        }
        int left = 0;
        int right = height.length - 1;
        int shortBoard = 0;
        int volume = 0;
        while (left < right) {
            if (height[left] < height[right]) {
                if (height[left] >= shortBoard) {
                    shortBoard = height[left];
                } else {
                    volume += shortBoard - height[left];
                }
                left++;
            } else {
                if (height[right] >= shortBoard) {
                    shortBoard = height[right];
                } else {
                    volume += shortBoard - height[right];
                }
                right--;
            }
        }
        return volume;
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
0 <= n <= 3 * 104
0 <= height[i] <= 105

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/trapping-rain-water
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */