package com.nicht.promote.DataStruct_Algorithrem.src.Algorithrem.codewars.kata;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

/**
 * 功能描述：
 *
 * @author xujian8
 */
//"7777...8?!??!",
// exclaimed Bob, "I missed it again! Argh!"
// Every time there's an interesting number coming up,
// he notices and then promptly forgets.
// Who doesn't like catching those one-off interesting mileage numbers?
//Let's make it so Bob never misses another interesting number. We've hacked into his car's computer, and we have a box hooked up that reads mileage numbers. We've got a box glued to his dash that lights up yellow or green depending on whether it receives a 1 or a 2 (respectively).
//It's up to you, intrepid warrior, to glue the parts together. Write the function that parses the mileage number input, and returns a 2 if the number is "interesting" (see below), a 1 if an interesting number occurs within the next two miles, or a 0 if the number is not interesting.
//Note: In Haskell, we use No, Almost and Yes instead of 0, 1 and 2.
//"Interesting" Numbers
//Interesting numbers are 3-or-more digit numbers that meet one or more of the following criteria:
//Any digit followed by all zeros: 100, 90000
//Every digit is the same number: 1111
//The digits are sequential, incementing†: 1234
//The digits are sequential, decrementing‡: 4321
//The digits are a palindrome: 1221 or 73837
//The digits match one of the values in the awesomePhrases array
//† For incrementing sequences, 0 should come after 9, and not before 1, as in 7890.
//‡ For decrementing sequences, 0 should co
//
//
// me after 1, and not before 9, as in 3210.
public class CarMileage {

    public static void main(String[] args) {

//         List<CustDto>  a = new ArrayList<>();
//         CustDto   h  = new CustDto();
//
//         a.add(h);
//         a= a.stream().filter(t->null != t.getName()).collect(Collectors.toList());
//         List<String>  c =  new CopyOnWriteArrayList(a);
//         c.add("2");
//        System.out.println(c.toString());
        }
    private static final int minInterestingNum = 100;
    public static int isInteresting(int number, int[] awesomePhrases) {
        if(number< minInterestingNum) {return 0;}
         return  0;
    }

    public  boolean oneZeroFllowByAllZero(int number ){
        return  false;
    }
    public boolean sameDigit(int number){
                   return  false;
    }

    public  boolean incrementing(int number){
        return  false;
    }
    public boolean decrementing(int number){
        return      false;
    }
    public boolean palindrome(int number){
        return  false;
    }
    public  boolean matchArray(int number){
    return  false;
    }





}
