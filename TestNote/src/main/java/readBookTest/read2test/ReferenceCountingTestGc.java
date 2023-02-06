package readBookTest.read2test;

/**
 * @Description
 * @Date 2023/1/11
 */
public class ReferenceCountingTestGc {
    public  Object instance =null;

    private  static  final  int _1MB = 1024 *1024;

    /**
     * 这个成员属性的唯一意义就是占点内存 以便在 GC日志里看清楚算法有回收过
     */

    private  byte[] bigSize = new byte[2*_1MB];


    public static void main(String[] args) {
        ReferenceCountingTestGc  objA = new ReferenceCountingTestGc();

        ReferenceCountingTestGc  objB = new ReferenceCountingTestGc();


        objA.instance = objB;
        objB.instance = objA;


        objA =null;

        objB =null;

        // 手动触发 gc 看看 互相引用的a b 是否会被回收
        System.gc();
    }










}
