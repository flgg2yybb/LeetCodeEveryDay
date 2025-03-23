package main

import (
	"fmt"
)

func main() {
	root1 := &TreeNode{
		Val: 1,
		Left: &TreeNode{
			Val: 2,
			Left: &TreeNode{
				Val: 3,
			},
			Right: &TreeNode{
				Val: 4,
			},
		},
		Right: &TreeNode{
			Val: 5,
			Right: &TreeNode{
				Val: 6,
			},
		},
	}
	root2 := (*TreeNode)(nil)
	root3 := &TreeNode{Val: 0}
	flatten(root1)
	flatten(root2)
	flatten(root3)
	disp(root1)
	disp(root2)
	disp(root3)
}

type TreeNode struct {
	Val   int
	Left  *TreeNode
	Right *TreeNode
}

// 原地替换 - 记录前驱, times: O(n), space: O(1)
// 思路：每个节点遍历时，如果存在左子树，那么需要找到左子树前序遍历的最后一个节点，实际上就是左子树最右节点，即为当前节点右子树的前驱
func flatten(root *TreeNode) {
	p := root
	for p != nil {
		if p.Left == nil { // 无左子树
			p = p.Right
			continue
		}
		// 有左子树，需要调整到右边
		left, right := p.Left, p.Right // 保留原左，右子树
		prev := left                   // 前驱
		for prev.Right != nil {        // 找到最右节点，在先序遍历时，最右节点是遍历的最后一个节点
			prev = prev.Right
		}
		prev.Right = right // 将原右子树移动到最右节点的右节点
		p.Right = left
		p.Left = nil
		p = p.Right
	}
}

// 原地替换 - 反前序遍历，参考反转链表递归, times: O(n), space: O(1)
var pre *TreeNode

func flatten2(root *TreeNode) {
	pre = nil
	antiPreOrder(root)
}

func antiPreOrder(root *TreeNode) {
	if root == nil {
		return
	}
	antiPreOrder(root.Right)
	antiPreOrder(root.Left)
	// 当前 root 一定是剩余节点下按照前序遍历的最后一个节点
	root.Left = nil
	root.Right = pre
	pre = root
}

// PreOrder, times: O(n), space: O(n)
var newHead *TreeNode
var cur *TreeNode

func flatten1(root *TreeNode) {
	if root == nil {
		return
	}
	newHead = &TreeNode{}
	cur = newHead
	preOrder(root)
	// PS: root = newHead.Right 无法将新 root 传出去，因此只能更新原节点指针
	root.Left = nil
	root.Right = newHead.Right.Right
	print(root)
}

func preOrder(root *TreeNode) {
	if root == nil {
		return
	}
	cur.Right = &TreeNode{Val: root.Val}
	cur = cur.Right
	preOrder(root.Left)
	preOrder(root.Right)
}

func disp(root *TreeNode) {
	dfs(root)
	println()
}

func dfs(root *TreeNode) {
	if root == nil {
		return
	}
	print(fmt.Sprintf("%d ", root.Val))
	dfs(root.Left)
	dfs(root.Right)
}

/*
 给你二叉树的根结点 root ，请你将它展开为一个单链表：

展开后的单链表应该同样使用 TreeNode ，其中 right 子指针指向链表中下一个结点，而左子指针始终为 null 。
展开后的单链表应该与二叉树 先序遍历 顺序相同。

示例 1：
输入：root = [1,2,5,3,4,null,6]
输出：[1,null,2,null,3,null,4,null,5,null,6]

示例 2：
输入：root = []
输出：[]

示例 3：
输入：root = [0]
输出：[0]

提示：
树中结点数在范围 [0, 2000] 内
-100 <= Node.val <= 100

进阶：你可以使用原地算法（O(1) 额外空间）展开这棵树吗？
*/
