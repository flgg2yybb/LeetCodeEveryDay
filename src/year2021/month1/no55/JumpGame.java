package year2021.month1.no55;

public class JumpGame {
    public static void main(String[] args) {
        int[] nums1 = new int[]{2, 3, 1, 1, 4};
        int[] nums2 = new int[]{3, 2, 1, 0, 4};
        System.out.println(canJump3(nums1));
        System.out.println(canJump3(nums2));
    }

    private static boolean canJump3(int[] nums) {
//        canJump2优化版本
        int reach = 0;
        for (int i = 0; i <= reach && reach < nums.length - 1; i++) {
            reach = Math.max(reach, i + nums[i]);
        }
        return reach >= nums.length - 1;
    }

    private static boolean canJump2(int[] nums) {
        /*Greedy,
         * 从遍历每个元素，并更新当前所能达到的最远位置
         * 若某一个位置的索引大于能达到的最远位置，则意为着当前位置无法到达，
         * 则最后一个位置也必无法到达
         * 若顺利遍历到最后一个元素，则说明可以到达最后一个元素
         * */
        int reach = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i > reach) {
                return false;
            }
            reach = Math.max(reach, i + nums[i]);
        }
        return true;
    }

    private static boolean canJump4(int[] nums) {
        /*canJump1 优化版本
         * 逆向往前遍历，用last最早可以跳到最后一个位置的元素的索引，
         * 逆向遍历时，若当前元素可以跳的范围超过last，则更新last
         * 指向当前元素索引
         * 遍历完后，若last == 0，意味着第一个元素可以跳到最后一个元素
         * */
        int last = nums.length - 1;
        for (int i = nums.length - 2; i >= 0; i--) {
            if (i + nums[i] >= last) {
                last = i;
            }
        }
        return last == 0;
    }

    private static boolean canJump1(int[] nums) {
        /*DP，
         * 状态定义：
         * dp[i]表示索引为i的元素可以抵达最后一个位置
         * 状态转移方程：
         * dp[i] = dp[i + nums[i]] || dp[i + nums[i - 1]] || ... || dp[i + 1]
         * 初始值：
         * dp[nums.length - 1] = true
         * */
        boolean[] dp = new boolean[nums.length];
        dp[nums.length - 1] = true;
        for (int i = nums.length - 2; i >= 0; i--) {
            int maxStep = Math.min(nums.length - 1, i + nums[i]);
            for (int j = maxStep; j > i; j--) {
                if (dp[j]) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[0];
    }

    public static boolean canJump(int[] nums) {
        //DFS
        return dfs(nums, 0);
    }

    private static boolean dfs(int[] nums, int pos) {
        if (pos == nums.length - 1) {
            return true;
        }
        int maxStep = nums[pos];
        if (maxStep == 0) {
            return false;
        }
        int start = Math.min(nums.length - 1, pos + maxStep);
        for (int i = start; i > pos; i--) {
            if (dfs(nums, i)) {
                return true;
            }
        }
        return false;
    }

}

/*
 * 给定一个非负整数数组，你最初位于数组的第一个位置。

数组中的每个元素代表你在该位置可以跳跃的最大长度。

判断你是否能够到达最后一个位置。

示例 1:

输入: [2,3,1,1,4]
输出: true
解释: 我们可以先跳 1 步，从位置 0 到达 位置 1, 然后再从位置 1 跳 3 步到达最后一个位置。
示例 2:

输入: [3,2,1,0,4]
输出: false
解释: 无论怎样，你总会到达索引为 3 的位置。但该位置的最大跳跃长度是 0 ， 所以你永远不可能到达最后一个位置。
* 
* */
