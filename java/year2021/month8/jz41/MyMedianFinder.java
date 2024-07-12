package year2021.month8.jz41;

import java.util.PriorityQueue;

public class MyMedianFinder {

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
     * 将数据流的数据分为两部分，前半部分用最大堆，后半部分用最小堆
     * 若为偶数时，最大堆大小等于最小堆
     * 若为奇数时，是最大堆比最小堆多一个元素
     * */

    private final PriorityQueue<Integer> maxHeap;
    private final PriorityQueue<Integer> minHeap;

    public MedianFinder() {
        maxHeap = new PriorityQueue<>((c1, c2) -> c2 - c1);
        minHeap = new PriorityQueue<>();
    }

    public void addNum(int num) {
        maxHeap.offer(num);
        Integer poll = maxHeap.poll();
        minHeap.offer(poll);
        if (maxHeap.size() != minHeap.size()) {
            maxHeap.offer(minHeap.poll());
        }
    }

    public double findMedian() {
        return maxHeap.size() == minHeap.size() ? (maxHeap.peek() + minHeap.peek()) / 2d : maxHeap.peek();
    }
}
