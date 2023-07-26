package com.nicht.hadoop01;

/**
 * @Description
 * @Date 2023/7/17
 */
public class HiveSQL {
    /**
     *   create table gulivideo_ori(
     *     videoid string,
     *     uploader string,
     *     age int,
     *     category array<string>,
     *     length int,
     *     views int,
     *     rate float,
     *     ratings int,
     *     comments int,
     *     relatedId array<string>)
     * row format delimited fields terminated by "\t"
     * collection items terminated by "&";
     */

    /**
     * create table gulivideo_user(
     *  uploader string,
     *  videos int,
     *  friends int)
     * row format delimited
     * fields terminated by "\t";
     */

    /**
     *   统计视频类别热度 Top10
     *   select   t1.categoryName,count(videoid)  hot
     *    from (
     *    select videoid ,categoryName
     *    from  gulivideo_ori lateral view  explode(category) gulivideo_orc_tmp as  categoryName
     *    ) t1
     *    group by categoryName
     *    order by hot desc  limit 10
     */

    /**
     * 统计出视频观看数最高的 20 个视频的所属类别以及类别包含Top20 视频的个数
     *   select t2.categoryName, count(t2.videoid) from
     *     （
     *     select  videoid , categoryName from (
     *         select videoid,views,category
     *              from gulivideo_ori
     *              order by views desc
     *              limit 20
     *      )t1  lateral view explode(category) gulivideo_orc_tmp as  categoryName
     *     )t2
     *     group by t2.categoryName
     */

    /**
     * 统计每个类别中的视频热度 Top10，以 Music 为例
     *
     *   select
     *       t1.videoid ,t1.views, t1.categoryName
     *     from   (
     *      select  videoid ,views, categoryName
     *      from gulivideo_ori  lateral view explode(category) gulivideo_ori_tmp as categoryName
     *      ) t1
     *     where t1.categoryName = 'MUSIC'
     *     order by  t1.views order by desc limit 10
     */

    /**
     *   统计每个类别视频观看数 Top10
     *
     * select
     *     t2.views,t2.categoryName,t2.topRank
     * from  (
     *       select  t1.views, t1.categoryName,
     *        rank() over(partiton by t1.categoryName order by t1.views desc ) as topRank
     *         (
     *          select  views, categoryName
     *           from  gulivideo_ori lateral view explode(category)  gulivideo_ori_tmp as categoryName
     *         ) t1
     *      ) t2
     * where t2.topRank<=10
     *
     *
     *  */

    /**
     * 统计上传视频最多的用户 Top10以及他们上传的视频观看次数在前 20 的视频
     *
     *   select
     *    t2.uploader,
     *    t2.views
     *   from (
     *       select  uploader,videos from gulivideo_user  order by  videos desc limit 10
     *    ) t1
     *   left join   gulivideo_ori  t2
     *   on t2.uploader = t1.uploader
     *  order by  t2.views desc limti 20
     */


    /**
     * CREATE TABLE visit(
     *     userId string,
     *     visitDate string,
     *     visitCount int
     * )
     * ROW format delimited FIELDS TERMINATED BY "\t";
     *
     * data
     * u01	2017/1/21	5
     * u02	2017/1/23	6
     * u03	2017/1/22	8
     * u04	2017/1/20	3
     * u01	2017/1/23	6
     * u01	2017/2/21	8
     * U02	2017/1/23	6
     * U01	2017/2/22	4
     *
     */


    /**  求 用户 每月小计访问量 以及截止当前月累计访问量
     *
     * select
     *   t2.userId,
     *   t2.month,
     *   t2.month_total,
     *   sum(t2.month_total)  over(partiiton by t2.userId order by t2.month row between unbounded preceding and current row) as
     * from (
     *   select
     *    t1.userId,
     *    t1.month,
     *    sum(t1.visitCount) as month_total
     *   from (
     *      select
     *        userId,
     *        date_format(replace(visitDate,"/","-")) as month,
     *        visitCount
     *      from  visit
     *   ) t1
     *    group by t1.userId , t1.month
     * ) t2
     */

    /**
     *  CREATE TABLE shopVisit(
     *           userId string,
     *           shopName string
     *       )
     *  ROW format delimited FIELDS TERMINATED BY "\t";
     */

