package year2021.month1.no53;

public class MaximumSubarray {
    public static void main(String[] args) {
        int[] nums1 = new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4};
        System.out.println(maxSubArray2(nums1));
    }

    private static int maxSubArray2(int[] nums) {
        /*Divide and conquer
         * 定义一个操作 get(a, l, r)表示查询 a数组中[l,r]区间的连续子数组的最大和，那么最终我们需要求得答案
         * 即是 get(nums, 0, nums.length - 1)，可用分治法求出
         * 对于一个区间[l, r]，令 m = (l + r) / 2，对区间[l, m], [m, r]进行分治求解，当递归逐层深入直到
         * 区间长度为 1的时候，开始递归回升，这里我们需要考虑如何将 [l, m]和[m, r]的信息合并为区间[l, r]的
         * 信息
         * 对于[l, r]区间，定义以下四个变量：
         * lSum：以l为左端点的连续子数组的最大和
         * rSum：以r为右端点的连续子数组的最大和
         * mSum：[l, r]区间的连续子数组的最大和
         * iSum：[l, r]区间和
         * 则，对于[l, r]中的[l, m]以及[m, r]，我们有：
         * res.lSum = max(left.lSum, left.iSum + right.lSum)
         * res.rSum = max(right.rSum, right.iSum + left.rSum)
         * res.mSum = max(left.mSum, right.mSum, left.rSum + right.lSum)
         * res.iSum = left.iSum + right.iSum
         * 最后，区间[l, r]的连续子数组的最大和即为
         * 时间复杂度，数组上每一个元素都遍历了一遍，则为 O(n)
         * 空间复杂度，为栈递归的空间，O(log n)
         * 【题外话】
         * 它不仅可以解决区间 [0, n - 1][0,n−1]，还可以用于解决任意的子区间 [l, r] 的问题。
         * 如果我们把 [0, n - 1]分治下去出现的所有子区间的信息都用堆式存储的方式记忆化下来，
         * 即建成一颗真正的树之后，我们就可以在 O(log n) 的时间内求到任意区间内的答案，
         * 我们甚至可以修改序列中的值，做一些简单的维护，之后仍然可以在 O(log n)
         * 的时间内求到任意区间内的答案，对于大规模查询的情况下，这种方法的优势便体现了出来。
         * 这棵树就是上文提及的一种神奇的数据结构 —— 线段树。
         * */
        return get(nums, 0, nums.length - 1).mSum;
    }


    private static Status get(int[] nums, int l, int r) {
        if (l == r) {
            return new Status(nums[l], nums[l], nums[l], nums[l]);
        }
        int m = (l + r) >> 1;
        Status leftStatus = get(nums, l, m);
        Status rightStatus = get(nums, m + 1, r);
        return merge(leftStatus, rightStatus);
    }

    private static Status merge(Status leftStatus, Status rightStatus) {
        Status status = new Status();
        status.lSum = Math.max(leftStatus.lSum, leftStatus.iSum + rightStatus.lSum);
        status.rSum = Math.max(rightStatus.rSum, rightStatus.iSum + leftStatus.rSum);
        status.mSum = Math.max(Math.max(leftStatus.mSum, rightStatus.mSum), leftStatus.rSum + rightStatus.lSum);
        status.iSum = leftStatus.iSum + rightStatus.iSum;
        return status;
    }

    public static int maxSubArray1(int[] nums) {
        /*DP，
         * 状态定义：
         * dp[i]为包含下班i的连续子数组的最大和
         * 状态转移方程：
         * dp[i] = dp[i - 1] > 0 ? dp[i - 1] + nums[i] : nums[i]
         * 空间压缩：
         * 动态方程只与dp[i-1]有关，则可用pre变量指向dp[i-1]
         * */
        int max = nums[0];
        int pre = nums[0];
        for (int i = 1; i < nums.length; i++) {
            pre = pre > 0 ? pre + nums[i] : nums[i];
            max = Math.max(max, pre);
        }
        return max;
    }

    public static int maxSubArray(int[] nums) {
        /*DP，
         * 状态定义：
         * dp[i]为包含下班i的连续子数组的最大和
         * 状态转移方程：
         * dp[i] = dp[i - 1] > 0 ? dp[i - 1] + nums[i] : nums[i]
         * */
        int[] dp = new int[nums.length];
        int max = nums[0];
        dp[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            dp[i] = dp[i - 1] > 0 ? dp[i - 1] + nums[i] : nums[i];
            max = Math.max(max, dp[i]);
        }
        return max;
    }

}

class Status {
    int lSum;   //以l为左端点的连续子数组的最大和
    int rSum;   //以r为右端点的连续子数组的最大和
    int mSum;   //[l, r]区间的连续子数组的最大和
    int iSum;   //[l, r]区间和

    public Status() {
    }

    public Status(int lSum, int rSum, int mSum, int iSum) {
        this.lSum = lSum;
        this.rSum = rSum;
        this.mSum = mSum;
        this.iSum = iSum;
    }
}

/*
 * 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。

示例:

输入: [-2,1,-3,4,-1,2,1,-5,4]
输出: 6
解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
进阶:

如果你已经实现复杂度为 O(n) 的解法，尝试使用更为精妙的分治法求解。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/maximum-subarray
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */
