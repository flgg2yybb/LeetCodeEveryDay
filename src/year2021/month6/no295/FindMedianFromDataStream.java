package year2021.month6.no295;

import java.util.Comparator;
import java.util.PriorityQueue;

public class FindMedianFromDataStream {

    public static void main(String[] args) {
        MedianFinder obj = new MedianFinder();
        obj.addNum(1);
        System.out.println(obj.findMedian());   //1
        obj.addNum(3);
        System.out.println(obj.findMedian());   //2
        obj.addNum(2);
        System.out.println(obj.findMedian());   //2
    }

}

class MedianFinder {

    /*
     * 进阶问题
     * 1. 如果数据流中所有整数都在 0 到 100 范围内，你将如何优化你的算法？
     *   用一个长度为 101 的静态数组模拟桶，index 表示元素的值，value 表示当前值的元素个数
     *   插入时直接相应位置 +1 即可
     *   中位数计算则从数组前到后遍历，找到中位数即可，由于数组长度固定，可认为是 O(1) 时间复杂度
     * 2. 如果数据流中 99% 的整数都在 0 到 100 范围内，你将如何优化你的算法？
     *   由于99% 的整数都在 0 到 100 范围内，故中位数也必定在 0 到 100 范围内
     *   所以还是使用桶计数（同上），同时使用两个变量
     *       smaller 表示小于 0 的元素个数；larger 表示大于 100 的元素个数
     *   这两个变量将在查找中位数时起到作用
     * */

    /**
     * maxHeap: 存放数据流中较小的一半元素 minHeap: 存放数据流中较大的一半元素 一个数据流的元素： a, b, c, d, e, f, g, h, i, j, k, l ... |->   maxHeap    <-| |->     minHeap   <-| 则数据流元素为偶数时，中位数即为 maxHeap 的最大值与 minHeap 的最小值 的 平均数
     * 为奇数时，不妨定义 maxHeap 的个数比 minHeap 多一，则 maxHeap 的最大值即为 中位数
     */

    private final PriorityQueue<Integer> maxHeap;   // 存放数据流中较小的一半元素
    private final PriorityQueue<Integer> minHeap;   // 存放数据流中较大的一半元素

    public MedianFinder() {
        maxHeap = new PriorityQueue<>((c1, c2) -> c2 - c1);
        minHeap = new PriorityQueue<>(Comparator.comparingInt(c -> c));
    }

    public void addNum(int num) {
        // 为了保证奇数时，maxHeap 的元素数量比 minHeap 的多一
        // 每次插入元素时均先往 maxHeap 插入，随后将 maxHeap 的元素弹出比插入 minHeap（调整两个堆）
        // 若调整后 maxHeap.size() < minHeap.size()，则在需要把 minHeap 的元素弹出插入到 maxHeap
        maxHeap.offer(num);
        minHeap.offer(maxHeap.poll());
        if (maxHeap.size() < minHeap.size()) {
            maxHeap.offer(minHeap.poll());
        }
    }

    public double findMedian() {
        return maxHeap.size() > minHeap.size() ? maxHeap.peek() : (maxHeap.peek() + minHeap.peek()) / 2.0;
    }
}

/*
* 中位数是有序列表中间的数。如果列表长度是偶数，中位数则是中间两个数的平均值。

例如，

[2,3,4] 的中位数是 3

[2,3] 的中位数是 (2 + 3) / 2 = 2.5

设计一个支持以下两种操作的数据结构：

void addNum(int num) - 从数据流中添加一个整数到数据结构中。
double findMedian() - 返回目前所有元素的中位数。
示例：

addNum(1)
addNum(2)
findMedian() -> 1.5
addNum(3) 
findMedian() -> 2
进阶:

如果数据流中所有整数都在 0 到 100 范围内，你将如何优化你的算法？
如果数据流中 99% 的整数都在 0 到 100 范围内，你将如何优化你的算法？

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/find-median-from-data-stream
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */
