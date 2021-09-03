package com.nicht.promote.DataStruct_Algorithrem.src.DataStruct;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author admin
 * @description
 * @ 2020/11/3
 */
public class CurrentMap {

    public static void main(String[] args) {
        Map hashmap = new HashMap(16,0.76f);

      /*   public HashMap(int initialCapacity) {
            this(initialCapacity, DEFAULT_LOAD_FACTOR);
        }*/

     /*       public HashMap(int initialCapacity, float loadFactor) {
            if (initialCapacity < 0)
                throw new IllegalArgumentException("Illegal initial capacity: " +
                        initialCapacity);
            if (initialCapacity > MAXIMUM_CAPACITY)  //超过最大容量
                initialCapacity = MAXIMUM_CAPACITY; 取最大容量
            if (loadFactor <= 0 || Float.isNaN(loadFactor))
                throw new IllegalArgumentException("Illegal load factor: " +
                        loadFactor);
            this.loadFactor = loadFactor;
            this.threshold = tableSizeFor(initialCapacity);
        }*/








    }

/*    public ConcurrentHashMap(int initialCapacity) {
        if (initialCapacity < 0)
            throw new IllegalArgumentException();
        int cap = ((initialCapacity >= (MAXIMUM_CAPACITY >>> 1)) ?
                MAXIMUM_CAPACITY :
                tableSizeFor(initialCapacity + (initialCapacity >>> 1) + 1));
        this.sizeCtl = cap;
    }*/
}
