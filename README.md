# BloodManger
血站数字化信息管理平台

使用SpringBoot作为框架

SpringSecurity+Jwt作为鉴权认证

使用redis作为验证码存储（我知道大材小用了）

使用knife4j作为api文档引擎前端

使用Mybatis+MybatisPlus作为持久层

| 表名             | 列名                | 数据类型     | 默认值 | 备注                                                         |
| ---------------- | ------------------- | ------------ | ------ | ------------------------------------------------------------ |
| blood            | blood_group         | int          |        | 血型BLOOD_GROUP(abo)                                         |
| blood            | blood_id            | char(32)     |        | 主键                                                         |
| blood            | blood_type          | int          |        | 血液种类BLOOD_TYPE (0普通冰冻血浆、1悬浮红细胞、2机采血小板) |
| blood            | blood_volume        | int          |        | 血量 cc                                                      |
| blood            | expire_time         | datetime     |        | 过期日期                                                     |
| blood            | in_person           | char(32)     |        | 献血者id                                                     |
| blood            | in_source           | varchar(64)  |        | 血液来源                                                     |
| blood            | in_time             | datetime     |        | 入库时间                                                     |
| blood            | rh                  | int          |        | RH0阴性 1阳性                                                |
| blood            | state               | int          | 0      | 血液状态 BLOOD_STATE(0入库未使用 1已使用 2过期弃用)          |
| blood            | take_person         | char(32)     |        | 采血人id                                                     |
| blood            | take_time           | datetime     |        | 采血日期                                                     |
| blood_trans_form | apply_time          | datetime     |        | 申请时间                                                     |
| blood_trans_form | apply_user          | char(32)     |        | 申请医师id                                                   |
| blood_trans_form | blood_trans_succ_id | char(32)     |        | 输血申请成功匹配id                                           |
| blood_trans_form | form_id             | char(32)     |        | 输血申请单id                                                 |
| blood_trans_form | need_person         | char(32)     |        | 患者id                                                       |
| blood_trans_form | need_volume         | int          |        | 预订量cc                                                     |
| blood_trans_form | state               | char(1)      | 0      | 申请单状态BLOOD_TRANS_FORM_STATE(0申请未处理 1通过 2拒绝)    |
| blood_trans_succ | blood_trans_succ_id | char(32)     |        | 配型                                                         |
| blood_trans_succ | need_volume         | int          |        | 预订量cc                                                     |
| blood_trans_succ | trans_bloods_id     | char(32)     |        | 血液组ID                                                     |
| blood_trans_succ | user_id             | char(32)     |        | 患者ID                                                       |
| dict             | code                | varchar(255) |        | 字典码                                                       |
| dict             | dict_key            | int          |        | 字典值                                                       |
| dict             | dict_value          | varchar(255) |        | 字典名称                                                     |
| dict             | remark              | varchar(255) |        | 字典备注                                                     |
| role             | role_id             | int          |        | 用户角色                                                     |
| role             | role_name           | char(16)     |        | 角色名称                                                     |
| role             | role_name_en        | char(16)     |        | 角色名称EN                                                   |
| trans_bloods     | blood_id            | char(32)     |        | 血液ID                                                       |
| trans_bloods     | trans_bloods_id     | char(32)     |        | 血液组ID                                                     |
| user             | age                 | int          |        | 年龄                                                         |
| user             | blood_group         | int          |        | 血型BLOOD_GROUP(abo)                                         |
| user             | email               | varchar(255) |        | 邮箱                                                         |
| user             | password            | char(64)     |        | 登录密码，加密后保存                                         |
| user             | real_name           | char(16)     |        | 姓名                                                         |
| user             | rh                  | int          |        | RH0阴性 1阳性                                                |
| user             | role_id             | int          | 2      | 角色id                                                       |
| user             | sex                 | tinyint      |        | 性别USER_SEX 0女 1男                                         |
| user             | user_id             | char(32)     |        | 主键                                                         |
| user             | user_name           | char(16)     |        | 昵称                                                         |
