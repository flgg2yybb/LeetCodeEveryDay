package main

import "fmt"

// MonotonousQueue => Decreasing Queue (单调递减队列)
type MonotonousQueue struct {
	queue []int
}

func NewMonotonousQueue() *MonotonousQueue {
	return &MonotonousQueue{queue: make([]int, 0)}
}

// RPush considered the slice's append method is pushing element to the end of the slice,
// so need to RPush for inQueue
func (mq *MonotonousQueue) RPush(val int) {
	// keep the elements in the queue is increasing order
	// remove the elements which are smaller than the new element
	for len(mq.queue) > 0 && mq.queue[len(mq.queue)-1] < val {
		mq.queue = mq.queue[:len(mq.queue)-1]
	}

	mq.queue = append(mq.queue, val)
}

// LPop pop element for deQueue
func (mq *MonotonousQueue) LPop(remove int) {
	if mq.GetMax() == remove {
		mq.queue = mq.queue[1:len(mq.queue)]
	}
}

func (mq *MonotonousQueue) GetMax() int {
	return mq.queue[0]
}

// Sliding Window & Monotonous Queue (单调队列), time: O(n), space: O(n)
func maxSlidingWindow(nums []int, k int) []int {
	ans := make([]int, 0)
	decrQueue := NewMonotonousQueue()
	left := 0
	right := 0
	for right = 0; right < k; right++ {
		decrQueue.RPush(nums[right])
	}
	ans = append(ans, decrQueue.GetMax())
	for ; right < len(nums); right++ {
		decrQueue.LPop(nums[left])
		decrQueue.RPush(nums[right])
		left++
		ans = append(ans, decrQueue.GetMax())
	}

	return ans
}

func main() {
	nums1 := []int{1, 3, -1, -3, 5, 3, 6, 7}
	k1 := 3
	println(fmt.Sprintf("%+v", maxSlidingWindow(nums1, k1)))
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
