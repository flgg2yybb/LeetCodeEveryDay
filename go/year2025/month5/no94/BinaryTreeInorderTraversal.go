package main

import "fmt"

func main() {
	root1 := &TreeNode{
		Val: 1,
		Right: &TreeNode{
			Val: 2,
			Left: &TreeNode{
				Val: 3,
			},
		},
	}
	root2 := (*TreeNode)(nil)
	root3 := &TreeNode{
		Val: 1,
	}
	println(fmt.Sprintf("%+v", inorderTraversal(root1)))
	println(fmt.Sprintf("%+v", inorderTraversal(root2)))
	println(fmt.Sprintf("%+v", inorderTraversal(root3)))
}

type TreeNode struct {
	Val   int
	Left  *TreeNode
	Right *TreeNode
}

// Stack, times: O(n), space: O(h)
func inorderTraversal(root *TreeNode) (ans []int) {
	stack := make([]*TreeNode, 0)
	p := root
	for len(stack) > 0 || p != nil {
		for p != nil {
			stack = append(stack, p)
			p = p.Left
		}
		pop := stack[len(stack)-1]
		stack = stack[:len(stack)-1]
		ans = append(ans, pop.Val)
		p = pop.Right
	}
	return ans
}

// dfs, times: O(n), space: O(h)
func inorderTraversal1(root *TreeNode) []int {
	return dfs(root, make([]int, 0))
}

func dfs(root *TreeNode, arr []int) []int {
	if root == nil {
		return arr
	}
	arr = dfs(root.Left, arr)
	arr = append(arr, root.Val)
	arr = dfs(root.Right, arr)
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
*/
