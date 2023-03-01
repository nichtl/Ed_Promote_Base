package com.nicht.promote.DataStruct_Algorithrem.src.Algorithrem.Leetcode.newCoder;

import java.util.*;

/**
 * @Description
 * @Date 2023/2/7
 */
public class alertNames {

    public List<String> alertNames(String[] keyName, String[] keyTime) {
        Map<String, List<Integer> > timeMap = new HashMap<String, List<Integer>>();
        for (int i = 0,len = keyName.length; i <len ; i++) {
            List<Integer> times = timeMap.get(keyName[i]);
            if (times == null){
                times = new ArrayList<Integer>();
            }
            String time  = keyTime[i];
            int hour = (time.charAt(0) - '0') * 10 + (time.charAt(1) - '0');
            int minute = (time.charAt(3) - '0') * 10 + (time.charAt(4) - '0');
            times.add(hour*60+minute);
            timeMap.put(keyName[i], times);
        }

        List<String> res = new ArrayList<String>();
        Set<String> keySet = timeMap.keySet();
        for (String name : keySet) {
            List<Integer> list = timeMap.get(name);
            Collections.sort(list);
            int size = list.size();
            for (int i = 2; i < size; i++) {
                int time1 = list.get(i - 2), time2 = list.get(i);
                int difference = time2 - time1;
                if (difference <= 60) {
                    res.add(name);
                    break;
                }
            }
        }
        Collections.sort(res);

        return res;
    }








}
