package year2025.month1.no239;


import java.util.*;

// 单调递减队列
class DecreasingQueue {
    Deque<Integer> queue = new LinkedList<>();

    public DecreasingQueue() {
    }

    public void lPush(int value) {
        while (!queue.isEmpty() && queue.peekLast() < value) {
            queue.pollLast();
        }
        queue.offerLast(value);
    }

    public void rPop(int remove) {
        if (!queue.isEmpty() && queue.peekFirst() == remove) {
            queue.pollFirst();
        }
    }

    public int getMax() {
        return queue.peekFirst();
    }
}

public class SlidingWindowMaximum {

    // 单调递减队列，time: O(n), space: O(k)
    public static int[] maxSlidingWindow(int[] nums, int k) {
        List<Integer> ans = new ArrayList<>(nums.length - k + 1);
        DecreasingQueue decreQueue = new DecreasingQueue();
        int left = 0;
        int right = 0;
        for (; right < k; right++) {
            decreQueue.lPush(nums[right]);
        }
        ans.add(decreQueue.getMax());
        for (; right < nums.length; right++) {
            decreQueue.lPush(nums[right]);
            decreQueue.rPop(nums[left]);
            left++;
            ans.add(decreQueue.getMax());
        }
        return ans.stream().mapToInt(Integer::valueOf).toArray();
    }

    public static void main(String[] args) {
        int[] nums = {1,3,-1,-3,5,3,6,7};
        int k = 3;
        System.out.println(Arrays.toString(maxSlidingWindow(nums, k)));

    }
}

/*
给你一个整数数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位。

返回 滑动窗口中的最大值 。



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


提示：

1 <= nums.length <= 105
-104 <= nums[i] <= 104
1 <= k <= nums.length
*/
