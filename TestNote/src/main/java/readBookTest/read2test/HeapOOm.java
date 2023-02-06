package readBookTest.read2test;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @Description 测试堆溢出
 * @Args -Xms20m -Xmx20m -XX:+HeapDumpOnOutOfMemoryError
 * @Date 2022/12/28
 */
public class HeapOOm {

    static  class  OOMObject{
       char i;
    }

    public static void main(String[] args) {
        List<Object>  list = new ArrayList<Object>();
        int i =0;
        while(i<=600000){
            i++;
           // list.add(new OOMObject());
            //new WeakReference<OOMObject>(new OOMObject()) 即使使用 弱引用 也会抛出oom 这个因为gc的不确定性
            // Java中当一个对象仅被一个弱引用引用时，如果GC运行, 那么这个对象就会被回收
          // WeakReference<OOMObject> weakReference =  new WeakReference<OOMObject>(new OOMObject());
           list.add( new WeakReference<OOMObject>(new OOMObject()));
            // 主动Gc
            if(i%500000==0) {
                System.gc();
                try {
                    //给GC留点时间，保证GC执行完成
                    TimeUnit.SECONDS.sleep(4);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(list.size());
            }

        }
        System.out.println("end time = "+i);

    }

}
