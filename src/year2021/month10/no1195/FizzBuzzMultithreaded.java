package year2021.month10.no1195;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.IntConsumer;

public class FizzBuzzMultithreaded {

    public static void main(String[] args) {
        Runnable printFizz = () -> System.out.println("Fizz");
        Runnable printBuzz = () -> System.out.println("Buzz");
        Runnable printFizzBuzz = () -> System.out.println("FizzBuzz");
        IntConsumer consumer = System.out::println;
        FizzBuzz fizzBuzz = new FizzBuzz(15);
        new Thread(() -> {
            try {
                fizzBuzz.fizz(printFizz);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> {
            try {
                fizzBuzz.buzz(printBuzz);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> {
            try {
                fizzBuzz.fizzbuzz(printFizzBuzz);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> {
            try {
                fizzBuzz.number(consumer);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

}

class FizzBuzz {

    public static final String NUMBER = "NUMBER";
    public static final String FIZZ = "FIZZ";
    public static final String BUZZ = "BUZZ";
    public static final String FIZZBUZZ = "FIZZBUZZ";
    private final int n;
    private final Lock lock = new ReentrantLock();
    private final Condition numCondition = lock.newCondition();
    private final Condition fizzCondition = lock.newCondition();
    private final Condition buzzCondition = lock.newCondition();
    private final Condition fizzBuzzCondition = lock.newCondition();
    private volatile String current = NUMBER;

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
            try {
                if (!FIZZ.equals(current)) {
                    fizzCondition.await();
                }
                printFizz.run();
                current = NUMBER;
                numCondition.signal();
            } finally {
                lock.unlock();
            }
        }
    }

    // printBuzz.run() outputs "buzz".
    public void buzz(Runnable printBuzz) throws InterruptedException {
        for (int i = 5; i <= n; i += 5) {
            if (i % 3 == 0) {
                continue;
            }
            lock.lock();
            try {
                if (!BUZZ.equals(current)) {
                    buzzCondition.await();
                }
                printBuzz.run();
                current = NUMBER;
                numCondition.signal();
            } finally {
                lock.unlock();
            }
        }
    }

    // printFizzBuzz.run() outputs "fizzbuzz".
    public void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {
        for (int i = 5; i <= n; i += 5) {
            if (i % 3 != 0) {
                continue;
            }
            lock.lock();
            try {
                if (!FIZZBUZZ.equals(current)) {
                    fizzBuzzCondition.await();
                }
                printFizzBuzz.run();
                current = NUMBER;
                numCondition.signal();
            } finally {
                lock.unlock();
            }
        }
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void number(IntConsumer printNumber) throws InterruptedException {
        for (int i = 1; i <= n; i++) {
            lock.lock();
            try {
                if (!NUMBER.equals(current)) {
                    numCondition.await();
                }
                if (i % 3 == 0 && i % 5 == 0) {
                    current = FIZZBUZZ;
                    fizzBuzzCondition.signal();
                } else if (i % 3 == 0) {
                    current = FIZZ;
                    fizzCondition.signal();
                } else if (i % 5 == 0) {
                    current = BUZZ;
                    buzzCondition.signal();
                } else {
                    printNumber.accept(i);
                }
            } finally {
                lock.unlock();
            }
        }
    }
}

class FizzBuzz3 {

    public static final String NUMBER = "NUMBER";
    public static final String FIZZ = "FIZZ";
    public static final String BUZZ = "BUZZ";
    public static final String FIZZBUZZ = "FIZZBUZZ";
    private final int n;
    private final Lock lock = new ReentrantLock();
    private final Condition condition = lock.newCondition();
    private volatile String current = NUMBER;

    public FizzBuzz3(int n) {
        this.n = n;
    }

    // printFizz.run() outputs "fizz".
    public void fizz(Runnable printFizz) throws InterruptedException {
        for (int i = 3; i <= n; i += 3) {
            if (i % 5 == 0) {
                continue;
            }
            lock.lock();
            try {
                while (!FIZZ.equals(current)) {
                    condition.await();
                }
                printFizz.run();
                current = NUMBER;
                condition.signalAll();
            } finally {
                lock.unlock();
            }
        }
    }

    // printBuzz.run() outputs "buzz".
    public void buzz(Runnable printBuzz) throws InterruptedException {
        for (int i = 5; i <= n; i += 5) {
            if (i % 3 == 0) {
                continue;
            }
            lock.lock();
            try {
                while (!BUZZ.equals(current)) {
                    condition.await();
                }
                printBuzz.run();
                current = NUMBER;
                condition.signalAll();
            } finally {
                lock.unlock();
            }
        }
    }

    // printFizzBuzz.run() outputs "fizzbuzz".
    public void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {
        for (int i = 5; i <= n; i += 5) {
            if (i % 3 != 0) {
                continue;
            }
            lock.lock();
            try {
                while (!FIZZBUZZ.equals(current)) {
                    condition.await();
                }
                printFizzBuzz.run();
                current = NUMBER;
                condition.signalAll();
            } finally {
                lock.unlock();
            }
        }
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void number(IntConsumer printNumber) throws InterruptedException {
        for (int i = 1; i <= n; i++) {
            lock.lock();
            try {
                while (!NUMBER.equals(current)) {
                    condition.await();
                }
                if (i % 3 == 0 && i % 5 == 0) {
                    current = FIZZBUZZ;
                } else if (i % 3 == 0) {
                    current = FIZZ;
                } else if (i % 5 == 0) {
                    current = BUZZ;
                } else {
                    printNumber.accept(i);
                }
                condition.signalAll();
            } finally {
                lock.unlock();
            }
        }
    }
}

class FizzBuzz2 {

    public static final String NUMBER = "NUMBER";
    public static final String FIZZ = "FIZZ";
    public static final String BUZZ = "BUZZ";
    public static final String FIZZBUZZ = "FIZZBUZZ";
    private final int n;
    private final Object lock = new Object();
    private volatile String current = NUMBER;

    public FizzBuzz2(int n) {
        this.n = n;
    }

    // printFizz.run() outputs "fizz".
    public void fizz(Runnable printFizz) throws InterruptedException {
        for (int i = 3; i <= n; i += 3) {
            if (i % 5 == 0) {
                continue;
            }
            synchronized (lock) {
                while (!FIZZ.equals(current)) {
                    lock.wait();
                }
                printFizz.run();
                current = NUMBER;
                lock.notifyAll();
            }
        }
    }

    // printBuzz.run() outputs "buzz".
    public void buzz(Runnable printBuzz) throws InterruptedException {
        for (int i = 5; i <= n; i += 5) {
            if (i % 3 == 0) {
                continue;
            }
            synchronized (lock) {
                while (!BUZZ.equals(current)) {
                    lock.wait();
                }
                printBuzz.run();
                current = NUMBER;
                lock.notifyAll();
            }
        }
    }

    // printFizzBuzz.run() outputs "fizzbuzz".
    public void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {
        for (int i = 5; i <= n; i += 5) {
            if (i % 3 != 0) {
                continue;
            }
            synchronized (lock) {
                while (!FIZZBUZZ.equals(current)) {
                    lock.wait();
                }
                printFizzBuzz.run();
                current = NUMBER;
                lock.notifyAll();
            }
        }
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void number(IntConsumer printNumber) throws InterruptedException {
        for (int i = 1; i <= n; i++) {
            synchronized (lock) {
                while (!NUMBER.equals(current)) {
                    lock.wait();
                }
                if (i % 3 == 0 && i % 5 == 0) {
                    current = FIZZBUZZ;
                } else if (i % 3 == 0) {
                    current = FIZZ;
                } else if (i % 5 == 0) {
                    current = BUZZ;
                } else {
                    printNumber.accept(i);
                }
                lock.notifyAll();
            }
        }
    }
}

class FizzBuzz1 {

    private final int n;
    private final Semaphore numSema = new Semaphore(1);
    private final Semaphore fizzSema = new Semaphore(0);
    private final Semaphore buzzSema = new Semaphore(0);
    private final Semaphore fizzBuzzSema = new Semaphore(0);

    public FizzBuzz1(int n) {
        this.n = n;
    }

    // printFizz.run() outputs "fizz".
    public void fizz(Runnable printFizz) throws InterruptedException {
        for (int i = 3; i <= n; i += 3) {
            if (i % 5 == 0) {
                continue;
            }
            fizzSema.acquire();
            printFizz.run();
            numSema.release();
        }
    }

    // printBuzz.run() outputs "buzz".
    public void buzz(Runnable printBuzz) throws InterruptedException {
        for (int i = 5; i <= n; i += 5) {
            if (i % 3 == 0) {
                continue;
            }
            buzzSema.acquire();
            printBuzz.run();
            numSema.release();
        }
    }

    // printFizzBuzz.run() outputs "fizzbuzz".
    public void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {
        for (int i = 5; i <= n; i += 5) {
            if (i % 3 != 0) {
                continue;
            }
            fizzBuzzSema.acquire();
            printFizzBuzz.run();
            numSema.release();
        }
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void number(IntConsumer printNumber) throws InterruptedException {
        for (int i = 1; i <= n; i++) {
            numSema.acquire();
            if (i % 3 == 0 && i % 5 == 0) {
                fizzBuzzSema.release();
            } else if (i % 3 == 0) {
                fizzSema.release();
            } else if (i % 5 == 0) {
                buzzSema.release();
            } else {
                printNumber.accept(i);
                numSema.release();
            }
        }
    }
}

/*
* 编写一个可以从 1 到 n 输出代表这个数字的字符串的程序，但是：

如果这个数字可以被 3 整除，输出 "fizz"。
如果这个数字可以被 5 整除，输出 "buzz"。
如果这个数字可以同时被 3 和 5 整除，输出 "fizzbuzz"。
例如，当 n = 15，输出： 1, 2, fizz, 4, buzz, fizz, 7, 8, fizz, buzz, 11, fizz, 13, 14, fizzbuzz。

假设有这么一个类：

class FizzBuzz {
  public FizzBuzz(int n) { ... }               // constructor
  public void fizz(printFizz) { ... }          // only output "fizz"
  public void buzz(printBuzz) { ... }          // only output "buzz"
  public void fizzbuzz(printFizzBuzz) { ... }  // only output "fizzbuzz"
  public void number(printNumber) { ... }      // only output the numbers
}
请你实现一个有四个线程的多线程版  FizzBuzz， 同一个 FizzBuzz 实例会被如下四个线程使用：

线程A将调用 fizz() 来判断是否能被 3 整除，如果可以，则输出 fizz。
线程B将调用 buzz() 来判断是否能被 5 整除，如果可以，则输出 buzz。
线程C将调用 fizzbuzz() 来判断是否同时能被 3 和 5 整除，如果可以，则输出 fizzbuzz。
线程D将调用 number() 来实现输出既不能被 3 整除也不能被 5 整除的数字。
 编写一个可以从 1 到 n 输出代表这个数字的字符串的程序，但是：

如果这个数字可以被 3 整除，输出 "fizz"。
如果这个数字可以被 5 整除，输出 "buzz"。
如果这个数字可以同时被 3 和 5 整除，输出 "fizzbuzz"。
例如，当 n = 15，输出： 1, 2, fizz, 4, buzz, fizz, 7, 8, fizz, buzz, 11, fizz, 13, 14, fizzbuzz。

假设有这么一个类：

class FizzBuzz {
  public FizzBuzz(int n) { ... }               // constructor
  public void fizz(printFizz) { ... }          // only output "fizz"
  public void buzz(printBuzz) { ... }          // only output "buzz"
  public void fizzbuzz(printFizzBuzz) { ... }  // only output "fizzbuzz"
  public void number(printNumber) { ... }      // only output the numbers
}
请你实现一个有四个线程的多线程版  FizzBuzz， 同一个 FizzBuzz 实例会被如下四个线程使用：

线程A将调用 fizz() 来判断是否能被 3 整除，如果可以，则输出 fizz。
线程B将调用 buzz() 来判断是否能被 5 整除，如果可以，则输出 buzz。
线程C将调用 fizzbuzz() 来判断是否同时能被 3 和 5 整除，如果可以，则输出 fizzbuzz。
线程D将调用 number() 来实现输出既不能被 3 整除也不能被 5 整除的数字。
 

提示：

本题已经提供了打印字符串的相关方法，如 printFizz() 等，具体方法名请参考答题模板中的注释部分。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/fizz-buzz-multithreaded
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

提示：

本题已经提供了打印字符串的相关方法，如 printFizz() 等，具体方法名请参考答题模板中的注释部分。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/fizz-buzz-multithreaded
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */
