package year2021.month8.no1116;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.IntConsumer;

public class PrintZeroEvenOdd {

    public static void main(String[] args) {
        int n1 = 2;
        int n2 = 5;
        ZeroEvenOdd zeroEvenOdd = new ZeroEvenOdd(n2);
        IntConsumer print = System.out::println;
        Thread zeroThread = new Thread(() -> {
            try {
                zeroEvenOdd.zero(print);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread evenThread = new Thread(() -> {
            try {
                zeroEvenOdd.even(print);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread oddThread = new Thread(() -> {
            try {
                zeroEvenOdd.odd(print);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        evenThread.start();
        oddThread.start();
        zeroThread.start();
    }

}

class ZeroEvenOdd {

    private final int n;
    private final Object lock = new Object();
    private volatile int count = 1;

    public ZeroEvenOdd(int n) {
        this.n = n;
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void zero(IntConsumer printNumber) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            synchronized (lock) {
                while (count % 2 == 0) {
                    lock.wait();
                }
                printNumber.accept(0);
                count++;
                lock.notifyAll();
            }
        }
    }

    public void even(IntConsumer printNumber) throws InterruptedException {
        for (int i = 2; i <= n; i += 2) {
            synchronized (lock) {
                while (count % 2 == 1 || (count / 2) % 2 == 1) {
                    lock.wait();
                }
                printNumber.accept(i);
                count++;
                lock.notifyAll();
            }
        }
    }

    public void odd(IntConsumer printNumber) throws InterruptedException {
        for (int i = 1; i <= n; i += 2) {
            synchronized (lock) {
                while (count % 2 == 1 || (count / 2) % 2 == 0) {
                    lock.wait();
                }
                printNumber.accept(i);
                count++;
                lock.notifyAll();
            }
        }
    }
}

class ZeroEvenOdd2 {

    private final int n;
    private final Lock lock = new ReentrantLock();
    private final Condition conditionZero = lock.newCondition();
    private final Condition conditionOdd = lock.newCondition();
    private final Condition conditionEven = lock.newCondition();
    private volatile int count = 1;

    public ZeroEvenOdd2(int n) {
        this.n = n;
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void zero(IntConsumer printNumber) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            lock.lock();
            if (count % 2 == 0) {
                conditionZero.await();
            }
            printNumber.accept(0);
            count++;
            if ((count / 2) % 2 == 0) {
                conditionEven.signal();
            } else {
                conditionOdd.signal();
            }
            lock.unlock();
        }
    }

    public void even(IntConsumer printNumber) throws InterruptedException {
        for (int i = 2; i <= n; i += 2) {
            lock.lock();
            if (count % 2 == 1 || (count / 2) % 2 == 1) {
                conditionEven.await();
            }
            printNumber.accept(i);
            count++;
            conditionZero.signal();
            lock.unlock();
        }
    }

    public void odd(IntConsumer printNumber) throws InterruptedException {
        for (int i = 1; i <= n; i += 2) {
            lock.lock();
            if (count % 2 == 1 || (count / 2) % 2 == 0) {
                conditionOdd.await();
            }
            printNumber.accept(i);
            count++;
            conditionZero.signal();
            lock.unlock();
        }
    }
}

class ZeroEvenOdd1 {

    private final int n;
    private final AtomicInteger ai = new AtomicInteger(1);

    public ZeroEvenOdd1(int n) {
        this.n = n;
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void zero(IntConsumer printNumber) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            while (ai.get() % 2 == 0) {
                Thread.yield();
            }
            printNumber.accept(0);
            ai.incrementAndGet();
        }
    }

    public void even(IntConsumer printNumber) throws InterruptedException {
        for (int i = 2; i <= n; i += 2) {
            while (ai.get() % 2 == 1 || (ai.get() / 2) % 2 == 1) {
                Thread.yield();
            }
            printNumber.accept(i);
            ai.incrementAndGet();
        }
    }

    public void odd(IntConsumer printNumber) throws InterruptedException {
        for (int i = 1; i <= n; i += 2) {
            while (ai.get() % 2 == 1 || (ai.get() / 2) % 2 == 0) {
                Thread.yield();
            }
            printNumber.accept(i);
            ai.incrementAndGet();
        }
    }
}

/*
* 假设有这么一个类：

class ZeroEvenOdd {
  public ZeroEvenOdd(int n) { ... }      // 构造函数
  public void zero(printNumber) { ... }  // 仅打印出 0
  public void even(printNumber) { ... }  // 仅打印出 偶数
  public void odd(printNumber) { ... }   // 仅打印出 奇数
}
相同的一个 ZeroEvenOdd 类实例将会传递给三个不同的线程：

线程 A 将调用 zero()，它只输出 0 。
线程 B 将调用 even()，它只输出偶数。
线程 C 将调用 odd()，它只输出奇数。
每个线程都有一个 printNumber 方法来输出一个整数。请修改给出的代码以输出整数序列 010203040506... ，其中序列的长度必须为 2n。

 

示例 1：

输入：n = 2
输出："0102"
说明：三条线程异步执行，其中一个调用 zero()，另一个线程调用 even()，最后一个线程调用odd()。正确的输出为 "0102"。
示例 2：

输入：n = 5
输出："0102030405"

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/print-zero-even-odd
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */
