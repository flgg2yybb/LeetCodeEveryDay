package main

import "fmt"

func main() {
	root1 := &TreeNode{Val: 3,
		Left: &TreeNode{Val: 9},
		Right: &TreeNode{Val: 20,
			Left:  &TreeNode{Val: 15},
			Right: &TreeNode{Val: 7}},
	}
	root2 := &TreeNode{Val: 1}
	root3 := (*TreeNode)(nil)
	println(fmt.Sprintf("%+v", levelOrder(root1))) // [[3],[9,20],[15,7]]
	println(fmt.Sprintf("%+v", levelOrder(root2))) // [[1]]
	println(fmt.Sprintf("%+v", levelOrder(root3))) // []
}

// BFS, Time: O(n), Space: O(n)
func levelOrder(root *TreeNode) [][]int {
	ans := make([][]int, 0)
	if root == nil {
		return ans
	}
	queue := make([]*TreeNode, 0)
	queue = append(queue, root)
	for len(queue) > 0 {
		level := make([]int, 0)
		nextQueue := make([]*TreeNode, 0)
		for _, node := range queue {
			level = append(level, node.Val)
			if node.Left != nil {
				nextQueue = append(nextQueue, node.Left)
			}
			if node.Right != nil {
				nextQueue = append(nextQueue, node.Right)
			}
		}
		ans = append(ans, level)
		queue = nextQueue
	}
	return ans
}

type TreeNode struct {
	Val   int
	Left  *TreeNode
	Right *TreeNode
}

/*
 给你二叉树的根节点 root ，返回其节点值的 层序遍历 。 （即逐层地，从左到右访问所有节点）。

示例 1：
输入：root = [3,9,20,null,null,15,7]
输出：[[3],[9,20],[15,7]]

示例 2：
输入：root = [1]
输出：[[1]]

示例 3：
输入：root = []
输出：[]

提示：
树中节点数目在范围 [0, 2000] 内
-1000 <= Node.val <= 1000
*/
