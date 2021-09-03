package com.nicht.promote.DataStruct_Algorithrem.src.DataStruct;
import java.util.Scanner;
/**
 * @author Ni
 * @version 1.0
 * @date 2019/11/22 17:26
 */
public class Stack {


        public static void main(String[] args){
//声明循环条件
            Scanner in=new Scanner(System.in);
            String name="Y";
            do{
//输入年月
                System.out.println("请输入年份：");
                int newyear=in.nextInt();
                System.out.println("请输入月份：");
                int newmonth=in.nextInt();

//计算总共的天数，下边需要计算一共度过了多少个星期以求出某月一号星期几
                int sum=0;
                for(int year=1900;year<newyear;year++){
                    if(newyear%4==0&&newyear%100!=0||newyear%400==0){
                        sum+=366;
                    }else{
                        sum+=365;
                    }
                }
                for(int month=1;month<newmonth;month++){
                    if(month==2){
                        if(newyear%4==0&&newyear%100!=0||newyear%400==0){
                            sum+=29;
                        }else{
                            sum+=28;
                        }
                    }else{
                        if(month==4||month==6||month==9||month==11){
                            sum+=30;
                        }else{
                            sum+=31;
                        }
                    }
                }
//表头及第一行空格
                sum+=1;
                int wekday=sum%7;
                System.out.println("日\t一\t二\t三\t四\t五\t六");
                for(int i=1;i<=wekday;i++){
                    System.out.print("\t");
                }
//表头以下部分
                int j;
                if(newyear%4==0&&newyear%100!=0||newyear%400==0){
                    if(newmonth==2){
                        j=29;
                    }else{
                        if(newmonth==4||newmonth==6||newmonth==9||newmonth==11){
                            j=30;
                        }else{
                            j=31;
                        }
                    }
                }else{
                    if(newmonth==2){
                        j=28;
                    }else{
                        if(newmonth==4||newmonth==6||newmonth==9||newmonth==11){
                            j=30;
                        }else{
                            j=31;
                        }
                    }
                }
                for(int i=1;i<=j;i++){
                    if(sum%7==0){
                        System.out.print("\n"+i+"\t");
                    }else{
                        System.out.print(i+"\t");
                    }
                    sum++;
                }
                System.out.println();
                System.out.println("是否继续查询（输入N退出）？");
                name=in.next();
            }while(!name.equals("N"));
        }
    }


