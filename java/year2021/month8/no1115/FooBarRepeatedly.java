package year2021.month8.no1115;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Function;

public class FooBarRepeatedly {

    public static void main(String[] args) {
        int n = 5;
        FooBar fooBar = new FooBar(n);
        Function<String, Runnable> function = (message) -> (Runnable) () -> System.out.println(message);
        Runnable printFoo = function.apply("Foo");
        Runnable printBar = function.apply("Bar");
        Thread fooThread = new Thread(() -> {
            try {
                fooBar.foo(printFoo);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread barThread = new Thread(() -> {
            try {
                fooBar.bar(printBar);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        barThread.start();
        fooThread.start();
    }

}

class FooBar {

    private final int n;
    private final CyclicBarrier cb = new CyclicBarrier(2);
    private boolean isFooPrint = true;

    public FooBar(int n) {
        this.n = n;
    }

    public void foo(Runnable printFoo) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            while (!isFooPrint) {
                Thread.yield();
            }
            // printFoo.run() outputs "foo". Do not change or remove this line.
            printFoo.run();
            isFooPrint = false;
            try {
                cb.await();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }

    public void bar(Runnable printBar) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            try {
                cb.await();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
            // printBar.run() outputs "bar". Do not change or remove this line.
            printBar.run();
            isFooPrint = true;
        }
    }

}

class FooBar7 {

    private final int n;
    private final AtomicInteger ai = new AtomicInteger(1);

    public FooBar7(int n) {
        this.n = n;
    }

    public void foo(Runnable printFoo) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            while (ai.get() % 2 != 1) {
                Thread.yield();
            }
            // printFoo.run() outputs "foo". Do not change or remove this line.
            printFoo.run();
            ai.incrementAndGet();
        }
    }

    public void bar(Runnable printBar) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            while (ai.get() % 2 != 0) {
                Thread.yield();
            }
            // printBar.run() outputs "bar". Do not change or remove this line.
            printBar.run();
            ai.incrementAndGet();
        }
    }

}

class FooBar6 {

    private final int n;
    private final BlockingQueue<Integer> blockFirst = new LinkedBlockingQueue() {{
        add(1);
    }};
    private final BlockingQueue<Integer> blockSecond = new LinkedBlockingQueue<>();

    public FooBar6(int n) {
        this.n = n;
    }

    public void foo(Runnable printFoo) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            blockFirst.take();
            // printFoo.run() outputs "foo". Do not change or remove this line.
            printFoo.run();
            blockSecond.put(1);
        }
    }

    public void bar(Runnable printBar) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            blockSecond.take();
            // printBar.run() outputs "bar". Do not change or remove this line.
            printBar.run();
            blockFirst.put(1);
        }
    }

}

class FooBar5 {

    private final int n;
    private final Semaphore firstSemaphore = new Semaphore(1);
    private final Semaphore secondSemaphore = new Semaphore(0);

    public FooBar5(int n) {
        this.n = n;
    }

    public void foo(Runnable printFoo) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            firstSemaphore.acquire();
            // printFoo.run() outputs "foo". Do not change or remove this line.
            printFoo.run();
            secondSemaphore.release();
        }
    }

    public void bar(Runnable printBar) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            secondSemaphore.acquire();
            // printBar.run() outputs "bar". Do not change or remove this line.
            printBar.run();
            firstSemaphore.release();
        }
    }

}

class FooBar4 {

    private final int n;
    private final Lock lock = new ReentrantLock();
    private final Condition condition1 = lock.newCondition();
    private final Condition condition2 = lock.newCondition();
    private int count = 1;  // 在 ReentrantLock 作用域中可以保证内存可见性

    public FooBar4(int n) {
        this.n = n;
    }

    public void foo(Runnable printFoo) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            lock.lock();
            while (count % 2 == 0) {
                condition1.await();
            }
            // printFoo.run() outputs "foo". Do not change or remove this line.
            printFoo.run();
            count++;
            condition2.signal();
            lock.unlock();

        }
    }

    public void bar(Runnable printBar) throws InterruptedException {

        for (int i = 0; i < n; i++) {
            lock.lock();
            while (count % 2 == 1) {
                condition2.await();
            }
            // printBar.run() outputs "bar". Do not change or remove this line.
            printBar.run();
            count++;
            condition1.signal();
            lock.unlock();
        }
    }
}

class FooBar3 {

    private final int n;
    private final Object lock = new Object();
    private int count = 1;      // 在 synchronized 作用域中相当于加了 volatile 修饰

    public FooBar3(int n) {
        this.n = n;
    }

    public void foo(Runnable printFoo) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            synchronized (lock) {
                while (count % 2 == 0) {
                    lock.wait();
                }
                // printFoo.run() outputs "foo". Do not change or remove this line.
                printFoo.run();
                count++;
                lock.notifyAll();
            }
        }
    }

    public void bar(Runnable printBar) throws InterruptedException {

        for (int i = 0; i < n; i++) {
            synchronized (lock) {
                while (count % 2 == 1) {
                    lock.wait();
                }
                // printBar.run() outputs "bar". Do not change or remove this line.
                printBar.run();
                count++;
                lock.notifyAll();
            }
        }
    }
}

class FooBar2 {

    private final int n;
    private volatile int count = 1;

    public FooBar2(int n) {
        this.n = n;
    }

    public void foo(Runnable printFoo) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            while (count % 2 == 0) {

            }
            // printFoo.run() outputs "foo". Do not change or remove this line.
            printFoo.run();
            count++;
        }
    }

    public void bar(Runnable printBar) throws InterruptedException {

        for (int i = 0; i < n; i++) {
            while (count % 2 == 1) {

            }
            // printBar.run() outputs "bar". Do not change or remove this line.
            printBar.run();
            count++;
        }
    }
}

class FooBar1 {

    private final int n;
    private final AtomicInteger ai = new AtomicInteger(1);

    public FooBar1(int n) {
        this.n = n;
    }

    public void foo(Runnable printFoo) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            while (ai.get() % 2 == 0) {

            }
            // printFoo.run() outputs "foo". Do not change or remove this line.
            printFoo.run();
            ai.incrementAndGet();
        }
    }

    public void bar(Runnable printBar) throws InterruptedException {

        for (int i = 0; i < n; i++) {
            while (ai.get() % 2 == 1) {

            }
            // printBar.run() outputs "bar". Do not change or remove this line.
            printBar.run();
            ai.incrementAndGet();
        }
    }
}

/*
* 我们提供一个类：

class FooBar {
  public void foo() {
    for (int i = 0; i < n; i++) {
      print("foo");
    }
  }

  public void bar() {
    for (int i = 0; i < n; i++) {
      print("bar");
    }
  }
}
两个不同的线程将会共用一个 FooBar 实例。其中一个线程将会调用 foo() 方法，另一个线程将会调用 bar() 方法。

请设计修改程序，以确保 "foobar" 被输出 n 次。

 

示例 1:

输入: n = 1
输出: "foobar"
解释: 这里有两个线程被异步启动。其中一个调用 foo() 方法, 另一个调用 bar() 方法，"foobar" 将被输出一次。
示例 2:

输入: n = 2
输出: "foobarfoobar"
解释: "foobar" 将被输出两次。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/print-foobar-alternately
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */
