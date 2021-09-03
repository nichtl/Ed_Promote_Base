package com.nicht.promote.DataStruct_Algorithrem.src.Algorithrem;

import java.util.HashMap;

/**
 * @Author Nicht
 * @Description
 * @Time 2021/8/26
 * @Link
 */
public class worddesc {
    public static void main(String[] args) {
        String str = "abc";
        HashMap<Character,Integer> map  = new HashMap<>();
        int sum = 0;
        for(int i =0;i<str.length();i++){
            if(!map.containsKey(str.charAt(i))){
                map.put(str.charAt(i),i);
                sum+=1;
            }
        }
        System.out.println(sum);



        
    }
    
    
    
    
    
    
}
