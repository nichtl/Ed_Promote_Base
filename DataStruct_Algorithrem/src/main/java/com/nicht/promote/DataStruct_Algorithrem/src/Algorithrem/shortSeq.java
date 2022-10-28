package com.nicht.promote.DataStruct_Algorithrem.src.Algorithrem;

//假设你有两个数组，一个长一个短，短的元素均不相同。找到长数组中包含短数组所有的元素的最短子数组，其出现顺序无关紧要。
//
// 返回最短子数组的左端点和右端点，如有多个满足条件的子数组，返回左端点最小的一个。若不存在，返回空数组。
//
// 示例 1:
//
// 输入:
//big = [7,5,9,0,2,1,3,5,7,9,1,1,5,8,8,9,7]
//small = [1,5,9]
//输出: [7,10]
//
// 示例 2:
//
// 输入:
//big = [1,2,3]
//small = [4]
//输出: []
//
// 提示：
//
//
// big.length <= 100000
// 1 <= small.length <= 100000
//
// Related Topics 数组 哈希表 滑动窗口 👍 57 👎 0


import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.stream.Collectors;

//leetcode submit region begin(Prohibit modification and deletion)
class shortSeq {
    public static void main(String[] args) {
        int[] big = new int[]{1, 2, 1, 2, 1, 2};
        int[] small = new int[]{1,2};
        System.out.println(shortestSeq(big,small));
    }


    public static int[] shortestSeq(int[] big, int[] small) {
           List<int []> results = new ArrayList<>();
            int [] res = new int[]{};
            int left=-1;
            int right=-1;
           Map<Integer, Integer >  baseshortMap = new LinkedHashMap<>();
            for (int i = 0; i <small.length; i++) {
                baseshortMap.put(small[i],i);
            }

           Map<Integer, Integer >  shortMap = new LinkedHashMap<>(baseshortMap);

            Integer min = Integer.MAX_VALUE;


            for  (int i = 0; i < big.length; i++) {
                 if(left==-1&&shortMap.containsKey(big[i])){
                     left = i;
                 }
                 if(shortMap.size() == 1 && shortMap.containsKey(big[i])){
                     shortMap.remove(big[i]);
                     right = i;
                 }
                 if(shortMap.containsKey(big[i])){
                     shortMap.remove(big[i]);
                 }
                 if(shortMap.size()==0){
                     if(min>(right-left)){
                         min=right-left;
                         res= new int[]{left,right};
                     }
                     if(right< big.length){
                         left =-1;
                         right=-1;
                         shortMap = new LinkedHashMap<>(baseshortMap);
                     }
                 }

            }

           return  res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
