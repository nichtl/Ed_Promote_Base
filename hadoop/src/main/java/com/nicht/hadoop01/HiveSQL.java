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
     *  videos int,o
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
     *              order by views
     *              desc
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
     * u01	2017/2/`21	8
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
     *   sum(t2.month_total)  over(partiiton by t2.userId order by t2.month row between unbounded preceding and current row) as curSum
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
     *    select user_id , plant_count, lead(plant_count,1,'0') over (order by plant_count desc  ) next_count  from
     *
     *    (select user_id,(a1.sum_low_carbon)/a3.low_carbon as plant_count from
     *        (select user_id,sum(low_carbon) as sum_low_carbon
     *         from user_low_carbon
     *          where data_dt < '2017/10/1'
     *       group by user_id
     *       order by sum_low_carbon desc) a1,
     *       (select low_carbon from plant_carbon where plant_id = 'p004') a2,
     *       (select low_carbon from plant_carbon where plant_id = 'p002') a3) a4
     *
     *
     *
     *
     *
     */


    /**
     * 4、将每一条记录对应的用户领取的沙柳棵数和排在他下面的用户领取的棵数，进行相减，就是当前用户比他后一名所多的棵数
     *
     *
     *
     *
     */

    /**
     * 5、使用floor函数，对进行取整操作，我们只需在获取棵数那里进行取整即可，因为那里涉及了除的操作，所以会产生小数，所以在这里进行取整即可。
     */


    /**
     * 业务域: crm 会员业务 tof 订单业务 mob 在线客服 供应链
     */




/**
 * rowkey的规则 前两位会员id64 取模  后10位会员id前补0
 *  负责业务:crm会员业务域相关系统,mob无线业务域,负责无线底层im系统开发维护.
 *  项目技术: Spring、SpringMVC、Spring Boot、iBatis、MyBatis、Thrift、Zookeeper 、MQ、Redis 、Hadoop 、Hive、Hbase
 *  责任描述:
 *  负责CRM会员检索维护优化,CRM会员检索基于crm大数据平台实现,
 *  由业务人员在页面选取取数条件,异步执行hql任务落库数据,由于crm取数维度不断拓展导致hql越发大且杂,
 *  导致检索任务执行时长增加,按照检索逻辑维度细化拆分条件 将大hql拆分多个hql执行,最终再次聚合数据，降低整体执行时间。
 *  负责crm机器人外呼相关业务维护,实现从创建外呼任务管理到外呼后根据对话意图分发人工任务项,自动添加企微等流程串联,
 *  长期参与crm自动化场景开发,对接业务及BI,实现业务营销场景多渠道推送,参与crm场景营销渠道统一改造,对接决策中心,实现crm营销业务收口及管控,
 *  参与订单让价改造及额度管理相关功能开发,负责让价金额计算,
 *  负责在线客服维护迭代,负责在线客服FAQ后台管理页面及功能重构,独立搭建im机器人及Faq动态应答功能,使得FAQ自由配置内部业务接口,实现FAQ答案灵活对接内部业务信息.
 *  对接百度机器人,实现机器人对话,负责在线客服对接公司内部基于rasa chatGpt 实现的对话机器人,接入im对话流程.实现智能客服应答。
 *
 *
 * **/








}

