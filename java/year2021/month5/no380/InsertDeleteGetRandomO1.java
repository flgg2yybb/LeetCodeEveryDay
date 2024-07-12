package year2021.month5.no380;

import java.util.*;

public class InsertDeleteGetRandomO1 {
    public static void main(String[] args) {
        RandomizedSet randomSet1 = new RandomizedSet();
        System.out.println(randomSet1.insert(0));
        System.out.println(randomSet1.insert(1));
        System.out.println(randomSet1.remove(0));
        System.out.println(randomSet1.insert(2));
        System.out.println(randomSet1.remove(1));
        System.out.println(randomSet1.getRandom());
    }
}

class RandomizedSet {

    /**
     * Initialize your data structure here.
     */

    private final Map<Integer, Integer> indexMap;
    private final List<Integer> valueList;
    private final Random random;

    public RandomizedSet() {
        this.indexMap = new HashMap<>();
        this.valueList = new ArrayList<>();
        random = new Random();
    }

    /**
     * Inserts a value to the set. Returns true if the set did not already contain the specified element.
     */
    public boolean insert(int val) {
        // map 记录 值与索引的关系，list 记录 值
        if (indexMap.containsKey(val)) {
            return false;
        }
        indexMap.put(val, valueList.size());
        // 在 ArrayList 不发生扩容的情况下时间为 O(1)，扩容则 O(n)，n为当前 list 元素数量
        valueList.add(val);
        return true;
    }

    /**
     * Removes a value from the set. Returns true if the set contained the specified element.
     */
    public boolean remove(int val) {
        //先从 map 中找到要 remove 的值的索引， 将其与 list 中最后一个元素交换，再删除
        if (!indexMap.containsKey(val)) {
            return false;
        }
        int index = indexMap.get(val);
        int lastIndex = valueList.size() - 1;
        int lastElement = valueList.get(lastIndex);
        indexMap.put(lastElement, index);
        Collections.swap(valueList, index, lastIndex);
        // 删除 ArrayList 最后一个节点时间为 O(1)
        valueList.remove(lastIndex);
        indexMap.remove(val);
        return true;
    }

    /**
     * Get a random element from the set.
     */
    public int getRandom() {
        // 取随机值通过 list 的索引进行 O(1) 访问
        int randomIndex = random.nextInt(valueList.size());
        return valueList.get(randomIndex);
    }
}

/*
 * 设计一个支持在平均 时间复杂度 O(1) 下，执行以下操作的数据结构。

insert(val)：当元素 val 不存在时，向集合中插入该项。
remove(val)：元素 val 存在时，从集合中移除该项。
getRandom：随机返回现有集合中的一项。每个元素应该有相同的概率被返回。
示例 :

// 初始化一个空的集合。
RandomizedSet randomSet = new RandomizedSet();

// 向集合中插入 1 。返回 true 表示 1 被成功地插入。
randomSet.insert(1);

// 返回 false ，表示集合中不存在 2 。
randomSet.remove(2);

// 向集合中插入 2 。返回 true 。集合现在包含 [1,2] 。
randomSet.insert(2);

// getRandom 应随机返回 1 或 2 。
randomSet.getRandom();

// 从集合中移除 1 ，返回 true 。集合现在包含 [2] 。
randomSet.remove(1);

// 2 已在集合中，所以返回 false 。
randomSet.insert(2);

// 由于 2 是集合中唯一的数字，getRandom 总是返回 2 。
randomSet.getRandom();

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/insert-delete-getrandom-o1
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */
