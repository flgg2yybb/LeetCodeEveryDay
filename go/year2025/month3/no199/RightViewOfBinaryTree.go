package main

import "fmt"

func main() {
	root1 := &TreeNode{Val: 1,
		Left: &TreeNode{Val: 2,
			Right: &TreeNode{Val: 5},
		},
		Right: &TreeNode{Val: 3,
			Right: &TreeNode{Val: 4},
		},
	}
	root2 := &TreeNode{Val: 1,
		Left: &TreeNode{Val: 2,
			Right: &TreeNode{Val: 4,
				Left: &TreeNode{Val: 5},
			},
		},
		Right: &TreeNode{Val: 3},
	}
	root3 := &TreeNode{Val: 1,
		Right: &TreeNode{Val: 3},
	}
	root4 := (*TreeNode)(nil)
	println(fmt.Sprintf("%+v", rightSideView(root1))) // [1 3 4]
	println(fmt.Sprintf("%+v", rightSideView(root2))) // [1 3 4 5]
	println(fmt.Sprintf("%+v", rightSideView(root3))) // [1 3]
	println(fmt.Sprintf("%+v", rightSideView(root4))) // []
}

// 思路：中需遍历，每一层只返回最右的节点（当前层最后一个节点）
// BST, times: O(n), space: O(n)
func rightSideView(root *TreeNode) []int {
	ans := make([]int, 0)
	if root == nil {
		return ans
	}
	queue := make([]*TreeNode, 0)
	queue = append(queue, root)
	for len(queue) > 0 {
		nextQueue := make([]*TreeNode, 0)
		for _, node := range queue {
			if node.Left != nil {
				nextQueue = append(nextQueue, node.Left)
			}
			if node.Right != nil {
				nextQueue = append(nextQueue, node.Right)
			}
		}
		ans = append(ans, queue[len(queue)-1].Val)
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
给定一个二叉树的 根节点 root，想象自己站在它的右侧，按照从顶部到底部的顺序，返回从右侧所能看到的节点值。

示例 1：
输入：root = [1,2,3,null,5,null,4]
输出：[1,3,4]
解释：

示例 2：
输入：root = [1,2,3,4,null,null,null,5]
输出：[1,3,4,5]
解释：

示例 3：
输入：root = [1,null,3]
输出：[1,3]

示例 4：
输入：root = []
输出：[]

提示:
二叉树的节点个数的范围是 [0,100]
-100 <= Node.val <= 100
*/
