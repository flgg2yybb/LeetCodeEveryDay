package main

import "fmt"

func main() {
	root1 := &TreeNode{Val: 1, Right: &TreeNode{Val: 2, Left: &TreeNode{Val: 3}}} // [1,null,2,3]
	println(fmt.Sprintf("%+v", inorderTraversal(root1)))                          // [1,3,2]
	root2 := (*TreeNode)(nil)                                                     // []
	println(fmt.Sprintf("%+v", inorderTraversal(root2)))                          // []
	root3 := &TreeNode{Val: 1}                                                    // [1]
	println(fmt.Sprintf("%+v", inorderTraversal(root3)))                          // [1]
}

type TreeNode struct {
	Val   int
	Left  *TreeNode
	Right *TreeNode
}

// Iteration, times: O(n), space: O(n)
func inorderTraversal(root *TreeNode) []int {
	ans := make([]int, 0)
	p := root
	stack := make([]*TreeNode, 0)
	for len(stack) > 0 || p != nil {
		for p != nil {
			stack = append(stack, p)
			p = p.Left // 左
		}
		pop := stack[len(stack)-1]
		stack = stack[0 : len(stack)-1]
		ans = append(ans, pop.Val) // 根
		p = pop.Right              // 右

	}
	return ans
}

// Iteration, times: O(n), space: O(n)
func inorderTraversal2(root *TreeNode) []int {
	ans := make([]int, 0)
	p := root
	stack := make([]*TreeNode, 0)
	for p != nil || len(stack) > 0 {
		if p != nil { // 一直往左走
			stack = append(stack, p)
			p = p.Left
		} else { // 没有左节点了，栈顶元素为上一个节点，弹出栈顶元素，读取栈顶元素值，然后遍历栈顶元素的右节点，继续往作左走
			pop := stack[len(stack)-1]
			stack = stack[0 : len(stack)-1]
			ans = append(ans, pop.Val)
			p = pop.Right
		}
	}
	return ans
}

// Recursion, times: O(n), space: O(n)
func inorderTraversal1(root *TreeNode) []int {
	return recursiveInorder([]int{}, root)
}

func recursiveInorder(arr []int, root *TreeNode) []int {
	if root == nil {
		return arr
	}
	arr = recursiveInorder(arr, root.Left)
	arr = append(arr, root.Val)
	arr = recursiveInorder(arr, root.Right)
	return arr
}

/*
给定一个二叉树的根节点 root ，返回 它的 中序 遍历 。

示例 1：
输入：root = [1,null,2,3]
输出：[1,3,2]

示例 2：
输入：root = []
输出：[]

示例 3：
输入：root = [1]
输出：[1]

提示：
树中节点数目在范围 [0, 100] 内
-100 <= Node.val <= 100

进阶: 递归算法很简单，你可以通过迭代算法完成吗？
*/
