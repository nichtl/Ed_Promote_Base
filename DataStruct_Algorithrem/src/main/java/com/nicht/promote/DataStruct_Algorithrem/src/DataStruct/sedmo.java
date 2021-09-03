package com.nicht.promote.DataStruct_Algorithrem.src.DataStruct;
/**
 * @author Ni
 * @version 1.0
 * @date 2019/11/22 17:26
 */
public class sedmo {


    public static void main(String[] args) {
        String  s ="kohellom";
          Get get = new Get();
                   int m =get.checkgog(s,0,s.length());  //边界值

         System.out.println(m);


    }


    static class  Get {
    public  void CaculateWeekDay(int y, int m, int d) {
        if (m <= 2) {
            m += 12;
            y--;
        }
        int iWeek = (d + 2 * m + 3 * (m + 1) / 5 + y + y / 4 - y / 100 + y / 400) % 7;
        switch (iWeek) {
            case 0:
                System.out.printf("星期一\n");
                break;
            case 1:
                System.out.printf("星期二\n");
                break;
            case 2:
                System.out.printf("星期三\n");
                break;
            case 3:
                System.out.printf("星期四\n");
                break;
            case 4:
                System.out.printf("星期五\n");
                break;
            case 5:
                System.out.printf("星期六\n");
                break;
            case 6:
                System.out.printf("星期日\n");
                break;
        }
    }
    //abdebak
    // f j k jl ks j g
    public  static  int checkgog(String s,int bt, int tb){
        int max  =0;
        int last =0 ;
        int disable = 0; //用来记录本次丢弃的字符
    /*    if(s.length()<=2){
            return disable;
        }*/
        for(int i =bt ; i<s.length()-1;i++){
          for (int j =tb-1;j>0;j--){
              if(s.substring(i,i+1) == s.substring(j-1,j)){
                        max=i;
                        last =j;
                        disable += s.length()-s.substring(max,last+1).length(); //获得本次丢弃数
              }
          }
        }

        return  disable;
    }


}

}