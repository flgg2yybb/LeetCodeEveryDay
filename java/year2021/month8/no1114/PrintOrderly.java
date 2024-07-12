package year2021.month8.no1114;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class PrintOrderly {

    public static void main(String[] args) throws InterruptedException {
        Runnable first = () -> System.out.println("first");
        Runnable second = () -> System.out.println("second");
        Runnable third = () -> System.out.println("third");
        Foo foo = new Foo();
        new Thread(() -> {
            try {
                foo.second(second);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> {
            try {
                foo.third(third);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> {
            try {
                foo.first(first);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

    }

}

class Foo {

    private final Lock lock = new ReentrantLock();
    private final Condition condition1 = lock.newCondition();
    private final Condition condition2 = lock.newCondition();
    private final Condition condition3 = lock.newCondition();
    private volatile int job = 1;

    public Foo() {
    }

    public void first(Runnable printFirst) throws InterruptedException {
        lock.lock();
        while (job != 1) {
            condition1.await();
        }
        // printFirst.run() outputs "first". Do not change or remove this line.
        printFirst.run();
        job = 2;
        condition2.signal();
        lock.unlock();
    }

    public void second(Runnable printSecond) throws InterruptedException {
        lock.lock();
        while (job != 2) {
            condition2.await();
        }
        // printSecond.run() outputs "second". Do not change or remove this line.
        printSecond.run();
        job = 3;
        condition3.signal();
        lock.unlock();
    }

    public void third(Runnable printThird) throws InterruptedException {
        lock.lock();
        while (job != 3) {
            condition3.await();
        }
        // printThird.run() outputs "third". Do not change or remove this line.
        printThird.run();
        job = 1;
        condition1.signal();
        lock.unlock();
    }
}

class Foo7 {

    private final BlockingQueue<Integer> blockSecond = new SynchronousQueue<>();
    private final BlockingQueue<Integer> blockThird = new SynchronousQueue<>();

    public Foo7() {
    }

    public void first(Runnable printFirst) throws InterruptedException {
        // printFirst.run() outputs "first". Do not change or remove this line.
        printFirst.run();
        blockSecond.put(1);
    }

    public void second(Runnable printSecond) throws InterruptedException {
        blockSecond.take();
        // printSecond.run() outputs "second". Do not change or remove this line.
        printSecond.run();
        blockThird.put(1);
    }

    public void third(Runnable printThird) throws InterruptedException {
        blockThird.take();
        // printThird.run() outputs "third". Do not change or remove this line.
        printThird.run();
    }
}

class Foo6 {

    private final CountDownLatch secondCountDownLatch = new CountDownLatch(1);
    private final CountDownLatch thirdCountDownLatch = new CountDownLatch(1);

    public Foo6() {
    }

    public void first(Runnable printFirst) throws InterruptedException {
        // printFirst.run() outputs "first". Do not change or remove this line.
        printFirst.run();
        secondCountDownLatch.countDown();
    }

    public void second(Runnable printSecond) throws InterruptedException {
        secondCountDownLatch.await();
        // printSecond.run() outputs "second". Do not change or remove this line.
        printSecond.run();
        thirdCountDownLatch.countDown();
    }

    public void third(Runnable printThird) throws InterruptedException {
        thirdCountDownLatch.await();
        // printThird.run() outputs "third". Do not change or remove this line.
        printThird.run();
    }
}

class Foo5 {

    private final Semaphore secondSemaphore = new Semaphore(0);
    private final Semaphore thirdSemaphore = new Semaphore(0);

    public Foo5() {
    }

    public void first(Runnable printFirst) throws InterruptedException {
        // printFirst.run() outputs "first". Do not change or remove this line.
        printFirst.run();
        secondSemaphore.release();
    }

    public void second(Runnable printSecond) throws InterruptedException {
        secondSemaphore.acquire();
        // printSecond.run() outputs "second". Do not change or remove this line.
        printSecond.run();
        thirdSemaphore.release();
    }

    public void third(Runnable printThird) throws InterruptedException {
        thirdSemaphore.acquire();
        // printThird.run() outputs "third". Do not change or remove this line.
        printThird.run();
    }
}

class Foo4 {

    private final Object lock = new Object();
    private boolean firstJobDone = false;
    private boolean secondJobDone = false;

    public Foo4() {
    }

    public void first(Runnable printFirst) throws InterruptedException {
        synchronized (lock) {
            // printFirst.run() outputs "first". Do not change or remove this line.
            printFirst.run();
            firstJobDone = true;
            lock.notifyAll();
        }
    }

    public void second(Runnable printSecond) throws InterruptedException {
        synchronized (lock) {
            while (!firstJobDone) {
                lock.wait();
            }
            // printSecond.run() outputs "second". Do not change or remove this line.
            printSecond.run();
            secondJobDone = true;
            lock.notifyAll();
        }
    }

    public void third(Runnable printThird) throws InterruptedException {
        synchronized (lock) {
            while (!secondJobDone) {
                lock.wait();
            }
            // printThird.run() outputs "third". Do not change or remove this line.
            printThird.run();
        }
    }
}

class Foo3 {

    private volatile int done = 0;

    public Foo3() {
    }

    public void first(Runnable printFirst) throws InterruptedException {

        // printFirst.run() outputs "first". Do not change or remove this line.
        printFirst.run();
        done++;
    }

    public void second(Runnable printSecond) throws InterruptedException {

        while (done < 1) {

        }
        // printSecond.run() outputs "second". Do not change or remove this line.
        printSecond.run();
        done++;
    }

    public void third(Runnable printThird) throws InterruptedException {

        while (done < 2) {

        }
        // printThird.run() outputs "third". Do not change or remove this line.
        printThird.run();
    }
}

class Foo2 {

    private final AtomicInteger firstJobDone;
    private final AtomicInteger secondJobDone;

    public Foo2() {
        firstJobDone = new AtomicInteger(0);
        secondJobDone = new AtomicInteger(0);
    }

    public void first(Runnable printFirst) throws InterruptedException {

        // printFirst.run() outputs "first". Do not change or remove this line.
        printFirst.run();
        firstJobDone.incrementAndGet();
    }

    public void second(Runnable printSecond) throws InterruptedException {

        while (firstJobDone.get() != 1) {

        }
        // printSecond.run() outputs "second". Do not change or remove this line.
        printSecond.run();
        secondJobDone.incrementAndGet();
    }

    public void third(Runnable printThird) throws InterruptedException {

        while (secondJobDone.get() != 1) {

        }
        // printThird.run() outputs "third". Do not change or remove this line.
        printThird.run();
    }
}

class Foo1 {

    private final AtomicInteger ai = new AtomicInteger(1);

    public Foo1() {

    }

    public void first(Runnable printFirst) throws InterruptedException {

        // printFirst.run() outputs "first". Do not change or remove this line.
        printFirst.run();
        ai.incrementAndGet();
    }

    public void second(Runnable printSecond) throws InterruptedException {

        while (ai.get() != 2) {

        }
        // printSecond.run() outputs "second". Do not change or remove this line.
        printSecond.run();
        ai.incrementAndGet();
    }

    public void third(Runnable printThird) throws InterruptedException {

        while (ai.get() != 3) {

        }
        // printThird.run() outputs "third". Do not change or remove this line.
        printThird.run();
    }
}

/*
* 我们提供了一个类：

public class Foo {
  public void first() { print("first"); }
  public void second() { print("second"); }
  public void third() { print("third"); }
}
三个不同的线程 A、B、C 将会共用一个 Foo 实例。

一个将会调用 first() 方法
一个将会调用 second() 方法
还有一个将会调用 third() 方法
请设计修改程序，以确保 second() 方法在 first() 方法之后被执行，third() 方法在 second() 方法之后被执行。

 

示例 1:

输入: [1,2,3]
输出: "firstsecondthird"
解释: 
有三个线程会被异步启动。
输入 [1,2,3] 表示线程 A 将会调用 first() 方法，线程 B 将会调用 second() 方法，线程 C 将会调用 third() 方法。
正确的输出是 "firstsecondthird"。
示例 2:

输入: [1,3,2]
输出: "firstsecondthird"
解释: 
输入 [1,3,2] 表示线程 A 将会调用 first() 方法，线程 B 将会调用 third() 方法，线程 C 将会调用 second() 方法。
正确的输出是 "firstsecondthird"。
 

提示：

尽管输入中的数字似乎暗示了顺序，但是我们并不保证线程在操作系统中的调度顺序。
你看到的输入格式主要是为了确保测试的全面性。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/print-in-order
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */
