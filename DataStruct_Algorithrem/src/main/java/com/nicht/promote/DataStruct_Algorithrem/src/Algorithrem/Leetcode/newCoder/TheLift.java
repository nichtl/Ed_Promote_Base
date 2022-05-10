package com.nicht.promote.DataStruct_Algorithrem.src.Algorithrem.Leetcode.newCoder;

import java.util.*;

/**
 * 功能描述：
 * codeWars theLift 3kyu
 *
 * @author nicht
 */
public class TheLift {
    public static int[] theLift(final int[][] queues, final int capacity) {
        if(queueIsEmpty(queues)){return new int[]{0}; }
        List<Integer> theLift  = new ArrayList<>(capacity);
        List<Integer> theLiftStopRecord  = new ArrayList<>();
        Boolean  isUp =Boolean.TRUE;
        int curFloor = 0;
        while (true){
            // when theLift isEmpty &&  nobody waiting for theLift
            if(theLift.isEmpty()&&queueIsEmpty(queues)) {
                // when  theLift  stop curFloor is not first floor
                if(curFloor!=0){theLiftStopRecord.add(0);}
                break;
            }
            if(canStopLift(queues,curFloor,theLift,isUp)) { doTakeTheLift(queues,capacity,curFloor,theLift,theLiftStopRecord,isUp);}
            if(isUp){
                if(canChangeDirection(queues,curFloor,theLift,isUp)){
                    isUp=false;
                    continue;
                }
                curFloor++;
            }
            else  {
                if(canChangeDirection(queues,curFloor,theLift,isUp)){
                    isUp=true;
                    continue;
                }
                curFloor--;
            }
        }
        //Your code here
        return  theLiftStopRecord.stream().mapToInt(Integer::valueOf).toArray();
    }

    public static  boolean canChangeDirection(int [][]queues ,int curFloor,List<Integer> theLift,Boolean isUp){

        if(theLift.size()!=0){return false;}
        if(isUp&&curFloor==queues.length-1) return  true;
        int lasterfloor =curFloor;

        if(isUp) {
            for (int i = curFloor, len = queues.length; i < len; i++) {
                int finalI = i;
                if (Arrays.stream(queues[i]).anyMatch(t -> t > finalI)) return false;
                if(queues[i].length>0) {lasterfloor=i;}
            }
        }
        else{
            for (int j = curFloor; j >=0 ; j--) {
                int finalJ = j;
                if (Arrays.stream(queues[j]).anyMatch(t -> t < finalJ)) return false;
                if(queues[j].length>0) {lasterfloor=j;}
            }
        }
        return lasterfloor==curFloor;
    }

    public static boolean queueIsEmpty(int [][] queue){
        return Arrays.stream(queue).noneMatch(q->q.length>0);
    }

    public  static  boolean canStopLift(final int[][] queues,int curFloor,List<Integer> theLift,Boolean isUp){
        if( theLift.contains(curFloor)) return  true;
        if( isUp  &&  Arrays.stream(queues[curFloor]).anyMatch(t->t>curFloor)) return  true;
        if(!isUp && Arrays.stream(queues[curFloor]).anyMatch(t->t<curFloor) ) return  true;
        return  curFloor==0;
    }

    public static void doTakeTheLift(final int[][] queues, final int capacity,int curFloor,List<Integer> theLift,List<Integer> theLiftStopRecord,Boolean isUp){
        theLift.removeIf(t->t==curFloor);
        if(theLiftStopRecord.size()==0 || theLiftStopRecord.get(theLiftStopRecord.size()-1) != curFloor) {
            theLiftStopRecord.add(curFloor);
        }
        for (int i = 0,len=queues[curFloor].length; i <len ; i++) {
            if(isUp && theLift.size()<capacity && queues[curFloor][i]> curFloor){
                theLift.add(queues[curFloor][i]);
                queues[curFloor][i] = -1;
            }
            if(!isUp&& theLift.size()<capacity&& queues[curFloor][i]< curFloor ){
                theLift.add(queues[curFloor][i]);
                queues[curFloor][i] = -1;
            }
        }
        queues[curFloor] = Arrays.stream(queues[curFloor]).filter(t->t!=-1).toArray();

    }

}
