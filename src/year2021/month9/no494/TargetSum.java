package year2021.month9.no494;

public class TargetSum {
    public static void main(String[] args) {
        int[] nums1 = {1, 1, 1, 1, 1};
        int target1 = 3;
        int[] nums2 = {1};
        int target2 = 1;
        System.out.println(findTargetSumWays(nums1, target1));
        System.out.println(findTargetSumWays(nums2, target2));
    }

    private static int count = 0;

    public static int findTargetSumWays(int[] nums, int target) {
        // backtrack
        count = 0;
        dfs(nums, target, 0, 0);
        return count;
    }

    private static void dfs(int[] nums, int target, int cur, int pos) {
        if (pos == nums.length) {
            if (cur == target) {
                count++;
            }
            return;
        }
        dfs(nums, target, cur + nums[pos], pos + 1);
        dfs(nums, target, cur - nums[pos], pos + 1);
    }
}

/*
* 给你一个整数数组 nums 和一个整数 target 。

向数组中的每个整数前添加 '+' 或 '-' ，然后串联起所有整数，可以构造一个 表达式 ：

例如，nums = [2, 1] ，可以在 2 之前添加 '+' ，在 1 之前添加 '-' ，然后串联起来得到表达式 "+2-1" 。
返回可以通过上述方法构造的、运算结果等于 target 的不同 表达式 的数目。

 

示例 1：

输入：nums = [1,1,1,1,1], target = 3
输出：5
解释：一共有 5 种方法让最终目标和为 3 。
-1 + 1 + 1 + 1 + 1 = 3
+1 - 1 + 1 + 1 + 1 = 3
+1 + 1 - 1 + 1 + 1 = 3
+1 + 1 + 1 - 1 + 1 = 3
+1 + 1 + 1 + 1 - 1 = 3
示例 2：

输入：nums = [1], target = 1
输出：1
 

提示：

1 <= nums.length <= 20
0 <= nums[i] <= 1000
0 <= sum(nums[i]) <= 1000
-1000 <= target <= 1000

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/target-sum
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */
