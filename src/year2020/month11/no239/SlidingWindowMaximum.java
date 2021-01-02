package year2020.month11.no239;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.stream.IntStream;

public class SlidingWindowMaximum {
    public static void main(String[] args) {
        int[] nums = new int[]{1, 3, 1, 2, 0, 5};
        int k = 3;
        Arrays.stream(maxSlidingWindow2(nums, k)).forEach(System.out::println);
    }

    private static int[] maxSlidingWindow2(int[] nums, int k) {
//        Time is O(n)
//        store the index
        Deque<Integer> indexQueue = new LinkedList<>();
        int[] result = new int[nums.length - k + 1];
        IntStream.range(0, nums.length).forEach(index -> {
            int left = Math.max(index - k + 1, 0);
            if (index >= k && !indexQueue.isEmpty() && indexQueue.peek() < left) {
//                remove the element that's out of window
                indexQueue.pollFirst();
            }
            int value = nums[index];
            while (!indexQueue.isEmpty() && nums[indexQueue.peekLast()] < value) {
//                remove all the element in queue that's smaller than current value
                indexQueue.pollLast();
            }
//            new value enter queue, and queue not exist one of element which is smaller than new value
            indexQueue.offerLast(index);
            if (index >= k - 1) {
                result[index - k + 1] = nums[indexQueue.peekFirst()];
            }
        });
        return result;
    }

    private static int[] maxSlidingWindow1(int[] nums, int k) {
//        time is O(n * log k), overtime
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>((o1, o2) -> o2 - o1);
        int left = 0;
        int right = k - 1;
        int[] result = new int[nums.length - k + 1];
        IntStream.range(left, right).forEach(index -> priorityQueue.offer(nums[index]));
        while (right < nums.length) {
            priorityQueue.offer(nums[right]);
            result[left] = priorityQueue.peek();
            right++;
            priorityQueue.remove(nums[left++]);
        }
        return result;
    }
}

/*
* 给定一个数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位。

返回滑动窗口中的最大值。

 

进阶：

你能在线性时间复杂度内解决此题吗？

 

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

1 <= nums.length <= 10^5
-10^4 <= nums[i] <= 10^4
1 <= k <= nums.length

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/sliding-window-maximum
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */