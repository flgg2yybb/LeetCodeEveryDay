package main

func main() {
	root1 := &TreeNode{Val: 1,
		Left: &TreeNode{Val: 2,
			Left:  &TreeNode{Val: 4},
			Right: &TreeNode{Val: 5}},
		Right: &TreeNode{Val: 3},
	}
	println(diameterOfBinaryTree(root1)) // 3
	root2 := &TreeNode{Val: 1,
		Left: &TreeNode{Val: 2},
	}
	println(diameterOfBinaryTree(root2)) // 1
}

type TreeNode struct {
	Val   int
	Left  *TreeNode
	Right *TreeNode
}

/* diameterOfBinaryTree, times: O(n), space: O(h), h is the height of the tree
 * 任意两个节点之间的路径长度，一定就是某一个节点的 左子树最大深度 + 右子树最大深度
 * 写出递归代码，找出每一个节点的 左子树最大深度 + 右子树最大深度 的值，
 * 然后不断更新全局变量 res 即可
 * */
func diameterOfBinaryTree(root *TreeNode) int {
	diameter, _ := dfs(root)
	return diameter
}

// diameter 最大直径（不一定经过根节点），dfs 深度
func dfs(root *TreeNode) (diameter, depth int) {
	if root == nil {
		return 0, 0
	}
	diaL, deL := dfs(root.Left)
	diaR, deR := dfs(root.Right)
	return max(diaL, diaR, deL+deR), max(deL, deR) + 1
}

func diameterOfBinaryTree1(root *TreeNode) int {
	diameter, _ := dfs1(root)
	return diameter
}

// times: O(n), space: O(h), h is the height of the tree
// diameter 最大直径（不一定经过根节点），dfs 最大深度（经过根节点，根节点不算深度）
func dfs1(root *TreeNode) (diameter, depth int) {
	if root == nil || root.Left == nil && root.Right == nil {
		return 0, 0
	}
	diaL, deL := dfs1(root.Left)
	diaR, deR := dfs1(root.Right)
	diameter = deL + deR  // 经过当前节点的直径，一定是左子树最大边数 + 右子树最大边数 + 根节点连接左右子树的边数
	if root.Left != nil { // 左子树不为空，根节点和左子树相连，边数加一
		diameter += 1
	}
	if root.Right != nil { // 右子树不为空，根节点和右子树相连，边数加一
		diameter += 1
	}
	return max(diaL, diaR, diameter), max(deL, deR) + 1
}

/*
 给你一棵二叉树的根节点，返回该树的 直径 。

二叉树的 直径 是指树中任意两个节点之间最长路径的 长度 。这条路径可能经过也可能不经过根节点 root 。

两节点之间路径的 长度 由它们之间边数表示。



示例 1：


输入：root = [1,2,3,4,5]
输出：3
解释：3 ，取路径 [4,2,1,3] 或 [5,2,1,3] 的长度。
示例 2：

输入：root = [1,2]
输出：1


提示：

树中节点数目在范围 [1, 104] 内
-100 <= Node.val <= 100
*/
