package main

import "slices"

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
	println(slices.Equal(postorderTraversal(root1), recursive(root1)))
	println(slices.Equal(postorderTraversal(root2), recursive(root2)))
	println(slices.Equal(postorderTraversal(root3), recursive(root3)))
	println(slices.Equal(postorderTraversal(root4), recursive(root4)))
}

// PostOrder Traversal - Iteration, times: O(n), space: O(n)
func postorderTraversal(root *TreeNode) []int {
	ans := make([]int, 0)
	stack := make([]*TreeNode, 0)
	p := root
	var prev *TreeNode
	for len(stack) > 0 || p != nil {
		for p != nil {
			stack = append(stack, p)
			p = p.Left // 左
		}
		top := stack[len(stack)-1]
		// 需要记录前驱，因为后序遍历是左右根，所以需要判断右子树是否访问过
		if top.Right != nil && top.Right != prev { // 有右子树，并且右子树未访问
			p = top.Right // 右
			continue
		}
		stack = stack[:len(stack)-1] // 左右子树都访问过，弹出当前栈顶元素
		ans = append(ans, top.Val)   // 根
		prev = top
	}
	return ans
}

type TreeNode struct {
	Val   int
	Left  *TreeNode
	Right *TreeNode
}

// Recursion, times: O(n), space: O(n)
func recursive(root *TreeNode) []int {
	ans := make([]int, 0)
	dfs(root, &ans)
	return ans
}

func dfs(root *TreeNode, ans *[]int) {
	if root == nil {
		return
	}
	dfs(root.Left, ans)
	dfs(root.Right, ans)
	*ans = append(*ans, root.Val)
}

/*
给你一棵二叉树的根节点 root ，返回其节点值的 后序遍历 。

示例 1：
输入：root = [1,null,2,3]
输出：[3,2,1]

示例 2：
输入：root = [1,2,3,4,5,null,8,null,null,6,7,9]
输出：[4,6,7,5,2,9,8,3,1]

示例 3：
输入：root = []
输出：[]

示例 4：
输入：root = [1]
输出：[1]

提示：
树中节点的数目在范围 [0, 100] 内
-100 <= Node.val <= 100

进阶：递归算法很简单，你可以通过迭代算法完成吗？
*/
