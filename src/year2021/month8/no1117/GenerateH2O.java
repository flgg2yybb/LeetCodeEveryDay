package year2021.month8.no1117;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Consumer;

public class GenerateH2O {

    public static void main(String[] args) {
        Consumer<String> print = System.out::print;
        Runnable releaseHydrogen = () -> print.accept("H");
        Runnable releaseOxygen = () -> print.accept("O");
        H2O h2O = new H2O();
        int n = 5;
        Thread thread1 = new Thread(() -> {
            try {
                for (int i = 0; i < 2 * n; i++) {
                    h2O.hydrogen(releaseHydrogen);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread thread2 = new Thread(() -> {
            try {
                for (int i = 0; i < n; i++) {
                    h2O.oxygen(releaseOxygen);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        thread2.start();
        thread1.start();
    }

}

class H2O {

    private final Semaphore hSemaphore = new Semaphore(2);
    private final Semaphore oSemaphore = new Semaphore(0);

    public H2O() {

    }

    public void hydrogen(Runnable releaseHydrogen) throws InterruptedException {
        hSemaphore.acquire();
        // releaseHydrogen.run() outputs "H". Do not change or remove this line.
        releaseHydrogen.run();
        oSemaphore.release();
    }

    public void oxygen(Runnable releaseOxygen) throws InterruptedException {
        oSemaphore.acquire(2);
        // releaseOxygen.run() outputs "O". Do not change or remove this line.
        releaseOxygen.run();
        hSemaphore.release(2);
    }

}

class H2O3 {

    private final Lock lock = new ReentrantLock();
    private final Condition condition = lock.newCondition();
    private int hCount = 0;
    private int oCount = 0;

    public H2O3() {

    }

    public void hydrogen(Runnable releaseHydrogen) throws InterruptedException {
        lock.lock();
        while (hCount == 2) {
            condition.await();
        }
        // releaseHydrogen.run() outputs "H". Do not change or remove this line.
        releaseHydrogen.run();
        hCount++;
        if (oCount == 1 && hCount == 2) {
            oCount = 0;
            hCount = 0;
        }
        condition.signalAll();
        lock.unlock();
    }

    public void oxygen(Runnable releaseOxygen) throws InterruptedException {
        lock.lock();
        while (oCount == 1) {
            condition.await();
        }
        // releaseOxygen.run() outputs "O". Do not change or remove this line.
        releaseOxygen.run();
        oCount++;
        if (oCount == 1 && hCount == 2) {
            oCount = 0;
            hCount = 0;
        }
        condition.signalAll();
        lock.unlock();
    }

}

class H2O2 {

    private final Object lock = new Object();
    private int hCount = 0;
    private int oCount = 0;

    public H2O2() {

    }

    public void hydrogen(Runnable releaseHydrogen) throws InterruptedException {
        synchronized (lock) {
            while (hCount == 2) {
                lock.wait();
            }
            // releaseHydrogen.run() outputs "H". Do not change or remove this line.
            releaseHydrogen.run();
            hCount++;
            if (oCount == 1 && hCount == 2) {
                oCount = 0;
                hCount = 0;
            }
            lock.notifyAll();
        }
    }

    public void oxygen(Runnable releaseOxygen) throws InterruptedException {
        synchronized (lock) {
            while (oCount == 1) {
                lock.wait();
            }
            // releaseOxygen.run() outputs "O". Do not change or remove this line.
            releaseOxygen.run();
            oCount++;
            if (oCount == 1 && hCount == 2) {
                oCount = 0;
                hCount = 0;
            }
            lock.notifyAll();
        }
    }

}

class H2O1 {

    private final Semaphore hSemaphore = new Semaphore(2);
    private final Semaphore oSemaphore = new Semaphore(0);

    public H2O1() {

    }

    public void hydrogen(Runnable releaseHydrogen) throws InterruptedException {
        hSemaphore.acquire();
        // releaseHydrogen.run() outputs "H". Do not change or remove this line.
        releaseHydrogen.run();
        if (hSemaphore.availablePermits() == 0) {
            oSemaphore.release();
        }
    }

    public void oxygen(Runnable releaseOxygen) throws InterruptedException {
        oSemaphore.acquire();
        // releaseOxygen.run() outputs "O". Do not change or remove this line.
        releaseOxygen.run();
        hSemaphore.release(2);
    }

}

/*
* 现在有两种线程，氧 oxygen 和氢 hydrogen，你的目标是组织这两种线程来产生水分子。

存在一个屏障（barrier）使得每个线程必须等候直到一个完整水分子能够被产生出来。

氢和氧线程会被分别给予 releaseHydrogen 和 releaseOxygen 方法来允许它们突破屏障。

这些线程应该三三成组突破屏障并能立即组合产生一个水分子。

你必须保证产生一个水分子所需线程的结合必须发生在下一个水分子产生之前。

换句话说:

如果一个氧线程到达屏障时没有氢线程到达，它必须等候直到两个氢线程到达。
如果一个氢线程到达屏障时没有其它线程到达，它必须等候直到一个氧线程和另一个氢线程到达。
书写满足这些限制条件的氢、氧线程同步代码。

 

示例 1:

输入: "HOH"
输出: "HHO"
解释: "HOH" 和 "OHH" 依然都是有效解。
示例 2:

输入: "OOHHHH"
输出: "HHOHHO"
解释: "HOHHHO", "OHHHHO", "HHOHOH", "HOHHOH", "OHHHOH", "HHOOHH", "HOHOHH" 和 "OHHOHH" 依然都是有效解。
 

提示：

输入字符串的总长将会是 3n, 1 ≤ n ≤ 50；
输入字符串中的 “H” 总数将会是 2n 。
输入字符串中的 “O” 总数将会是 n 。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/building-h2o
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */
