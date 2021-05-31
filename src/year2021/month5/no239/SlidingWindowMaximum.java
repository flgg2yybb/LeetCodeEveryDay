package year2021.month5.no239;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

public class SlidingWindowMaximum {

    public static void main(String[] args) {
        int[] nums1 = {1, 3, -1, -3, 5, 3, 6, 7};
        int k1 = 3;
        int[] nums2 = {1};
        int k2 = 1;
        int[] nums3 = {1, -1};
        int k3 = 1;
        int[] nums4 = {9, 11};
        int k4 = 2;
        int[] nums5 = {4, -2};
        int k5 = 2;
        System.out.println(Arrays.toString(maxSlidingWindow(nums1, k1)));
        System.out.println(Arrays.toString(maxSlidingWindow(nums2, k2)));
        System.out.println(Arrays.toString(maxSlidingWindow(nums3, k3)));
        System.out.println(Arrays.toString(maxSlidingWindow(nums4, k4)));
        System.out.println(Arrays.toString(maxSlidingWindow(nums5, k5)));
    }

    public static int[] maxSlidingWindow(int[] nums, int k) {
        // 单调不增队列，将窗口内的元素依次入队，并维持队列为单调不增队列
        // 则对于每一次窗口来说，队列头部元素即为窗口最大值
        Deque<Integer> queue = new LinkedList<>();
        for (int i = 0; i < k; i++) {
            inQueue(queue, nums[i]);
        }
        int[] ans = new int[nums.length - k + 1];
        ans[0] = queue.peekFirst();
        int pos = 1;
        for (int i = k; i < nums.length; i++) {
            int pop = nums[i - k];
            deQueueIfPresent(queue, pop);
            inQueue(queue, nums[i]);
            ans[pos] = queue.peekFirst();
            pos++;
        }
        return ans;
    }

    private static void deQueueIfPresent(Deque<Integer> queue, int pop) {
        if (!queue.isEmpty() && queue.peekFirst() == pop) {
            queue.pollFirst();
        }
    }

    private static void inQueue(Deque<Integer> queue, int num) {
        while (!queue.isEmpty() && queue.peekLast() < num) {
            queue.pollLast();
        }
        queue.offerLast(num);
    }

}

/*
* 给你一个整数数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位。

返回滑动窗口中的最大值。

 

示例 1：

输入：nums = [1,3,-1,-3,5,3,6,7], k = 3
输出：[3,3,5,5,6,7]
解释：
滑动窗口的位置                最大值
---------------               -----
[1  3  -1] -3  5  3  6  7       3
 1 [3  -1  -3] 5  3  6  7       3
 1  3 [-1  -3  5] 3  6  7       5
 1  3  -1 [-3  5  3] 6  7       5
 1  3  -1  -3 [5  3  6] 7       6
 1  3  -1  -3  5 [3  6  7]      7
示例 2：

输入：nums = [1], k = 1
输出：[1]
示例 3：

输入：nums = [1,-1], k = 1
输出：[1,-1]
示例 4：

输入：nums = [9,11], k = 2
输出：[11]
示例 5：

输入：nums = [4,-2], k = 2
输出：[4]
 

提示：

1 <= nums.length <= 105
-104 <= nums[i] <= 104
1 <= k <= nums.length

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/sliding-window-maximum
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */
