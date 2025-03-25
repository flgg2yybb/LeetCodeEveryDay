package main

import (
	"slices"
)

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
	root2 := &TreeNode{
		Val: 1,
		Left: &TreeNode{
			Val:  2,
			Left: &TreeNode{Val: 4},
			Right: &TreeNode{Val: 5,
				Left:  &TreeNode{Val: 6},
				Right: &TreeNode{Val: 7},
			},
		},
		Right: &TreeNode{
			Val: 3,
			Right: &TreeNode{Val: 8,
				Left: &TreeNode{Val: 9},
			},
		},
	}
	root3 := (*TreeNode)(nil)
	root4 := &TreeNode{Val: 1}
	println(slices.Equal(preorderTraversal(root1), recursive(root1)))
	println(slices.Equal(preorderTraversal(root2), recursive(root2)))
	println(slices.Equal(preorderTraversal(root3), recursive(root3)))
	println(slices.Equal(preorderTraversal(root4), recursive(root4)))
}

// PreOrder Traversal - Iteration, times: O(n), space: O(n)
func preorderTraversal(root *TreeNode) []int {
	ans := make([]int, 0)
	stack := make([]*TreeNode, 0)
	p := root
	for len(stack) > 0 || p != nil {
		for p != nil {
			stack = append(stack, p)
			ans = append(ans, p.Val) // 根
			p = p.Left               // 左
		}
		pop := stack[len(stack)-1]
		stack = stack[:len(stack)-1]
		p = pop.Right // 右
	}
	return ans
}

func recursive(root *TreeNode) []int {
	ans := make([]int, 0)
	dfs(root, &ans)
	return ans
}

func dfs(root *TreeNode, ans *[]int) {
	if root == nil {
		return
	}
	*ans = append(*ans, root.Val)
	dfs(root.Left, ans)
	dfs(root.Right, ans)
}

type TreeNode struct {
	Val   int
	Left  *TreeNode
	Right *TreeNode
}

/*
给你二叉树的根节点 root ，返回它节点值的 前序 遍历。

示例 1：
输入：root = [1,null,2,3]
输出：[1,2,3]

示例 2：
输入：root = [1,2,3,4,5,null,8,null,null,6,7,9]
输出：[1,2,4,5,6,7,3,8,9]

示例 3：
输入：root = []
输出：[]

示例 4：
输入：root = [1]
输出：[1]

提示：
树中节点数目在范围 [0, 100] 内
-100 <= Node.val <= 100

进阶：递归算法很简单，你可以通过迭代算法完成吗？
*/
