package year2021.month6.no42;

public class TrappingRainWater {

    public static void main(String[] args) {
        int[] height1 = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        int[] height2 = {4, 2, 0, 3, 2, 5};
        System.out.println(trap1(height1));
        System.out.println(trap1(height2));
    }

    private static int trap1(int[] height) {
        /*
         * 双指针, time is O(n), space is O(1)
         * 分别用 left、right 指针指向头尾，向中间夹逼
         * 每次移动较小的指针，同时根据 left、right 指针
         * 求出当前较小的边界高度 minBound
         * 若当前位置小于 minBound，意味着当前为凹槽
         * 则将当前位置的装水量累加
         * 循环往复，直至指针相遇
         * */
        int left = 0;
        int right = height.length - 1;
        int count = 0;
        int minBound = 0;
        while (left < right) {
            if (height[left] <= height[right]) {
                minBound = Math.max(minBound, height[left]);
                count += minBound - height[left];
                left++;
            } else {
                minBound = Math.max(minBound, height[right]);
                count += minBound - height[right];
                right--;
            }
        }
        return count;
    }

    public static int trap(int[] height) {
        /*
         * 分别计算每个位置上左右的最大边界
         * 若左右最大边界中较小的那个比当前位置的高度高
         * 表示当前位置是个凹槽，可以接雨水
         * 则用较小的边界高度减去当前位置高度即可当前位置接到的雨水量
         * time is O(n), space is O(n)
         * */
        if (height.length < 3) {
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
        int count = 0;
        for (int i = 0; i < height.length; i++) {
            int minBound = Math.min(leftBound[i], rightBound[i]);
            count += minBound > height[i] ? minBound - height[i] : 0;
        }
        return count;
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
