package com.nicht.promote.multithreading.chapter.Chapter;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author Nicht
 * @Description java 中的读写锁
 * @Time 2021/5/10
 * @Link http://ifeve.com/read-write-locks/
 */
public class read_write_locks {

    /**
     * 相比chapter11中lock 实现 读写锁要考虑的问题更多
     * 实现也更复杂,假设你的程序中涉及到对一些共享资源的读和写操作
     * 且写操作没有读操作那么频繁,在没有写操作的时候两个线程同时读一个资源没有任何问题,
     * 所以应该允许多个线程能在同时读取资源,但当开始有一个线程想去写这些共享资源,就不应该
     * 再有其他线程对该资源进行读或写,(也就是 读-读能共存,读-写不能共存,写写不能共存),
     * 这就需要一个读/写锁来解决这个问题
     * Java5在java.util.concurrent包中已经包含了读写锁。
     * 尽管如此，我们还是应该了解其实现背后的原理。
     * */
    /**
     * 读写锁的实现
     * 先让我们对读写访问资源的条件做个概述
     * 读取操作 没有线程正在做写操作 且没有线程在请求写操作
     * 写入 没有线程正在做读写操作
     *
     * */
    /**
     *
     */
    public class ReadWriterLock {
        private int reader = 0;
        private int writer = 0;
        private int WriterQuest = 0;

        private synchronized void LockRead() throws InterruptedException {
            if (writer > 0 || WriterQuest > 0) {
                wait();
            }
            reader++;
        }

        private synchronized void UnLockRead() throws InterruptedException {
            reader--;
            notifyAll();
        }

        private synchronized void LockWrite() throws InterruptedException {
            WriterQuest++; //每次调用写入锁都会增加写请求次数
            if (reader > 0 || WriterQuest > 0) {
                wait();
            }
            WriterQuest--;
            writer++;
        }

        public synchronized void UnLockWrite() throws InterruptedException {
            writer--;
            notifyAll();
        }
    }
    /**
     *ReadWriteLock类中，读锁和写锁各有一个获取锁和释放锁的方法。
     * */
    /**
     * WriterQuest 是为了统计在没有实际写操作的时候，是否有写的请求进入
     * 读锁的实现在lockRead()中,只要没有线程拥有写锁（writers==0）
     * 且没有线程在请求写锁（writeRequests ==0）,所有想获得读锁的线程都能成功获取、
     * 写锁的实现在lockWrite()中,当一个线程想获得写锁的时候，
     * 首先会把写锁请求数加1（writeRequests++），然后再去判断是否能够真能获得写锁，
     * 当没有线程持有读锁（readers==0 ）,且没有线程持有写锁（writers==0）时就能获得写锁。
     * 有多少线程在请求写锁并无关系。
     * */
    /**
     * 注意释放读写锁的方法使用的是notifyAll()不是notify(),
     * 要解释这个原因，我们可以想象下面一种情形：
     * */
    /**
     * Thread1  -> WAIT() 读锁
     * Thread2  -> WAIT() 写锁
     * 两个线程一个等待读锁 一个等待 写锁 这个时候条件WriterQuest>0是成立的
     * 若 Thread1 被 notify() 唤醒后发现 WriterQuest>0 又陷入wait()
     * 然而，等待写锁的线程一个也没被唤醒，就像什么也没发生过一样（译者注：信号丢失现象）。
     * 如果用的是notifyAll方法，所有的线程都会被唤醒，然后判断能否获得其请求的锁。
     * 当这种情况下让所有等待的线程都被唤醒 、读线程让步写线程 这样问题就解决
     * */
    /**
     * 用notifyAll还有一个好处。如果有多个读线程在等待读锁且没有线程在等待写锁时,
     * 调用unlockWrite()后,所有等待读锁的线程都能立马成功获取读锁 -— 而不是一次只允许一个。
     * 这样在没有等待写入线程时候多个等待读锁的线程可以同时访问共享资源而非一个一个的访问
     * */
    /**
     * 读写锁的可重入性 读/写锁的重入
     * */

    /**
     * 上面实现的读/写锁(ReadWriteLock) 是不可重入的,
     * 当一个已经持有写锁的线程再次请求写锁时，就会被阻塞。
     * 原因是已经有一个写线程了——就是它自己。此外，考虑下面的例子：
     * 1 Thead1 获得读锁
     * 2 Thead2 想要获得写锁 但此时Thead1持有读锁，写锁请求被阻塞wait()
     * 3 Thead1 想要再请求一次读锁，但因为Thead2处于请求写锁的状态，所有想再次获取读锁也会被阻塞。
     * 上面这种情况使用之前的ReadWriteLock就会被锁定————一种类似写锁的情形
     * 不会再有线程能够成功获取读锁或写锁了,Thead1 被自己阻塞陷入死循环
     * 为了让ReadWriteLock可重入，需要对它做一些改进。下面会分别处理读锁的重入和写锁的重入.
     * */
    /**
     * 读锁重入
     * 为了让ReadWriteLock的读锁可重入，我们要先为读锁重入建立规则：
     * 要保证某个线程中的读锁可重入，要么满足获取读锁的条件（没有写或写请求）
     * 要么已经持有读锁（不管是否有写请求）。
     * 要确定一个线程是否已经持有读锁，
     * 可以用一个map来存储已经持有读锁的线程以及对应线程获取读锁的次数，
     * 当需要判断某个线程能否获得读锁时，就利用map中存储的数据进行判断。
     * 下面是方法lockRead和unlockRead修改后的的代码
     *
     */
    /*按自己思路*/
    public class ReadWriteReenTrantLock1 {
        //为了实现读可重入 需要记录获得读锁的线程 使用Map
        Map<Thread, Boolean> ThreadMap = new HashMap();
        private int ReadLockReader = 0;
        private int WriterLockWriter = 0;
        private int WriterRequest = 0;


        public synchronized void ReadLock() throws InterruptedException {
            Thread callingThread = Thread.currentThread();
            if (ThreadMap.get(callingThread) == null) {
                ThreadMap.put(callingThread, false);
            }
            if(!canGetAccessGrant(callingThread)){wait();}
            ReadLockReader++;
            ThreadMap.put(callingThread,true);
        }

        public  synchronized  void ReadUnlock() throws InterruptedException{
            Thread callingThread = Thread.currentThread();
               ThreadMap.put(callingThread,false);
               ReadLockReader--;
               notifyAll();
        }


        private  boolean canGetAccessGrant(Thread callingThread){
            //代码中我们可以看到，只有在没有线程拥有写锁的情况下才允许读锁的重入。此外，重入的读锁比写锁优先级高
            if(WriterLockWriter>0){return  false ;}
            if(getCurrentAccess(callingThread)){return  true;}
            if (WriterRequest >0){ return  false;}
             return  true;
        }

        private boolean getCurrentAccess(Thread callingThread ){
            // true  已获锁
            // false 未获锁
            return ThreadMap.get(callingThread)==true;
        }




    }
    /**文章思路 可重入读锁*/
    public class ReadWriteReenTrantLock  {
        Map<Thread, Integer> readingThreads  = new HashMap();
        private int ReadLockReader = 0;
        private int WriterLockWriter = 0;
        private int WriterRequest = 0;

        private synchronized  void ReadLock() throws InterruptedException{
            Thread callingThread = Thread.currentThread();
            if(!canGrantReadAccess(callingThread)){wait();}
            readingThreads.put(callingThread,getReadAccessCount(callingThread)+1);
        }

        private synchronized  void ReadUnLock() throws InterruptedException{
            Thread  callingThread = Thread.currentThread();
            if(getReadAccessCount(callingThread)  ==1){
                readingThreads.remove(callingThread);
            }else {
                readingThreads.put(callingThread,getReadAccessCount(callingThread)-1);
            }
            notifyAll();
        }





        private  boolean canGrantReadAccess(Thread callingThread){
            if(WriterLockWriter>0)return  false;
            if(isReader(callingThread)) return  true; //是读线程放行
            if(WriterRequest>0) return  false;
            return true;
        }
         // 该线程访问lock的次数
        private  int getReadAccessCount(Thread callingThread){
            Integer  ThreadReadCount = readingThreads.get(callingThread);
            if(ThreadReadCount ==null) return 0;
            return ThreadReadCount.intValue();
        }
        // 当前线程是否在map中
        private  boolean isReader(Thread callingThread){
           return  readingThreads.get(callingThread)!=null;
       }

    }
     // 可重入写锁
     private  class  WriteReenTrantLock{
          //不同与读线程可以多个 写线程同时只有一个
         private  Thread  writerThread  = null;
         private  int     writeAccess   = 0;
         private  int     writequest    = 0;
         private Map<Thread,Integer> readingThread = new HashMap<>();
         private synchronized void lock() throws InterruptedException{
             Thread  callingThead = Thread.currentThread();
             writequest++;
             if(!canGrantWriteAccess(callingThead)){wait();}
             writeAccess++;
             writequest--;
             writerThread = callingThead;
         }

         private synchronized void  UnLock() throws  InterruptedException{
                   writequest --;
                   if (writequest==0){
                       writerThread=null;
                   }
                   notifyAll();
         }


         private  boolean canGrantWriteAccess(Thread callingThread){
             if(hasReaders()){return  false;}
             if(writerThread == null){return  true;}
             if(!isWriter(callingThread)){return  false;}
             return  false;
         }

         private  boolean hasReaders(){
             return  readingThread.size()>0;
         }

         private  boolean  isWriter(Thread callingThread){
             return  writerThread==callingThread;
         }

    }
      // 如果一个已经有读锁的线程想访问写锁同步方法是不支持的
    //我们希望一个拥有读锁的线程，也能获得写锁。想要允许这样的操作，要求这个线程是唯一一个拥有读锁的线程。writeLock()需要做点改动来达到这个
      private  class  ReadLockUpGradeWriteReenTrantLock{
          //不同与读线程可以多个 写线程同时只有一个
          private  Thread  writerThread  = null;
          private  int     writeAccess   = 0;
          private  int     readLockReader= 0;
          private  int     writequest    = 0;
          private  int     WriterLockWriter = 0;
          private  int     WriterRequest = 0;
          private Map<Thread,Integer> readingThreads = new HashMap<>();


          private synchronized  void ReadLock() throws InterruptedException{
              Thread callingThread = Thread.currentThread();
              if(!canGrantReadAccess(callingThread)){wait();}
              readLockReader++;
              readingThreads.put(callingThread,getReadAccessCount(callingThread)+1);
          }

          private synchronized  void ReadUnLock() throws InterruptedException{
              Thread  callingThread = Thread.currentThread();
              if(getReadAccessCount(callingThread)  ==1){
                  readingThreads.remove(callingThread);
              }else {
                  readingThreads.put(callingThread,getReadAccessCount(callingThread)-1);
              }
              readLockReader--;
              notifyAll();
          }

          private  boolean canGrantReadAccess(Thread callingThread){
              if(WriterLockWriter>0)return  false;
              if(isReader(callingThread)) return  true; //是读线程放行
              if(WriterRequest>0) return  false;
              return true;
          }
          // 该线程访问lock的次数
          private  int getReadAccessCount(Thread callingThread){
              Integer  ThreadReadCount = readingThreads.get(callingThread);
              if(ThreadReadCount ==null) return 0;
              return ThreadReadCount.intValue();
          }
          // 当前线程是否在map中
          private  boolean isReader(Thread callingThread){
              return  readingThreads.get(callingThread)!=null;
          }

          private synchronized void writeLock() throws InterruptedException{
              Thread  callingThead = Thread.currentThread();
              writequest++;
              if(!canGrantWriteAccess(callingThead)){wait();}
              writeAccess++;
              writequest--;
              writerThread = callingThead;
          }

          private synchronized void  writeUnLock() throws  InterruptedException{
              writequest --;
              if (writequest==0){
                  writerThread=null;
              }
              notifyAll();
          }



          private  boolean canGrantWriteAccess(Thread callingThread){
              if(isOnlyReader(callingThread)){return true;}  // 先检查是否读锁升级写锁
              if(hasReaders()){return  false;}
              if(writerThread == null){return  true;}
              if(!isWriter(callingThread)){return  false;}
              return  false;
          }

          private  boolean hasReaders(){
              return  readingThreads.size()>0;
          }

          private  boolean  isWriter(Thread callingThread){
              return  writerThread==callingThread;
          }

          private boolean isOnlyReader(Thread callingRead){
             return    readLockReader == 1 && readingThreads.get(callingRead)!=null;
          }
      }
    //写锁重入 仅当一个线程已经持有写锁，才允许写锁重入（再次获得写锁）。下面是方法lockWrite和unlockWrite修改后的的代码。
    /*  public class ReadWriteLock{
        private boolean canGrantReadAccess(Thread callingThread){
            if(isWriter(callingThread)) return true;
            if(writingThread != null) return false;
            if(isReader(callingThread) return true;
            if(writeRequests > 0) return false;
            return true;
        }
    }*/












}




          /**
           *
           *
           *
           * */



































