package com.nicht.promote.DataStruct_Algorithrem.src.Algorithrem.Leetcode.newCoder;

import cn.hutool.cron.timingwheel.TimerTask;

/**
 * @Description
 * @Date 2023/5/12
 */
public class coinChange {

    /**
     *  select name , sum(cost) from tb  where month like '2017-04%' group by name
     *
     *  select name, sum(cost) over()  from  tb  where  month  like '2017-04%'
     *
     *  1250879469(合作店)   网络单 需求确认 杨婷婷3  95811202   "allowanceDto": {
     *         "salerAvailableBalance": 2,
     *         "totalPrice": 11420.00,
     *         "totalCost": 9700,
     *         "strategyPrice": 57,
     *         "canAllowance": false,
     *         "msg": "未查到让价促销活动",
     *         "formula": null,
     *         "allowancePrice": 2,
     *         "orderLimitPrice": null,
     *         "inWhiteList": false
     *       }
     *     },
     *  1250879443  网络单 需求确认 无客服专员
     *  1250879358  (合作店) 网络单 需求确认 李楠6    97738842    总价-成本-总价*7%-其他优惠(立减黑卡)：21500.00-17770-21500.00* 0.07-0=2225.0000
     *  1250879348  (合作店) 网络单 确认反馈中 王书豪  10744210   总价-成本-总价*7%-其他优惠(立减黑卡)：43150.00-38350-43150.00* 0.07-0=1779.5000
     *  1250879313  (合作店) 网络单 需求确认  徐可7    68968110  总价-成本-总价*7%-其他优惠(立减黑卡)：14260.00-13300-14260.00* 0.07-0=-38.2000
     *  1250879197  (合作店)   网络单 需求确认 杨婷婷3 97738505  总价-成本-总价*7%-其他优惠(立减黑卡)：8060.00-7360-8060.00* 0.07-0=135.8000
     *  1250879048  (合作店) 网络单 确认反馈中 李楠6   97058680 "allowanceDto": {
     *         "salerAvailableBalance": 2,
     *         "totalPrice": 4690.00,
     *         "totalCost": 3240,
     *         "strategyPrice": 23,
     *         "canAllowance": false,
     *         "msg": "未查到让价促销活动",
     *         "formula": null,
     *         "allowancePrice": 2,
     *         "orderLimitPrice": null,
     *         "inWhiteList": false
     *       }
     *  1250879047  (合作店) 网络单 需求确认 王书豪    97738206 总价-成本-总价*7%-其他优惠(立减黑卡)：45192.00-41592-45192.00* 0.07-0=436.5600
     *  1250878912  网络单 需求确认  胡德华  659268   总价-成本-总价*7%-其他优惠(立减黑卡)：14186.00-11790-14186.00* 0.07-0=1402.9800
     *  1250878466  网络单 已取消   韩笑梅  4540044  订单取消
     *
     * 95811202,68968110,659268,97738842,10744210,97738505,97058680,97738206
     */

    public static void main(String[] args) {
        System.out.println(System.currentTimeMillis());
    }











}
