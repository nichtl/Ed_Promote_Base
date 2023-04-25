package readBookTest;

import readBookTest.entity.User;

/**
 * @Description
 * @Date 2022/12/26
 */
public class test2 {

    
    public static void main(String[] args) {

     int minid= 2616 ;
     int maxid= 2684;

     StringBuilder sb = new StringBuilder();

        for (int i = minid; i <=maxid ; i++) {
            sb.append(i).append(",");
        }
        System.out.println(sb.substring(0, sb.length()-1));

    }
    
    

}
