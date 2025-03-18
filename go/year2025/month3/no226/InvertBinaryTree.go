package main

func main() {
	root1 := &TreeNode{Val: 4,
		Left: &TreeNode{Val: 2,
			Left:  &TreeNode{Val: 1},
			Right: &TreeNode{Val: 3}},
		Right: &TreeNode{Val: 7,
			Left:  &TreeNode{Val: 6},
			Right: &TreeNode{Val: 9}},
	} // [4,2,7,1,3,6,9]
	bfs(invertTree(root1))
	root2 := &TreeNode{Val: 2,
		Left:  &TreeNode{Val: 1},
		Right: &TreeNode{Val: 3},
	} // [2,1,3]
	bfs(invertTree(root2))
	root3 := (*TreeNode)(nil) // []
	bfs(invertTree(root3))
}

func bfs(root *TreeNode) {
	if root == nil {
		return
	}
	queue := []*TreeNode{root}
	for len(queue) > 0 {
		nextQueue := make([]*TreeNode, 0) // nextQueue 下一层的节点
		for _, node := range queue {      // queue 当前层的节点
			print(node.Val, " ")
			if node.Left != nil {
				nextQueue = append(nextQueue, node.Left)
			}
			if node.Right != nil {
				nextQueue = append(nextQueue, node.Right)
			}
		}
		queue = nextQueue
	}
	println()
}

type TreeNode struct {
	Val   int
	Left  *TreeNode
	Right *TreeNode
}

func invertTree(root *TreeNode) *TreeNode {
	if root == nil {
		return nil
	}
	root.Left, root.Right = invertTree(root.Right), invertTree(root.Left)
	return root
}

/*
给你一棵二叉树的根节点 root ，翻转这棵二叉树，并返回其根节点。

示例 1：
输入：root = [4,2,7,1,3,6,9]
输出：[4,7,2,9,6,3,1]

示例 2：
输入：root = [2,1,3]
输出：[2,3,1]
示例 3：

输入：root = []
输出：[]

提示：
树中节点数目范围在 [0, 100] 内
-100 <= Node.val <= 100
*/
