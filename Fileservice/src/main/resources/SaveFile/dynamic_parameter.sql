create table mob_faq_dynamic_parameter
(
    id                          int(1) auto_increment
        primary key,
    parent_id                   int(1)        default 0                 not null comment '如果该id不为0 代表父id字段为对象 ',
    field_name                  varchar(200)                            not null comment '字段名称',
    const_value                 varchar(100)  default ''                not null comment 'data_source_type =2表示参数值是固定的
若 值 为固定值选项的值 则会在代码里进行取值',
    field_type                  int(1)        default 0                 not null comment '1String  2Long 3Double  4 List  
 list 只作标记出参字段为数组  5Date  6 boolean',
    data_source_type            int(1)        default 1                 null comment '1 获取答案接口传入 2 固定值 3 配置的上文接口出参',
    context_interface_id        int(1)        default 0                 not null comment '如果data_source_type =3取上文接口出参',
    context_interface_out_param varchar(100)  default ''                not null comment '上文接口出参 ',
    description                 varchar(200)  default ''                not null comment '参数的中文名称',
    level                       int(1)        default 0                 not null comment '节点级别',
    required                    tinyint(1)    default 0                 not null comment '控制入参必填',
    add_time                    datetime      default CURRENT_TIMESTAMP not null,
    update_time                 datetime      default CURRENT_TIMESTAMP not null,
    path                        varchar(500)  default ''                not null comment '字段树的及联的path',
    field_alias                 varchar(200)  default ''                not null comment '参数别名 针对已有入参但字段名称不同 支持别名取值',
    param_dict                  varchar(2000) default ''                not null comment '参数字典'
)
    comment '变量参数 字段树' charset = utf8mb4;


