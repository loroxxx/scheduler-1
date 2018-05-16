--
-- 渠道开户表的结构 `chn_open`
--

DROP TABLE IF EXISTS `chn_open`;
CREATE TABLE `chn_open` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '表主键',
  `serial_number` varchar(20) NOT NULL COMMENT '金飞镖业务流水号',
  `batch_code` varchar(10) NOT NULL COMMENT '批次号',
  `investor_id` varchar(20) NOT NULL COMMENT '投资人ID',
  `chn_id` varchar(30) NOT NULL COMMENT '渠道用户ID',
  `chn_code` varchar(10) DEFAULT NULL COMMENT '渠道方代码(渠道在平台中的代码标识)',
  `name` varchar(60) NOT NULL COMMENT '投资人姓名',
  `app_sheet_serial_no` varchar(24) NOT NULL COMMENT '申请单编号',
  `apply_date` datetime NOT NULL COMMENT '开户申请时间',
  `confirm_date` date DEFAULT NULL COMMENT '开户确认日期',
  `open_state` varchar(4) DEFAULT NULL COMMENT '开户处理状态，0000-成功，0010-失败，0411-已开户',
  `user_flag` char(1) NOT NULL COMMENT '客户类别: 0-个人 1-机构  2-自营',
  `id_type` char(1) NOT NULL COMMENT '证件类型:0-身份证 1-护照 2-组织机构代码证 3-营业执照',
  `id_no` varchar(60) NOT NULL COMMENT '证件号码',
  `sex` char(1) DEFAULT NULL COMMENT '性别，0-男，1-女 2-非自然人',
  `phone` varchar(15) NOT NULL COMMENT '投资人电话',
  `address` varchar(120) DEFAULT NULL COMMENT '投资人联系地址',
  `zipcode` varchar(6) DEFAULT NULL COMMENT '投资人邮政编码',
  `email` varchar(60) DEFAULT NULL COMMENT '投资人电子邮箱',
  `fax` varchar(24) DEFAULT NULL COMMENT '投资人传真号码',
  `open_bank` varchar(60) NOT NULL COMMENT '开户行',
  `bank_account` varchar(24) NOT NULL COMMENT '银行账号',
  `profession` char(3) DEFAULT NULL COMMENT '投资人职业: 01-党政机关、事业单位 02-企业单位 03-自由业主 04- 学生 05-军人 06-其他',
  `education` char(3) DEFAULT NULL COMMENT '投资人学历 01-研究生 02-大学本科 03- 大学专科 04-中专或技校 05-技工学校 06-高中 07-初中 08-小学 09-文盲或半文盲',
  `annual_income` decimal(15,2) DEFAULT NULL COMMENT '投资人年收入',
  `risk_level` char(1) DEFAULT NULL COMMENT '客户风险级别:0-默认型 1-保守型 2-稳健型 3-积极型 4-激进型',
  `inst_repr_id_name` varchar(60) DEFAULT NULL COMMENT '法人代表姓名',
  `inst_repr_id_type` char(1) DEFAULT NULL COMMENT '法人代表证件类型 0-身份证 1-护照',
  `inst_repr_id_no` varchar(60) DEFAULT NULL COMMENT '法人代表证件号码',
  `inst_repr_expdate` date DEFAULT NULL COMMENT '法人代表证件有效期',
  `inst_repr_phone` varchar(24) DEFAULT NULL COMMENT '法人代表联系电话',
  `inst_repr_manage_range` varchar(120) DEFAULT NULL COMMENT '机构经营范围',
  `controlholder_name` varchar(60) DEFAULT NULL COMMENT '控股股东名称',
  `controlholder_id_type` char(1) DEFAULT NULL COMMENT '控股股东证件类型 0-身份证 1-护照 2-组织机构代码证 3-营业执照',
  `controlholder_id_no` varchar(24) DEFAULT NULL COMMENT '控股股东证件号码',
  `controlholder_expdate` date DEFAULT NULL COMMENT '控股股东证件有效期',
  `vocation` varchar(60) DEFAULT NULL COMMENT '行业',
  `corpoProperty` char(2) DEFAULT NULL COMMENT '企业性质 00-国有 01-集体 02-合资 03-独资 04-私营 05-个体工商户 06-其他',
  `staff_num` int(10) DEFAULT NULL COMMENT '员工人数',
  `province` varchar(30) DEFAULT NULL COMMENT '省份',
  `city` varchar(30) DEFAULT NULL COMMENT '市',
  `county` varchar(30) DEFAULT NULL COMMENT '县区',
  `registered_capital` decimal(15,2) DEFAULT NULL COMMENT '注册资本',
  `contact_name` varchar(30) DEFAULT NULL COMMENT '联系人名称',
  `contact_id_type` char(1) DEFAULT NULL COMMENT '联系人证件类型 0-身份证 1-护照',
  `contact_id_no` varchar(24) DEFAULT NULL COMMENT '联系人证件号码',
  `contact_expdate` date DEFAULT NULL COMMENT '联系人证件有效期',
  `contact_phone` varchar(24) DEFAULT NULL COMMENT '联系人电话号码',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=75 DEFAULT CHARSET=utf8 COMMENT='渠道开户表';


