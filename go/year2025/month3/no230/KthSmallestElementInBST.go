package main

func main() {
	root1 := &TreeNode{
		Val: 3,
		Left: &TreeNode{
			Val:   1,
			Left:  nil,
			Right: &TreeNode{Val: 2},
		},
		Right: &TreeNode{Val: 4},
	}
	println(kthSmallest(root1, 1)) // Output: 1

	root2 := &TreeNode{
		Val: 5,
		Left: &TreeNode{
			Val:   3,
			Left:  &TreeNode{Val: 2, Left: &TreeNode{Val: 1}},
			Right: &TreeNode{Val: 4},
		},
		Right: &TreeNode{Val: 6},
	}
	println(kthSmallest(root2, 3)) // Output: 3

}

// 思路：中序遍历 BST 时，元素递增，因此可以在遍历时递减 K 来找到 Kth min 元素
// times: O(k), space: O(h)
var curK = -1

func kthSmallest(root *TreeNode, k int) int {
	curK = k
	return getKthMin(root)
}

func getKthMin(root *TreeNode) int {
	if root == nil {
		return -1
	}
	res := getKthMin(root.Left)
	if res != -1 {
		return res
	}
	if curK == 1 {
		return root.Val
	}
	curK--
	return getKthMin(root.Right)
}

type TreeNode struct {
	Val   int
	Left  *TreeNode
	Right *TreeNode
}

/*
 给定一个二叉搜索树的根节点 root ，和一个整数 k ，
请你设计一个算法查找其中第 k 小的元素（从 1 开始计数）。

示例 1：
输入：root = [3,1,4,null,2], k = 1
输出：1

示例 2：
输入：root = [5,3,6,2,4,null,null,1], k = 3
输出：3

提示：
树中的节点数为 n 。
1 <= k <= n <= 104
0 <= Node.val <= 104

进阶：如果二叉搜索树经常被修改（插入/删除操作）并且你需要频繁地查找第 k 小的值，你将如何优化算法？
*/
