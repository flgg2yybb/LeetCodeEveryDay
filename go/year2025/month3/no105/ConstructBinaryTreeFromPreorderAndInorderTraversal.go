package main

import "fmt"

func main() {
	preorder := []int{3, 9, 20, 15, 7}
	inorder := []int{9, 3, 15, 20, 7}
	disp(buildTree(preorder, inorder))
}

func buildTree(preorder []int, inorder []int) *TreeNode {
	return dfsBuildTree(preorder, inorder, 0, 0, len(inorder)-1)
}

// dfs, times: O(n), space: O(n)
// 思路：前序遍历的第一个节点是根节点，中序遍历根节点左边是左子树，右边是右子树，根据该特性递归构建二叉树
func dfsBuildTree(preorder, inorder []int, preK, inLeft, inRight int) *TreeNode {
	if preK >= len(preorder) || inLeft > inRight {
		return nil
	}
	root := &TreeNode{Val: preorder[preK]}
	inRoot := -1
	for i := inLeft; i <= inRight; i++ {
		if inorder[i] == preorder[preK] {
			inRoot = i
			break
		}
	}
	root.Left = dfsBuildTree(preorder, inorder, preK+1, inLeft, inRoot-1)
	root.Right = dfsBuildTree(preorder, inorder, preK+inRoot-inLeft+1, inRoot+1, inRight)
	return root
}

type TreeNode struct {
	Val   int
	Left  *TreeNode
	Right *TreeNode
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
给定两个整数数组 preorder 和 inorder ，其中 preorder 是二叉树的先序遍历， inorder 是同一棵树的中序遍历，请构造二叉树并返回其根节点。

示例 1:
输入: preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]
输出: [3,9,20,null,null,15,7]

示例 2:
输入: preorder = [-1], inorder = [-1]
输出: [-1]

提示:
1 <= preorder.length <= 3000
inorder.length == preorder.length
-3000 <= preorder[i], inorder[i] <= 3000

preorder 和 inorder 均 无重复 元素
inorder 均出现在 preorder
preorder 保证 为二叉树的前序遍历序列
inorder 保证 为二叉树的中序遍历序列
*/
