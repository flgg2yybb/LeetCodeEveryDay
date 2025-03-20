package main

func main() {
	root1 := &TreeNode{Val: 1,
		Left: &TreeNode{Val: 2,
			Left:  &TreeNode{Val: 3},
			Right: &TreeNode{Val: 4}},
		Right: &TreeNode{Val: 2,
			Left:  &TreeNode{Val: 4},
			Right: &TreeNode{Val: 3},
		},
	}
	println(isSymmetric(root1)) // true
	root2 := &TreeNode{Val: 1,
		Left: &TreeNode{Val: 2,
			Right: &TreeNode{Val: 3}},
		Right: &TreeNode{Val: 2,
			Right: &TreeNode{Val: 3},
		},
	}
	println(isSymmetric(root2)) // false
}

type TreeNode struct {
	Val   int
	Left  *TreeNode
	Right *TreeNode
}

// Iteration, times: O(n), space: O(n)
func isSymmetric(root *TreeNode) bool {
	if root == nil {
		return true
	}
	queue := []*TreeNode{root.Left, root.Right}
	for len(queue) > 0 {
		left, right := queue[0], queue[1]
		queue = queue[2:]
		if left == nil && right == nil {
			continue
		}
		if left != nil && right != nil && left.Val == right.Val {
			queue = append(queue, left.Left, right.Right, left.Right, right.Left)
			continue
		}
		return false
	}
	return true
}

// Recursive times: O(n), space: O(h), h is the height of the tree
func isSymmetric1(root *TreeNode) bool {
	if root == nil {
		return true
	}
	return dfs(root.Left, root.Right)
}

func dfs(left *TreeNode, right *TreeNode) bool {
	if left == nil && right == nil {
		return true

	}
	if left != nil && right != nil && left.Val == right.Val {
		return dfs(left.Left, right.Right) && dfs(left.Right, right.Left)
	}
	return false
}

/*
给你一个二叉树的根节点 root ， 检查它是否轴对称。

示例 1：
输入：root = [1,2,2,3,4,4,3]
输出：true

示例 2：
输入：root = [1,2,2,null,3,null,3]
输出：false

提示：
树中节点数目在范围 [1, 1000] 内
-100 <= Node.val <= 100

进阶：你可以运用递归和迭代两种方法解决这个问题吗？
*/
