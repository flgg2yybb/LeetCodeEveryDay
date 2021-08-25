package year2021.month8.no460;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;

public class MyLFUCache {

    public static void main(String[] args) {
        LFUCache lfuCache1 = new LFUCache(2);
        lfuCache1.put(1, 1);
        lfuCache1.put(2, 2);
        System.out.println(lfuCache1.get(1));
        lfuCache1.put(3, 3);
        System.out.println(lfuCache1.get(2));
        System.out.println(lfuCache1.get(3));
        lfuCache1.put(4, 4);
        System.out.println(lfuCache1.get(1));
        System.out.println(lfuCache1.get(3));
        System.out.println(lfuCache1.get(4));
        System.out.println("=============================");
        LFUCache lfuCache2 = new LFUCache(0);
        lfuCache2.put(0, 0);
        System.out.println(lfuCache2.get(0));
    }

}

class LFUCache {

    private final Map<Integer, Integer> keyValueMap;
    private final Map<Integer, Integer> keyFreqMap;
    // 需要根据 freq 找到对应的一个或多个 key
    // 在新增元素且容量已满时，需要移除 freq 最小的 key，若存在多个 key 的 freq 最小，移除最先加入的那个
    // 在某个 key 访问 get 时，需要将其 freq 增加，故**需要从原来的 freq 对应的 key 集合中删除**，且新增到 freq + 1 对应的 key 集合
    // 能同时以 O(1) 复杂度满足以上三点的，只有 LinkedHashSet，双向链表只能满足前两点，不满足在集合中 O(1) 时间复杂度随机删除 key
    private final Map<Integer, LinkedHashSet<Integer>> freqKeysMap;
    private final int capacity;
    // LFU 并不需要实时获取最小的 freq，而移除最小 freq 的 key 只会发生在 put 中，故只需在 put 方法删除最小 freq 的 key 之前，记住过之前最小的 freq 即可
    // 而对于移除掉最小 freq 的 key 的情况，必定代表 put 了一个新 key，则此时 minFreq 被初始化为 1
    private int minFreq;

    public LFUCache(int capacity) {
        this.keyValueMap = new HashMap<>();
        this.keyFreqMap = new HashMap<>();
        this.freqKeysMap = new HashMap<>();
        this.capacity = capacity;
    }

    public int get(int key) {
        if (!keyValueMap.containsKey(key)) {
            return -1;
        }
        incrFreqByKey(key);
        return keyValueMap.get(key);
    }

    private void incrFreqByKey(int key) {
        Integer originFreq = keyFreqMap.get(key);
        keyFreqMap.put(key, originFreq + 1);
        LinkedHashSet<Integer> originSet = freqKeysMap.get(originFreq);
        originSet.remove(key);
        freqKeysMap.putIfAbsent(originFreq + 1, new LinkedHashSet<>());
        freqKeysMap.get(originFreq + 1).add(key);
        if (originSet.isEmpty()) {
            freqKeysMap.remove(originFreq);
            if (originFreq == minFreq) {
                minFreq++;
            }
        }
    }

    public void put(int key, int value) {
        if (capacity == 0) {
            return;
        }
        if (keyValueMap.containsKey(key)) {
            keyValueMap.put(key, value);
            incrFreqByKey(key);
            return;
        }
        if (keyValueMap.size() == capacity) {
            // 有元素才能 remove
            removeMinFreqKey();
        }
        keyValueMap.put(key, value);
        initFreqForNewKey(key);
    }

    private void removeMinFreqKey() {
        LinkedHashSet<Integer> minFreqKeySet = freqKeysMap.get(minFreq);
        Integer lruKey = minFreqKeySet.iterator().next();   // 获取最先加入 set 的 key 值
        minFreqKeySet.remove(lruKey);
        keyValueMap.remove(lruKey);
        keyFreqMap.remove(lruKey);
        if (minFreqKeySet.isEmpty()) {
            freqKeysMap.remove(minFreq);
            // 此处不需要更新 minFreq，因为后面会新增元素，minFreq 会被初始化为 1
        }
    }

    private void initFreqForNewKey(int key) {
        keyFreqMap.put(key, 1);
        freqKeysMap.putIfAbsent(1, new LinkedHashSet<>());
        freqKeysMap.get(1).add(key);
        minFreq = 1;
    }

}

/*
* 请你为 最不经常使用（LFU）缓存算法设计并实现数据结构。

实现 LFUCache 类：

LFUCache(int capacity) - 用数据结构的容量 capacity 初始化对象
int get(int key) - 如果键存在于缓存中，则获取键的值，否则返回 -1。
void put(int key, int value) - 如果键已存在，则变更其值；如果键不存在，请插入键值对。当缓存达到其容量时，则应该在插入新项之前，使最不经常使用的项无效。
在此问题中，当存在平局（即两个或更多个键具有相同使用频率）时，应该去除 最近最久未使用 的键。

注意「项的使用次数」就是自插入该项以来对其调用 get 和 put 函数的次数之和。使用次数会在对应项被移除后置为 0 。

为了确定最不常使用的键，可以为缓存中的每个键维护一个 使用计数器 。使用计数最小的键是最久未使用的键。

当一个键首次插入到缓存中时，它的使用计数器被设置为 1 (由于 put 操作)。对缓存中的键执行 get 或 put 操作，使用计数器的值将会递增。

 

示例：

输入：
["LFUCache", "put", "put", "get", "put", "get", "get", "put", "get", "get", "get"]
[[2], [1, 1], [2, 2], [1], [3, 3], [2], [3], [4, 4], [1], [3], [4]]
输出：
[null, null, null, 1, null, -1, 3, null, -1, 3, 4]

解释：
// cnt(x) = 键 x 的使用计数
// cache=[] 将显示最后一次使用的顺序（最左边的元素是最近的）
LFUCache lFUCache = new LFUCache(2);
lFUCache.put(1, 1);   // cache=[1,_], cnt(1)=1
lFUCache.put(2, 2);   // cache=[2,1], cnt(2)=1, cnt(1)=1
lFUCache.get(1);      // 返回 1
                      // cache=[1,2], cnt(2)=1, cnt(1)=2
lFUCache.put(3, 3);   // 去除键 2 ，因为 cnt(2)=1 ，使用计数最小
                      // cache=[3,1], cnt(3)=1, cnt(1)=2
lFUCache.get(2);      // 返回 -1（未找到）
lFUCache.get(3);      // 返回 3
                      // cache=[3,1], cnt(3)=2, cnt(1)=2
lFUCache.put(4, 4);   // 去除键 1 ，1 和 3 的 cnt 相同，但 1 最久未使用
                      // cache=[4,3], cnt(4)=1, cnt(3)=2
lFUCache.get(1);      // 返回 -1（未找到）
lFUCache.get(3);      // 返回 3
                      // cache=[3,4], cnt(4)=1, cnt(3)=3
lFUCache.get(4);      // 返回 4
                      // cache=[3,4], cnt(4)=2, cnt(3)=3
 

提示：

0 <= capacity, key, value <= 104
最多调用 105 次 get 和 put 方法
 

进阶：你可以为这两种操作设计时间复杂度为 O(1) 的实现吗？

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/lfu-cache
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */