package year2021.month8.no1195;

import java.util.concurrent.atomic.AtomicInteger;
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

    private final int n;
    private final AtomicInteger ai = new AtomicInteger(1);

    public FizzBuzz(int n) {
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
