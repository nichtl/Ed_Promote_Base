package readBookTest.read2test;

import java.util.HashSet;
import java.util.Set;

/**
 * @Description
 * @Vm Args -XX:PermSize=6m -XX:MaxPermSize=6m test / jdk1.6 /jdk 1.8
 * @Date 2023/1/4
 */
public class RuntimeConstantPoolOOM {


    public static void main(String[] args) {
         test2();
    }

        public  static  void test2(){

            String   sb1 = new StringBuilder().append("计算机").append("软件").toString();

            System.out.println( sb1.intern() == sb1 );

            String   sb2 = new StringBuilder().append("ja").append("va").toString();

            System.out.println( sb2.intern() == sb2 );

        }



        public  static  void test1(){
        // 使用set保持常量池引用  避免FullGc 回收常量池行为
        Set<String> set = new HashSet<String>();
        short i =0 ;

        while(true){
            set.add(String.valueOf(i++).intern());
        }
    }



}
