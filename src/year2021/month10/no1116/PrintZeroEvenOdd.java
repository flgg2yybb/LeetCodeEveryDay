package year2021.month10.no1116;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.IntConsumer;

public class PrintZeroEvenOdd {

    public static void main(String[] args) {
        IntConsumer printZero = System.out::println;
        ZeroEvenOdd zeroEvenOdd = new ZeroEvenOdd(5);
        new Thread(() -> {
            try {
                zeroEvenOdd.even(printZero);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> {
            try {
                zeroEvenOdd.odd(printZero);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> {
            try {
                zeroEvenOdd.zero(printZero);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

}

class ZeroEvenOdd {

    private final Lock lock = new ReentrantLock();
    private final Condition condition0 = lock.newCondition();
    private final Condition condition1 = lock.newCondition();
    private final Condition condition2 = lock.newCondition();
    private final int n;
    private int job = 0;

    public ZeroEvenOdd(int n) {
        this.n = n;
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void zero(IntConsumer printNumber) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            lock.lock();
            try {
                if (job != 0) {
                    condition0.await();
                }
                printNumber.accept(0);
                if ((i & 1) == 0) {
                    job = 1;
                    condition1.signal();
                } else {
                    job = 2;
                    condition2.signal();
                }
            } finally {
                lock.unlock();
            }
        }
    }

    public void even(IntConsumer printNumber) throws InterruptedException {
        for (int i = 2; i <= n; i += 2) {
            lock.lock();
            try {
                if (job != 2) {
                    condition2.await();
                }
                printNumber.accept(i);
                job = 0;
                condition0.signal();
            } finally {
                lock.unlock();
            }
        }
    }

    public void odd(IntConsumer printNumber) throws InterruptedException {
        for (int i = 1; i <= n; i += 2) {
            lock.lock();
            try {
                if (job != 1) {
                    condition1.await();
                }
                printNumber.accept(i);
                job = 0;
                condition0.signal();
            } finally {
                lock.unlock();
            }
        }
    }
}

class ZeroEvenOdd2 {

    private final Object lock = new Object();
    private final int n;
    private int job = 0;

    public ZeroEvenOdd2(int n) {
        this.n = n;
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void zero(IntConsumer printNumber) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            synchronized (lock) {
                while (job != 0) {
                    lock.wait();
                }
                printNumber.accept(0);
                if ((i & 1) == 0) {
                    job = 1;
                } else {
                    job = 2;
                }
                lock.notifyAll();
            }
        }
    }

    public void even(IntConsumer printNumber) throws InterruptedException {
        for (int i = 2; i <= n; i += 2) {
            synchronized (lock) {
                while (job != 2) {
                    lock.wait();
                }
                printNumber.accept(i);
                job = 0;
                lock.notifyAll();
            }

        }
    }

    public void odd(IntConsumer printNumber) throws InterruptedException {
        for (int i = 1; i <= n; i += 2) {
            synchronized (lock) {
                while (job != 1) {
                    lock.wait();
                }
                printNumber.accept(i);
                job = 0;
                lock.notifyAll();
            }
        }
    }
}

class ZeroEvenOdd1 {

    private final Semaphore zeroSema = new Semaphore(1);
    private final Semaphore oddSema = new Semaphore(0);
    private final Semaphore evenSema = new Semaphore(0);
    private final int n;

    public ZeroEvenOdd1(int n) {
        this.n = n;
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void zero(IntConsumer printNumber) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            zeroSema.acquire();
            printNumber.accept(0);
            if ((i & 1) == 0) {
                oddSema.release(1);
            } else {
                evenSema.release(1);
            }
        }
    }

    public void even(IntConsumer printNumber) throws InterruptedException {
        for (int i = 2; i <= n; i += 2) {
            evenSema.acquire();
            printNumber.accept(i);
            zeroSema.release(1);
        }
    }

    public void odd(IntConsumer printNumber) throws InterruptedException {
        for (int i = 1; i <= n; i += 2) {
            oddSema.acquire();
            printNumber.accept(i);
            zeroSema.release(1);
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
