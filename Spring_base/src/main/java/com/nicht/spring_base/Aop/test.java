package com.nicht.spring_base.Aop;


import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.io.watch.WatchUtil;
import org.springframework.util.ObjectUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @Author Nicht
 * @Description
 * @Time 2021/10/12
 * @Link
 */
public class test {
    public static void main(String[] args) {
        List<TreeModel> list = new ArrayList<>();
        list.add(new TreeModel(1L,"测试1", null));
        list.add(new TreeModel(2L,"测试2", null));
        list.add(new TreeModel(3L,"测试3",1L));
        list.add(new TreeModel(4L,"测试4",1L));
        list.add(new TreeModel(5L,"测试5",3L));
        list.add(new TreeModel(6L,"测试6",5L));
        list.add(new TreeModel(7L,"测试7",2L));
        list.add(new TreeModel(8L,"测试8",7L));
        System.out.println(Arrays.toString(list.stream().filter(t-> t.getParentId()!=null&&String.valueOf(t.getParentId()).equals(String.valueOf(1L)) ).toArray()));

        List<TreeModel> res = buildTree(list,"id","parentId","children");
        System.out.println(Arrays.toString(res.toArray()));



    /*    Apple  a  = new Apple();
        System.out.println(pigIt("Hello world !"));
        System.out.println(expandedForm(70304));*/
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

    public static String pigIt(String str) {
        // Write code here
       return Arrays.stream(str.split(" ")).map(x-> {
                    if (Character.isLetter(x.charAt(0))){
                        x = new String(x.substring(1) + x.charAt(0) + "ay"); }return x; }).collect(Collectors.joining(" "));
    }

    public static <T> List<T>  buildTree(List<T> list, String idFieldName, String parentFieldName, String childrenFieldName){
        return buildTree(list,idFieldName,parentFieldName,childrenFieldName,null);
    }
    public static <T> List<T> buildTree(List<T>  list,String idFieldName,String parentFieldName,String childrenFieldName,String parentIdValue){
        return   list.stream().map(node->{
            List  value = list.stream().filter(t -> BeanUtil.getFieldValue(t,parentFieldName)!=null &&
                    String.valueOf(BeanUtil.getFieldValue(t,parentFieldName)).equals(String.valueOf(BeanUtil.getFieldValue(node,idFieldName))) ).collect(Collectors.toList());
            BeanUtil.setFieldValue(node,childrenFieldName,value);
           return  node;
        }).collect(Collectors.toList());
    }

}
