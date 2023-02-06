package com.nicht.promote.DataStruct_Algorithrem.src;

import com.alibaba.fastjson.JSONObject;

import java.text.Collator;
import java.util.*;
import java.util.function.Function;

/**
 * @author Ni
 * @version 1.0
 * @date 2019/11/22 18:51
 */
public class Jva12 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            System.out.println(Integer.parseInt(sc.nextLine(), 10));
        }
//        list.sort(Comparator.comparing((Function<JSONObject, String>) obj->obj.getString("cityName"), Collator.getInstance(Locale.CHINA))
//                .thenComparing(obj->obj.getString("countyName"),Collator.getInstance(Locale.CHINA)));

    }


    public int maximumScore(int a, int b, int c) {
        List<Integer> scores = Arrays.asList(a, b, c);
        Arrays.sort(scores.toArray());

        if(scores.get(0) >=(scores.get(1)+scores.get(2))){
            return  scores.get(1)+scores.get(2);
        }
        return  scores.stream().reduce(0,(x,y) ->x+y).intValue()/2;


    }


}






