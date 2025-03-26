package main

import "fmt"

func main() {
	preorder := []int{3, 9, 20, 15, 7}
	inorder := []int{9, 3, 15, 20, 7}
	disp(buildTree(preorder, inorder))
}

// Stack, times: O(n), space: O(n)
// 思路：根据前序遍历来构建树，用中序遍历来判断左右子树
// 栈里存的是前序遍历的节点，每次弹出栈顶元素，判断中序遍历的值是否等于栈顶元素的值，
// * 如果不等于，说明当前栈顶元素的左子树还没遍历完，继续构建左子树
// * 如果等于，说明当前栈顶元素的左子树遍历完了，继续构建右子树
func buildTree(preorder []int, inorder []int) *TreeNode {
	if len(preorder) == 0 {
		return nil
	}
	root := &TreeNode{Val: preorder[0]}
	stack := make([]*TreeNode, 0)
	stack = append(stack, root)
	inIndex := 0 // 当前中序遍历的索引
	// preorder => [root, left, right], inorder => [left, root, right]
	// stack => [root, left, right]
	for i := 1; i < len(preorder); i++ {
		lastNode := stack[len(stack)-1]
		if lastNode.Val != inorder[inIndex] { // 当前栈顶元素不等于中序遍历的值，说明当前栈顶元素的左子树还没遍历完
			lastNode.Left = &TreeNode{Val: preorder[i]}
			stack = append(stack, lastNode.Left)
		} else {
			// 当前栈顶元素等于中序遍历的值，说明当前栈顶元素的左子树遍历完了
			for len(stack) != 0 && stack[len(stack)-1].Val == inorder[inIndex] {
				lastNode = stack[len(stack)-1]
				stack = stack[:len(stack)-1]
				inIndex++
			}
			// 当前栈顶元素不等于中序遍历的值，说明中序遍历的值为当前栈顶元素的右子树
			lastNode.Right = &TreeNode{Val: preorder[i]}
			stack = append(stack, lastNode.Right)
		}
	}
	return root
}

func buildTree1(preorder []int, inorder []int) *TreeNode {
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
