package com.nicht.promote.multithreading.chapter.Chapter;

/**
 * @Author Nicht
 * @Description   如何避免死锁
 * @Time 2021/4/29
 * @Link  http://ifeve.com/deadlock-prevention/
 */
public class ten_deadlock_prevention {
/*    加锁顺序
    当多个线程需要相同的一些锁，但是按照不同的顺序加锁，死锁就很容易发生。
    如果能确保所有的线程都是按照相同的顺序获得锁，那么死锁就不会发生。看下面这个例子：*/
/*

    Thread 1:
    lock A
    lock B
    Thread 2:
    wait for A
    lock C (when A locked)
    Thread 3:
    wait for A
    wait for B
    wait for C
    如果一个线程（比如线程3）需要一些锁，那么它必须按照确定的顺序获取锁。它只有获得了从顺序上排在前面的锁之后，才能获取后面的锁。
    例如，线程2和线程3只有在获取了锁A之后才能尝试获取锁C(译者注：获取锁A是获取锁C的必要条件)。因为线程1已经拥有了锁A，所以线程2和3需要一直等到锁A被释放。然后在它们尝试对B或C加锁之前，必须成功地对A加了锁。
    按照顺序加锁是一种有效的死锁预防机制。但是，这种方式需要你事先知道所有可能会用到的锁(译者注：并对这些锁做适当的排序)，但总有些时候是无法预知的。
*/
/*    加锁时限
    另外一个可以避免死锁的方法是在尝试获取锁的时候加一个超时时间，
    这也就意味着在尝试获取锁的过程中若超过了这个时限该线程则放弃对该锁请求。
    若一个线程没有在给定的时限内成功获得所有需要的锁，
    则会进行回退并释放所有已经获得的锁，
    然后等待一段随机的时间再重试。
    这段随机的等待时间让其它线程有机会尝试获取相同的这些锁
    ,并且让该应用在没有获得锁的时候可以继续运行
    (译者注：加锁超时后可以先继续运行干点其它事情，再回头来重复之前加锁的逻辑)。
    以下是一个例子，展示了两个线程以不同的顺序尝试获取相同的两个锁，在发生超时后回退并重试的场景：*/
/**
 * 这种机制存在一个问题，在Java中不能对synchronized同步块设置超时时间。
 * 你需要创建一个自定义锁，或使用Java5中java.util.concurrent包下的工具。
 * 写一个自定义锁类不复杂，但超出了本文的内容。后续的Java并发系列会涵盖自定义锁的内容。
 * */
/*扩展阅读:译者注
《现代操作系统 第三版》§6.2~§6.7*/






















}
