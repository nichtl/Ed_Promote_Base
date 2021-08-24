package com.learn_netty_one.TimeServerHandle.Bean;

import java.util.Date;

/**
 * @Author ni
 * @description
 * @ 2021/1/4
 */
public class UnixTime {

    private final long value;

    public UnixTime() {
        this(System.currentTimeMillis() / 1000L + 2208988800L);
    }
    public UnixTime(long value) {
        this.value = value;
    }

    public long getValue() {
        return value;
    }


    @Override
    public String toString() {
        return new Date((getValue() - 2208988800L) * 1000L).toString();
    }
}