--
-- 用户基本信息 `user`
--
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '表主键',
  `investor_id` char(20) NOT NULL COMMENT '投资人渠道id,导入渠道数据后由平台生成',
  `name` varchar(60) NOT NULL COMMENT '投资人姓名',
  `user_flag` char(10) DEFAULT NULL COMMENT '客户类别:1-机构,1-个人,2-自营',
  `id_type` char(20) NOT NULL COMMENT '证件类型:1-营业执照,0-组织机构代码证,1-护照,0-身份证',
  `id_no` char(24) NOT NULL COMMENT '证件号码',
  `sex` char(2) DEFAULT NULL COMMENT '性别，0-男，1-女,0-非自然人',
  `phone` char(15) DEFAULT NULL COMMENT '联系电话',
  `address` varchar(120) DEFAULT NULL COMMENT '地址',
  `open_bank` varchar(60) DEFAULT NULL COMMENT '开户行',
  `bank_account` char(24) DEFAULT NULL COMMENT '银行账号',
  `exchange_code` char(10) DEFAULT NULL COMMENT '交易所代码(交易所在平台中的代码标识)',
  `confirm_date` date DEFAULT NULL COMMENT '确认日期',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8 COMMENT='用户表';




--
-- 投资人交易记录 `investor_trans`
--
DROP TABLE IF EXISTS `investor_trans`;
CREATE TABLE `investor_trans` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '表主键',
  `serial_number` varchar(20) NOT NULL COMMENT '金飞镖业务流水号',
  `batch_code` varchar(10) NOT NULL COMMENT '批次号',
  `investor_id` char(20) NOT NULL COMMENT '投资人id,导入渠道数据后由平台生成',
  `chn_id` char(30) NOT NULL COMMENT '渠道用户id',
  `chn_code` varchar(30) NOT NULL COMMENT '渠道代码',
  `name` varchar(60) NOT NULL COMMENT '投资人姓名',
  `trans_time` datetime NOT NULL COMMENT '交易时间',
  `confirm_date` datetime DEFAULT NULL COMMENT '确认日期',
  `trans_state` char(20) DEFAULT NULL COMMENT '交易处理状态：0000-成功，0010-失败',
  `app_sheet_serial_no` char(24) NOT NULL COMMENT '申请单编号',
  `product_no` varchar(30) NOT NULL COMMENT '金飞镖产品代码',
  `product_name` varchar(60) NOT NULL COMMENT '产品名称',
  `trans_type` char(10) NOT NULL COMMENT '交易类型，020-认购，024-赎回',
  `trans_vol` decimal(15,2) NOT NULL COMMENT '交易份额',
  `trans_amount` decimal(15,2) NOT NULL COMMENT '交易金额，用于平台内部统计使用',
  `product_vol` decimal(15,2) NOT NULL COMMENT '产品每份交易金额',
  `trans_fix` decimal(15,2) NOT NULL COMMENT '手续费',
  `take_income_flag` varchar(10) DEFAULT NULL COMMENT '带走收益标志：0-不带走,1-带走',
  `huge_subs_flag` char(10) DEFAULT NULL COMMENT '巨额购买处理标志:0-取消，1-顺延',
  `huge_redem_flag` char(10) DEFAULT NULL COMMENT '巨额赎回处理标志:0-取消，1-顺延',
  `risk_disclosure` char(20) DEFAULT NULL COMMENT '用户是否签署风险揭示书：0-已签署，1-未签署',
  `trans_bank_name` varchar(60) DEFAULT NULL COMMENT '交易银行名称',
  `trans_bank_act` char(24) DEFAULT NULL COMMENT '交易银行账号',
  `charge_type` char(20) DEFAULT NULL COMMENT '收费方式：0-前端收费，1-后端收费，2-混合收费',
  `charge_way` char(20) DEFAULT NULL COMMENT '收费类型：1-指定费率，2-指定费用',
  `charge_rate` decimal(15,2) DEFAULT NULL COMMENT '指定费率',
  `specify_fee` decimal(15,2) DEFAULT NULL COMMENT '指定费用',
  `rolling_flag` char(10) DEFAULT NULL COMMENT '滚存标志：0-关闭，1-开启',
  `force_redem_reason` char(20) DEFAULT NULL COMMENT '强行赎回原因：0-小于最低持有数，1-司法执行，2-政策原因',
  `force_redem_type` char(20) DEFAULT NULL COMMENT '强制赎回类型：0-强制赎回，1-违约赎回，2-到期',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=71 DEFAULT CHARSET=utf8 COMMENT='投资人交易记录';





