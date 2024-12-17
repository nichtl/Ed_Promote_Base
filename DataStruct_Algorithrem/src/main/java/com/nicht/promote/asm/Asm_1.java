package com.nicht.promote.asm;

import org.objectweb.asm.ClassVisitor;

/**
 * @Description
 * @Date 2023/2/28
 */
public class Asm_1 {

    public static void main(String[] args) {

    }
    /**
     *   工作中碰到或解决的难题
     *   bug类型的
     *   排查系统上线运行一段时间后就oom  运维不配合 只能通过 gc打印log 查看回收情况 发现回收年轻代开始能回收90%但是逐渐的 80% 70% 回收不掉对象 导致越来越多对象进到老年代
     *
     *
     *   crm业务数据同步来自订单的数据 反馈 数据同步不及时 或没有同步
     *   排查同步系统问题  同步系统先拉取订单数据 落到临时表里 在从临时表里mq分发给各个crm消费落地同步
     *   排查发现有的消费者会出现卡死的不消费的情况
     *
     *   接手crm会员检索业务 涉及到crm大数据相关的知识 所以需要主动理解学习大数据相关知识
     *
     *
     */



}
