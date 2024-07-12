package year2021.month6.no341;

import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

interface NestedInteger {

    // @return true if this NestedInteger holds a single integer, rather than a nested list.
    boolean isInteger();

    // @return the single integer that this NestedInteger holds, if it holds a single integer
    // Return null if this NestedInteger holds a nested list
    Integer getInteger();

    // @return the nested list that this NestedInteger holds, if it holds a nested list
    // Return empty list if this NestedInteger holds a single integer
    List<NestedInteger> getList();
}

public class FlattenNestedListIterator {

    public static void main(String[] args) {

    }

}

class NestedIterator implements Iterator<Integer> {

    private final List<Integer> flattenList;
    private final Iterator<Integer> it;

    public NestedIterator(List<NestedInteger> nestedList) {
        flattenList = new LinkedList<>();
        flattenNestedList1(nestedList);
        it = flattenList.iterator();
    }

    private void flattenNestedList1(List<NestedInteger> nestedList) {
        //Stack
        Deque<Iterator<NestedInteger>> stack = new LinkedList<>();
        stack.push(nestedList.iterator());
        while (!stack.isEmpty()) {
            Iterator<NestedInteger> top = stack.peek();
            if (!top.hasNext()) {
                stack.pop();
                continue;
            }
            NestedInteger next = top.next();
            if (next.isInteger()) {
                flattenList.add(next.getInteger());
            } else {
                stack.push(next.getList().iterator());
            }
        }
    }

    private void flattenNestedList(List<NestedInteger> nestedList) {
        //Recursion
        for (NestedInteger cur : nestedList) {
            if (cur.isInteger()) {
                flattenList.add(cur.getInteger());
            } else {
                flattenNestedList(cur.getList());
            }
        }
    }

    @Override
    public Integer next() {
        return it.next();
    }

    @Override
    public boolean hasNext() {
        return it.hasNext();
    }
}

/*
* 给你一个嵌套的整型列表。请你设计一个迭代器，使其能够遍历这个整型列表中的所有整数。

列表中的每一项或者为一个整数，或者是另一个列表。其中列表的元素也可能是整数或是其他列表。

 

示例 1:

输入: [[1,1],2,[1,1]]
输出: [1,1,2,1,1]
解释: 通过重复调用 next 直到 hasNext 返回 false，next 返回的元素的顺序应该是: [1,1,2,1,1]。
示例 2:

输入: [1,[4,[6]]]
输出: [1,4,6]
解释: 通过重复调用 next 直到 hasNext 返回 false，next 返回的元素的顺序应该是: [1,4,6]。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/flatten-nested-list-iterator
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */
