package com.nicht.promote.DataStruct_Algorithrem.src.Algorithrem.Leetcode.newCoder;

import java.util.*;

/**
 * 功能描述：
 * codeWars theLift 3kyu
 *
 * @author nicht
 *
 * A multi-floor building has a Lift in it.
 *
 * People are queued on different floors waiting for the Lift.
 *
 * Some people want to go up. Some people want to go down.
 *
 * The floor they want to go to is represented by a number (i.e. when they enter the Lift this is the button they will press)
 *
 * BEFORE (people waiting in queues)               AFTER (people at their destinations)
 *                    +--+                                          +--+
 *   /----------------|  |----------------\        /----------------|  |----------------\
 * 10|                |  | 1,4,3,2        |      10|             10 |  |                |
 *   |----------------|  |----------------|        |----------------|  |----------------|
 *  9|                |  | 1,10,2         |       9|                |  |                |
 *   |----------------|  |----------------|        |----------------|  |----------------|
 *  8|                |  |                |       8|                |  |                |
 *   |----------------|  |----------------|        |----------------|  |----------------|
 *  7|                |  | 3,6,4,5,6      |       7|                |  |                |
 *   |----------------|  |----------------|        |----------------|  |----------------|
 *  6|                |  |                |       6|          6,6,6 |  |                |
 *   |----------------|  |----------------|        |----------------|  |----------------|
 *  5|                |  |                |       5|            5,5 |  |                |
 *   |----------------|  |----------------|        |----------------|  |----------------|
 *  4|                |  | 0,0,0          |       4|          4,4,4 |  |                |
 *   |----------------|  |----------------|        |----------------|  |----------------|
 *  3|                |  |                |       3|            3,3 |  |                |
 *   |----------------|  |----------------|        |----------------|  |----------------|
 *  2|                |  | 4              |       2|          2,2,2 |  |                |
 *   |----------------|  |----------------|        |----------------|  |----------------|
 *  1|                |  | 6,5,2          |       1|            1,1 |  |                |
 *   |----------------|  |----------------|        |----------------|  |----------------|
 *  G|                |  |                |       G|          0,0,0 |  |                |
 *   |====================================|        |====================================|
 *
 *   Rules
 * Lift Rules
 * The Lift only goes up or down!
 * Each floor has both UP and DOWN Lift-call buttons (except top and ground floors which have only DOWN and UP respectively)
 * The Lift never changes direction until there are no more people wanting to get on/off in the direction it is already travelling
 * When empty the Lift tries to be smart. For example,
 * If it was going up then it may continue up to collect the highest floor person wanting to go down
 * If it was going down then it may continue down to collect the lowest floor person wanting to go up
 * The Lift has a maximum capacity of people
 * When called, the Lift will stop at a floor even if it is full, although unless somebody gets off nobody else can get on!
 * If the lift is empty, and no people are waiting, then it will return to the ground floor
 *
 * People Rules
 * People are in "queues" that represent their order of arrival to wait for the Lift
 * All people can press the UP/DOWN Lift-call buttons
 * Only people going the same direction as the Lift may enter it
 * Entry is according to the "queue" order, but those unable to enter do not block those behind them that can
 * If a person is unable to enter a full Lift, they will press the UP/DOWN Lift-call button again after it has departed without them
 */
public class TheLift {
    public static int[] theLift(final int[][] queues, final int capacity) {
        if (queueIsEmpty(queues)) {
            return new int[]{0};
        }
        List<Integer> theLift = new ArrayList<>(capacity);
        List<Integer> theLiftStopRecord = new ArrayList<>();
        Boolean isUp = Boolean.TRUE;
        int curFloor = 0;
        while (true) {
            // when theLift isEmpty &&  nobody waiting for theLift
            if (theLift.isEmpty() && queueIsEmpty(queues)) {
                // when theLift  stop curFloor is not first floor
                if (curFloor != 0) {
                    theLiftStopRecord.add(0);
                }
                break;
            }
            if (canStopLift(queues, curFloor, theLift, isUp)) {
                doTakeTheLift(queues, capacity, curFloor, theLift, theLiftStopRecord, isUp);
            }
            if (isUp) {
                if (canChangeDirection(queues, curFloor, theLift, isUp)) {
                    isUp = false;
                    continue;
                }
                curFloor++;
            } else {
                if (canChangeDirection(queues, curFloor, theLift, isUp)) {
                    isUp = true;
                    continue;
                }
                curFloor--;
            }
        }
        //Your code here
        return theLiftStopRecord.stream().mapToInt(Integer::valueOf).toArray();
    }

    public static boolean canChangeDirection(int[][] queues, int curFloor, List<Integer> theLift, Boolean isUp) {

        if (theLift.size() != 0) {
            return false;
        }
        if (isUp && curFloor == queues.length - 1) return true;
        int lasterfloor = curFloor;

        if (isUp) {
            for (int i = curFloor, len = queues.length; i < len; i++) {
                int finalI = i;
                if (Arrays.stream(queues[i]).anyMatch(t -> t > finalI)) return false;
                if (queues[i].length > 0) {
                    lasterfloor = i;
                }
            }
        } else {
            for (int j = curFloor; j >= 0; j--) {
                int finalJ = j;
                if (Arrays.stream(queues[j]).anyMatch(t -> t < finalJ)) return false;
                if (queues[j].length > 0) {
                    lasterfloor = j;
                }
            }
        }
        return lasterfloor == curFloor;
    }

    public static boolean queueIsEmpty(int[][] queue) {
        return Arrays.stream(queue).noneMatch(q -> q.length > 0);
    }

    public static boolean canStopLift(final int[][] queues, int curFloor, List<Integer> theLift, Boolean isUp) {
        if (theLift.contains(curFloor)) return true;
        if (isUp && Arrays.stream(queues[curFloor]).anyMatch(t -> t > curFloor)) return true;
        if (!isUp && Arrays.stream(queues[curFloor]).anyMatch(t -> t < curFloor)) return true;
        return curFloor == 0;
    }

    public static void doTakeTheLift(final int[][] queues, final int capacity, int curFloor, List<Integer> theLift, List<Integer> theLiftStopRecord, Boolean isUp) {
        theLift.removeIf(t -> t == curFloor);
        if (theLiftStopRecord.size() == 0 || theLiftStopRecord.get(theLiftStopRecord.size() - 1) != curFloor) {
            theLiftStopRecord.add(curFloor);
        }
        for (int i = 0, len = queues[curFloor].length; i < len; i++) {
            if (isUp && theLift.size() < capacity && queues[curFloor][i] > curFloor) {
                theLift.add(queues[curFloor][i]);
                queues[curFloor][i] = -1;
            }
            if (!isUp && theLift.size() < capacity && queues[curFloor][i] < curFloor) {
                theLift.add(queues[curFloor][i]);
                queues[curFloor][i] = -1;
            }
        }
        queues[curFloor] = Arrays.stream(queues[curFloor]).filter(t -> t != -1).toArray();

    }

}