--
-- 投资人持仓表 `investor_position`
--
DROP TABLE IF EXISTS `investor_position`;
CREATE TABLE `investor_position` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '表主键',
  `gather_date` date NOT NULL COMMENT '汇总日期',
  `investor_id` char(10) NOT NULL COMMENT '投资人id,导入渠道数据后由平台生成',
  `chn_id` char(10) NOT NULL COMMENT '渠道用户id',
  `chn_code` char(10) DEFAULT NULL COMMENT '渠道代码(渠道在平台中的代码标识)',
  `name` varchar(60) NOT NULL COMMENT '投资人姓名',
  `product_no` varchar(30) NOT NULL COMMENT '金飞镖产品代码',
  `product_name` varchar(30) NOT NULL COMMENT '产品名称',
  `total_postion_vol` decimal(15,2) NOT NULL COMMENT '持有份额',
  `product_vol` decimal(15,2) NOT NULL COMMENT '产品每份金额',
  `total_postion_amount` decimal(15,2) NOT NULL COMMENT '持有金额',
  `total_income` decimal(15,2) NOT NULL COMMENT '总收益',
  `total_redemed_amount` decimal(15,2) NOT NULL COMMENT '总赎回金额',
  `total_subs_amount` decimal(15,2) NOT NULL COMMENT '总申购金额',
  `rolling_flag` char(10) DEFAULT NULL COMMENT '滚存标志：0-关闭，1-开启',
  `rolling_vol` decimal(15,2) DEFAULT NULL COMMENT '滚存份额',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='投资人持仓表';



--
-- 投资人收益表 `investor_icome`
--
DROP TABLE IF EXISTS `investor_income`;
CREATE TABLE `investor_income` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '表主键',
  `serial_number` varchar(20) NOT NULL COMMENT '金飞镖业务流水号',
  `investor_id` char(10) NOT NULL COMMENT '投资人Id,导入渠道数据后由平台生成',
  `chn_id` char(10) NOT NULL COMMENT '渠道用户id',
  `chn_code` char(10) DEFAULT NULL COMMENT '渠道代码，渠道在平台中的代码标识',
  `name` varchar(60) NOT NULL COMMENT '投资人姓名',
  `apply_date` date NOT NULL COMMENT '收益计提日期',
  `confirm_date` date DEFAULT NULL COMMENT '收益确认日期',
  `product_no` varchar(30) NOT NULL COMMENT '金飞镖产品代码',
  `product_name` varchar(30) NOT NULL COMMENT '产品名称',
  `total_postion_amount` decimal(15,2) NOT NULL COMMENT '持有金额',
  `income_amount` decimal(15,2) NOT NULL COMMENT '收益金额：定期产品，为固定期限后的收益；活期产品为每日收益',
  `income_type` char(15) NOT NULL COMMENT '收益类型:0-收益率，1-万元收益',
  `income_rate` decimal(15,3) NOT NULL COMMENT '收益率',
  `percent_income` decimal(15,4) NOT NULL COMMENT '万元收益',
  `income_state` char(20) NOT NULL COMMENT '收益处理状态：0000-成功，0010-失败',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='收益表';







