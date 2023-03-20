package readBookTest.read2test;




import java.lang.reflect.Field;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description
 * @VM args  -Xmx20M -XX:MaxDirectMemorySize=10M
 * @Date 2023/1/10
 */
public class DirectMemoryOOM {

    private  static  final int _10MB = 10*1024*1024;

    public static void main(String[] args)  throws  Exception {
        //  为什么不用 unsafe.allocateMemory() 来模拟分配内存？
        //因为 Unsafe.allocateMemory() 是系统调用的os::malloc一个包装，并没有关心 VM 要求的内存限制，所以会绕过了 MaxDirectMemorySize 的限制
        //作者：分布式与微服务
        //链接：https://www.jianshu.com/p/a63c3ace0a2f
//        Field  unsafeField   = Unsafe.class.getDeclaredField("theUnsafe");
//          unsafeField.setAccessible(true);
//          Unsafe unsafe = (Unsafe) unsafeField.get(null);
//        while (true){
//            System.out.println(
//            unsafe.allocateMemory(100*_1MB)
//            );
//        }

        List<ByteBuffer> list = new ArrayList<>();
        // 分配 20MB
        list.add(ByteBuffer.allocateDirect(_10MB));
        list.add(ByteBuffer.allocateDirect(_10MB));



    }























}
