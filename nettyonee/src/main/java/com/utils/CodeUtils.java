package com.utils;

import java.util.List;

/**
 * @Author ni
 * @description
 * @ 2021/2/19
 */
public class CodeUtils {
    /**
     * List是否为NULL
     * @param list
     * @return isNull true  NotNull false
     */
    public static  boolean listIsNull(List list){
        if (list == null || list.isEmpty() || list.size() ==0 || list.get(0) ==null ){
            return true;
        }
        return false;
    }

    /**
     * 对象是否不为NULL
     *
     * @param param Object
     * @return Null:FALSE ; NotNull : TRUE
     */
    public static boolean isNotNull(Object param) {
        return null != param;
    }

    public static  void pt(Integer n){
        Double c = Math.floor(n/2);
        System.out.println(c);
        Integer l =c.intValue();


        for (int i = 1; i < l ; i+=2) {
            if(i%2 !=0){
                pr(i,l);
            }


        }
        System.out.println();

    }

    /* if(i==c.intValue()){
                for (int j = i; j <0 ; j--) {
                    if(i%2 >0){
                        pr(j,i);
                    }
                }
                break;
            }*/
    public static void pr(Integer n,int maxn) {
        int d = maxn-n;

        if(d>0){
            for (int i = d; i >0 ; i--) {
                System.out.print(" ");
            }
        }
        if(d==0){ System.out.print(" ");}
        for (int i = 0; i < n; i++) {
            System.out.print("*");
        }
        System.out.println();
    }

    public static int myAtoi(String s) {

        if(s==null||s.length()==0 || s.equals("null")){return 0;}
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < s.length(); i++) {
            if(s.charAt(i)==43)continue;
            if( (s.charAt(i)<48 || s.charAt(i)>58 )&&s.charAt(i)!=45){break;}

            sb.append(s.charAt(i));
        }
        if(sb.length()>0) {
            long res  = Long.valueOf(sb.toString());
            if( res>= Integer.MAX_VALUE || res<=Integer.MIN_VALUE) {
                return  res<0 ? Integer.MIN_VALUE : Integer.MAX_VALUE;
            }
            else {return Integer.parseInt(sb.toString());}
        }

        return 0;
    }
    public static int StrToInt(String s,boolean isFs){
        if( Long.getLong(s)>= Integer.MAX_VALUE || Long.getLong(s)<=Integer.MIN_VALUE) {
            return  isFs ? Integer.MIN_VALUE : Integer.MAX_VALUE;
        }
        System.out.println(s);
        return  isFs?(-1)*Integer.parseInt(s):Integer.parseInt(s);
    }public static String toUTF8(String str) {
        if (isEmpty(str)) {
            return "";
        }
        try {
            if (str.equals(new String(str.getBytes("GB2312"), "GB2312"))) {
                str = new String(str.getBytes("GB2312"), "utf-8");
                return str;
            }
        } catch (Exception exception) {
        }
        try {
            if (str.equals(new String(str.getBytes("ISO-8859-1"), "ISO-8859-1"))) {
                str = new String(str.getBytes("ISO-8859-1"), "utf-8");
                return str;
            }
        } catch (Exception exception1) {
        }
        try {
            if (str.equals(new String(str.getBytes("GBK"), "GBK"))) {
                str = new String(str.getBytes("GBK"), "utf-8");
                return str;
            }
        } catch (Exception exception3) {
        }
        return str;
    }


    /**
     * 判断是否为空
     *
     * @param str
     * @return
     */
    public static boolean isEmpty(String str) {
        // 如果字符串不为null，去除空格后值不与空字符串相等的话，证明字符串有实质性的内容
        if (str != null && !str.trim().isEmpty()) {
            return false;// 不为空
        }
        return true;// 为空
    }


  /*  public static void main(String[] args) throws Throwable, ExecutionException {
         ExecutorService executors   = Executors.newFixedThreadPool(10);
         ForkJoinPool forkJoinPool = new ForkJoinPool(10);
        // 创建异步执行任务:
        CompletableFuture<Double> cf1 = CompletableFuture.supplyAsync(
                ()->{
                    System.out.println(Thread.currentThread()+" start job1,time->"+System.currentTimeMillis());
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                    }
                    System.out.println(Thread.currentThread()+" exit job1,time->"+System.currentTimeMillis());
                    return 1.2;
                }
                ).
                thenApplyAsync((result)->{
            System.out.println(Thread.currentThread()+" start job2,time->"+System.currentTimeMillis());
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
            }
            System.out.println(Thread.currentThread()+" exit job2,time->"+System.currentTimeMillis());

            return 1.3+result;
        });
        System.out.println("等待结果异步");
    }
*/

    public static void main(String[] args) {

        String  us  = "{'sa':sasaa}";
        System.out.println("%5B%7B%22alarm_code%22%3A%220%22%2C%22coord_type%22%3A%22bd09%22%2C%22power%22%3A%2230%22%2C%22sn%22%3A%22863995151343974%22%2C%22oui%22%3A%22ZKNF%22%2C%22timestamp%22%3A%221626665616029%22%2C%22lat%22%3A%2222.57362%22%2C%22loc_type%22%3A%221%22%2C%22lng%22%3A%22113.872765%22%2C%22address%22%3A%22123%22%7D%5D");
        System.out.println(toUTF8(us));
    }


}
