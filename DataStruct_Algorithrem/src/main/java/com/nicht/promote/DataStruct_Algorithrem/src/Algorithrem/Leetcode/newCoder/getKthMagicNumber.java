package com.nicht.promote.DataStruct_Algorithrem.src.Algorithrem.Leetcode.newCoder;

//有些数的素因子只有 3，5，7，请设计一个算法找出第 k 个数。注意，不是必须有这些素因子，而是必须不包含其他的素因子。例如，前几个数按顺序应该是 1，3，
//5，7，9，15，21。
//
// 示例 1:
//
// 输入: k = 5
//
//输出: 9
//
//
// Related Topics 哈希表 数学 动态规划 堆（优先队列） 👍 146 👎 0

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.util.*;

/**
 * @Description
 * @Date 2022/9/28
 */
public class getKthMagicNumber {

    public static void main(String[] args) {



//        String   phones = "13911559800,13951859939,13616726818,13965141388,13914760179,13801509096,13816319777,18734385318,18851984925,13305753169,19923806900,18950449738,13716863222,13009600230,18645340377,13869889826,13801977702,13019809257,18017898572,13571627890,13939131317,13404369464,18648539619,13964076689,13141003346,15838818168,15879101031,18562526122,15831125636,13611592674,13992982999,13451179150,13611171091,13691289869,13904658963,18608986185,18690271878,13914457040,15221918288,13304015157,13985770247,13795177580,13892479723,18918129796,15541666811,18121024202,13579636999,13422056024,17770422099,13393416679,13920290091,13335393267,13718538193,18799701975,13995402081,13905526994,15065872885,15618283300,15062813887,18307051390,13816365965,18001871758,13982277882,13950317555,13312602427,18918630417,13379758152,13899085253,13906813753,13778321822,13985442408,13873892848,13808364218,13521394520,13911075848,13962606086,13865118177,13716609545,13901873750,13971417455,13702833636,15804186593,13320996313,13601901300,15160890155\n" +
//                "18047102211,15065183357,13301222923,13803286103,15558506777,13721242100,15911645501,13901304616,13991896785,18615597895,13916561774";


        String phones = "18923442705,13020200290";
        List<String> phoneList = Arrays.asList(phones.split(","));
        Map<String,Object> params = Maps.newHashMap();
        params.put("status",0);
        params.put("custId",0);
        params.put("sourceType",9);
        for (String num : phoneList) {
            params.put("phone_num",num);
           String res =  HttpUtil.post("http://crm.tuniu.org/muses-web-site/phone/insertPhoneNum.htm", JSON.toJSONString(params)
            );
        }


//        List<List<String>> countryList = Lists.partition(phoneList,20);
//        List<String>  type = Arrays.asList("10","11","12","14","16","9");
//        for (int i = 0; i < countryList.size() ; i++) {
//            String sourceType = type.get(i);
//            Map<String,Object> params = Maps.newHashMap();
//            params.put("status",0);
//            params.put("custId",0);
//            params.put("sourceType",sourceType);
//            for (String num : countryList.get(i)) {
//                params.put("phone_num",num);
//                HttpUtil.post("http://crm.tuniu.org/muses-web-site/phone/insertPhoneNum.htm", JSON.toJSONString(params)
//                );
//            }
//        }

    }
    public int getKthMagicNumber(int k) {
        String s = null;
        PriorityQueue<Integer>  priorityQueue = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1;
            }
        });
    return  0;
    }
    

   
}
