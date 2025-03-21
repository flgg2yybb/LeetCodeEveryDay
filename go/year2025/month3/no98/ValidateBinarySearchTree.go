package main

import "math"

func main() {
	root1 := &TreeNode{Val: 2,
		Left:  &TreeNode{Val: 1},
		Right: &TreeNode{Val: 3},
	}
	root2 := &TreeNode{Val: 5,
		Left: &TreeNode{Val: 1},
		Right: &TreeNode{Val: 4,
			Left:  &TreeNode{Val: 3},
			Right: &TreeNode{Val: 6}},
	}
	root3 := &TreeNode{Val: 5,
		Left: &TreeNode{Val: 1},
		Right: &TreeNode{Val: 4,
			Left:  &TreeNode{Val: 3},
			Right: &TreeNode{Val: 6}},
	}
	root4 := &TreeNode{Val: 0}
	println(isValidBST(root1)) // true
	println(isValidBST(root2)) // false
	println(isValidBST(root3)) // false
	println(isValidBST(root4)) // true

}

type TreeNode struct {
	Val   int
	Left  *TreeNode
	Right *TreeNode
}

var pre *TreeNode

// dfs, Time: O(n), Space: O(h)
func isValidBST(root *TreeNode) bool {
	pre = nil
	return inorder(root)
}

func inorder(root *TreeNode) bool {
	if root == nil {
		return true
	}
	if !inorder(root.Left) {
		return false
	}
	if pre != nil && root.Val <= pre.Val {
		return false
	}
	pre = root
	return inorder(root.Right)
}

// dfs, Time: O(n), Space: O(h)
func isValidBST1(root *TreeNode) bool {
	return isBST(root, math.MaxInt64, math.MinInt64)
}

func isBST(root *TreeNode, max, min int64) bool {
	if root == nil {
		return true
	}
	if int64(root.Val) >= max || int64(root.Val) <= min {
		return false
	}
	return isBST(root.Left, int64(root.Val), min) && isBST(root.Right, max, int64(root.Val))
}

/*
 给你一个二叉树的根节点 root ，判断其是否是一个有效的二叉搜索树。

有效 二叉搜索树定义如下：

节点的左子树只包含 小于 当前节点的数。
节点的右子树只包含 大于 当前节点的数。
所有左子树和右子树自身必须也是二叉搜索树。


示例 1：


输入：root = [2,1,3]
输出：true
示例 2：


输入：root = [5,1,4,null,null,3,6]
输出：false
解释：根节点的值是 5 ，但是右子节点的值是 4 。

[5,4,6,null,null,3,7]


提示：

树中节点数目范围在[1, 104] 内
-231 <= Node.val <= 231 - 1
*/
