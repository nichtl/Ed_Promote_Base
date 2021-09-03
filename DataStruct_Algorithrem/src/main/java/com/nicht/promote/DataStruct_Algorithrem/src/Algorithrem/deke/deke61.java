package com.nicht.promote.DataStruct_Algorithrem.src.Algorithrem.deke;

/**
 * @Author Nicht
 * @Description
 * @Time 2021/8/26
 * @Link
 */
public class deke61 {


    public static void main(String[] args) {
        String  str  = "AVERDXIVYERDIAN";
        String  p = "RDXI";
         if(str.length()<p.length()){
             System.out.println("No");
         }
         if(str.replaceAll(p,"@").length()<str.length()){
             String  temp=str.replaceAll(p,"@");
             System.out.println(temp.indexOf("@")+1);
         }else{
             System.out.println("No");
         }
    }
}
