package com.nicht.promote.DataStruct_Algorithrem.src.Algorithrem.Leetcode;

import org.springframework.util.StringUtils;

import java.util.*;

/**
 * 功能描述：
 *
 * @author xujian8
 */

public class test {

    public static void main(String[] args) {

        String  testa  = "hahah--jjja-mkmsak-aanns-sasasa";
        System.out.println(testa.replace("-","_"));

        int [] widths = new int[]{10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10};
        String  s  = "abcdefghijklmnopqrstuvwxyz";
        System.out.println(Arrays.toString(numberOfLines(widths,s)));
        System.out.println((char) 97);
        List<String>  ts  = new ArrayList<>();
        ts.add("1"); ts.add("2"); ts.add("3"); ts.add("4"); ts.add("5");


        System.out.println(get(ts));

    }
    public static boolean get(List<String> list){
        boolean linksByteLenIsLegal = true;
        for (String  t:list) {
            if("2".equals(t)) {linksByteLenIsLegal=false;}
            if(!linksByteLenIsLegal) return false;
        }
        return true;
    }

    // 806 easy
    /**
     * 我们要把给定的字符串 S从左到右写到每一行上，每一行的最大宽度为100个单位，如果我们在写某个字母的时候会使这行超过了100 个单位，那么我们应该把这个字母写到下一行。我们给定了一个数组widths，这个数组widths[0] 代表 'a' 需要的单位，widths[1] 代表 'b' 需要的单位，...，widths[25] 代表 'z' 需要的单位。
     *
     * 现在回答两个问题：至少多少行能放下S，以及最后一行使用的宽度是多少个单位？将你的答案作为长度为2的整数列表返回。
     *
     * 示例 1:
     * 输入:
     * widths = [10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10]
     * S = "abcdefghijklmnopqrstuvwxyz"
     * 输出: [3, 60]
     * 解释:
     * 所有的字符拥有相同的占用单位10。所以书写所有的26个字母，
     * 我们需要2个整行和占用60个单位的一行。
     *
     * 示例 2:
     * 输入:
     * widths = [4,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10]
     * S = "bbbcccdddaaa"
     * 输出: [2, 4]
     * 解释:
     * 除去字母'a'所有的字符都是相同的单位10，并且字符串 "bbbcccdddaa" 将会覆盖 9 * 10 + 2 * 4 = 98 个单位.
     * 最后一个字母 'a' 将会被写到第二行，因为第一行只剩下2个单位了。
     * 所以，这个答案是2行，第二行有4个单位宽度。

     */
    public static int[] numberOfLines(int[] widths, String s) {
        // 构建 a -z  -> width[i] 映射表
        Map<Character,Integer>    letterIndex = new HashMap<>();
        for (int i = 97; i <123 ; i++) {
           letterIndex.put((char)i,widths[i-97]);
        }

        int rowMaxlen  = 100;
        // 行数
        int row  =1;
        // 最后一行长度
        int lastLen = 0;
        char [] strArr = s.toCharArray();
        for (int i = 0,len=strArr.length; i <len ; i++) {
            Integer letterLen = letterIndex.get(strArr[i]);
            if(lastLen+letterLen > rowMaxlen ){
                row+=1;
                lastLen=0;
            }
            lastLen+=letterLen;
        }

     return   new int[]{row,lastLen};
    }
   // 380 mid
    /**
     * 实现RandomizedSet 类：
     *
     * RandomizedSet() 初始化 RandomizedSet 对象
     * bool insert(int val) 当元素 val 不存在时，向集合中插入该项，并返回 true ；否则，返回 false 。
     * bool remove(int val) 当元素 val 存在时，从集合中移除该项，并返回 true ；否则，返回 false 。
     * int getRandom() 随机返回现有集合中的一项（测试用例保证调用此方法时集合中至少存在一个元素）。每个元素应该有 相同的概率 被返回。
     * 你必须实现类的所有函数，并满足每个函数的 平均 时间复杂度为 O(1) 。
     */
    class  RandomizedSet {
         HashMap<Integer, Integer>  map ;
         List<Integer>  nums;
         Random  random ;
         public RandomizedSet(){
           map = new HashMap<>();
           nums = new ArrayList<>();
           random = new Random();
         }

         public boolean insert(int val){
          if(map.containsKey(val)){
              return  false;
          }
          int index = nums.size();
          map.put(val,index);
          nums.add(val);
          return  true;
         }

         public boolean remove(int val){
          if(!map.containsKey(val)){
              return  false;
          }
          int index = map.get(val);
          int last  = nums.get(nums.size()-1);

          map.put(last,index);
          nums.set(index,last);

          map.remove(val);
          nums.remove(nums.size()-1);

          return true;

         }

         public  int  getRandom(){
          int randomIndex = random.nextInt(nums.size());
          return nums.get(randomIndex);
         }


    }
    // 1672 easy
    /**
     * 给你一个 m x n 的整数网格 accounts ，其中 accounts[i][j] 是第 i 位客户在第 j 家银行托管的资产数量。
     * 返回最富有客户所拥有的 资产总量 。
     * 客户的 资产总量 就是他们在各家银行托管的资产数量之和。最富有客户就是 资产总量 最大的客户。
     *
     * 输入：accounts = [[1,2,3],[3,2,1]]
     * 输出：6
     * 解释：
     * 第 1 位客户的资产总量 = 1 + 2 + 3 = 6
     * 第 2 位客户的资产总量 = 3 + 2 + 1 = 6
     * 两位客户都是最富有的，资产总量都是 6 ，所以返回 6 。
     *
     * 输入：accounts = [[1,5],[7,3],[3,5]]
     * 输出：10
     * 解释：
     * 第 1 位客户的资产总量 = 6
     * 第 2 位客户的资产总量 = 10
     * 第 3 位客户的资产总量 = 8
     * 第 2 位客户是最富有的，资产总量是 10
     * 
     * 输入：accounts = [[2,8,7],[7,1,3],[1,9,5]]
     * 输出：17
     */
    public int maximumWealth(int[][] accounts) {
     return    Arrays.stream(Arrays.stream(accounts).max(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return Integer.compare(Arrays.stream(o1).sum(),Arrays.stream(o2).sum());
            }
        }).get()).sum();
    }

    /**
     * 386. 字典序排数
     * @Description
     * 给你一个整数 n ，按字典序返回范围 [1, n] 内所有整数。
     * 你必须设计一个时间复杂度为 O(n) 且使用 O(1) 额外空间的算法。
     *
     * 输入：n = 13
     * 输出：[1,10,11,12,13,2,3,4,5,6,7,8,9]
     *
     * 输入：n = 2
     * 输出：[1,2]
     * @param n
     * @return
     *
     * 1,10,11,12,13   14  15 16 17 18
     *
     */
    public List<Integer> lexicalOrder(int n) {
      // 1 10
        List<Integer>  list  = new ArrayList<>();
        Integer num= 1;
        for (int i = 0; i < n; i++) {
            list.add(num);
          if(num*10 <=n){
              num*=10;
          }else {
              while (num +1 >n ||  num%10==9){
                  num/=10;
              }
              num++;
          }
        }
        return list;

    }








}
