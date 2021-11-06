package year2021.month11.no146;

import java.util.HashMap;
import java.util.Map;

public class MyLRUCache {

    public static void main(String[] args) {
        LRUCache lRUCache = new LRUCache(2);
        lRUCache.put(1, 1); // 缓存是 {1=1}
        lRUCache.put(2, 2); // 缓存是 {1=1, 2=2}
        System.out.println(lRUCache.get(1));    // 返回 1
        lRUCache.put(3, 3); // 该操作会使得关键字 2 作废，缓存是 {1=1, 3=3}
        System.out.println(lRUCache.get(2));    // 返回 -1 (未找到)
        lRUCache.put(4, 4); // 该操作会使得关键字 1 作废，缓存是 {4=4, 3=3}
        System.out.println(lRUCache.get(1));// 返回 -1 (未找到)
        System.out.println(lRUCache.get(3));// 返回 3
        System.out.println(lRUCache.get(4));// 返回 4
    }

}

class LRUCache {

    private final int capacity;
    private final Map<Integer, Node> map;
    private final DoubleLinkedList linkedList;


    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.map = new HashMap<>(capacity);
        linkedList = new DoubleLinkedList();
    }

    public int get(int key) {
        Node node = map.getOrDefault(key, null);
        if (node == null) {
            return -1;
        }
        linkedList.refresh(node);
        return node.value;
    }

    public void put(int key, int value) {
        if (capacity <= 0) {
            return;
        }
        if (map.containsKey(key)) {
            Node node = map.get(key);
            node.value = value;
            linkedList.refresh(node);
            return;
        }
        if (map.size() == capacity) {
            Node node = linkedList.removeOld();
            map.remove(node.key);
        }
        Node node = linkedList.addNew(new Node(key, value));
        map.put(key, node);
    }
}

class DoubleLinkedList {

    private final Node head;
    private final Node rear;

    public DoubleLinkedList() {
        this.head = new Node(-1, -1);
        this.rear = new Node(-1, -1);
        head.next = rear;
        rear.prev = head;
    }

    public Node addNew(Node node) {
        node.next = head.next;
        node.prev = head;
        head.next.prev = node;
        head.next = node;
        return node;
    }

    public Node removeOld() {
        return remove(rear.prev);
    }

    public void refresh(Node node) {
        // 必须保证 node 为同一个对象，否则会出现 refersh 后，双向链表中的 node 与 map 中保存的 node 不一致
        remove(node);
        addNew(node);
    }

    private Node remove(Node removed) {
        if (removed == head || removed == rear) {
            throw new IllegalArgumentException();
        }
        Node prev = removed.prev;
        Node next = removed.next;
        prev.next = next;
        next.prev = prev;
        return removed;
    }
}

class Node {

    int key;
    int value;
    Node next;
    Node prev;

    public Node(int key, int value) {
        this.key = key;
        this.value = value;
    }
}

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
0 <= key <= 10000
0 <= value <= 105
最多调用 2 * 105 次 get 和 put

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/lru-cache
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */
