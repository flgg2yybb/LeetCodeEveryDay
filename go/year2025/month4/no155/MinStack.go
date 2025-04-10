package main

func main() {
	stack := Constructor()
	stack.Push(-2)
	stack.Push(0)
	stack.Push(-3)
	println(stack.GetMin()) // 返回 -3.
	stack.Pop()
	println(stack.Top())    // 返回 0.
	println(stack.GetMin()) // 返回 -2.
}

type MinStack struct {
	elements []int
	minStack []int // 单调递减栈
}

func Constructor() MinStack {
	return MinStack{
		elements: make([]int, 0),
		minStack: make([]int, 0),
	}
}

func (this *MinStack) Push(val int) {
	this.elements = append(this.elements, val)
	// 保持 minStack 单调递减
	if len(this.minStack) == 0 || this.minStack[len(this.minStack)-1] >= val {
		this.minStack = append(this.minStack, val)
	}
}

func (this *MinStack) Pop() {
	pop := this.elements[len(this.elements)-1]
	this.elements = this.elements[:len(this.elements)-1]
	// 只有弹出元素为当前最小元素，才需要对 minStack 弹出栈顶元素
	if this.minStack[len(this.minStack)-1] == pop {
		this.minStack = this.minStack[:len(this.minStack)-1]
	}
}

func (this *MinStack) Top() int {
	return this.elements[len(this.elements)-1]
}

func (this *MinStack) GetMin() int {
	return this.minStack[len(this.minStack)-1]
}

/**
 * Your MinStack object will be instantiated and called as such:
 * obj := Constructor();
 * obj.Push(val);
 * obj.Pop();
 * param_3 := obj.Top();
 * param_4 := obj.GetMin();
 */

/*
设计一个支持 push ，pop ，top 操作，并能在常数时间内检索到最小元素的栈。

实现 MinStack 类:

MinStack() 初始化堆栈对象。
void push(int val) 将元素val推入堆栈。
void pop() 删除堆栈顶部的元素。
int top() 获取堆栈顶部的元素。
int getMin() 获取堆栈中的最小元素。

示例 1:
输入：
["MinStack","push","push","push","getMin","pop","top","getMin"]
[[],[-2],[0],[-3],[],[],[],[]]
输出：
[null,null,null,null,-3,null,0,-2]
解释：
MinStack minStack = new MinStack();
minStack.push(-2);
minStack.push(0);
minStack.push(-3);
minStack.getMin();   --> 返回 -3.
minStack.pop();
minStack.top();      --> 返回 0.
minStack.getMin();   --> 返回 -2.


提示：
-231 <= val <= 231 - 1
pop、top 和 getMin 操作总是在 非空栈 上调用
push, pop, top, and getMin最多被调用 3 * 104 次
*/
