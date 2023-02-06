package com.nicht.promote.Classload;

/**
 * @Description
 * @Date 2022/11/24
 */
public class test {

//        813018965622,813139185209,813150970927,813154928780,813224713937,
//        813493561276,813402142804,813461440240,813603810484,813612292143,
//        813614464811,813643855109,813669669992,813790792738,813742037319,
//        813777540465,813899251615,813808303702,813805003017,813806340625,
//        813829791202,813849385899,813859612977,813880357093,813990372412
//
//
//
//                813909325830,813900445548,813904795049,813904864190,813919186807,
//        813949565348,813943603852,813951847368,813953867631,813982020088,
//        815060021445,815144342444,815160140080,815248571274,815544876758,
//        815693031563,815606545594,815898169980,815800756201,815923995489,
//        815949366068,817300831119,817632907833,817740128725,818011225044,
//        818043082534,818144093513,818208658458,818355825670,818591984692,
//        818619404400,818799616059,818744976427,818780792783,818843855351,818911107137,818937553088
    private static String helloWorldString = "Hello, world!";
    private static volatile int helloWorldTrigger = 0;
    private static final boolean useMethodInvoke = false;
    private static Object lock = new Object();

    public test() {
    }

    public static void main(String[] args) {
        int foo = a();
        System.out.println("HelloWorld exiting. a() = " + foo);
    }

    private static int a() {
        return 1 + b();
    }

    private static int b() {
        return 1 + c();
    }

    private static int c() {
        return 1 + d("Hi");
    }

    private static int d(String x) {
        System.out.println("HelloWorld.d() received \"" + x + "\" as argument");
        synchronized(lock) {
            int i = fib(10);
            long l = (long)i;
            float f = (float)i;
            double d = (double)i;
            char c = (char)i;
            short s = (short)i;
            byte b = (byte)i;
            int ret = e();
            System.out.println("Tenth Fibonacci number in all formats: " + i + ", " + l + ", " + f + ", " + d + ", " + c + ", " + s + ", " + b);
            return ret;
        }
    }

    public static int e() {
        System.out.println("Going to sleep...");
        int i = 0;

        while(helloWorldTrigger == 0) {
            ++i;
            if (i == 10000) {
                System.out.println("before gc i="+i);

                System.gc();
            }
            System.out.println("after gc i="+i);
        }

        System.out.println(helloWorldString);

        while(helloWorldTrigger != 0) {
        }

        return i;
    }

    public static int fib(int n) {
        return n < 2 ? 1 : fib(n - 1) + fib(n - 2);
    }

}
