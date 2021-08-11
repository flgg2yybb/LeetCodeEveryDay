package year2021.month8.jz59b;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

public class MyMaxQueue {

    public static void main(String[] args) {
        MaxQueue maxQueue = new MaxQueue();
        maxQueue.push_back(1);
        System.out.println(maxQueue.max_value()); //1
        maxQueue.push_back(3);
        System.out.println(maxQueue.max_value()); //3
        maxQueue.push_back(2);
        System.out.println(maxQueue.max_value()); //3
        maxQueue.pop_front();
        System.out.println(maxQueue.max_value()); //3
        maxQueue.pop_front();
        System.out.println(maxQueue.max_value()); //2
    }

}

class MaxQueue {

    private final Queue<Integer> queue;
    private final Deque<Integer> maxQueue;  // 单调不减队列

    public MaxQueue() {
        queue = new LinkedList<>();
        maxQueue = new LinkedList<>();
    }

    public int max_value() {
        if (queue.isEmpty()) {
            return -1;
        }
        return maxQueue.peekFirst();
    }

    public void push_back(int value) {
        queue.offer(value);
        while (!maxQueue.isEmpty() && maxQueue.peekLast() < value) {
            maxQueue.pollLast();
        }
        maxQueue.offerLast(value);
    }

    public int pop_front() {
        if (queue.isEmpty()) {
            return -1;
        }
        Integer poll = queue.poll();
        if (poll.equals(maxQueue.peekFirst())) {
            maxQueue.pollFirst();
        }
        return poll;
    }
}


/*
* 请定义一个队列并实现函数 max_value 得到队列里的最大值，要求函数max_value、push_back 和 pop_front 的均摊时间复杂度都是O(1)。

若队列为空，pop_front 和 max_value 需要返回 -1

示例 1：

输入: 
["MaxQueue","push_back","push_back","max_value","pop_front","max_value"]
[[],[1],[2],[],[],[]]
输出: [null,null,null,2,1,2]
示例 2：

输入: 
["MaxQueue","pop_front","max_value"]
[[],[],[]]
输出: [null,-1,-1]
 

限制：

1 <= push_back,pop_front,max_value的总操作数 <= 10000
1 <= value <= 10^5

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/dui-lie-de-zui-da-zhi-lcof
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */
