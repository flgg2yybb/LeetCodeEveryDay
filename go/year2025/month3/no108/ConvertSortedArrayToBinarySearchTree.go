package main

import "fmt"

func main() {
	nums1 := []int{-10, -3, 0, 5, 9}
	println(fmt.Sprintf("%+v", sortedArrayToBST(nums1).toString()))
	nums2 := []int{1, 3}
	println(fmt.Sprintf("%+v", sortedArrayToBST(nums2).toString()))
}

type TreeNode struct {
	Val   int
	Left  *TreeNode
	Right *TreeNode
}

func (t *TreeNode) toString() string {
	if t == nil {
		return "nil"
	}
	return fmt.Sprintf("{Val:%+v, Left:%+v, Right:%+v}", t.Val, t.Left.toString(), t.Right.toString())
}

// Divide and Conquer, times: O(n), space: O(h)
func sortedArrayToBST(nums []int) *TreeNode {
	return dfsBuildTree(nums, 0, len(nums)-1)
}

func dfsBuildTree(nums []int, left, right int) *TreeNode {
	if left > right {
		return nil
	}
	mid := (left + right) / 2
	root := &TreeNode{Val: nums[mid]}
	root.Left = dfsBuildTree(nums, left, mid-1)
	root.Right = dfsBuildTree(nums, mid+1, right)
	return root
}

/*
给你一个整数数组 nums ，其中元素已经按 升序 排列，请你将其转换为一棵 平衡 二叉搜索树。

示例 1：
输入：nums = [-10,-3,0,5,9]
输出：[0,-3,9,-10,null,5]
解释：[0,-10,5,null,-3,null,9] 也将被视为正确答案：

示例 2：
输入：nums = [1,3]
输出：[3,1]
解释：[1,null,3] 和 [3,1] 都是高度平衡二叉搜索树。

提示：
1 <= nums.length <= 104
-104 <= nums[i] <= 104
nums 按 严格递增 顺序排列
*/
