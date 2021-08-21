package year2021.month8.no1195;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Consumer;
import java.util.function.IntConsumer;

public class PrintFizzBuzz {
    public static void main(String[] args) {
        int n1 = 15;
        FizzBuzz fizzBuzz = new FizzBuzz(n1);
        Consumer<String> consumer = (value) -> System.out.print(value + " ");
        IntConsumer intConsumer = (value) -> System.out.print(value + " ");
        Runnable printFizz = () -> consumer.accept("fizz");
        Runnable printBuzz = () -> consumer.accept("buzz");
        Runnable printFizzBuzz = () -> consumer.accept("fizzbuzz");
        Thread fizzThread = new Thread(() -> {
            try {
                fizzBuzz.fizz(printFizz);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread buzzThread = new Thread(() -> {
            try {
                fizzBuzz.buzz(printBuzz);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread fizzbuzzThread = new Thread(() -> {
            try {
                fizzBuzz.fizzbuzz(printFizzBuzz);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread numThread = new Thread(() -> {
            try {
                fizzBuzz.number(intConsumer);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        fizzThread.start();
        buzzThread.start();
        fizzbuzzThread.start();
        numThread.start();
    }
}

class FizzBuzz {

    private static final int NUMBER = 0;
    private static final int FIZZ = 3;
    private static final int BUZZ = 5;
    private static final int FIZZBUSS = 15;
    private final int n;
    private final Lock lock = new ReentrantLock();
    private final Condition condition = lock.newCondition();
    private volatile int curr = NUMBER;   // 0 打印数字，3 打印 fizz，5 打印 buzz，15 打印 fizzbuzz


    public FizzBuzz(int n) {
        this.n = n;
    }

    // printFizz.run() outputs "fizz".
    public void fizz(Runnable printFizz) throws InterruptedException {
        for (int i = 3; i <= n; i += 3) {
            if (i % 5 == 0) {
                continue;
            }
            lock.lock();
            while (curr != FIZZ) {
                condition.await();
            }
            printFizz.run();
            curr = NUMBER;
            condition.signalAll();
            lock.unlock();
        }
    }

    // printBuzz.run() outputs "buzz".
    public void buzz(Runnable printBuzz) throws InterruptedException {
        for (int i = 5; i <= n; i += 5) {
            if (i % 3 == 0) {
                continue;
            }
            lock.lock();
            while (curr != BUZZ) {
                condition.await();
            }
            printBuzz.run();
            curr = NUMBER;
            condition.signalAll();
            lock.unlock();
        }
    }

    // printFizzBuzz.run() outputs "fizzbuzz".
    public void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {
        for (int i = 1; i <= n; i++) {
            if (i % 3 != 0 || i % 5 != 0) {
                continue;
            }
            lock.lock();
            while (curr != FIZZBUSS) {
                condition.await();
            }
            printFizzBuzz.run();
            curr = NUMBER;
            condition.signalAll();
            lock.unlock();
        }
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void number(IntConsumer printNumber) throws InterruptedException {
        for (int i = 1; i <= n; i++) {
            lock.lock();
            while (curr != NUMBER) {
                condition.await();
            }
            if (i % 3 == 0 && i % 5 == 0) {
                curr = FIZZBUSS;
            } else if (i % 3 == 0) {
                curr = FIZZ;
            } else if (i % 5 == 0) {
                curr = BUZZ;
            } else {
                printNumber.accept(i);
            }
            condition.signalAll();
            lock.unlock();
        }
    }

}

class FizzBuzz3 {

    private final int n;
    private final Semaphore numSemaphore = new Semaphore(1);
    private final Semaphore fizzSemaphore = new Semaphore(0);
    private final Semaphore buzzSemaphore = new Semaphore(0);
    private final Semaphore fizzBuzzSemaphore = new Semaphore(0);

    public FizzBuzz3(int n) {
        this.n = n;
    }

    // printFizz.run() outputs "fizz".
    public void fizz(Runnable printFizz) throws InterruptedException {
        for (int i = 3; i <= n; i += 3) {
            if (i % 5 == 0) {
                continue;
            }
            fizzSemaphore.acquire();
            printFizz.run();
            numSemaphore.release();
        }
    }

    // printBuzz.run() outputs "buzz".
    public void buzz(Runnable printBuzz) throws InterruptedException {
        for (int i = 5; i <= n; i += 5) {
            if (i % 3 == 0) {
                continue;
            }
            buzzSemaphore.acquire();
            printBuzz.run();
            numSemaphore.release();
        }
    }

    // printFizzBuzz.run() outputs "fizzbuzz".
    public void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {
        for (int i = 1; i <= n; i++) {
            if (i % 3 != 0 || i % 5 != 0) {
                continue;
            }
            fizzBuzzSemaphore.acquire();
            printFizzBuzz.run();
            numSemaphore.release();
        }
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void number(IntConsumer printNumber) throws InterruptedException {
        for (int i = 1; i <= n; i++) {
            numSemaphore.acquire();
            if (i % 3 == 0 && i % 5 == 0) {
                fizzBuzzSemaphore.release();
            } else if (i % 3 == 0) {
                fizzSemaphore.release();
            } else if (i % 5 == 0) {
                buzzSemaphore.release();
            } else {
                printNumber.accept(i);
                numSemaphore.release();
            }
        }
    }

}

class FizzBuzz2 {

    private final int n;
    private final AtomicInteger ai = new AtomicInteger(1);
    private final Object lock = new Object();

    public FizzBuzz2(int n) {
        this.n = n;
    }

    // printFizz.run() outputs "fizz".
    public void fizz(Runnable printFizz) throws InterruptedException {
        while (ai.get() <= n) {
            synchronized (lock) {
                while (ai.get() <= n && (ai.get() % 3 != 0 || ai.get() % 5 == 0)) {
                    lock.wait();
                }
                if (ai.get() > n) {
                    break;
                }
                printFizz.run();
                ai.incrementAndGet();
                lock.notifyAll();
            }
        }
    }

    // printBuzz.run() outputs "buzz".
    public void buzz(Runnable printBuzz) throws InterruptedException {
        while (ai.get() <= n) {
            synchronized (lock) {
                while (ai.get() <= n && (ai.get() % 5 != 0 || ai.get() % 3 == 0)) {
                    lock.wait();
                }
                if (ai.get() > n) {
                    break;
                }
                printBuzz.run();
                ai.incrementAndGet();
                lock.notifyAll();
            }
        }
    }

    // printFizzBuzz.run() outputs "fizzbuzz".
    public void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {
        while (ai.get() <= n) {
            synchronized (lock) {
                while (ai.get() <= n && (ai.get() % 3 != 0 || ai.get() % 5 != 0)) {
                    lock.wait();
                }
                if (ai.get() > n) {
                    break;
                }
                printFizzBuzz.run();
                ai.incrementAndGet();
                lock.notifyAll();
            }
        }
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void number(IntConsumer printNumber) throws InterruptedException {
        while (ai.get() <= n) {
            synchronized (lock) {
                while (ai.get() <= n && (ai.get() % 3 == 0 || ai.get() % 5 == 0)) {
                    lock.wait();
                }
                if (ai.get() > n) {
                    break;
                }
                printNumber.accept(ai.intValue());
                ai.incrementAndGet();
                lock.notifyAll();
            }
        }
    }

}

class FizzBuzz1 {

    private final int n;
    private final AtomicInteger ai = new AtomicInteger(1);

    public FizzBuzz1(int n) {
        this.n = n;
    }

    // printFizz.run() outputs "fizz".
    public void fizz(Runnable printFizz) throws InterruptedException {
        while (ai.get() <= n) {
            while (ai.get() <= n && (ai.get() % 3 != 0 || ai.get() % 5 == 0)) {
                Thread.yield();
            }
            if (ai.get() > n) {
                break;
            }
            printFizz.run();
            ai.incrementAndGet();
        }
    }

    // printBuzz.run() outputs "buzz".
    public void buzz(Runnable printBuzz) throws InterruptedException {
        while (ai.get() <= n) {
            while (ai.get() <= n && (ai.get() % 5 != 0 || ai.get() % 3 == 0)) {
                Thread.yield();
            }
            if (ai.get() > n) {
                break;
            }
            printBuzz.run();
            ai.incrementAndGet();
        }
    }

    // printFizzBuzz.run() outputs "fizzbuzz".
    public void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {
        while (ai.get() <= n) {
            while (ai.get() <= n && (ai.get() % 3 != 0 || ai.get() % 5 != 0)) {
                Thread.yield();
            }
            if (ai.get() > n) {
                break;
            }
            printFizzBuzz.run();
            ai.incrementAndGet();
        }
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void number(IntConsumer printNumber) throws InterruptedException {
        while (ai.get() <= n) {
            while (ai.get() <= n && (ai.get() % 3 == 0 || ai.get() % 5 == 0)) {
                Thread.yield();
            }
            if (ai.get() > n) {
                break;
            }
            printNumber.accept(ai.intValue());
            ai.incrementAndGet();
        }
    }

}

/*
 * 编写一个可以从 1 到 n 输出代表这个数字的字符串的程序，但是：

如果这个数字可以被 3 整除，输出 "fizz"。
如果这个数字可以被 5 整除，输出 "buzz"。
如果这个数字可以同时被 3 和 5 整除，输出 "fizzbuzz"。
例如，当 n = 15，输出： 1, 2, fizz, 4, buzz, fizz, 7, 8, fizz, buzz, 11, fizz, 13, 14, fizzbuzz。

假设有这么一个类：

class FizzBuzz {
  public FizzBuzz(int n) { ... }               // constructor
  public void fizz(printFizz) { ... }          // only output "fizz"
  public void buzz(printBuzz) { ... }          // only output "buzz"
  public void fizzbuzz(printFizzBuzz) { ... }  // only output "fizzbuzz"
  public void number(printNumber) { ... }      // only output the numbers
}
请你实现一个有四个线程的多线程版  FizzBuzz， 同一个 FizzBuzz 实例会被如下四个线程使用：

线程A将调用 fizz() 来判断是否能被 3 整除，如果可以，则输出 fizz。
线程B将调用 buzz() 来判断是否能被 5 整除，如果可以，则输出 buzz。
线程C将调用 fizzbuzz() 来判断是否同时能被 3 和 5 整除，如果可以，则输出 fizzbuzz。
线程D将调用 number() 来实现输出既不能被 3 整除也不能被 5 整除的数字。
 

提示：

本题已经提供了打印字符串的相关方法，如 printFizz() 等，具体方法名请参考答题模板中的注释部分。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/fizz-buzz-multithreaded
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */
