package year2021.month8.jz59a;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class MaxSlidingWindow {

    public static void main(String[] args) {
        int[] nums1 = {1, 3, -1, -3, 5, 3, 6, 7};
        int k1 = 3;
        int[] nums2 = {-7, -8, 7, 5, 7, 1, 6, 0};
        int k2 = 4;
        System.out.println(Arrays.toString(maxSlidingWindow1(nums1, k1)));
        System.out.println(Arrays.toString(maxSlidingWindow1(nums2, k2)));
    }

    private static int[] maxSlidingWindow1(int[] nums, int k) {
        // 单调不增队列
        if (nums.length == 0) {
            return new int[0];
        }
        Deque<Integer> queue = new LinkedList<>();
        for (int i = 0; i < k; i++) {
            addToQueue(queue, nums[i]);
        }
        int[] ans = new int[nums.length - k + 1];
        ans[0] = queue.peekFirst();
        int pos = 1;
        for (int i = k; i < nums.length; i++) {
            int out = nums[i - k];
            if (queue.peekFirst() == out) {
                queue.pollFirst();
            }
            addToQueue(queue, nums[i]);
            ans[pos++] = queue.peekFirst();
        }
        return ans;
    }

    private static void addToQueue(Deque<Integer> queue, int num) {
        // 此处 queue.peekLast() == num 不能进行出队
        // 否则对于 [7, 7] 这个数组，其队列元素为 [7]
        // 则在出队第一个 7 时，就会把队列元素的 7 出掉
        while (!queue.isEmpty() && queue.peekLast() < num) {
            queue.pollLast();
        }
        queue.offerLast(num);
    }

    public static int[] maxSlidingWindow(int[] nums, int k) {
        if (nums.length == 0) {
            return new int[0];
        }
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((c1, c2) -> c2 - c1);
        for (int i = 0; i < k; i++) {
            maxHeap.offer(nums[i]);
        }
        int[] ans = new int[nums.length - k + 1];
        ans[0] = maxHeap.peek();
        int pos = 1;
        for (int i = k; i < nums.length; i++) {
            int out = nums[i - k];
            maxHeap.remove(out);
            maxHeap.offer(nums[i]);
            ans[pos++] = maxHeap.peek();
        }
        return ans;
    }

}

/*
* 给定一个数组 nums 和滑动窗口的大小 k，请找出所有滑动窗口里的最大值。

示例:

输入: nums = [1,3,-1,-3,5,3,6,7], 和 k = 3
输出: [3,3,5,5,6,7] 
解释: 

  滑动窗口的位置                最大值
---------------               -----
[1  3  -1] -3  5  3  6  7       3
 1 [3  -1  -3] 5  3  6  7       3
 1  3 [-1  -3  5] 3  6  7       5
 1  3  -1 [-3  5  3] 6  7       5
 1  3  -1  -3 [5  3  6] 7       6
 1  3  -1  -3  5 [3  6  7]      7


提示：

你可以假设 k 总是有效的，在输入数组不为空的情况下，1 ≤ k ≤输入数组的大小。

注意：本题与主站 239 题相同：https://leetcode-cn.com/problems/sliding-window-maximum/

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/hua-dong-chuang-kou-de-zui-da-zhi-lcof
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */
