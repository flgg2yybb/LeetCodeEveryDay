package year2021.month1.no55;

public class JumpGame {
    public static void main(String[] args) {
        int[] nums1 = new int[]{2, 3, 1, 1, 4};
        int[] nums2 = new int[]{3, 2, 1, 0, 4};
        System.out.println(canJump(nums1));
        System.out.println(canJump(nums2));
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
