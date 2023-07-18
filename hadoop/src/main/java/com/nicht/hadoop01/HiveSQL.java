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
     *   每个店铺的uv 访问数
     *
     *
     *
     *
     *
     *
     *
     */







}
