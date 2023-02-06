package com.nicht.promote.DataStruct_Algorithrem.src.Algorithrem.Leetcode.newCoder;

import org.springframework.util.CollectionUtils;

import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * @Description
 * @Date 2022/12/22
 */
public class similarpar {

    public static void main(String[] args) {
       String [] s =  new String[]{"aba","aabb","abcd","bac","aabc"};
         similarPairs( s );
    }


    public  static  int similarPairs(String[] words) {
        int res = 0 ;
        List<String>  stringList = Arrays.asList(words);
        if(CollectionUtils.isEmpty(stringList)){
            return  res;
        }
        for (int i = 0; i <  stringList.size(); i++) {

            for (int j = i+1; j < stringList.size(); j++) {
                if(isSimilar(stringList.get(i),stringList.get(j))){
                    res++;
                }
            }
        }
        return  res;






    }

    public static  boolean isSimilar(String a, String b) {



    return  false;
    }


}
