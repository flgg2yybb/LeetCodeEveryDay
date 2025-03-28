package main

func main() {
	root1 := &TreeNode{
		Val: 1,
		Left: &TreeNode{
			Val: 2,
		},
		Right: &TreeNode{
			Val: 3,
		},
	}
	println(maxPathSum(root1)) //6
	root2 := &TreeNode{
		Val: -10,
		Left: &TreeNode{
			Val: 9,
		},
		Right: &TreeNode{
			Val: 20,
			Left: &TreeNode{
				Val: 15,
			},
			Right: &TreeNode{
				Val: 7,
			},
		},
	}
	println(maxPathSum(root2)) //42
	root3 := &TreeNode{Val: -3}
	println(maxPathSum(root3)) //-3
	root4 := &TreeNode{Val: 1,
		Left: &TreeNode{Val: -2,
			Left:  &TreeNode{Val: 1},
			Right: &TreeNode{Val: 3},
		},
		Right: &TreeNode{Val: -3,
			Right: &TreeNode{Val: -1},
		},
	}
	println(maxPathSum(root4)) //3
}

// dfs, times: O(n), space: O(n)
func maxPathSum(root *TreeNode) int {
	cMax, _ := dfs(root)
	return cMax
}

// 返回值：
//   - finalMax 不包含根节点的最大路径和 - 已经是最大路径，不可再与 parent 连接
//   - rootMax 包含根节点的最大路径和 - 可能与 parent 连接
func dfs(root *TreeNode) (finalMax int, rootMax int) {
	if root == nil {
		return -9999, -9999
	}
	lf, lr := dfs(root.Left)
	rf, rr := dfs(root.Right)
	// 三种情况： 1. 左子树的路径最大 ; 2. 右子树的路径最大; 3. 以 root 为根节点的路径最大
	finalMax = max(lf, rf, root.Val, root.Val+lr, root.Val+rr, root.Val+lr+rr)
	rootMax = max(root.Val, root.Val+lr, root.Val+rr) // 一定要带上 root 才能再给 parent 连接
	return
}

type TreeNode struct {
	Val   int
	Left  *TreeNode
	Right *TreeNode
}

/*
二叉树中的 路径 被定义为一条节点序列，序列中每对相邻节点之间都存在一条边。
同一个节点在一条路径序列中 至多出现一次 。该路径 至少包含一个 节点，且不一定经过根节点。

路径和 是路径中各节点值的总和。
给你一个二叉树的根节点 root ，返回其 最大路径和 。

示例 1：
输入：root = [1,2,3]
输出：6
解释：最优路径是 2 -> 1 -> 3 ，路径和为 2 + 1 + 3 = 6

示例 2：
输入：root = [-10,9,20,null,null,15,7]
输出：42
解释：最优路径是 15 -> 20 -> 7 ，路径和为 15 + 20 + 7 = 42


提示：
树中节点数目范围是 [1, 3 * 104]
-1000 <= Node.val <= 1000
*/
