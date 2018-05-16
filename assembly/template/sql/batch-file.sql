DROP TABLE IF EXISTS `schedule_file_sequence`;
CREATE TABLE `schedule_file_sequence` (
  `file_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `file_key` varchar(128) NOT NULL COMMENT '带Pattern的文件Key',
  `file_seq` int(11) NOT NULL COMMENT '文件序号',
  PRIMARY KEY (`file_id`),
  UNIQUE KEY `SEQ_FILE_U` (`file_key`)
) ENGINE=InnoDB AUTO_INCREMENT=219 DEFAULT CHARSET=utf8 COMMENT='记录文件生成序列';

DROP TABLE IF EXISTS `schedule_generated_file`;
CREATE TABLE `schedule_generated_file` (
  `file_id` int(11) NOT NULL AUTO_INCREMENT,
  `file_name` varchar(200) NOT NULL DEFAULT '' COMMENT '文件名',
  `institution` varchar(16) NOT NULL DEFAULT '' COMMENT '文件发送机构',
  `batch_date` datetime NOT NULL,
  `backup_location` varchar(128) NOT NULL DEFAULT '' COMMENT '文件备份路径',
  PRIMARY KEY (`file_id`),
  UNIQUE KEY `GEN_FILE_U` (`file_name`,`institution`)
) ENGINE=InnoDB AUTO_INCREMENT=113 DEFAULT CHARSET=utf8 COMMENT='记录生成的文件记录';

DROP TABLE IF EXISTS `schedule_id_sequence`;
CREATE TABLE `schedule_id_sequence` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `seq_key` varchar(128) NOT NULL COMMENT '递增Key',
  `seq` bigint(20) NOT NULL COMMENT '序号',
  PRIMARY KEY (`id`),
  UNIQUE KEY `SEQ_FILE_U` (`seq_key`)
) ENGINE=InnoDB AUTO_INCREMENT=3533 DEFAULT CHARSET=utf8 COMMENT='记录生成id的序列';

DROP TABLE IF EXISTS `schedule_institution_file`;
CREATE TABLE `schedule_institution_file` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `file_pattern` varchar(100) NOT NULL,
  `institution` varchar(20) NOT NULL,
  `file_type` varchar(100) NOT NULL,
  `gmt_create` datetime NOT NULL,
  `gmt_modified` datetime NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `pattern_institution` (`file_pattern`,`institution`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `schedule_received_file`;
CREATE TABLE `schedule_received_file` (
  `file_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '文件存放路径',
  `file_name` varchar(200) NOT NULL COMMENT '文件名',
  `institution` varchar(16) NOT NULL COMMENT '文件提供方',
  `receive_time` datetime NOT NULL COMMENT '文件接收时间',
  `batch_date` datetime DEFAULT NULL COMMENT '文件批次时间',
  `backup_location` varchar(128) NOT NULL COMMENT '备份位置',
  PRIMARY KEY (`file_id`),
  UNIQUE KEY `RECEV_FILE_U` (`file_name`,`institution`)
) ENGINE=InnoDB AUTO_INCREMENT=140 DEFAULT CHARSET=utf8 COMMENT='记录处理过的渠道和机构端发送过来的文件';
