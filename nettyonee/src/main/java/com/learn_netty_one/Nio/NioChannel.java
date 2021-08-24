package com.learn_netty_one.Nio;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * @Author ni
 * @description
 * @Link http://tutorials.jenkov.com/java-nio/channels.html
 * @ 2021/3/15
 */
public class NioChannel {

    public final static boolean isNumeric(String s) {
        if (s != null && !"".equals(s.trim()))
            return s.matches("^[0-9]*$");
        else
            return false;
    }

    public static void main(String[] args) {
        String  s  = "20000A";
        System.out.println(isNumeric(s)?"yes":"no");
    }



    public NioChannel() throws IOException {
         throw  new FileNotFoundException("not_found_file");
    }
}




