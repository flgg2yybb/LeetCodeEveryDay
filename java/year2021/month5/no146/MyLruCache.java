package year2021.month5.no146;

import java.util.HashMap;
import java.util.Map;

public class MyLruCache {

    public static void main(String[] args) {
        LRUCache lRUCache = new LRUCache(2);
        lRUCache.put(1, 1);     // 缓存是 {1=1}
        lRUCache.put(2, 2);     // 缓存是 {1=1, 2=2}
        System.out.println(lRUCache.get(1));        // 返回 1
        lRUCache.put(3, 3);     // 该操作会使得关键字 2 作废，缓存是 {1=1, 3=3}
        System.out.println(lRUCache.get(2));        // 返回 -1 (未找到)
        lRUCache.put(4, 4);     // 该操作会使得关键字 1 作废，缓存是 {4=4, 3=3}
        System.out.println(lRUCache.get(1));        // 返回 -1 (未找到)
        System.out.println(lRUCache.get(3));        // 返回 3
        System.out.println(lRUCache.get(4));        // 返回 4
    }

}

class LRUCache {

    private final Map<Integer, ListNode> map;
    private final DoubleLinkedList list;
    private final int capacity;


    public LRUCache(int capacity) {
        this.map = new HashMap<>(capacity);
        this.list = new DoubleLinkedList();
        this.capacity = capacity;
    }

    public int get(int key) {
        if (!map.containsKey(key)) {
            return -1;
        }
        ListNode listNode = map.get(key);
        list.renewNode(listNode);
        return listNode.value;
    }

    public void put(int key, int value) {
        if (map.containsKey(key)) {
            ListNode listNode = map.get(key);
            listNode.value = value;
            list.renewNode(listNode);
            return;
        }
        if (capacity == map.size()) {
            ListNode listNode = list.removeLast();
            map.remove(listNode.key);
        }
        ListNode listNode = new ListNode(key, value);
        list.addFirst(listNode);
        map.put(key, listNode);
    }
}

class DoubleLinkedList {

    private final ListNode head;
    private final ListNode tail;

    public DoubleLinkedList() {
        head = new ListNode(-1, -1);
        tail = new ListNode(-1, -1);
        head.next = tail;
        tail.prev = head;
    }

    public void addFirst(ListNode node) {
        node.next = head.next;
        node.prev = head;
        head.next.prev = node;
        head.next = node;
    }

    public ListNode removeLast() {
        if (tail.prev == head) {
            throw new ArrayIndexOutOfBoundsException();
        }
        ListNode remove = tail.prev;
        removeNode(remove);
        return remove;
    }

    private void removeNode(ListNode node) {
        if (node == head || node == tail) {
            throw new ArrayIndexOutOfBoundsException();
        }
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    public void renewNode(ListNode node) {
        removeNode(node);
        addFirst(node);
    }

}

class ListNode {

    int key;
    int value;
    ListNode next;
    ListNode prev;

    public ListNode(int key, int value) {
        this.key = key;
        this.value = value;
    }

}

/**
 * Your LRUCache object will be instantiated and called as such: LRUCache obj = new LRUCache(capacity); int param_1 = obj.get(key); obj.put(key,value);
 */

/*
* 运用你所掌握的数据结构，设计和实现一个  LRU (最近最少使用) 缓存机制 。
实现 LRUCache 类：

LRUCache(int capacity) 以正整数作为容量 capacity 初始化 LRU 缓存
int get(int key) 如果关键字 key 存在于缓存中，则返回关键字的值，否则返回 -1 。
void put(int key, int value) 如果关键字已经存在，则变更其数据值；如果关键字不存在，则插入该组「关键字-值」。当缓存容量达到上限时，它应该在写入新数据之前删除最久未使用的数据值，从而为新的数据值留出空间。
 

进阶：你是否可以在 O(1) 时间复杂度内完成这两种操作？

 

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
0 <= key <= 3000
0 <= value <= 104
最多调用 3 * 104 次 get 和 put

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/lru-cache
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */
