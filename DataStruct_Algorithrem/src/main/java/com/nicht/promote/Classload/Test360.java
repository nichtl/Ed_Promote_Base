package com.nicht.promote.Classload;

import java.io.Serializable;
import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Collectors;

public class Test360 implements Serializable {
    public static void main(String[] args) {



        ExecutorService forkJoinPool= new ThreadPoolExecutor(10,100,10L,
                TimeUnit.SECONDS,new LinkedBlockingQueue<Runnable>(100),
                Executors.defaultThreadFactory(), new ThreadPoolExecutor.DiscardOldestPolicy());
        // 创建异步执行任务:
       // CompletableFuture.runAsync(()->{
        //    System.out.println(Fbonaci(10));
        //},forkJoinPool);
        /*CompletableFuture<Double> cf1 = CompletableFuture.supplyAsync(
                ()->{
                    System.out.println(Thread.currentThread()+" start job1,time->"+System.currentTimeMillis());
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                    }
                    System.out.println(Thread.currentThread()+" exit job1,time->"+System.currentTimeMillis());
                    return 1.2;
                }
                ,forkJoinPool);
        cf1.thenApplyAsync((result)->{
            System.out.println(Thread.currentThread()+" start job2,time->"+System.currentTimeMillis());
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
            }
            System.out.println(Thread.currentThread()+" exit job2,time->"+System.currentTimeMillis());

            return 1.3+result;
        },forkJoinPool);*/

 /*       Set<String> stringSet = new HashSet<>();
        stringSet.add("sa");
        stringSet.add("cd");
        System.out.println();
        System.out.println();
        String  s = "a #b\\nc\\nd $e f g";
        System.out.println(stripComments(s,new String[] { "#", "!" }));
        s=s.replaceAll("\n","");
       
        int [] a = new int[]{6,6,6,6};*/
        //System.out.println(distributeCandies(a));
    }
    public static  String admin(){
        return "1111111";
    }

    static String name="test";

    public static long[] productFib(long prod) {
        long a = 0L;
        long b = 1L;
        while (a * b < prod) {
            long tmp = a;
            a = b;
            b = tmp + b;
        }
        return new long[] { a, b, a * b == prod ? 1 : 0 };

    }
    public static String stripComments(String text, String[] commentSymbols) {
        String pattern = String.format(
                "[ ]*([%s].*)?$",
                Arrays.stream( commentSymbols ).collect( Collectors.joining() )
        );
        return Arrays.stream(text.split("\n"))
                .map(x->x.replaceAll(pattern,""))
                .collect(Collectors.joining("\n"));
    }
    public  static  int distributeCandies(int[] candyType) {
        HashMap<Integer,Integer>  map  = new HashMap();
        int count=0;
        for (int i = 0,len=candyType.length; i <len ; i++) {
            if(map.containsKey(candyType[i])){
                continue;
            };
            map.put(candyType[i],1);
            count++;
        }
       return  count<=candyType.length/2?count:candyType.length/2;
    }
    public  static long  Fbonaci(long n){
        return  n<=2?1:Fbonaci(n-1)+Fbonaci(n-2);
    }






    public String convert(String s, int numRows) {
        if(numRows<2) return s;
        ArrayList<StringBuilder>  arrayList  = new ArrayList<>();
        int i=0 ,flag=-1;
        for (int j = 0; j <numRows ; j++) {
            arrayList.add(new StringBuilder());
        }
        for (char c:s.toCharArray()) {
            arrayList.get(i).append(c);
            if(i==0||i==numRows-1) flag=-flag;
            i+=flag;
        }
        StringBuilder sb  = new StringBuilder();
        for (int j = 0,len=arrayList.size(); j <len ; j++) {
            sb.append(arrayList.get(j).toString());
        }
        return sb.toString();
    }

    public int maxArea(int[] height) {
        int i = 0, j = height.length - 1, res = 0;
        while (i<j){
            res = height[i]<height[j]?
            Math.max(res,(j-i)*(height[i++])): Math.max(res,(j-i)*(height[j--]));
        }
        return  res;
    }

