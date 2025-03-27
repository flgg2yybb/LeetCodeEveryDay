package main

func main() {
	root1 := &TreeNode{Val: 10,
		Left: &TreeNode{
			Val: 5,
			Left: &TreeNode{
				Val: 3,
				Left: &TreeNode{
					Val: 3,
				},
				Right: &TreeNode{
					Val: -2,
				},
			},
			Right: &TreeNode{
				Val: 2,
				Right: &TreeNode{
					Val: 1,
				},
			},
		},
		Right: &TreeNode{
			Val: -3,
			Right: &TreeNode{
				Val: 11,
			},
		},
	}
	println(pathSum(root1, 8)) // 3
	root2 := &TreeNode{Val: 5,
		Left: &TreeNode{
			Val: 4,
			Left: &TreeNode{
				Val: 11,
				Left: &TreeNode{
					Val: 7,
				},
				Right: &TreeNode{
					Val: 2,
				},
			},
		},
		Right: &TreeNode{
			Val: 8,
			Left: &TreeNode{
				Val: 13,
			},
			Right: &TreeNode{
				Val: 4,
				Left: &TreeNode{
					Val: 5,
				},
				Right: &TreeNode{
					Val: 1,
				},
			},
		},
	}
	println(pathSum(root2, 22)) // 3
}

// Prefix-Sum, times: O(n), space: O(n)
func pathSum(root *TreeNode, targetSum int) int {
	prefixMap := make(map[int]int) // key：根节点到某个节点的路径和，value：节点个数
	prefixMap[0] = 1
	return dfs(root, targetSum, prefixMap, 0)
}

func dfs(root *TreeNode, target int, prefixMap map[int]int, curSum int) int {
	if root == nil {
		return 0
	}
	res := 0
	curSum += root.Val
	// curSum：根节点到当前节点的路径和					[	root -> ... ->		ancestor		-> ... ->	curNode	]
	// target：某个祖先节点到当前节点的路径和			  	root -> ... ->	[	ancestor 		-> ... ->	curNode]
	// curSum - target：根节点到某个祖先节点的路径和		[	root -> ... ->		ancestor	]	-> ... -> 	curNode
	if count, ok := prefixMap[curSum-target]; ok {
		res += count
	}
	if _, ok := prefixMap[curSum]; !ok {
		prefixMap[curSum] = 0
	}
	prefixMap[curSum]++
	leftRes := dfs(root.Left, target, prefixMap, curSum)
	RightRes := dfs(root.Right, target, prefixMap, curSum)
	prefixMap[curSum]-- // map 是指针需要回溯，curSum 是传值，会自动回溯
	return res + leftRes + RightRes
}

type TreeNode struct {
	Val   int
	Left  *TreeNode
	Right *TreeNode
}

/*
给定一个二叉树的根节点 root ，和一个整数 targetSum ，求该二叉树里节点值之和等于 targetSum 的 路径 的数目。
路径 不需要从根节点开始，也不需要在叶子节点结束，但是路径方向必须是向下的（只能从父节点到子节点）。

示例 1：
输入：root = [10,5,-3,3,2,null,11,3,-2,null,1], targetSum = 8
输出：3
解释：和等于 8 的路径有 3 条，如图所示。

示例 2：
输入：root = [5,4,8,11,null,13,4,7,2,null,null,5,1], targetSum = 22
输出：3

提示:
二叉树的节点个数的范围是 [0,1000]
-109 <= Node.val <= 109
-1000 <= targetSum <= 1000
*/
