package main

func main() {
	root1 := &TreeNode{Val: 3,
		Left: &TreeNode{Val: 5,
			Left: &TreeNode{Val: 6},
			Right: &TreeNode{Val: 2,
				Left:  &TreeNode{Val: 7},
				Right: &TreeNode{Val: 4}}},
		Right: &TreeNode{Val: 1,
			Left:  &TreeNode{Val: 0},
			Right: &TreeNode{Val: 8}},
	} // [3,5,1,6,2,0,8,null,null,7,4]
	println(lowestCommonAncestor(root1, &TreeNode{Val: 5}, &TreeNode{Val: 1}).Val) // 3
	root2 := root1
	println(lowestCommonAncestor(root2, &TreeNode{Val: 5}, &TreeNode{Val: 4}).Val) // 5
	root3 := &TreeNode{Val: 1,
		Left: &TreeNode{Val: 2},
	} // [1,2]
	println(lowestCommonAncestor(root3, &TreeNode{Val: 1}, &TreeNode{Val: 2}).Val) // 1
}

// dfs, time: O(n), space: O(n)
// 返回值：表示是否包含 p 或 q
func lowestCommonAncestor(root, p, q *TreeNode) *TreeNode {
	if root == nil || root.Val == p.Val || root.Val == q.Val {
		return root
	}
	// 采用后序遍历，第一个 left 和 right 都包含 p 或 q 的节点就是最近公共祖先
	left := lowestCommonAncestor(root.Left, p, q)
	right := lowestCommonAncestor(root.Right, p, q)
	if left != nil && right != nil {
		return root
	}
	if left != nil {
		return left
	}
	return right
}

// dfs, time: O(n), space: O(n)
func lowestCommonAncestor1(root, p, q *TreeNode) *TreeNode {
	res, _ := dfs(root, p, q)
	return res
}

// 返回值：第一个参数是最近公共祖先，第二个当前 root 以及子树是否包含 p 或 q
func dfs(root *TreeNode, p *TreeNode, q *TreeNode) (*TreeNode, bool) {
	exist := false
	if root == nil {
		return nil, false
	}
	// 如果左子树或者右子树已经包含最近公共组先，则直接返回
	lNode, lExist := dfs(root.Left, p, q)
	if lNode != nil {
		return lNode, true
	}
	rNode, rExist := dfs(root.Right, p, q)
	if rNode != nil {
		return rNode, true
	}
	// 如果左右子树分别包含 p 和 q，则当前 root 就是最近公共祖先
	if lExist && rExist {
		return root, true
	}
	// 如果当前 root 是 p 或 q，且左右子树包含 p 或 q，则当前 root 就是最近公共祖先
	if (root.Val == p.Val || root.Val == q.Val) && (lExist || rExist) {
		return root, true
	}
	// 如果当前 root 是 p 或 q，则当前 root 以及子树包含 p 或 q，则 root 包含 p 或 q
	if root.Val == p.Val || root.Val == q.Val || lExist || rExist {
		exist = true
	}
	return nil, exist
}

type TreeNode struct {
	Val   int
	Left  *TreeNode
	Right *TreeNode
}

/*
 给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。

百度百科中最近公共祖先的定义为：“对于有根树 T 的两个节点 p、q，最近公共祖先表示为一个节点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”

示例 1：
输入：root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
输出：3
解释：节点 5 和节点 1 的最近公共祖先是节点 3 。

示例 2：
输入：root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
输出：5
解释：节点 5 和节点 4 的最近公共祖先是节点 5 。因为根据定义最近公共祖先节点可以为节点本身。

示例 3：
输入：root = [1,2], p = 1, q = 2
输出：1


提示：
树中节点数目在范围 [2, 105] 内。
-109 <= Node.val <= 109
所有 Node.val 互不相同 。
p != q
p 和 q 均存在于给定的二叉树中。
*/
