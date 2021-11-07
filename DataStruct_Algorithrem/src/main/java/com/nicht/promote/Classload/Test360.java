package com.nicht.promote.Classload;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

public class Test360 implements Serializable {
    public static void main(String[] args) {
        Set<String> stringSet = new HashSet<>();
        stringSet.add("sa");
        stringSet.add("cd");
        System.out.println();
        System.out.println();
        String  s = "a #b\\nc\\nd $e f g";
        System.out.println(stripComments(s,new String[] { "#", "!" }));
        s=s.replaceAll("\n","");
       
        int [] a = new int[]{6,6,6,6};
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

    
}
