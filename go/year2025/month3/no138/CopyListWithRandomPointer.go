package main

import (
	"fmt"
	"strconv"
)

func main() {
	node4 := &Node{Val: 1}
	node3 := &Node{Val: 10, Next: node4}
	node2 := &Node{Val: 11, Next: node3}
	node1 := &Node{Val: 13, Next: node2}
	head := &Node{Val: 7, Next: node1}
	head.Random = nil
	node1.Random = head
	node2.Random = node4
	node3.Random = node2
	node4.Random = head
	fmt.Println(copyRandomList(head).toString())
}

// Map，用 Map 保存原节点和新节点的映射关系，从而在构建新节点的 next 和 random 指针时，可以直接从 Map 中获取
func copyRandomList(head *Node) *Node {
	nodeMap := make(map[*Node]*Node)
	root := &Node{}
	cur := root
	// 先拷贝链表，只复制 next 节点，同时构建 map 影射关系
	for p := head; p != nil; p = p.Next {
		c := &Node{Val: p.Val}
		cur.Next = c
		cur = cur.Next
		nodeMap[p] = c
	}
	// 再次遍历链表，构建 random 指针
	for p, c := head, root.Next; p != nil; p, c = p.Next, c.Next {
		if p.Random != nil {
			c.Random = nodeMap[p.Random]
		}
	}
	return root.Next
}

type Node struct {
	Val    int
	Next   *Node
	Random *Node
}

func (n *Node) toString() string {
	if n == nil {
		return "nil"
	}
	random := "nil"
	if n.Random != nil {
		random = strconv.Itoa(n.Random.Val)
	}
	return fmt.Sprintf("{val=%d, random=%v, next=%v}", n.Val, random, n.Next.toString())
}

/*
 给你一个长度为 n 的链表，每个节点包含一个额外增加的随机指针 random ，该指针可以指向链表中的任何节点或空节点。
构造这个链表的 深拷贝。 深拷贝应该正好由 n 个 全新 节点组成，其中每个新节点的值都设为其对应的原节点的值。
新节点的 next 指针和 random 指针也都应指向复制链表中的新节点，并使原链表和复制链表中的这些指针能够表示相同的链表状态。
复制链表中的指针都不应指向原链表中的节点 。

例如，如果原链表中有 X 和 Y 两个节点，其中 X.random --> Y 。
那么在复制链表中对应的两个节点 x 和 y ，同样有 x.random --> y 。

返回复制链表的头节点。

用一个由 n 个节点组成的链表来表示输入/输出中的链表。每个节点用一个 [val, random_index] 表示：
* val：一个表示 Node.val 的整数。
* random_index：随机指针指向的节点索引（范围从 0 到 n-1）；如果不指向任何节点，则为  null 。
你的代码 只 接受原链表的头节点 head 作为传入参数。

示例 1：
输入：head = [[7,null],[13,0],[11,4],[10,2],[1,0]]
输出：[[7,null],[13,0],[11,4],[10,2],[1,0]]

示例 2：
输入：head = [[1,1],[2,1]]
输出：[[1,1],[2,1]]

示例 3：
输入：head = [[3,null],[3,0],[3,null]]
输出：[[3,null],[3,0],[3,null]]

提示：
0 <= n <= 1000
-104 <= Node.val <= 104
Node.random 为 null 或指向链表中的节点。

*/