    /**
    * https://files.cnblogs.com/files/yxym2016/%E8%9A%82%E8%9A%81%E6%A3%AE%E6%9E%97%E6%95%B0%E6%8D%AE.zip
    */
    /**
     *  用户碳量
     * create table
     * user_low_carbon
     * (user_id String,
     * data_dt String,
     * low_carbon int) //碳量
     * row format delimited fields terminated by '\t';
     *
     *  植物兑换碳量
     * create table plant_carbon
     * (plant_id string,
     * plant_name String,
     * low_carbon int)
     * row format delimited fields terminated by '\t';
     */

    /**
     *  统计每个用户在2017年10月1日之前收集的总碳量,并按照碳量进行倒序排序
     *   select user_id ,
     *          sum(low_carbon) as total_low_carbon,
     *    from low_carbon
     *    where data_dt<= '2017-10-01'
     *    group by user_id
     *    order by  total_low_carbon desc
     */


    /**
     * 每个用户的总碳量减去一颗胡杨的碳量，然后用剩余的碳量除以沙柳的碳量
     * select user_id,(a1.sum_low_carbon - a2.low_carbon)/a3.low_carbon as plant_count from
     * (select user_id,sum(low_carbon) as sum_low_carbon
     * from user_low_carbon
     * where data_dt < '2017/10/1'
     * group by user_id
     * order by sum_low_carbon desc) a1,
     * (select low_carbon from plant_carbon where plant_id = 'p004') a2,
     * (select low_carbon from plant_carbon where plant_id = 'p002') a3
     */

    /**
     * 3、按照每个人领取的沙柳棵数进行倒序排序,并获取当前记录的下一条记录所领取的沙柳的棵数
     */


    /**
     * 4、将每一条记录对应的用户领取的沙柳棵数和排在他下面的用户领取的棵数，进行相减，就是当前用户比他后一名所多的棵数
     */

    /**
     * 5、使用floor函数，对进行取整操作，我们只需在获取棵数那里进行取整即可，因为那里涉及了除的操作，所以会产生小数，所以在这里进行取整即可。
     */

/**
 *
 * 项目简介:安徽省社区矫正司法系统信息一体化平台。这个系统适用范围为安徽省内省市县街道以及社区和社会志愿者
 * 等司法系统的工作人员,主要用于将社区矫正人员进行信息化管理实现不同司法机构对社区矫正人员的同步管理,
 * 同时将省市县等不同司法机构之间的对社区相关工作如定位监管告警,社区矫正人员跨市县活动审批,调查评估委托,矫
 * 正人员在线教育等审批和监管流程按照账号权限划分,进行管理。社区矫正工作人员可通过平台对相关业务审批流转上
 * 报,同时配合移动端App对佩戴社区矫正定制终端,如腕表,手机。实现对社区矫正人员信息化监管核查,和定位追踪
 * ,是安徽省内针对社区矫正司法信息一体化系统。
 * 项目技术: Spring + SpringMVC + spring boot +SpringSecurity+ Jwt+MyBatisPlus + redis + Mysql +Mycat+Minio
 * 责任描述:
 * 总体负责的是后端接口的代码实现与数据库的设计实现。
 * 个人主要负责 信息化核查模块、矫正衔接模块的功能与数据库的设计和代码实现。
 * 信息化核查包括定位开通维护对佩戴矫正终端的人员进行设备号绑定,设置活动边界和时间对矫正人员进行监管
 * 首页统计及人脸签到 矫正对象通过对移动端,定制终端及第三方设备进行人脸签到 ,日常签到。
 * 人员分析 轨迹回放 通过矫正终端定时定位以及主动上报或工作人员手动发起定位的定位数据进行分析,利用空
 * 间分析工具包JTS进行越界,跨市县,非法聚集分析,并通过移动端向工作人员告警核查。
 * 矫正衔接模块用于法院检察院或上级司法机构委托的矫正人员衔接报道,矫正人员由委托机构发函以及材料到被委
 * 派机构,被委派机构对矫正人员进行接受和调查评估向上级机构进行回函。流转数据使用zip,包括xml以及附件材
 * 料通过中间平台的ftp服务器进行流转。
 */

    /**
     * 业务域: crm 会员业务 tof 订单业务 mob 在线客服 供应链
     */
/**
 * 项目: crm业务域相关系统(核心系统:会员系统交叉推荐web应用(site),会员档案系统(sun),客服子系统(svr),营销子系统(mkt),会员精准营销子系统(pms),CRM系统短信应用(sms))
 *
 * 责任描述:  参与crm自动化场景开发,
 *
 **/





}

