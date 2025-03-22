package main

func main() {
	lruCache := Constructor(2)
	lruCache.Put(1, 1)       // 缓存是 {1=1}
	lruCache.Put(2, 2)       // 缓存是 {1=1, 2=2}
	println(lruCache.Get(1)) // 返回 1
	lruCache.Put(3, 3)       // 该操作会使得关键字 2 作废，缓存是 {1=1, 3=3}
	println(lruCache.Get(2)) // 返回 -1 (未找到)
	lruCache.Put(4, 4)       // 该操作会使得关键字 1 作废，缓存是 {4=4, 3=3}
	println(lruCache.Get(1)) // 返回 -1 (未找到)
	println(lruCache.Get(3)) // 返回 3
	println(lruCache.Get(4)) // 返回 4
}

type DoubleListNode struct {
	Key  int
	Val  int
	Next *DoubleListNode
	Prev *DoubleListNode
}

type DoubleLinkedList struct {
	Head *DoubleListNode
	Tail *DoubleListNode
}

func (this *DoubleLinkedList) AddFirst(node *DoubleListNode) {
	node.Next = this.Head.Next
	node.Prev = this.Head
	this.Head.Next.Prev = node
	this.Head.Next = node
}

func (this *DoubleLinkedList) Remove(node *DoubleListNode) *DoubleListNode {
	node.Prev.Next = node.Next
	node.Next.Prev = node.Prev
	node.Next = nil
	node.Prev = nil
	return node
}

func (this *DoubleLinkedList) RemoveLast() *DoubleListNode {
	if this.Head.Next == this.Tail {
		panic("RemoveLast err: empty DoubleLinkedList")
	}
	return this.Remove(this.Tail.Prev)
}

func NewDoubleLinkedList() *DoubleLinkedList {
	head := &DoubleListNode{}
	tail := &DoubleListNode{}
	head.Next = tail
	tail.Prev = head
	return &DoubleLinkedList{Head: head, Tail: tail}
}

type LRUCache struct {
	Capacity         int
	Cache            map[int]*DoubleListNode
	DoubleLinkedList *DoubleLinkedList
}

func Constructor(capacity int) LRUCache {
	return LRUCache{
		Capacity:         capacity,
		Cache:            make(map[int]*DoubleListNode),
		DoubleLinkedList: NewDoubleLinkedList(),
	}
}

func (this *LRUCache) Get(key int) int {
	if node, ok := this.Cache[key]; ok { // 读取需要把元素更新到最新
		this.DoubleLinkedList.Remove(node)
		this.DoubleLinkedList.AddFirst(node)
		return node.Val
	}
	return -1
}

func (this *LRUCache) Put(key int, value int) {
	if node, ok := this.Cache[key]; ok { // 更新元素，也需要把元素更新到最新
		this.DoubleLinkedList.Remove(node)
		node.Val = value
		this.DoubleLinkedList.AddFirst(node)
		return
	}
	// 添加元素
	if len(this.Cache) >= this.Capacity { // 检查是否超出上限
		remove := this.DoubleLinkedList.RemoveLast()
		delete(this.Cache, remove.Key)
	}
	node := &DoubleListNode{Key: key, Val: value}
	this.Cache[key] = node
	this.DoubleLinkedList.AddFirst(node)
}

/*
请你设计并实现一个满足  LRU (最近最少使用) 缓存 约束的数据结构。
实现 LRUCache 类：
LRUCache(int capacity) 以 正整数 作为容量 capacity 初始化 LRU 缓存
int get(int key) 如果关键字 key 存在于缓存中，则返回关键字的值，否则返回 -1 。
void put(int key, int value) 如果关键字 key 已经存在，则变更其数据值 value ；如果不存在，则向缓存中插入该组 key-value 。如果插入操作导致关键字数量超过 capacity ，则应该 逐出 最久未使用的关键字。
函数 get 和 put 必须以 O(1) 的平均时间复杂度运行。

示例：
输入
["LRUCache", "put", "put", "get", "put", "get", "put", "get", "get", "get"]
[[2], [1, 1], [2, 2], [1], [3, 3], [2], [4, 4], [1], [3], [4]]
输出
[null, null, null, 1, null, -1, null, -1, 3, 4]

解释
LRUCache lRUCache = new LRUCache(2);
lRUCache.put(1, 1); // 缓存是 {1=1}
lRUCache.put(2, 2); // 缓存是 {1=1, 2=2}
lRUCache.get(1);    // 返回 1
lRUCache.put(3, 3); // 该操作会使得关键字 2 作废，缓存是 {1=1, 3=3}
lRUCache.get(2);    // 返回 -1 (未找到)
lRUCache.put(4, 4); // 该操作会使得关键字 1 作废，缓存是 {4=4, 3=3}
lRUCache.get(1);    // 返回 -1 (未找到)
lRUCache.get(3);    // 返回 3
lRUCache.get(4);    // 返回 4


提示：

1 <= capacity <= 3000
0 <= key <= 10000
0 <= value <= 105
最多调用 2 * 105 次 get 和 put
*/
