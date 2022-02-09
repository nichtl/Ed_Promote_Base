package com.nicht.promote.DataStruct_Algorithrem.src.Design_Patterns;

/**
 * @Author admin
 * @description
 * @ 2020/12/7
 */
public class Factory_Pattern {

    public static void main(String[] args) {
        String res ="{\"state\":{\"code\":0,\"success\":true,\"ok\":1},\"aid\":\"249b49cf222540bea5ab7b0ef15622d8\",\"lattices\":[{\"lid\":1,\"spk\":0,\"begin\":90,\"end\":2620,\"sc\":0.98,\"json_cn1best\":\"{\\\"bg\\\":90,\\\"ed\\\":2620,\\\"rl\\\":1,\\\"ls\\\":false,\\\"msgtype\\\":\\\"sentence\\\",\\\"sn\\\":1,\\\"pa\\\":0,\\\"sc\\\":\\\"0.98\\\",\\\"ws\\\":[{\\\"bg\\\":4,\\\"cw\\\":[{\\\"sf\\\":1,\\\"w\\\":\\\"\\u55EF\\\",\\\"wb\\\":4,\\\"wc\\\":\\\"0.9060\\\",\\\"we\\\":9,\\\"wp\\\":\\\"s\\\"}]},{\\\"bg\\\":10,\\\"cw\\\":[{\\\"sf\\\":0,\\\"w\\\":\\\"\\u5230\\\",\\\"wb\\\":10,\\\"wc\\\":\\\"0.9993\\\",\\\"we\\\":93,\\\"wp\\\":\\\"n\\\"}]},{\\\"bg\\\":94,\\\"cw\\\":[{\\\"sf\\\":0,\\\"w\\\":\\\"\\u767D\\\",\\\"wb\\\":94,\\\"wc\\\":\\\"0.9962\\\",\\\"we\\\":114,\\\"wp\\\":\\\"n\\\"}]},{\\\"bg\\\":115,\\\"cw\\\":[{\\\"sf\\\":0,\\\"w\\\":\\\"\\u5E99\\\",\\\"wb\\\":115,\\\"wc\\\":\\\"0.9962\\\",\\\"we\\\":149,\\\"wp\\\":\\\"n\\\"}]},{\\\"bg\\\":150,\\\"cw\\\":[{\\\"sf\\\":0,\\\"w\\\":\\\"\\u4E4B\\\",\\\"wb\\\":150,\\\"wc\\\":\\\"1.0000\\\",\\\"we\\\":166,\\\"wp\\\":\\\"n\\\"}]},{\\\"bg\\\":167,\\\"cw\\\":[{\\\"sf\\\":0,\\\"w\\\":\\\"\\u516B\\\",\\\"wb\\\":167,\\\"wc\\\":\\\"1.0000\\\",\\\"we\\\":201,\\\"wp\\\":\\\"n\\\"}]}]}\",\"onebest\":\"\\u5230\\u767D\\u5E99\\u4E4B\\u516B\"},{\"lid\":2,\"spk\":0,\"begin\":3110,\"end\":4500,\"sc\":0.67,\"json_cn1best\":\"{\\\"bg\\\":3110,\\\"ed\\\":4500,\\\"rl\\\":1,\\\"ls\\\":false,\\\"msgtype\\\":\\\"sentence\\\",\\\"sn\\\":2,\\\"pa\\\":0,\\\"sc\\\":\\\"0.67\\\",\\\"ws\\\":[{\\\"bg\\\":21,\\\"cw\\\":[{\\\"sf\\\":0,\\\"w\\\":\\\"\\u5B66\\u4E60\\\",\\\"wb\\\":21,\\\"wc\\\":\\\"1.0000\\\",\\\"we\\\":67,\\\"wp\\\":\\\"n\\\"}]},{\\\"bg\\\":68,\\\"cw\\\":[{\\\"sf\\\":0,\\\"w\\\":\\\"\\u6C47\\u62A5\\\",\\\"wb\\\":68,\\\"wc\\\":\\\"0.9998\\\",\\\"we\\\":134,\\\"wp\\\":\\\"n\\\"}]},{\\\"bg\\\":134,\\\"cw\\\":[{\\\"sf\\\":0,\\\"w\\\":\\\"\\u3002\\\",\\\"wb\\\":134,\\\"wc\\\":\\\"0.0000\\\",\\\"we\\\":134,\\\"wp\\\":\\\"p\\\"}]},{\\\"bg\\\":134,\\\"cw\\\":[{\\\"sf\\\":0,\\\"w\\\":\\\"\\\",\\\"wb\\\":134,\\\"wc\\\":\\\"0.0000\\\",\\\"we\\\":134,\\\"wp\\\":\\\"g\\\"}]}]}\",\"onebest\":\"\\u5B66\\u4E60\\u6C47\\u62A5\\u3002\"}]}\n";
        int [] nums = new int[]{3,6,1,0};
        System.out.println(dominantIndex(nums));
    }

    public static int dominantIndex(int[] nums) {
        int max1 = Integer.MIN_VALUE;
        int max2 = Integer.MIN_VALUE;
        int res=-1;
        if(nums.length==0) return 0;

        for (int i = 0,len= nums.length; i <len ; i++) {
            if(max1<nums[i]){
                max2=max1;
                max1=nums[i];
                res=i;
            }else if(max2<nums[i]){
                max2=nums[i];
            }
        }
        if(max1>=max2*2){
           return res;
        }
       return -1;
    }

    public int removeDuplicates(int[] nums) {
        if(nums.length<0) {return nums.length;}
         int p=0,q=1,res=0;
        return  1;
    }
}
