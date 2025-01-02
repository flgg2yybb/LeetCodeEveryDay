package main

import "math"

type Stack[T any] struct {
	elements []T
}

func NewStack[T any]() *Stack[T] {
	return &Stack[T]{elements: make([]T, 0)}
}

func (s *Stack[T]) Push(e T) {
	s.elements = append(s.elements, e)
}

func (s *Stack[T]) Pop() T {
	e := s.elements[len(s.elements)-1]
	s.elements = s.elements[:len(s.elements)-1]
	return e
}

func (s *Stack[T]) Peek() T {
	return s.elements[len(s.elements)-1]
}

func (s *Stack[T]) IsEmpty() bool {
	return len(s.elements) == 0
}

// Monotone Stack, time: O(n), space: O(n)
// 思路：维护一个单调递减栈，从左到右遍历各个点，存储每个点的索引
// 当某个点 A 的高度，大于栈顶元素对应的高度时 [height[stack.peek()] < height[A]]
// 即说明可能存在一个凹槽可以积累雨水
// 则弹出栈顶元素 B，假设弹出后，新的栈顶为 C
// 如果 height[C] > height[B]，则说明有凹槽
// 积累的雨水量为：(min(height[C], height[A]) - height[B]) * (A - C - 1)
// 持续弹出栈顶，直到栈顶元素不小于当前元素 A ，继续往右遍历，循环往复
func trap(height []int) int {
	sum := 0
	stack := NewStack[int]()
	for A := 0; A < len(height); A++ {
		for !stack.IsEmpty() && height[stack.Peek()] < height[A] {
			B := stack.Pop()
			if !stack.IsEmpty() {
				C := stack.Peek()
				// 此处 height[A], height[C] > height[B]，因此如果 B 和 C 同高，则 high = 0，相当于不积累雨水量
				high := int(math.Min(float64(height[C]), float64(height[A]))) - height[B]
				width := A - C - 1
				sum += high * width
			}
		}
		stack.Push(A)

	}
	return sum
}

// Two Pointers, time: O(n), space: O(1)
// 思路：如果可以知道每一个点左右两边的最长板，即可知道当前点可以积累多少雨水
// 因此可以使用双指针，分别从左右两边向中间靠拢，维护当前区间内的左右两边的最长板
// 又因为雨水量只和较短的长版有关，所以仅需要维护左右两边的最长板中的短版即可
// 每次移动双指针较短的那一边，逐步积累每一个点的雨水，即可得到答案
func trap1(height []int) int {
	sum := 0
	left := 0
	right := len(height) - 1
	minBound := 0
	for left < right {
		if height[left] <= height[right] {
			minBound = int(math.Max(float64(minBound), float64(height[left])))
			sum += minBound - height[left]
			left++
		} else { // height[left] > height[right]
			minBound = int(math.Max(float64(minBound), float64(height[right])))
			sum += minBound - height[right]
			right--
		}
	}
	return sum
}

func main() {
	height1 := []int{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1}
	height2 := []int{4, 2, 0, 3, 2, 5}
	println(trap(height1))
	println(trap(height2))
}

/*
给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。

示例 1：

输入：height = [0,1,0,2,1,0,1,3,2,1,2,1]
输出：6
解释：上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。
示例 2：

输入：height = [4,2,0,3,2,5]
输出：9


提示：

n == height.length
1 <= n <= 2 * 104
0 <= height[i] <= 105
*/
