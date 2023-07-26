create table mob_faq_dynamic_interface
(
    id                        int(1) auto_increment comment '主键'
        primary key,
    name                      varchar(200)                           not null comment '接口名称',
    description               varchar(200) default ''                not null comment '接口描述',
    address                   varchar(200)                           not null comment '接口地址',
    type                      int(1)       default 0                 not null comment '接口类型 1 http 2 tsp 3 pebble',
    method_type               int(1)       default 0                 not null comment '当type =1 时 需要指定  1 get 2 post ',
    in_parameter              varchar(200) default ''                not null comment ' 入参id逗号分隔',
    out_parameter             varchar(200) default ''                not null comment ' 出参id 逗号分隔',
    add_time                  datetime     default CURRENT_TIMESTAMP not null,
    update_time               datetime     default CURRENT_TIMESTAMP not null,
    in_parameter_need_encode  int(1)       default 0                 not null comment '是否需要加密 0 不需要 1 base64 encode',
    out_parameter_need_decode int(1)       default 0                 not null comment '0 不需要 1 base64 decode'
)
    comment 'FAQ引入变量答案' charset = utf8mb4;


