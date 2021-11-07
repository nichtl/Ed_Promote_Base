package com.nicht.spring_base.Aop;

import java.util.*;

/**
 * @Author Nicht
 * @Description
 * @Time 2021/10/12
 * @Link
 */
public class test {
    public static void main(String[] args) {
        Apple a = new Apple("apple");
        System.out.println(expandedForm(70304));
    }


    public static boolean comp(int[] a, int[] b) {
        Map<Integer,Integer> map  = new HashMap<>();
        for (int i: b) {
            if(map.containsKey(i)){
                map.put(i,map.get(i)+1);
            }
            else {
                map.put(i,1);
            }
        }
        for (int i = 0,len=a.length; i <len ; i++) {
            int  v= a[i]*a[i];
             if(!map.containsKey(v)||map.get(v)<=0){
                 return  false;
             }
             map.put(v,map.get(v)-1);
        }
        return  true;

    }

    public static String expandedForm(int num)
    {   int pos=1;
        ArrayList<Integer> nums = new ArrayList<>();
        while(num!=0){
            nums.add((num%10)*pos);
            pos*=10;
            num/=10;
        }
        Collections.sort(nums, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2-o1;
            }
        });
        StringBuilder sb = new StringBuilder();
        nums.forEach(n->{
            if(n!=0){
                sb.append(" + ").append(n);
            }
        });
        return sb.substring(3);



        //your code here
    }
}