    public static long ipsBetween(String start, String end) {


        return -1;
    }
//
//  select 15010695020 as phone from dual   union all
// select 13911559800 as phone from dual   union all
// select 13951859939 as phone from dual   union all
// select 15826546810 as phone from dual   union all
// select 18115615986 as phone from dual   union all
// select 15021200635 as phone from dual   union all
// select 18965190396 as phone from dual   union all
// select 13616726818 as phone from dual   union all
// select 13965141388 as phone from dual   union all
// select 13914760179 as phone from dual   union all
// select 13431015595 as phone from dual   union all
// select 13813380512 as phone from dual   union all
// select 13817577449 as phone from dual   union all
// select 13801509096 as phone from dual   union all
// select 13816319777 as phone from dual   union all
// select 18734385318 as phone from dual   union all
// select 18116172457 as phone from dual   union all
// select 18851984925 as phone from dual   union all
// select 13305753169 as phone from dual   union all
// select 18701453719 as phone from dual   union all
// select 15775992698 as phone from dual   union all
// select 19923806900 as phone from dual   union all
// select 13394050587 as phone from dual   union all
// select 18825249692 as phone from dual   union all
// select 18950449738 as phone from dual   union all
// select 13716863222 as phone from dual   union all
// select 13009600230 as phone from dual   union all
// select 18645340377 as phone from dual   union all
// select 13869889826 as phone from dual   union all
// select 13801977702 as phone from dual   union all
// select 13019809257 as phone from dual   union all
// select 18017898572 as phone from dual   union all
// select 13571627890 as phone from dual   union all
// select 13939131317 as phone from dual   union all
// select 13404369464 as phone from dual   union all
// select 18648539619 as phone from dual   union all
// select 13964076689 as phone from dual   union all
// select 13141003346 as phone from dual   union all
// select 15838818168 as phone from dual   union all
// select 15879101031 as phone from dual   union all
// select 18562526122 as phone from dual   union all
// select 15831125636 as phone from dual   union all
// select 13611592674 as phone from dual   union all
// select 13992982999 as phone from dual   union all
// select 13451179150 as phone from dual   union all
// select 13611171091 as phone from dual   union all
// select 13691289869 as phone from dual   union all
// select 13904658963 as phone from dual   union all
// select 18608986185 as phone from dual   union all
// select 18690271878 as phone from dual   union all
// select 13914457040 as phone from dual   union all
// select 15221918288 as phone from dual   union all
// select 13304015157 as phone from dual   union all
// select 13985770247 as phone from dual   union all
// select 13795177580 as phone from dual   union all
// select 13892479723 as phone from dual   union all
// select 18918129796 as phone from dual   union all
// select 15541666811 as phone from dual   union all
// select 18121024202 as phone from dual   union all
// select 13579636999 as phone from dual   union all
// select 13422056024 as phone from dual   union all
// select 17770422099 as phone from dual   union all
// select 13393416679 as phone from dual   union all
// select 13920290091 as phone from dual   union all
// select 13335393267 as phone from dual   union all
// select 13718538193 as phone from dual   union all
// select 18799701975 as phone from dual   union all
// select 13995402081 as phone from dual   union all
// select 13905526994 as phone from dual   union all
// select 15065872885 as phone from dual   union all
// select 15618283300 as phone from dual   union all
// select 15062813887 as phone from dual   union all
// select 18307051390 as phone from dual   union all
// select 13816365965 as phone from dual   union all
// select 18001871758 as phone from dual   union all
// select 13982277882 as phone from dual   union all
// select 13950317555 as phone from dual   union all
// select 13312602427 as phone from dual   union all
// select 18918630417 as phone from dual   union all
// select 13379758152 as phone from dual   union all
// select 13899085253 as phone from dual   union all
// select 13906813753 as phone from dual   union all
// select 13778321822 as phone from dual   union all
// select 13985442408 as phone from dual   union all
// select 13873892848 as phone from dual   union all
// select 13808364218 as phone from dual   union all
// select 13521394520 as phone from dual   union all
// select 13911075848 as phone from dual   union all
// select 13962606086 as phone from dual   union all
// select 13865118177 as phone from dual   union all
// select 13716609545 as phone from dual   union all
// select 13901873750 as phone from dual   union all
// select 13971417455 as phone from dual   union all
// select 13702833636 as phone from dual   union all
// select 15804186593 as phone from dual   union all
// select 13320996313 as phone from dual   union all
// select 13601901300 as phone from dual   union all
// select 15160890155 as phone from dual   union all
// select 18047102211 as phone from dual   union all
// select 15065183357 as phone from dual   union all
// select 13301222923 as phone from dual   union all
// select 13803286103 as phone from dual   union all
// select 15558506777 as phone from dual   union all
// select 13721242100 as phone from dual   union all
// select 15911645501 as phone from dual   union all
// select 13901304616 as phone from dual   union all
// select 13991896785 as phone from dual   union all
// select 18615597895 as phone from dual   union all
// select 15869121507 as phone from dual   union all
// select 17721424337 as phone from dual   union all
// select 18055068551 as phone from dual   union all
// select 18955102685 as phone from dual   union all
// select 13241885963 as phone from dual   union all
// select 15215187773 as phone from dual   union all
// select 18188108029 as phone from dual   union all
// select 13567236744 as phone from dual   union all
// select 15653893966 as phone from dual   union all
// select 13578766346 as phone from dual   union all
// select 18083239936 as phone from dual   union all
// select 15002839388 as phone from dual   union all
// select 13817161168 as phone from dual   union all
// select 13601389416 as phone from dual   union all
// select 13302186993 as phone from dual   union all
// select 13402445862 as phone from dual   union all
// select 13601631596 as phone from dual   union all
// select 13033400618 as phone from dual   union all
// select 18630977766 as phone from dual   union all
// select 13724800539 as phone from dual   union all
// select 18721444782 as phone from dual   union all
// select 13916561774 as phone from dual   union all
// select 18121236255 as phone from dual   union all
// select 18921884686 as phone from dual   union all
// select 13838536637 as phone from dual   union all
// select 18611015869 as phone from dual   union all
// select 15944851500 as phone from dual   union all
// select 13935146510 as phone from dual   union all
// select 13833424255 as phone from dual   union all
// select 13131503031 as phone from dual   union all
// select 15943816824 as phone from dual   union all
// select 13519448317 as phone from dual   union all
// select 15899035689 as phone from dual   union all
// select 13804874493 as phone from dual   union all
// select 13985389292 as phone from dual   union all
// select 13718188074 as phone from dual   union all
// select 13797067588 as phone from dual   union all
// select 18966736377 as phone from dual   union all
// select 13772561899 as phone from dual   union all
// select 13582450495 as phone from dual   union all
// select 13325545222 as phone from dual   union all
// select 13813002490 as phone from dual   union all
// select 13705150867 as phone from dual   union all
// select 17712397902 as phone from dual   union all
// select 13372236026 as phone from dual   union all
// select 18240315548 as phone from dual   union all
// select 13311624599 as phone from dual   union all
// select 13954511056 as phone from dual   union all
// select 13903858427 as phone from dual   union all
// select 18118282991 as phone from dual   union all
// select 13901702850 as phone from dual   union all
// select 13168778280 as phone from dual   union all
// select 13851540667 as phone from dual   union all
// select 13892986652 as phone from dual   union all
// select 15706484333 as phone from dual   union all
// select 18920251125 as phone from dual   union all
// select 13947333132 as phone from dual   union all
// select 15107218661 as phone from dual   union all
// select 13623668013 as phone from dual   union all
// select 13305011628 as phone from dual   union all
// select 13992022776 as phone from dual   union all
// select 13244080685 as phone from dual   union all
// select 15026915288 as phone from dual   union all
// select 13717758127 as phone from dual   union all
// select 13521797297 as phone from dual   union all
// select 15021827028 as phone from dual   union all
// select 15533545581 as phone from dual   union all
// select 13805268920 as phone from dual   union all
// select 18925116168 as phone from dual   union all
// select 13595533548 as phone from dual   union all
// select 15928696217 as phone from dual   union all
// select 18691799648 as phone from dual   union all
// select 15589380509 as phone from dual   union all
// select 13303417051 as phone from dual   union all
// select 13154868262 as phone from dual   union all
// select 13738184842 as phone from dual   union all
// select 15712894186 as phone from dual   union all
// select 13940337483 as phone from dual   union all
// select 18600670639 as phone from dual   union all
// select 18782781513 as phone from dual   union all
// select 13843226621 as phone from dual   union all
// select 13838587138 as phone from dual   union all
// select 13941608077 as phone from dual   union all
// select 13392852751 as phone from dual   union all
// select 18678787864 as phone from dual   union all
// select 18180121936 as phone from dual   union all
// select 18956209269 as phone from dual   union all
// select 18537909807 as phone from dual   union all
// select 13817315225 as phone from dual   union all
// select 13905295511 as phone from dual   union all
// select 13752518149 as phone from dual   union all
// select 13504064888 as phone from dual   union all
// select 13032233346 as phone from dual   union all
// select 13331931263 as phone from dual   union all
// select 13371987136 as phone from dual   union all
// select 15951995709 as phone from dual   union all
// select 13850128338 as phone from dual







    

}