--
-- 产品表 `product`
--
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product` (
`id`  int NOT NULL AUTO_INCREMENT COMMENT '表主键',
`product_name`  varchar(60) NOT NULL COMMENT '产品名称',
`product_no`  char(30) NOT NULL COMMENT '金飞镖产品代码',
`plat_product_no` varchar(30) NOT NULL COMMENT '产品代码',
`parent_product_no`  varchar(30) DEFAULT NULL COMMENT '父产品代码',
`product_issuer` varchar(60) DEFAULT NULL COMMENT '产品发行方',
`exchange_no`  char(10) NOT NULL COMMENT '交易所代码',
`product_type` varchar(10) NOT NULL COMMENT '产品类型:0-活期,1-定期',
`product_expdate` decimal(6,0) DEFAULT NULL COMMENT '产品期限',
`expdate_unit` decimal(6,0) DEFAULT NULL COMMENT '期限单位：0-日，1-月，2-年',
`cal_income_way` char(20) NOT NULL COMMENT '收益计算类型:01-固定收益，02-固定+浮动，03-额度浮动，04-净值浮动，05-期限浮动，06-分配收益，07-约定收益 ',
`cal_rate_way` char(20) NOT NULL COMMENT '产品计息方式:0-ACT/360,1-ACT/365,2-月',
`setup_date` date NOT NULL COMMENT '产品成立日，对于活期产品',
`rate_date` date DEFAULT NULL COMMENT '产品起息日，对于定期产品',
`term_date` date DEFAULT NULL COMMENT '产品到期日，对于定期产品',
`cashe_date` date DEFAULT NULL COMMENT '产品兑付日',
`income_rate` decimal(6,3) NOT NULL COMMENT '产品收益率',
`ipo_start_date` datetime NOT NULL COMMENT '募集起始时间',
`ipo_end_date` datetime DEFAULT NULL COMMENT '募集结束时间',
`start_periods` decimal(15,2) NOT NULL COMMENT '起始期数',
`end_periods` decimal(15,2) NOT NULL COMMENT '结束期数',
`total_limit` decimal(15,2) NOT NULL  COMMENT '产品总额度',
`total_vol` decimal(15,2) NOT NULL  COMMENT '产品总份额',
`product_vol` decimal(15,2) DEFAULT NULL COMMENT '产品每份金额',
`subs_start_amount` decimal(15,2) NOT NULL COMMENT '申购起始金额',
`subs_range` decimal(15,2) NOT NULL COMMENT '申购增减幅度',
`risk_assess` char(20)  DEFAULT NULL COMMENT '是否需要风险测评：1-是，0-否',
`risk_level` char(20) DEFAULT NULL COMMENT '风险级别：01-低，02-较低，03-中，04-较高，05-高',
PRIMARY KEY (`id`)
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='产品表';

--
-- 产品历史利率表 `prd_history_rate`
--
DROP TABLE IF EXISTS `prd_history_rate`;
CREATE TABLE `prd_history_rate` (
`id`  int NOT NULL AUTO_INCREMENT COMMENT '表主键',
`product_name` varchar(30) NOT NULL COMMENT '产品名称',
`product_no` char(6) NOT NULL COMMENT '金飞镖产品代码',
`product_type` char(10) NOT NULL COMMENT '产品类型:活期,定期',
`income_rate` decimal(15,3) NOT NULL COMMENT '产品收益率',
`percent_income_rate` decimal(15,4) NOT NULL COMMENT '万元收益',
`update_date` date NOT NULL COMMENT '更新日期',
`exchange_code` varchar(20) DEFAULT NULL COMMENT '交易所',
PRIMARY KEY (`id`)
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='产品历史利率表';





--
-- 每日历史持仓表 `position_history`
--
DROP TABLE IF EXISTS `position_history`;
CREATE TABLE `position_history` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '表主键',
  `serial_number` varchar(20) NOT NULL COMMENT '金飞镖业务流水号',
  `update_date` date NOT NULL COMMENT '更新日期',
  `investor_id` char(10) NOT NULL COMMENT '投资人id,导入渠道数据后由平台生成',
  `chn_id` char(10) NOT NULL COMMENT '渠道用户id',
  `chn_code` char(10) NOT NULL COMMENT '渠道代码，渠道在平台中的代码标识',
  `name` varchar(60) NOT NULL COMMENT '投资人姓名',
  `product_no` char(6) NOT NULL COMMENT '金飞镖产品代码',
  `product_name` varchar(30) NOT NULL COMMENT '产品名称',
  `total_postion_vol` decimal(15,2) NOT NULL COMMENT '持有份额',
  `product_vol` decimal(15,2) DEFAULT NULL COMMENT '产品每份金额',
  `total_postion_amount` decimal(15,2) DEFAULT NULL COMMENT '持有金额',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='每日历史持仓表';



--
-- 业务汇总表 `business_gather`
--
DROP TABLE IF EXISTS `business_gather`;
CREATE TABLE `business_gather` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '表主键',
  `batch_code` varchar(10) NOT NULL COMMENT '批次号',
  `gather_date` date NOT NULL COMMENT '汇总日期',
  `plat_product_no`  varchar(30) NOT NULL COMMENT '产品代码',
  `product_no` char(6) NOT NULL COMMENT '金飞镖产品代码',
  `product_name` varchar(30) NOT NULL COMMENT '产品名称',
  `chn_code` char(10) NOT NULL COMMENT '渠道代码，渠道在平台中的代码标识',
  `trans_type` char(10) NOT NULL COMMENT '交易类型，0-认购，1-赎回',
  `trans_num` decimal(15,2) NOT NULL COMMENT '交易笔数汇总',
  `trans_vol` decimal(15,2) NOT NULL COMMENT '交易份额汇总',
  `trans_amount` decimal(15,2) NOT NULL COMMENT '交易金额汇总',
  `success_vol` decimal(15,2) NOT NULL COMMENT '成功份额汇总',
  `fail_vol` decimal(15,2) NOT NULL COMMENT '失败份额汇总',
  `success_amount` decimal(15,2) NOT NULL COMMENT '成功金额汇总',
  `fail_amount` decimal(15,2) NOT NULL COMMENT '失败金额汇总',
  `success_num` decimal(15,2) NOT NULL COMMENT '成功笔数汇总',
  `fail_num` decimal(15,2) NOT NULL COMMENT '失败笔数汇总',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='业务汇总表';



--
-- 渠道表 `chn_info`
--
DROP TABLE IF EXISTS `chn_info`;
CREATE TABLE `chn_info` (
`id`  int NOT NULL AUTO_INCREMENT COMMENT '表主键',
`chn_code` char(10) NOT NULL COMMENT '渠道代码，渠道在平台中的代码标识',
`chn_name` varchar(60) NOT NULL COMMENT '渠道名称',
`id_type` char(10) DEFAULT NULL COMMENT '证件类型：0-组织机构代码证，1-营业执照，2-社会统一信用代码',
`id_no` char(24) NOT NULL COMMENT '证件号码',
`contacts` varchar(30)  NOT NULL COMMENT '联系人',
`artificial` varchar(30) NOT NULL COMMENT '法人代表',
`adress` varchar(120) NOT NULL COMMENT '地址',
`phone` varchar(24) NOT NULL COMMENT '联系电话',
PRIMARY KEY (`id`)
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='渠道信息表';



--
-- 渠道产品关系表 `chn_product`
--
DROP TABLE IF EXISTS `chn_product`;
CREATE TABLE `chn_product` (
`id`  int NOT NULL AUTO_INCREMENT COMMENT '表主键',
`product_name` char(10) NOT NULL COMMENT '产品名称',
`product_no` varchar(30) NOT NULL COMMENT '金飞镖产品代码',
`income_rate` decimal(15,3) NOT NULL COMMENT '收益率',
`percent_income_rate` decimal(15,4) NOT NULL COMMENT '万元收益',
`subs_toplimit` decimal(15,2) NOT NULL COMMENT '客户当日申购金额上限',
`redeem_toplimit` decimal(15,2) NOT NULL COMMENT '客户当日赎回金额上限',
`position_limit` decimal(15,2) NOT NULL COMMENT '客户持仓限额',
`product_subs_toplimit` decimal(15,2) NOT NULL COMMENT '产品当日申购金额上限',
`product_redeem_toplimit` decimal(15,2) NOT NULL COMMENT '产品当日赎回金额上限',
`total_limit` decimal(15,2) NOT NULL COMMENT '产品总额度',
`assign_limit` decimal(15,2) NOT NULL COMMENT '渠道产品分配总额度',
`prefer_date` date DEFAULT NULL COMMENT '利率录入日期',
`chn_code` char(10) NOT NULL COMMENT '渠道代码，渠道在平台中的代码标识',
PRIMARY KEY (`id`)
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='渠道产品关系表';


--
-- 渠道产品历史表 `chn_product_history`
--
DROP TABLE IF EXISTS `chn_product_history`;
CREATE TABLE `chn_product_history` (
`id`  int NOT NULL AUTO_INCREMENT COMMENT '表主键',
`product_name` char(10) NOT NULL COMMENT '产品名称',
`product_no` varchar(30) NOT NULL COMMENT '金飞镖产品代码',
`income_rate` decimal(15,3) NOT NULL COMMENT '收益率',
`percent_income_rate` decimal(15,4) NOT NULL COMMENT '万元收益',
`subs_toplimit` decimal(15,2) NOT NULL COMMENT '客户当日申购金额上限',
`redeem_toplimit` decimal(15,2) NOT NULL COMMENT '客户当日赎回金额上限',
`position_limit` decimal(15,2) NOT NULL COMMENT '客户持仓限额',
`product_subs_toplimit` decimal(15,2) NOT NULL COMMENT '产品当日申购金额上限',
`product_redeem_toplimit` decimal(15,2) NOT NULL COMMENT '产品当日赎回金额上限',
`total_limit` decimal(15,2) NOT NULL COMMENT '产品总额度',
`assign_limit` decimal(15,2) NOT NULL COMMENT '渠道产品分配总额度',
`prefer_date` date DEFAULT NULL COMMENT '利率录入日期',
`chn_code` char(10) NOT NULL COMMENT '渠道代码，渠道在平台中的代码标识',
PRIMARY KEY (`id`)
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='渠道产品历史表';





--
-- 交易所表 `exchange_info`
--
DROP TABLE IF EXISTS `exchange_info`;
CREATE TABLE `exchange_info` (
`id`  int NOT NULL AUTO_INCREMENT COMMENT '表主键',
`chn_code` char(10) NOT NULL COMMENT '交易所代码',
`chn_name` varchar(60) NOT NULL COMMENT '交易所名称',
`id_type` char(10) DEFAULT NULL COMMENT '证件类型：0-组织机构代码证，1-营业执照，2-社会统一信用代码',
`id_no` char(24) NOT NULL COMMENT '证件号码',
`contacts` varchar(30)  NOT NULL COMMENT '联系人',
`artificial` varchar(30) NOT NULL COMMENT '法人代表',
`adress` varchar(120) NOT NULL COMMENT '地址',
`phone` varchar(24) NOT NULL COMMENT '联系电话',
PRIMARY KEY (`id`)
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='交易所表';



