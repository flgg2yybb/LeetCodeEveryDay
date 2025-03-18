package main

func main() {
	root1 := &TreeNode{Val: 3,
		Left: &TreeNode{Val: 9},
		Right: &TreeNode{Val: 20,
			Left:  &TreeNode{Val: 15},
			Right: &TreeNode{Val: 7}}} // [3,9,20,null,null,15,7]
	println(maxDepth(root1)) // 3                                                                                                         // 3
	root2 := &TreeNode{Val: 1,
		Right: &TreeNode{Val: 2}} // [1,null,2]
	println(maxDepth(root2)) // 2                                                                                                   // 2
}

// BFS, times: O(n), space: O(l), l is the width of the binary tree
func maxDepth(root *TreeNode) int {
	if root == nil {
		return 0
	}
	queue := []*TreeNode{root}
	depth := 0
	for len(queue) > 0 {
		nextQueue := make([]*TreeNode, 0) // nextQueue 下一层的节点
		for _, node := range queue {      // queue 当前层的节点
			if node.Left != nil {
				nextQueue = append(nextQueue, node.Left)
			}
			if node.Right != nil {
				nextQueue = append(nextQueue, node.Right)
			}
		}
		depth++
		queue = nextQueue
	}
	return depth
}

type TreeNode struct {
	Val   int
	Left  *TreeNode
	Right *TreeNode
}

// Recursion, times: O(n), space: O(h), h is the height of the binary tree
func maxDepth1(root *TreeNode) int {
	if root == nil {
		return 0
	}
	return 1 + max(maxDepth(root.Left), maxDepth(root.Right))
}

/*
给定一个二叉树 root ，返回其最大深度。
二叉树的 最大深度 是指从根节点到最远叶子节点的最长路径上的节点数。

示例 1：
输入：root = [3,9,20,null,null,15,7]
输出：3

示例 2：
输入：root = [1,null,2]
输出：2


提示：
树中节点的数量在 [0, 104] 区间内。
-100 <= Node.val <= 100
*/
