package com.nicht.promote.fileservice.Utils;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @Author ni
 * @description
 * @ 2021/2/26
 */
public class FileUtils {

    public  String  uploadSnapFile(File uploadFile, String savePath, String FileName){







    return null ;
    }
    public  static Integer getMonthNum(String month){
        Date date = null;
        try {
            SimpleDateFormat  simpleDateFormat = new SimpleDateFormat("yyyyMM");
             date = simpleDateFormat.parse(month);
        }catch (ParseException e){e.printStackTrace();}

        return date.getMonth()+1;
    }
    public static void main(String[] args) throws ParseException {

        String hexString = "42";

        Long l = FileUtils.parseLong(hexString, 16);
        Float f = Float.intBitsToFloat(l.intValue());

        System.out.println(hexString);
        System.out.println(l);
        System.out.println(f);
        System.out.println(Integer.toHexString(Float.floatToIntBits(f)));

        Integer i = Integer.parseInt(hexString, 16);
        f = Float.intBitsToFloat(i.intValue());
        System.out.println("");
        System.out.println(i);
        System.out.println(f);
        System.out.println(Integer.toHexString(Float.floatToIntBits(f)));

        hexString = "-c6105cec";
        l = FileUtils.parseLong(hexString, 16);
        f = Float.intBitsToFloat(l.intValue());
        System.out.println("");
        System.out.println(hexString);
        System.out.println(l);
        System.out.println(f);
        System.out.println(Integer.toHexString(Float.floatToIntBits(f))); // 使用 Long 会输出：ffffffffc6105cec

        i = Integer.parseInt(hexString, 16); // 使用 Integer 会抛异常： java.lang.NumberFormatException: For input string: "c6105cec"
        f = Float.intBitsToFloat(i.intValue());
        System.out.println("");
        System.out.println(i);
        System.out.println(f);
        System.out.println(Integer.toHexString(Float.floatToIntBits(f)));




    }
    public static long parseLong(String s, int radix) throws NumberFormatException {
        if (s == null) {
            throw new NumberFormatException("null");
        }

        if (radix < Character.MIN_RADIX) {
            throw new NumberFormatException("radix " + radix + " less than Character.MIN_RADIX");
        }
        if (radix > Character.MAX_RADIX) {
            throw new NumberFormatException("radix " + radix + " greater than Character.MAX_RADIX");
        }

        long result = 0;
        boolean negative = false;
        int i = 0, len = s.length();
        long limit = -Long.MAX_VALUE;
        long multmin;
        int digit;

        if (len > 0) {
            char firstChar = s.charAt(0);
            if (firstChar < '0') { // Possible leading "+" or "-"
                if (firstChar == '-') {
                    negative = true;
                    limit = Long.MIN_VALUE;
                } else if (firstChar != '+')
                    throw NumberFormatException.forInputString(s);

                if (len == 1) // Cannot have lone "+" or "-"
                    throw NumberFormatException.forInputString(s);
                i++;
            }
            multmin = limit / radix;
            while (i < len) {
                // Accumulating negatively avoids surprises near MAX_VALUE
                digit = Character.digit(s.charAt(i++), radix);
                if (digit < 0) {
                    throw NumberFormatException.forInputString(s);
                }
                if (result < multmin) {
                    throw NumberFormatException.forInputString(s);
                }
                result *= radix;
                if (result < limit + digit) {
                    throw NumberFormatException.forInputString(s);
                }
                result -= digit;
            }
        } else {
            throw NumberFormatException.forInputString(s);
        }
        return negative ? result : -result;
    }


}

class NumberFormatException extends IllegalArgumentException {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public NumberFormatException(String s) {
        super(s);
    }

    static NumberFormatException forInputString(String s) {
        return new NumberFormatException("For input string: \"" + s + "\"");
    }
}













