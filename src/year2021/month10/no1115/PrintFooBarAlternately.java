package year2021.month10.no1115;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class PrintFooBarAlternately {

    public static void main(String[] args) {
        Runnable printFoo = () -> System.out.println("Foo");
        Runnable printBar = () -> System.out.println("Bar");
        FooBar fooBar = new FooBar(5);
        new Thread(() -> {
            try {
                fooBar.foo(printFoo);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> {
            try {
                fooBar.bar(printBar);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

}

class FooBar {

    private final Lock lock = new ReentrantLock();
    private final Condition condition1 = lock.newCondition();
    private final Condition condition2 = lock.newCondition();
    private final int n;
    private int job = 1;

    public FooBar(int n) {
        this.n = n;
    }

    public void foo(Runnable printFoo) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            lock.lock();
            try {
                if (job != 1) {
                    condition1.await();
                }
                // printFoo.run() outputs "foo". Do not change or remove this line.
                printFoo.run();
                job = 2;
                condition2.signal();
            } finally {
                lock.unlock();
            }
        }
    }

    public void bar(Runnable printBar) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            lock.lock();
            try {
                if (job != 2) {
                    condition2.await();
                }
                // printBar.run() outputs "bar". Do not change or remove this line.
                printBar.run();
                job = 1;
                condition1.signal();
            } finally {
                lock.unlock();
            }
        }
    }
}

class FooBar3 {

    private final Object lock = new Object();
    private final int n;
    private int job = 1;

    public FooBar3(int n) {
        this.n = n;
    }

    public void foo(Runnable printFoo) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            synchronized (lock) {
                while (job != 1) {
                    lock.wait();
                }
                // printFoo.run() outputs "foo". Do not change or remove this line.
                printFoo.run();
                job = 2;
                lock.notify();
            }

        }
    }

    public void bar(Runnable printBar) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            synchronized (lock) {
                while (job != 2) {
                    lock.wait();
                }
                // printBar.run() outputs "bar". Do not change or remove this line.
                printBar.run();
                job = 1;
                lock.notify();
            }
        }
    }
}

class FooBar2 {

    private final AtomicInteger ai = new AtomicInteger(1);
    private final int n;

    public FooBar2(int n) {
        this.n = n;
    }

    public void foo(Runnable printFoo) throws InterruptedException {

        for (int i = 0; i < n; i++) {
            while ((ai.get() & 1) == 0) {
                Thread.yield();
            }
            // printFoo.run() outputs "foo". Do not change or remove this line.
            printFoo.run();
            ai.incrementAndGet();
        }
    }

    public void bar(Runnable printBar) throws InterruptedException {

        for (int i = 0; i < n; i++) {
            while ((ai.get() & 1) == 1) {
                Thread.yield();
            }
            // printBar.run() outputs "bar". Do not change or remove this line.
            printBar.run();
            ai.incrementAndGet();
        }
    }
}

class FooBar1 {

    private final Semaphore fooSem = new Semaphore(1);
    private final Semaphore barSem = new Semaphore(0);
    private final int n;

    public FooBar1(int n) {
        this.n = n;
    }

    public void foo(Runnable printFoo) throws InterruptedException {

        for (int i = 0; i < n; i++) {
            fooSem.acquire();
            // printFoo.run() outputs "foo". Do not change or remove this line.
            printFoo.run();
            barSem.release();
        }
    }

    public void bar(Runnable printBar) throws InterruptedException {

        for (int i = 0; i < n; i++) {
            barSem.acquire();
            // printBar.run() outputs "bar". Do not change or remove this line.
            printBar.run();
            fooSem.release();
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