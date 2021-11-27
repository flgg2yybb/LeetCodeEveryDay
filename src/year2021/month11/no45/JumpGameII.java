package year2021.month11.no45;

public class JumpGameII {

    public static void main(String[] args) {
        int[] nums1 = {2, 3, 1, 1, 4};
        int[] nums2 = {2, 3, 0, 1, 4};
        int[] nums3 = {2, 2, 0, 1, 4};
        System.out.println(jump(nums1));    //2
        System.out.println(jump(nums2));    //2
        System.out.println(jump(nums3));    //3
    }

    private static int jump(int[] nums) {
        // curMaxJump 为上一次可以跳到的最远距离
        int curMaxJump = 0;
        int step = 0;
        int maxPos = 0;
        for (int i = 0; i < nums.length - 1; i++) { // 不需要跳到 nums.length 否则会多一次跳跃数
            maxPos = Math.max(i + nums[i], maxPos);
            if (i == curMaxJump) {
                step++;
                curMaxJump = maxPos;
            }
        }
        return step;
    }

    public static int jump2(int[] nums) {
        // 贪心
        int cur = 0;
        int step = 0;
        while (cur < nums.length - 1) {
            int canJump = nums[cur];
            step++;
            if (cur + canJump >= nums.length - 1) {
                return step;
            }
            int nextJump = cur;
            for (int i = cur + 1; i <= cur + canJump; i++) {
                if (i + nums[i] > nextJump + nums[nextJump]) {
                    nextJump = i;
                }
            }
            cur = nextJump;
        }
        return step;
    }

    public static int jump1(int[] nums) {
        /*
         * DP, time is O(n^2), space is O(n)
         * dp[i] 表示跳跃到位置 i 的最小跳跃数
         * dp[0] = 0
         * dp[i] = min{dp[j]} + 1, j < i && j + nums[j] >= i
         * */
        int[] dp = new int[nums.length];
        for (int i = 1; i < nums.length; i++) {
            dp[i] = i;
            for (int j = 0; j < i; j++) {
                if (j + nums[j] >= i) {
                    dp[i] = Math.min(dp[i], dp[j] + 1);
                }
            }
        }
        return dp[nums.length - 1];
    }
}

/*
 * 给你一个非负整数数组 nums ，你最初位于数组的第一个位置。

数组中的每个元素代表你在该位置可以跳跃的最大长度。

你的目标是使用最少的跳跃次数到达数组的最后一个位置。

假设你总是可以到达数组的最后一个位置。

 

示例 1:

输入: nums = [2,3,1,1,4]
输出: 2
解释: 跳到最后一个位置的最小跳跃数是 2。
     从下标为 0 跳到下标为 1 的位置，跳 1 步，然后跳 3 步到达数组的最后一个位置。
示例 2:

输入: nums = [2,3,0,1,4]
输出: 2
 

提示:

1 <= nums.length <= 104
0 <= nums[i] <= 1000

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/jump-game-ii
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */
