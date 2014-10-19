-- phpMyAdmin SQL Dump
-- version 2.10.2
-- http://www.phpmyadmin.net
-- 
-- 主机: localhost
-- 生成日期: 2014 年 10 月 11 日 12:39
-- 服务器版本: 5.0.45
-- PHP 版本: 5.2.3

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";

-- 
-- 数据库: `graduation`
-- 

-- --------------------------------------------------------

-- 
-- 表的结构 `apply`
-- 

DROP TABLE IF EXISTS `apply`;
CREATE TABLE `apply` (
  `id` int(11) NOT NULL auto_increment COMMENT 'id自增',
  `user_id` varchar(255) NOT NULL COMMENT '申请人编号',
  `department_id` varchar(255) NOT NULL COMMENT '所属系部',
  `type` varchar(255) NOT NULL COMMENT '类型|1 开题答辩 2 毕业答辩',
  `pass` varchar(255) NOT NULL default '0' COMMENT '是否同意答辩|指导老师是否同意参加答辩| 0 未处理1 不同意 2 同意',
  `status` varchar(255) NOT NULL default '1' COMMENT '状态| 1 未受理 2 已受理',
  `create_time` varchar(255) NOT NULL COMMENT '创建时间|格式：2013-04-10 08:09:09',
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 COMMENT='开题答辩|毕业答辩申请表' AUTO_INCREMENT=70 ;

-- 
-- 导出表中的数据 `apply`
-- 

INSERT INTO `apply` VALUES (1, '1106401002', '064', '1', '1', '2', '');
INSERT INTO `apply` VALUES (3, '1106401002', '064', '2', '1', '2', '');
INSERT INTO `apply` VALUES (4, '1115403025', '064', '1', '2', '2', '');
INSERT INTO `apply` VALUES (5, '1106401001', '064', '1', '2', '2', '2014-09-06 10:28:39');
INSERT INTO `apply` VALUES (15, '1106401001', '064', '1', '0', '1', '2014-10-02 11:55:59');
INSERT INTO `apply` VALUES (7, '1106401023', '064', '1', '2', '2', '2014-09-14 13:14:32');
INSERT INTO `apply` VALUES (14, '1106401001', '064', '1', '0', '2', '2014-10-02 11:44:48');
INSERT INTO `apply` VALUES (12, '1106401023', '064', '2', '0', '1', '2014-09-14 20:13:19');
INSERT INTO `apply` VALUES (13, '1106401002', '064', '2', '1', '1', '2014-09-15 17:49:13');
INSERT INTO `apply` VALUES (16, '1106401001', '064', '1', '0', '1', '2014-10-02 12:05:01');
INSERT INTO `apply` VALUES (17, '1106401001', '064', '1', '0', '1', '2014-10-02 12:15:21');
INSERT INTO `apply` VALUES (18, '1106401001', '064', '1', '0', '1', '2014-10-02 12:18:23');
INSERT INTO `apply` VALUES (19, '1106401001', '064', '1', '0', '1', '2014-10-02 12:24:26');
INSERT INTO `apply` VALUES (20, '1106401001', '064', '1', '0', '1', '2014-10-09 21:14:08');
INSERT INTO `apply` VALUES (21, '1106401001', '064', '1', '0', '1', '2014-10-09 21:21:13');
INSERT INTO `apply` VALUES (22, '1106401001', '064', '1', '0', '1', '2014-10-10 10:39:49');
INSERT INTO `apply` VALUES (23, '1106401001', '064', '1', '0', '1', '2014-10-10 10:43:37');
INSERT INTO `apply` VALUES (24, '1106401001', '064', '1', '0', '1', '2014-10-10 10:45:05');
INSERT INTO `apply` VALUES (25, '1106401023', '064', '1', '0', '1', '2014-10-10 10:52:31');
INSERT INTO `apply` VALUES (26, '1106401001', '064', '1', '0', '1', '2014-10-10 11:12:27');
INSERT INTO `apply` VALUES (27, '1106401001', '064', '1', '0', '1', '2014-10-10 11:15:28');
INSERT INTO `apply` VALUES (28, '1106401001', '064', '1', '0', '1', '2014-10-10 11:16:36');
INSERT INTO `apply` VALUES (29, '1106401001', '064', '1', '0', '1', '2014-10-10 11:19:29');
INSERT INTO `apply` VALUES (30, '1106401001', '064', '1', '0', '1', '2014-10-10 11:29:10');
INSERT INTO `apply` VALUES (31, '1106401001', '064', '1', '0', '1', '2014-10-10 11:30:55');
INSERT INTO `apply` VALUES (32, '1106401001', '064', '1', '0', '1', '2014-10-10 11:32:44');
INSERT INTO `apply` VALUES (33, '1106401001', '064', '1', '0', '1', '2014-10-10 11:42:08');
INSERT INTO `apply` VALUES (34, '1106401001', '064', '1', '0', '1', '2014-10-10 11:45:07');
INSERT INTO `apply` VALUES (35, '1106401001', '064', '1', '0', '1', '2014-10-10 12:00:25');
INSERT INTO `apply` VALUES (36, '1106401001', '064', '1', '0', '1', '2014-10-10 12:02:46');
INSERT INTO `apply` VALUES (37, '1106401001', '064', '1', '0', '1', '2014-10-10 12:06:18');
INSERT INTO `apply` VALUES (38, '1106401001', '064', '1', '0', '1', '2014-10-10 12:11:20');
INSERT INTO `apply` VALUES (39, '1106401001', '064', '1', '0', '1', '2014-10-10 12:12:56');
INSERT INTO `apply` VALUES (40, '1106401001', '064', '1', '0', '1', '2014-10-10 12:18:50');
INSERT INTO `apply` VALUES (41, '1106401001', '064', '1', '0', '1', '2014-10-10 12:20:09');
INSERT INTO `apply` VALUES (42, '1106401002', '064', '1', '2', '2', '2014-10-10 14:22:30');
INSERT INTO `apply` VALUES (43, '1106401023', '064', '1', '0', '1', '2014-10-10 14:38:23');
INSERT INTO `apply` VALUES (44, '1106401023', '064', '1', '0', '1', '2014-10-10 14:46:21');
INSERT INTO `apply` VALUES (45, '1106401001', '064', '1', '0', '1', '2014-10-10 15:33:10');
INSERT INTO `apply` VALUES (46, '1106401001', '064', '1', '0', '1', '2014-10-10 15:43:24');
INSERT INTO `apply` VALUES (47, '1106401001', '064', '1', '0', '1', '2014-10-10 15:46:34');
INSERT INTO `apply` VALUES (48, '1106401023', '064', '1', '0', '1', '2014-10-10 15:48:28');
INSERT INTO `apply` VALUES (49, '1106401002', '064', '1', '1', '1', '2014-10-10 17:31:12');
INSERT INTO `apply` VALUES (50, '1106401023', '064', '1', '0', '1', '2014-10-10 18:09:20');
INSERT INTO `apply` VALUES (51, '1106401023', '064', '1', '0', '1', '2014-10-10 18:16:14');
INSERT INTO `apply` VALUES (52, '1106401023', '064', '1', '0', '1', '2014-10-10 18:18:26');
INSERT INTO `apply` VALUES (53, '1106401023', '064', '1', '0', '1', '2014-10-10 18:27:12');
INSERT INTO `apply` VALUES (54, '1106401023', '064', '1', '0', '1', '2014-10-10 19:21:30');
INSERT INTO `apply` VALUES (55, '1106401023', '064', '1', '0', '1', '2014-10-10 19:24:56');
INSERT INTO `apply` VALUES (56, '1106401023', '064', '1', '0', '1', '2014-10-10 19:28:45');
INSERT INTO `apply` VALUES (57, '1106401023', '064', '1', '0', '1', '2014-10-10 19:32:13');
INSERT INTO `apply` VALUES (58, '1106401023', '064', '1', '0', '1', '2014-10-10 19:44:09');
INSERT INTO `apply` VALUES (59, '1106401002', '064', '1', '2', '2', '2014-10-10 19:46:53');
INSERT INTO `apply` VALUES (60, '1106401023', '064', '1', '0', '1', '2014-10-10 19:49:29');
INSERT INTO `apply` VALUES (61, '1106401023', '064', '1', '0', '1', '2014-10-10 19:51:20');
INSERT INTO `apply` VALUES (62, '1106401001', '064', '1', '0', '1', '2014-10-10 19:55:24');
INSERT INTO `apply` VALUES (63, '1106401023', '064', '1', '0', '1', '2014-10-10 19:57:54');
INSERT INTO `apply` VALUES (64, '1106401023', '064', '1', '0', '1', '2014-10-10 19:59:10');
INSERT INTO `apply` VALUES (65, '1106401023', '064', '1', '0', '1', '2014-10-10 20:01:21');
INSERT INTO `apply` VALUES (66, '1106401023', '064', '1', '0', '1', '2014-10-10 20:04:03');
INSERT INTO `apply` VALUES (67, '1106401013', '064', '1', '0', '1', '2014-10-10 20:22:10');
INSERT INTO `apply` VALUES (68, '1106401013', '064', '2', '0', '1', '2014-10-10 21:45:44');
INSERT INTO `apply` VALUES (69, '1106401002', '064', '2', '2', '2', '2014-10-11 16:05:38');

-- --------------------------------------------------------

-- 
-- 表的结构 `department`
-- 

DROP TABLE IF EXISTS `department`;
CREATE TABLE `department` (
  `dept_ID` varchar(10) NOT NULL COMMENT '系部ID',
  `dept_Name` varchar(50) NOT NULL COMMENT '系部名称',
  `major_type` varchar(255) NOT NULL default '1' COMMENT '系部专业类型| 1 理工医农学类  2 人文社科类',
  PRIMARY KEY  (`dept_ID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='系部表';

-- 
-- 导出表中的数据 `department`
-- 

INSERT INTO `department` VALUES ('064', '计算机工程系3', '1');
INSERT INTO `department` VALUES ('065', '教育科学系', '1');
INSERT INTO `department` VALUES ('066', '人文教育系', '1');
INSERT INTO `department` VALUES ('068', '外国语言文学系', '1');
INSERT INTO `department` VALUES ('069', '计算机工程系', '1');
INSERT INTO `department` VALUES ('070', '教育科学系', '1');
INSERT INTO `department` VALUES ('061', '单端放大', '1');
INSERT INTO `department` VALUES ('072', 'dffdfds', '1');
INSERT INTO `department` VALUES ('073', 'jkjjkj', '1');
INSERT INTO `department` VALUES ('063', 'fsfsfsd', '1');
INSERT INTO `department` VALUES ('074', 'klpkopjo', '1');
INSERT INTO `department` VALUES ('075', '564511', '1');
INSERT INTO `department` VALUES ('076', '5645644', '1');
INSERT INTO `department` VALUES ('077', 'fdafdasffdsfew', '1');

-- --------------------------------------------------------

-- 
-- 表的结构 `deptmanager`
-- 

DROP TABLE IF EXISTS `deptmanager`;
CREATE TABLE `deptmanager` (
  `dm_ID` varchar(12) NOT NULL COMMENT '系管编号',
  `dept_ID` varchar(10) default NULL COMMENT '系部ID',
  `dm_Name` varchar(16) default NULL COMMENT '系管姓名',
  `dm_Sex` varchar(4) default NULL COMMENT '性别',
  `dm_Tel` varchar(15) default NULL COMMENT '联系电话',
  `dm_Email` varchar(30) NOT NULL COMMENT '电子邮箱',
  PRIMARY KEY  (`dm_ID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='系部管理员表';

-- 
-- 导出表中的数据 `deptmanager`
-- 

INSERT INTO `deptmanager` VALUES ('1106401042', '064', '黄泽西', '1', '15111592794', '2');
INSERT INTO `deptmanager` VALUES ('1106401001', '1', '11111', NULL, NULL, '');
INSERT INTO `deptmanager` VALUES ('06033', '065', '黄泽西', '1', '111', '1111');

-- --------------------------------------------------------

-- 
-- 表的结构 `gradeall`
-- 

DROP TABLE IF EXISTS `gradeall`;
CREATE TABLE `gradeall` (
  `gA_ID` int(11) NOT NULL auto_increment COMMENT '综合成绩编号',
  `stu_ID` varchar(12) default NULL COMMENT '学号',
  `department_id` varchar(255) NOT NULL COMMENT '所属系部',
  `gA_Grade` float default NULL COMMENT '综合成绩',
  `status` varchar(255) NOT NULL default '1' COMMENT '状态| 1 有效 2 无效',
  `create_time` varchar(255) NOT NULL COMMENT '创建时间|格式：2013-04-10 08:09:09',
  PRIMARY KEY  (`gA_ID`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 COMMENT='综合成绩表' AUTO_INCREMENT=2 ;

-- 
-- 导出表中的数据 `gradeall`
-- 

INSERT INTO `gradeall` VALUES (1, '1106401023', '064', 78.7, '1', '2014-10-04 11:57:38');

-- --------------------------------------------------------

-- 
-- 表的结构 `gradeone`
-- 

DROP TABLE IF EXISTS `gradeone`;
CREATE TABLE `gradeone` (
  `go_ID` int(11) NOT NULL auto_increment COMMENT '表一编号',
  `stu_ID` varchar(12) default NULL COMMENT '学号',
  `go_One` float default NULL COMMENT '项一成绩',
  `go_Two` float default NULL COMMENT '项二成绩',
  `go_Three` float default NULL COMMENT '项三成绩',
  `go_Four` float default NULL COMMENT '项四成绩',
  `go_Five` float default NULL COMMENT '项五成绩',
  `go_All` float default NULL COMMENT '总成绩',
  `content` varchar(255) default NULL COMMENT '指导教师评定意见',
  `status` varchar(255) NOT NULL default '1' COMMENT '状态| 1 有效 2 无效',
  `create_time` varchar(255) NOT NULL COMMENT '创建时间|格式：2013-04-10 08:09:09',
  PRIMARY KEY  (`go_ID`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 COMMENT='成绩表一|指导教师评分表' AUTO_INCREMENT=4 ;

-- 
-- 导出表中的数据 `gradeone`
-- 

INSERT INTO `gradeone` VALUES (2, '1106401023', 3, 12, 30, 23, 12, 80, '立意新颖 ，有创意 ， good well ', '1', '');
INSERT INTO `gradeone` VALUES (3, '1106401002', 10, 18, 26, 27, 7, 88, 'fdfd ', '1', '2014-10-11 16:07:46');

-- --------------------------------------------------------

-- 
-- 表的结构 `gradethree`
-- 

DROP TABLE IF EXISTS `gradethree`;
CREATE TABLE `gradethree` (
  `gtr_ID` int(11) NOT NULL auto_increment COMMENT '表三编号',
  `stu_ID` varchar(12) default NULL COMMENT '学号',
  `gtr_One` float default NULL COMMENT '项一成绩',
  `gtr_Two` float default NULL COMMENT '项二成绩',
  `gtr_Three` float default NULL COMMENT '项三成绩',
  `gtr_Four` float default NULL COMMENT '项四成绩',
  `gtr_Five` float default NULL COMMENT '项五成绩',
  `gtr_All` float default NULL COMMENT '总成绩',
  `content` varchar(255) default NULL COMMENT '评审意见',
  `status` varchar(255) NOT NULL default '1' COMMENT '状态| 1 有效 2 无效',
  `create_time` varchar(255) NOT NULL COMMENT '创建时间|格式：2013-04-10 08:09:09',
  PRIMARY KEY  (`gtr_ID`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 COMMENT='成绩表三|答辩小组老师评分表' AUTO_INCREMENT=3 ;

-- 
-- 导出表中的数据 `gradethree`
-- 

INSERT INTO `gradethree` VALUES (1, '1106401023', 7, 25, 27, 5, 17, 81, NULL, '1', '2014-10-04 11:46:38');
INSERT INTO `gradethree` VALUES (2, '1106401023', 5, 27, 24, 8, 15, 79, NULL, '1', '2014-10-04 11:57:38');

-- --------------------------------------------------------

-- 
-- 表的结构 `gradetwo`
-- 

DROP TABLE IF EXISTS `gradetwo`;
CREATE TABLE `gradetwo` (
  `gt_ID` int(11) NOT NULL auto_increment COMMENT '表二编号',
  `stu_ID` varchar(12) default NULL COMMENT '学号',
  `gt_One` float default NULL COMMENT '项一成绩',
  `gt_Two` float default NULL COMMENT '项二成绩',
  `gt_Three` float default NULL COMMENT '项三成绩',
  `gt_Four` float default NULL COMMENT '项四成绩',
  `gt_Five` float default NULL COMMENT '项五成绩',
  `gt_All` float default NULL COMMENT '总成绩',
  `content` varchar(255) default NULL COMMENT '评审意见',
  `status` varchar(255) NOT NULL default '1' COMMENT '状态| 1 有效 2 无效',
  `create_time` varchar(255) NOT NULL COMMENT '创建时间|格式：2013-04-10 08:09:09',
  PRIMARY KEY  (`gt_ID`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 COMMENT='成绩表二|评阅教师评分表' AUTO_INCREMENT=5 ;

-- 
-- 导出表中的数据 `gradetwo`
-- 

INSERT INTO `gradetwo` VALUES (4, '1106401023', 6, 15, 25, 26, 5, 77, 'dasdasdf打发士大夫实得分为更好地', '1', '2014-09-27 15:21:16');

-- --------------------------------------------------------

-- 
-- 表的结构 `graduateinfo`
-- 

DROP TABLE IF EXISTS `graduateinfo`;
CREATE TABLE `graduateinfo` (
  `gdi_ID` int(11) NOT NULL auto_increment COMMENT '答辩ID',
  `name` varchar(255) default NULL COMMENT '标题|信息标题',
  `stu_ID` varchar(12) default NULL COMMENT '学号',
  `department_id` varchar(255) NOT NULL COMMENT '所属系部',
  `gdi_Date` varchar(255) default NULL COMMENT '答辩日期',
  `gdi_Place` varchar(20) default NULL COMMENT '答辩地点',
  `gdi_Number` int(11) default NULL COMMENT '答辩序号',
  `judge` varchar(255) NOT NULL COMMENT '评审教师',
  `headerman` varchar(255) NOT NULL COMMENT '评审组长',
  `content` varchar(255) default NULL COMMENT '内容|会议记录摘要',
  `status` varchar(255) NOT NULL default '1' COMMENT '状态|1 未处理,2 已处理',
  `create_time` varchar(255) NOT NULL COMMENT '创建时间|格式：2013-04-10 08:09:09',
  PRIMARY KEY  (`gdi_ID`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 COMMENT='毕业答辩信息表' AUTO_INCREMENT=6 ;

-- 
-- 导出表中的数据 `graduateinfo`
-- 

INSERT INTO `graduateinfo` VALUES (3, '', '1106401023', '064', '2014-09-23', 'E1A-301', 1, '06033,001,010,1115403026', '010', '4', '2', '2014-09-23 16:44:04');
INSERT INTO `graduateinfo` VALUES (4, '', '1106401002', '064', '2014-09-23', 'E1A-302', 1, '002,079,1115403025', '1115403025', '', '1', '2014-09-23 16:47:16');
INSERT INTO `graduateinfo` VALUES (5, '', '1106401002', '064', '2014-10-11', 'E1A-301', 1, '010,1115403026', '1115403026', '', '1', '2014-10-11 17:49:08');

-- --------------------------------------------------------

-- 
-- 表的结构 `linkeddata_apply_graduateinfo`
-- 

DROP TABLE IF EXISTS `linkeddata_apply_graduateinfo`;
CREATE TABLE `linkeddata_apply_graduateinfo` (
  `id` int(11) NOT NULL auto_increment COMMENT 'ID|只能是数字',
  `item_id` varchar(255) NOT NULL COMMENT '被关联编号|多的对象的编号',
  `rel_id` varchar(255) NOT NULL COMMENT '关联编号|一的对象的编号',
  `extend` varchar(255) NOT NULL default '0' COMMENT '扩展信息|如标签关联的分类信息等',
  `create_time` varchar(255) NOT NULL COMMENT '创建时间|格式：2013-04-10 08:09:09',
  PRIMARY KEY  (`id`),
  KEY `rel_id` (`rel_id`),
  KEY `item_id` (`item_id`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 COMMENT='关联数据|答辩申请与毕业答辩信息表关联' AUTO_INCREMENT=2 ;

-- 
-- 导出表中的数据 `linkeddata_apply_graduateinfo`
-- 

INSERT INTO `linkeddata_apply_graduateinfo` VALUES (1, '69', '5', '0', '2014-10-11 17:49:08');

-- --------------------------------------------------------

-- 
-- 表的结构 `linkeddata_apply_topicapply`
-- 

DROP TABLE IF EXISTS `linkeddata_apply_topicapply`;
CREATE TABLE `linkeddata_apply_topicapply` (
  `id` int(11) NOT NULL auto_increment COMMENT 'ID|只能是数字',
  `item_id` varchar(255) NOT NULL COMMENT '被关联编号|多的对象的编号',
  `rel_id` varchar(255) NOT NULL COMMENT '关联编号|一的对象的编号',
  `extend` varchar(255) NOT NULL default '0' COMMENT '扩展信息|如标签关联的分类信息等',
  `create_time` varchar(255) NOT NULL COMMENT '创建时间|格式：2013-04-10 08:09:09',
  PRIMARY KEY  (`id`),
  KEY `rel_id` (`rel_id`),
  KEY `item_id` (`item_id`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 COMMENT='关联数据|答辩申请与答辩申请表关联' AUTO_INCREMENT=7 ;

-- 
-- 导出表中的数据 `linkeddata_apply_topicapply`
-- 

INSERT INTO `linkeddata_apply_topicapply` VALUES (1, '53', '43', '0', '2014-10-10 18:27:12');
INSERT INTO `linkeddata_apply_topicapply` VALUES (2, '59', '49', '0', '2014-10-10 19:46:53');
INSERT INTO `linkeddata_apply_topicapply` VALUES (3, '60', '50', '0', '2014-10-10 19:49:29');
INSERT INTO `linkeddata_apply_topicapply` VALUES (4, '65', '55', '0', '2014-10-10 20:01:21');
INSERT INTO `linkeddata_apply_topicapply` VALUES (5, '66', '56', '0', '2014-10-10 20:04:03');
INSERT INTO `linkeddata_apply_topicapply` VALUES (6, '67', '57', '0', '2014-10-10 20:22:10');

-- --------------------------------------------------------

-- 
-- 表的结构 `linkeddata_apply_topicfinish`
-- 

DROP TABLE IF EXISTS `linkeddata_apply_topicfinish`;
CREATE TABLE `linkeddata_apply_topicfinish` (
  `id` int(11) NOT NULL auto_increment COMMENT 'ID|只能是数字',
  `item_id` varchar(255) NOT NULL COMMENT '被关联编号|多的对象的编号',
  `rel_id` varchar(255) NOT NULL COMMENT '关联编号|一的对象的编号',
  `extend` varchar(255) NOT NULL default '0' COMMENT '扩展信息|如标签关联的分类信息等',
  `create_time` varchar(255) NOT NULL COMMENT '创建时间|格式：2013-04-10 08:09:09',
  PRIMARY KEY  (`id`),
  KEY `rel_id` (`rel_id`),
  KEY `item_id` (`item_id`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 COMMENT='关联数据|毕业答辩相关材料关联' AUTO_INCREMENT=3 ;

-- 
-- 导出表中的数据 `linkeddata_apply_topicfinish`
-- 

INSERT INTO `linkeddata_apply_topicfinish` VALUES (1, '68', '3', '0', '2014-10-10 21:45:45');
INSERT INTO `linkeddata_apply_topicfinish` VALUES (2, '69', '4', '0', '2014-10-11 16:05:38');

-- --------------------------------------------------------

-- 
-- 表的结构 `linkeddata_apply_topicinfo`
-- 

DROP TABLE IF EXISTS `linkeddata_apply_topicinfo`;
CREATE TABLE `linkeddata_apply_topicinfo` (
  `id` int(11) NOT NULL auto_increment COMMENT 'ID|只能是数字',
  `item_id` varchar(255) NOT NULL COMMENT '被关联编号|多的对象的编号',
  `rel_id` varchar(255) NOT NULL COMMENT '关联编号|一的对象的编号',
  `extend` varchar(255) NOT NULL default '0' COMMENT '扩展信息|如标签关联的分类信息等',
  `create_time` varchar(255) NOT NULL COMMENT '创建时间|格式：2013-04-10 08:09:09',
  PRIMARY KEY  (`id`),
  KEY `rel_id` (`rel_id`),
  KEY `item_id` (`item_id`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 COMMENT='关联数据|答辩申请与开题答辩信息表关联' AUTO_INCREMENT=5 ;

-- 
-- 导出表中的数据 `linkeddata_apply_topicinfo`
-- 

INSERT INTO `linkeddata_apply_topicinfo` VALUES (1, '59', '23', '0', '2014-10-11 11:20:11');
INSERT INTO `linkeddata_apply_topicinfo` VALUES (2, '42', '27', '0', '2014-10-11 11:20:11');
INSERT INTO `linkeddata_apply_topicinfo` VALUES (3, '59', '28', '0', '2014-10-11 14:57:51');
INSERT INTO `linkeddata_apply_topicinfo` VALUES (4, '42', '29', '0', '2014-10-11 14:57:51');

-- --------------------------------------------------------

-- 
-- 表的结构 `linkeddata_apply_topicreport`
-- 

DROP TABLE IF EXISTS `linkeddata_apply_topicreport`;
CREATE TABLE `linkeddata_apply_topicreport` (
  `id` int(11) NOT NULL auto_increment COMMENT 'ID|只能是数字',
  `item_id` varchar(255) NOT NULL COMMENT '被关联编号|多的对象的编号',
  `rel_id` varchar(255) NOT NULL COMMENT '关联编号|一的对象的编号',
  `extend` varchar(255) NOT NULL default '0' COMMENT '扩展信息|如标签关联的分类信息等',
  `create_time` varchar(255) NOT NULL COMMENT '创建时间|格式：2013-04-10 08:09:09',
  PRIMARY KEY  (`id`),
  KEY `rel_id` (`rel_id`),
  KEY `item_id` (`item_id`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 COMMENT='关联数据|答辩申请与答辩报告书关联' AUTO_INCREMENT=7 ;

-- 
-- 导出表中的数据 `linkeddata_apply_topicreport`
-- 

INSERT INTO `linkeddata_apply_topicreport` VALUES (1, '53', '5', '0', '2014-10-10 18:27:12');
INSERT INTO `linkeddata_apply_topicreport` VALUES (2, '59', '5', '0', '2014-10-10 19:46:53');
INSERT INTO `linkeddata_apply_topicreport` VALUES (3, '60', '6', '0', '2014-10-10 19:49:29');
INSERT INTO `linkeddata_apply_topicreport` VALUES (4, '65', '7', '0', '2014-10-10 20:01:21');
INSERT INTO `linkeddata_apply_topicreport` VALUES (5, '66', '8', '0', '2014-10-10 20:04:03');
INSERT INTO `linkeddata_apply_topicreport` VALUES (6, '67', '9', '0', '2014-10-10 20:22:10');

-- --------------------------------------------------------

-- 
-- 表的结构 `linkeddata_apply_topicscore`
-- 

DROP TABLE IF EXISTS `linkeddata_apply_topicscore`;
CREATE TABLE `linkeddata_apply_topicscore` (
  `id` int(11) NOT NULL auto_increment COMMENT 'ID|只能是数字',
  `item_id` varchar(255) NOT NULL COMMENT '被关联编号|多的对象的编号',
  `rel_id` varchar(255) NOT NULL COMMENT '关联编号|一的对象的编号',
  `extend` varchar(255) NOT NULL default '0' COMMENT '扩展信息|如标签关联的分类信息等',
  `create_time` varchar(255) NOT NULL COMMENT '创建时间|格式：2013-04-10 08:09:09',
  PRIMARY KEY  (`id`),
  KEY `rel_id` (`rel_id`),
  KEY `item_id` (`item_id`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 COMMENT='关联数据|答辩申请与开题答辩成绩关联' AUTO_INCREMENT=4 ;

-- 
-- 导出表中的数据 `linkeddata_apply_topicscore`
-- 

INSERT INTO `linkeddata_apply_topicscore` VALUES (1, '59', '26', '0', '2014-10-11 11:20:11');
INSERT INTO `linkeddata_apply_topicscore` VALUES (2, '42', '27', '0', '2014-10-11 11:20:11');
INSERT INTO `linkeddata_apply_topicscore` VALUES (3, '59', '8', '0', '2014-10-11 14:28:22');

-- --------------------------------------------------------

-- 
-- 表的结构 `meeting`
-- 

DROP TABLE IF EXISTS `meeting`;
CREATE TABLE `meeting` (
  `id` int(11) NOT NULL auto_increment COMMENT 'id自增',
  `name` varchar(255) default NULL COMMENT '会议标题',
  `parent_id` varchar(255) NOT NULL COMMENT '所属父类',
  `start_time` varchar(255) default NULL COMMENT '会议开始时间',
  `place` varchar(255) default NULL COMMENT '会议地点',
  `person` varchar(255) default NULL COMMENT '与会人员',
  `content` text COMMENT '会议记录摘要',
  `hoster` varchar(255) default NULL COMMENT '会议主持人',
  `recorder` varchar(255) default NULL COMMENT '会议记录人',
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 COMMENT='会议|会议记录表' AUTO_INCREMENT=6 ;

-- 
-- 导出表中的数据 `meeting`
-- 

INSERT INTO `meeting` VALUES (1, '学生 1115403025 的开题答辩会议记录', '1', '2014-09-22', 'E1A-302', '06034,002,079,010,1115403025,1115403026', '我是会议记录摘要 我是 摘要', '黄泽西', '蒋启明');
INSERT INTO `meeting` VALUES (2, '学生 1106401023 【贺盼华】 的毕业答辩会议记录', '0', '1106401023', '1106401023', '1106401023', NULL, '换嘎斯', '家复合物');
INSERT INTO `meeting` VALUES (3, '学生 1106401023 【贺盼华】 的毕业答辩会议记录', '0', '1106401023', '1106401023', '1106401023', '谈谈你对springmvc的理解', '很好理解', '哈里发');
INSERT INTO `meeting` VALUES (4, '学生 1106401023 【贺盼华】 的毕业答辩会议记录', '0', '2014-09-23', 'E1A-301', '06033,001,010,1115403026', '谈谈你对IOC的理解', '胡经理', '海明威');
INSERT INTO `meeting` VALUES (5, '学生 1106401042 的开题答辩会议记录', '59', '2014-09-22', 'E1A-302', '06034,010,1115403025,1115403026', '24234', '的丰盛的', '啊分为');

-- --------------------------------------------------------

-- 
-- 表的结构 `message`
-- 

DROP TABLE IF EXISTS `message`;
CREATE TABLE `message` (
  `id` int(11) NOT NULL auto_increment COMMENT '编号|系统自动生成|show|hidden',
  `name` varchar(255) default NULL COMMENT '标题|信息标题',
  `content` text COMMENT '内容|详细内容',
  `to_id` varchar(255) NOT NULL COMMENT '收件人|收件人编号|show|linked',
  `from_id` varchar(255) NOT NULL COMMENT '发送人|发送人编号|show|linked',
  `status` tinyint(4) NOT NULL default '1' COMMENT '查看状态|1->未查看,2->已查看|show|select',
  `create_time` varchar(255) default NULL COMMENT '创建时间|格式：2013-04-10 08:09:09',
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 COMMENT='消息|消息模块' AUTO_INCREMENT=11 ;

-- 
-- 导出表中的数据 `message`
-- 

INSERT INTO `message` VALUES (1, 'admin', 'aaaaaaaa', '12', '11', 1, '1406782203000');
INSERT INTO `message` VALUES (2, '云翻涌 成夏眼泪被岁月蒸发这条路上的 你我她有谁迷路了', '测试测试测试测试测试风吹雨 成花时间追不上白马你年少 掌心的梦话依然紧握着吗云翻涌 成夏眼泪被岁月蒸发这条路上的 你我她有谁迷路了吗我们说好不分离要一直一直在一起就算与时间为敌就算与全世界背离佘朝文与赵敏共同签署了《怀化学院--昆山杰普软件软件工程师校企联合专业共建人才培养协议书》。根据协议，双方共建怀化学院软件工程师班，在专业建设规划、课程体系建设、校内实验室和校内外实习实训基地建设、学生就业和职业发展及“双师型”教师培养等方面展开紧密合作，创建校企合作应用型人才培养新模式。\r\n　　杰普软件是一家软件外包、研发咨询、高端IT培训多元化发展的集团公司，总部位于上海，分别成立了研发中心、实训中心、软件外包中心，专注于物联网、移动互联、电子商务及云计算等领域的产品研发及人才培养。', '010', '12', 2, '2014.08.03 13:16:02');
INSERT INTO `message` VALUES (3, '测试', '测试大赛测试测试测试测试', '010', '11', 2, '1406788637000');
INSERT INTO `message` VALUES (4, '怀化学院本科毕业论文(设计)任务书', '指导老师 蒋启明 已经下发怀化学院本科毕业论文(设计)任务书，请注意查收', '22', '010', 1, '2014-08-30 16:05:39');
INSERT INTO `message` VALUES (5, '怀化学院本科毕业论文(设计)任务书', '指导老师 蒋启明 已经下发怀化学院本科毕业论文(设计)任务书，请注意查收', '1106401002', '010', 2, '2014-08-30 16:12:52');
INSERT INTO `message` VALUES (6, '怀化学院本科毕业论文(设计)任务书', '指导老师 向延珍 已经下发怀化学院本科毕业论文(设计)任务书，请注意查收', '1106401002', '010', 1, '2014-09-26 09:28:01');
INSERT INTO `message` VALUES (7, '怀化学院本科毕业论文(设计)任务书', '指导老师 向延珍 已经下发怀化学院本科毕业论文(设计)任务书，请注意查收', '1106401002', '010', 1, '2014-09-26 09:32:38');
INSERT INTO `message` VALUES (8, '怀化学院本科毕业论文(设计)任务书', '指导老师 黄泽西 已经下发怀化学院本科毕业论文(设计)任务书，请注意查收', '1106401001', '010', 1, '2014-10-08 19:48:56');
INSERT INTO `message` VALUES (9, '怀化学院本科毕业论文(设计)任务书', '指导老师 黄泽西 已经下发怀化学院本科毕业论文(设计)任务书，请注意查收', '1106401013', '010', 1, '2014-10-10 22:06:21');
INSERT INTO `message` VALUES (10, '怀化学院本科毕业论文(设计)任务书', '指导老师 黄泽西 已经下发怀化学院本科毕业论文(设计)任务书，请注意查收', '1106401013', '010', 1, '2014-10-10 22:06:46');

-- --------------------------------------------------------

-- 
-- 表的结构 `opentopicinfo`
-- 

DROP TABLE IF EXISTS `opentopicinfo`;
CREATE TABLE `opentopicinfo` (
  `opi_ID` int(11) NOT NULL auto_increment COMMENT '开题编号',
  `name` varchar(255) default NULL COMMENT '标题|信息标题',
  `stu_ID` varchar(12) default NULL COMMENT '学号',
  `department_id` varchar(255) NOT NULL COMMENT '所属系部',
  `opi_Date` varchar(255) default NULL COMMENT '开题日期',
  `opi_Place` varchar(20) default NULL COMMENT '开题地点',
  `opi_Number` varchar(255) default NULL COMMENT '开题答辩序号',
  `judge` varchar(255) NOT NULL COMMENT '评审教师',
  `headerman` varchar(255) NOT NULL COMMENT '评审组长',
  `content` text COMMENT '内容|会议记录摘要',
  `status` varchar(255) NOT NULL default '1' COMMENT '状态|1 未处理,2 已处理',
  `create_time` varchar(255) NOT NULL COMMENT '创建时间|格式：2013-04-10 08:09:09',
  PRIMARY KEY  (`opi_ID`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 COMMENT='开题答辩信息表' AUTO_INCREMENT=30 ;

-- 
-- 导出表中的数据 `opentopicinfo`
-- 

INSERT INTO `opentopicinfo` VALUES (24, '', '1106401023', '064', '2014-09-22', 'E1A-301', '1', '06033', '06033', '', '1', '2014-09-22 10:21:51');
INSERT INTO `opentopicinfo` VALUES (26, '', '1106401002', '064', '2014-10-11', 'E1A-301', '2', '002,079', '079', '', '1', '2014-10-11 11:20:11');
INSERT INTO `opentopicinfo` VALUES (23, '', '1115403025', '064', '2014-09-22', 'E1A-302', '1', '06034,010,1115403025,1115403026', '010', '', '2', '2014-09-22 09:49:21');
INSERT INTO `opentopicinfo` VALUES (25, '', '1106401002', '064', '2014-09-22', 'E1A-302', '2', '001', '001', '', '1', '2014-09-22 10:22:52');
INSERT INTO `opentopicinfo` VALUES (27, '', '1106401002', '064', '2014-10-11', 'E1A-301', '3', '002,079,010', '010', '', '1', '2014-10-11 11:20:11');
INSERT INTO `opentopicinfo` VALUES (28, '', '1106401002', '064', '2014-10-11', 'E1A-301', '4', '1115403025,1115403026', '1115403025', '', '1', '2014-10-11 14:57:51');
INSERT INTO `opentopicinfo` VALUES (29, '', '1106401002', '064', '2014-10-11', 'E1A-301', '5', '1115403025,1115403026', '1115403025', '', '1', '2014-10-11 14:57:51');

-- --------------------------------------------------------

-- 
-- 表的结构 `opentopicscore`
-- 

DROP TABLE IF EXISTS `opentopicscore`;
CREATE TABLE `opentopicscore` (
  `id` int(11) NOT NULL auto_increment COMMENT 'id自增',
  `student_id` varchar(255) NOT NULL COMMENT '学生编号',
  `department_id` varchar(255) NOT NULL COMMENT '所属系部',
  `score` double NOT NULL COMMENT '开题答辩分数',
  `create_time` varchar(255) NOT NULL COMMENT '创建时间|格式：2013-04-10 08:09:09',
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 COMMENT='开题答辩成绩表' AUTO_INCREMENT=9 ;

-- 
-- 导出表中的数据 `opentopicscore`
-- 

INSERT INTO `opentopicscore` VALUES (1, '1115403025', '064', 84, '2014-09-30 18:10:16');
INSERT INTO `opentopicscore` VALUES (2, '1115403025', '064', 2, '2014-10-02 15:31:49');
INSERT INTO `opentopicscore` VALUES (3, '1115403025', '064', 60, '2014-10-02 15:49:04');
INSERT INTO `opentopicscore` VALUES (4, '1115403025', '064', 99.4, '2014-10-02 15:58:05');
INSERT INTO `opentopicscore` VALUES (5, '1115403025', '064', 59.9, '2014-10-02 16:08:20');
INSERT INTO `opentopicscore` VALUES (6, '1115403025', '064', 94, '2014-10-02 16:10:13');
INSERT INTO `opentopicscore` VALUES (7, '1115403025', '064', 94, '2014-10-02 16:28:40');
INSERT INTO `opentopicscore` VALUES (8, '1115403025', '064', 66, '2014-10-11 14:28:22');

-- --------------------------------------------------------

-- 
-- 表的结构 `profession`
-- 

DROP TABLE IF EXISTS `profession`;
CREATE TABLE `profession` (
  `pro_ID` varchar(10) NOT NULL COMMENT '专业编号',
  `gra_ID` int(11) default NULL COMMENT '年级编号',
  `pro_Name` varchar(50) NOT NULL COMMENT '专业名称',
  `dept_ID` varchar(255) default NULL COMMENT '系部编号',
  PRIMARY KEY  (`pro_ID`),
  KEY `FKB7DCE0FCBBE179F1` (`dept_ID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='专业表';

-- 
-- 导出表中的数据 `profession`
-- 

INSERT INTO `profession` VALUES ('06001', 8, '计算机科学与技术', '066');
INSERT INTO `profession` VALUES ('06002', 5, '网络工程', '065');
INSERT INTO `profession` VALUES ('06006', 5, '软件工程', '064');
INSERT INTO `profession` VALUES ('06007', 8, '计算机科学与技术', '064');
INSERT INTO `profession` VALUES ('06008', 8, '网络工程', '064');
INSERT INTO `profession` VALUES ('15401', 2, '人文教育', '065');

-- --------------------------------------------------------

-- 
-- 表的结构 `revieworder`
-- 

DROP TABLE IF EXISTS `revieworder`;
CREATE TABLE `revieworder` (
  `id` int(11) NOT NULL auto_increment COMMENT 'id自增',
  `student_id` varchar(255) NOT NULL COMMENT '学生编号',
  `teacher_id` varchar(255) NOT NULL COMMENT '教师编号',
  `department_id` varchar(255) NOT NULL COMMENT '所属系部',
  `tbgrade_id` varchar(255) NOT NULL COMMENT '年级|所属年级',
  `status` varchar(255) NOT NULL default '1' COMMENT '状态|1 未处理,2 已处理',
  `create_time` varchar(255) NOT NULL COMMENT '创建时间|格式：2013-04-10 08:09:09',
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 COMMENT='毕业评阅教师安排表' AUTO_INCREMENT=8 ;

-- 
-- 导出表中的数据 `revieworder`
-- 

INSERT INTO `revieworder` VALUES (5, '1106401042', '1115403026', '064', '8', '1', '2014-09-29 17:35:51');
INSERT INTO `revieworder` VALUES (2, '1106401023', '010', '064', '8', '1', '2014-09-26 14:48:42');
INSERT INTO `revieworder` VALUES (7, '1106401002', '1115403025', '064', '8', '1', '2014-09-29 17:35:51');
INSERT INTO `revieworder` VALUES (6, '1106401001', '079', '064', '8', '1', '2014-09-29 17:35:51');

-- --------------------------------------------------------

-- 
-- 表的结构 `reviewresult`
-- 

DROP TABLE IF EXISTS `reviewresult`;
CREATE TABLE `reviewresult` (
  `id` int(11) NOT NULL auto_increment COMMENT '编号|系统自动生成',
  `top_ID` varchar(255) NOT NULL COMMENT '课题ID',
  `judge_ID` varchar(255) NOT NULL COMMENT '评审人ID',
  `status` varchar(255) NOT NULL default '1' COMMENT '评审结果|1 通过 2 不通过',
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 COMMENT='课题评审结果' AUTO_INCREMENT=25 ;

-- 
-- 导出表中的数据 `reviewresult`
-- 

INSERT INTO `reviewresult` VALUES (1, '20140925083645', '012', '1');
INSERT INTO `reviewresult` VALUES (2, '20140804170649', '010', '1');
INSERT INTO `reviewresult` VALUES (3, '20140804170649', '010', '1');
INSERT INTO `reviewresult` VALUES (4, '20140804170649', '010', '2');
INSERT INTO `reviewresult` VALUES (5, '4', '010', '1');
INSERT INTO `reviewresult` VALUES (6, '20140802164843', '010', '2');
INSERT INTO `reviewresult` VALUES (7, '20140805150626', '010', '2');
INSERT INTO `reviewresult` VALUES (8, '20140804170649', '010', '1');
INSERT INTO `reviewresult` VALUES (9, '20140804170649', '', '2');
INSERT INTO `reviewresult` VALUES (10, '20140925083645', '1115403025', '1');
INSERT INTO `reviewresult` VALUES (11, '20140805150626', '1115403025', '1');
INSERT INTO `reviewresult` VALUES (15, '20140925083645', '1115403025', '2');
INSERT INTO `reviewresult` VALUES (14, '20140809170454', '010', '1');
INSERT INTO `reviewresult` VALUES (16, '20140925083645', '1115403025', '1');
INSERT INTO `reviewresult` VALUES (17, '20140807164166', '010', '1');
INSERT INTO `reviewresult` VALUES (21, '20140925083645', '0101', '1');
INSERT INTO `reviewresult` VALUES (19, '20140925083645', '0105', '1');
INSERT INTO `reviewresult` VALUES (20, '20140925093020', '010', '1');
INSERT INTO `reviewresult` VALUES (24, '20140925083645', '010', '1');

-- --------------------------------------------------------

-- 
-- 表的结构 `room`
-- 

DROP TABLE IF EXISTS `room`;
CREATE TABLE `room` (
  `id` int(11) NOT NULL auto_increment COMMENT 'id自增',
  `number` varchar(255) NOT NULL COMMENT '编号',
  `name` varchar(255) default NULL COMMENT '标题|信息标题',
  `parent_id` varchar(255) NOT NULL COMMENT '所属父类',
  `usable` varchar(255) NOT NULL default '1' COMMENT '是否可用| 1 可用 2 不可用',
  `create_time` varchar(255) NOT NULL COMMENT '创建时间|格式：2013-04-10 08:09:09',
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 COMMENT='教室|教室模块' AUTO_INCREMENT=4 ;

-- 
-- 导出表中的数据 `room`
-- 

INSERT INTO `room` VALUES (1, 'E1A-302', '计算机基础实验室', '064', '1', '2014-09-16 18:01:35');
INSERT INTO `room` VALUES (3, 'E1A-301', '计算机软件实验室', '064', '1', '2014-09-22 10:21:16');

-- --------------------------------------------------------

-- 
-- 表的结构 `selectfirst`
-- 

DROP TABLE IF EXISTS `selectfirst`;
CREATE TABLE `selectfirst` (
  `sel_ID` int(11) NOT NULL auto_increment COMMENT '标号',
  `top_ID` varchar(255) NOT NULL COMMENT '课题ID',
  `stu_ID` varchar(12) NOT NULL COMMENT '选择人学号',
  `sel_knowing` text NOT NULL COMMENT '对课题的认识',
  `sel_status` varchar(10) NOT NULL COMMENT '状态--默认0未被选择，1确定为任务人 2 确定为非任务人',
  `tea_ID` varchar(12) default NULL COMMENT '教师ID',
  `dept_ID` varchar(10) default NULL,
  PRIMARY KEY  (`sel_ID`),
  KEY `FKD355B34BE9E36BC` (`top_ID`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 COMMENT='课题的初步选择记录表' AUTO_INCREMENT=22 ;

-- 
-- 导出表中的数据 `selectfirst`
-- 

INSERT INTO `selectfirst` VALUES (20, '201409250836451', '1106401002', '<p>使用android 技术开发 android &nbsp;ui&nbsp;</p>', '1', '010', NULL);
INSERT INTO `selectfirst` VALUES (21, '20140807164146', '1106401013', '<p>dfwefw发士大夫方式 &nbsp;</p>', '0', '010', NULL);

-- --------------------------------------------------------

-- 
-- 表的结构 `settime`
-- 

DROP TABLE IF EXISTS `settime`;
CREATE TABLE `settime` (
  `id` int(11) NOT NULL auto_increment COMMENT '系统自动生成',
  `name` varchar(255) default NULL COMMENT '标题',
  `dept_ID` varchar(255) default NULL COMMENT '系部编号',
  `gra_number` varchar(255) default NULL COMMENT '届数|对应哪一届',
  `start_time` varchar(255) default NULL COMMENT '开始时间',
  `end_time` varchar(255) default NULL COMMENT '结束时间',
  `mark` varchar(255) default NULL COMMENT '1 提交课题 2审核 3选择 4 查看课题',
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 COMMENT='时间设置表' AUTO_INCREMENT=11 ;

-- 
-- 导出表中的数据 `settime`
-- 

INSERT INTO `settime` VALUES (7, '2015届课题提交时间安排', '064', '8', '2014-07-17 11:31:04', '2014-10-17 11:31:06', '1');
INSERT INTO `settime` VALUES (2, '2014届毕业课题提交时间', '064', '5', '2014-05-15 00:00:00', '2014-09-30 23:59:59', '2');
INSERT INTO `settime` VALUES (3, '2013届毕业生课题提交时间', '064', '1', '2014-07-17 11:05:52', '2014-07-25 11:05:55', '1');
INSERT INTO `settime` VALUES (8, '2015届课题查看时间安排', '064', '8', '2014-07-17 12:56:28', '2014-10-17 12:56:31', '4');
INSERT INTO `settime` VALUES (9, '2014届审核课题时间', '064', '5', '2014-07-17 12:58:30', '2014-07-17 12:58:32', '2');
INSERT INTO `settime` VALUES (10, '2015届课题选择时间', '064', '8', '2014-09-17 9:00:00', '2014-10-17 11:30:00', '3');

-- --------------------------------------------------------

-- 
-- 表的结构 `student`
-- 

DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
  `stu_ID` varchar(12) NOT NULL COMMENT '学号',
  `gdi_ID` int(11) default NULL COMMENT '答辩ID',
  `opi_ID` int(11) default NULL COMMENT '开题编号',
  `tR_ID` int(11) default NULL COMMENT '开题报告书编号',
  `gt_ID` int(11) default NULL COMMENT '表二编号',
  `gtr_ID` int(11) default NULL COMMENT '表三编号',
  `go_ID` int(11) default NULL COMMENT '表一编号',
  `tD_ID` int(11) default NULL COMMENT '任务书编号',
  `stt_ID` int(11) default NULL COMMENT '关系编号',
  `cla_ID` int(11) default NULL COMMENT '班级编号',
  `gA_ID` int(11) default NULL COMMENT '综合成绩编号',
  `stu_Name` varchar(16) default NULL COMMENT '姓名',
  `stu_Sex` varchar(4) default NULL COMMENT '性别| 1 男 2 女',
  `stu_Tel` varchar(15) default NULL COMMENT '电话',
  `stu_Email` varchar(30) NOT NULL COMMENT '邮箱',
  PRIMARY KEY  (`stu_ID`),
  KEY `FK8FFE823BA0799862` (`cla_ID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='学生表';

-- 
-- 导出表中的数据 `student`
-- 

INSERT INTO `student` VALUES ('1106401042', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 201104, NULL, '黄泽西', '1', '15111592794', 'huangzec@foxmail.com');
INSERT INTO `student` VALUES ('1115403025', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1115403, NULL, '冯傲雪', '2', '', '1059033846@qq.com');
INSERT INTO `student` VALUES ('1106401001', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 201103, NULL, '张海兰', '2', '', '1505388138@qq.com');
INSERT INTO `student` VALUES ('1106401002', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 2, NULL, '李靖文', '1', '', 'xjiujiu@foxmail.com');
INSERT INTO `student` VALUES ('1106401023', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 2, NULL, '贺盼华', '2', '', 'huangzec@foxmail.com');
INSERT INTO `student` VALUES ('1106401013', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 2011, NULL, '姜其灿', '2', '', '112356@qq.com');

-- --------------------------------------------------------

-- 
-- 表的结构 `stu_tea_topic`
-- 

DROP TABLE IF EXISTS `stu_tea_topic`;
CREATE TABLE `stu_tea_topic` (
  `stt_ID` int(11) NOT NULL auto_increment COMMENT '关系编号',
  `stu_ID` varchar(12) default NULL COMMENT '学号',
  `tea_ID` varchar(12) default NULL COMMENT '教师工号',
  `Tea_tea_ID` varchar(12) default NULL COMMENT '教师工号',
  `stt_Status` tinyint(1) default NULL COMMENT '开题答辩通过与否',
  PRIMARY KEY  (`stt_ID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- 
-- 导出表中的数据 `stu_tea_topic`
-- 


-- --------------------------------------------------------

-- 
-- 表的结构 `taskdoc`
-- 

DROP TABLE IF EXISTS `taskdoc`;
CREATE TABLE `taskdoc` (
  `tD_ID` int(11) NOT NULL auto_increment COMMENT '任务书编号',
  `title` varchar(255) default NULL COMMENT '题目|毕业设计题目',
  `stu_ID` varchar(12) default NULL COMMENT '学号',
  `tea_ID` varchar(12) default NULL COMMENT '教师工号',
  `source` varchar(255) default NULL COMMENT '题目来源|1 科学技术 2 生产实践 3 社会经济 4 自拟 5 其他',
  `tD_conRequest` text COMMENT '毕业设计内容要求',
  `tD_devTools` text COMMENT '开发工具',
  `tD_refMaterial` text COMMENT '参考资料',
  `tD_workPlane` text COMMENT '工作计划',
  `receipt_time` varchar(255) default NULL COMMENT '接收任务日期',
  `finish_time` varchar(255) default NULL COMMENT '要求完成任务日期',
  `create_time` varchar(255) NOT NULL COMMENT '创建时间|格式：2013-04-10 08:09:09',
  PRIMARY KEY  (`tD_ID`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 COMMENT='毕业设计任务书' AUTO_INCREMENT=17 ;

-- 
-- 导出表中的数据 `taskdoc`
-- 

INSERT INTO `taskdoc` VALUES (1, '基于android的掌上点餐系统 后台', '1106401002', '010', '4', '&lt;p&gt;												&lt;&#x2F;p&gt;&lt;p&gt;&lt;br&#x2F;&gt;&lt;&#x2F;p&gt;&lt;p&gt;&lt;br&#x2F;&gt;&lt;&#x2F;p&gt;&lt;p&gt;&lt;br&#x2F;&gt;&lt;&#x2F;p&gt;&lt;p&gt;玩儿电费范德萨&lt;br&#x2F;&gt;&lt;&#x2F;p&gt;&lt;p style=&quot;text-align: center;&quot;&gt;发的所发生的&lt;&#x2F;p&gt;&lt;p&gt;&lt;br&#x2F;&gt;&lt;&#x2F;p&gt;&lt;p&gt;&lt;br&#x2F;&gt;&lt;&#x2F;p&gt;&lt;p&gt;&lt;br&#x2F;&gt;&lt;&#x2F;p&gt;&lt;p&gt;&lt;br&#x2F;&gt;&lt;&#x2F;p&gt;&lt;p&gt;&lt;br&#x2F;&gt;&lt;&#x2F;p&gt;&lt;p&gt;&lt;br&#x2F;&gt;&lt;&#x2F;p&gt;&lt;p&gt;&lt;br&#x2F;&gt;&lt;&#x2F;p&gt;&lt;p&gt;&lt;br&#x2F;&gt;&lt;&#x2F;p&gt;&lt;p&gt;&lt;br&#x2F;&gt;&lt;&#x2F;p&gt;&lt;p&gt;\r\n											&lt;&#x2F;p&gt;', NULL, '&lt;p&gt;												&lt;&#x2F;p&gt;&lt;p&gt;&lt;br&#x2F;&gt;&lt;&#x2F;p&gt;&lt;p&gt;&lt;br&#x2F;&gt;&lt;&#x2F;p&gt;&lt;p&gt;放大师傅&lt;br&#x2F;&gt;&lt;&#x2F;p&gt;&lt;p&gt;&lt;br&#x2F;&gt;&lt;&#x2F;p&gt;&lt;p&gt;&lt;br&#x2F;&gt;&lt;&#x2F;p&gt;&lt;p&gt;&lt;br&#x2F;&gt;&lt;&#x2F;p&gt;&lt;p&gt;&lt;br&#x2F;&gt;&lt;&#x2F;p&gt;&lt;p&gt;&lt;br&#x2F;&gt;&lt;&#x2F;p&gt;&lt;p&gt;&lt;br&#x2F;&gt;&lt;&#x2F;p&gt;&lt;p&gt;&lt;br&#x2F;&gt;&lt;&#x2F;p&gt;&lt;p&gt;&lt;br&#x2F;&gt;&lt;&#x2F;p&gt;&lt;p&gt;\r\n											&lt;&#x2F;p&gt;', '&lt;p&gt;												&lt;&#x2F;p&gt;&lt;p&gt;&lt;br&#x2F;&gt;&lt;&#x2F;p&gt;&lt;p&gt;&lt;br&#x2F;&gt;&lt;&#x2F;p&gt;&lt;p&gt;的法案是&lt;br&#x2F;&gt;&lt;&#x2F;p&gt;&lt;p&gt;&amp;nbsp; &amp;nbsp;放大师傅&lt;&#x2F;p&gt;&lt;p&gt;&lt;br&#x2F;&gt;&lt;&#x2F;p&gt;&lt;p&gt;&lt;br&#x2F;&gt;&lt;&#x2F;p&gt;&lt;p&gt;&lt;br&#x2F;&gt;&lt;&#x2F;p&gt;&lt;p&gt;&lt;br&#x2F;&gt;&lt;&#x2F;p&gt;&lt;p&gt;&lt;br&#x2F;&gt;&lt;&#x2F;p&gt;&lt;p&gt;&lt;br&#x2F;&gt;&lt;&#x2F;p&gt;&lt;p&gt;&lt;br&#x2F;&gt;&lt;&#x2F;p&gt;&lt;p&gt;&lt;br&#x2F;&gt;&lt;&#x2F;p&gt;&lt;p&gt;	\r\n											&lt;&#x2F;p&gt;', '2014-9-26', '2014-08-30', '');
INSERT INTO `taskdoc` VALUES (4, '测试', '1106401001', '010', NULL, '', NULL, '', '', '2014-10-8', '', '');
INSERT INTO `taskdoc` VALUES (16, '测试', '1106401013', '010', NULL, '&lt;p&gt;												&lt;&#x2F;p&gt;&lt;p&gt;服务二恶烷f&lt;&#x2F;p&gt;&lt;p&gt;\r\n											&lt;&#x2F;p&gt;', NULL, '&lt;p&gt;												&lt;&#x2F;p&gt;&lt;p&gt;是的范德萨&lt;&#x2F;p&gt;&lt;p&gt;\r\n											&lt;&#x2F;p&gt;', '&lt;p&gt;												&lt;&#x2F;p&gt;&lt;p&gt;方式的绯闻绯闻&lt;&#x2F;p&gt;&lt;p&gt;	\r\n											&lt;&#x2F;p&gt;', '2014-10-10', '2014-10-23', '2014-10-10 22:06:46');
INSERT INTO `taskdoc` VALUES (14, NULL, NULL, '1106401001', NULL, '', NULL, '', '', NULL, NULL, '');
INSERT INTO `taskdoc` VALUES (15, NULL, NULL, '1106401001', NULL, '', NULL, '', '', NULL, NULL, '');
INSERT INTO `taskdoc` VALUES (9, 'Html标签w3school网上学习', '1106401042', '010', NULL, '&lt;p&gt;												&lt;&#x2F;p&gt;&lt;p&gt;发的发文二五二&lt;&#x2F;p&gt;&lt;p&gt;\r\n											&lt;&#x2F;p&gt;', NULL, '&lt;p&gt;												&lt;&#x2F;p&gt;&lt;p&gt;维特我让他 二维&lt;&#x2F;p&gt;&lt;p&gt;\r\n											&lt;&#x2F;p&gt;', '&lt;p&gt;												&lt;&#x2F;p&gt;&lt;p&gt;发大发&lt;&#x2F;p&gt;&lt;p&gt;	\r\n											&lt;&#x2F;p&gt;', '2014-8-28', '2014-08-30', '');

-- --------------------------------------------------------

-- 
-- 表的结构 `tbclass`
-- 

DROP TABLE IF EXISTS `tbclass`;
CREATE TABLE `tbclass` (
  `cla_ID` int(11) NOT NULL COMMENT '班级编号',
  `cla_Name` varchar(255) default NULL COMMENT '班级名称',
  `pro_ID` varchar(10) default NULL COMMENT '专业编号',
  PRIMARY KEY  (`cla_ID`),
  KEY `FKA5336FCAAA202A33` (`pro_ID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='班级表';

-- 
-- 导出表中的数据 `tbclass`
-- 

INSERT INTO `tbclass` VALUES (2011, '11计算机1班', '06007');
INSERT INTO `tbclass` VALUES (2, '11计算机3班', '06007');
INSERT INTO `tbclass` VALUES (201103, '11网络工程4班', '06008');
INSERT INTO `tbclass` VALUES (201104, '11网络工程5班', '06008');
INSERT INTO `tbclass` VALUES (1115403, '11人文教育3班', '15401');
INSERT INTO `tbclass` VALUES (201102, '11计算机2班', '06007');

-- --------------------------------------------------------

-- 
-- 表的结构 `tbgrade`
-- 

DROP TABLE IF EXISTS `tbgrade`;
CREATE TABLE `tbgrade` (
  `gra_ID` int(11) NOT NULL auto_increment COMMENT '年级编号',
  `dept_ID` varchar(10) default NULL COMMENT '系部ID',
  `gra_Number` varchar(8) NOT NULL COMMENT '届数',
  PRIMARY KEY  (`gra_ID`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 COMMENT='年级表' AUTO_INCREMENT=9 ;

-- 
-- 导出表中的数据 `tbgrade`
-- 

INSERT INTO `tbgrade` VALUES (1, '064', '2013届');
INSERT INTO `tbgrade` VALUES (7, '064', '2012');
INSERT INTO `tbgrade` VALUES (5, '064', '2014');
INSERT INTO `tbgrade` VALUES (8, '064', '2015届');

-- --------------------------------------------------------

-- 
-- 表的结构 `tboffice`
-- 

DROP TABLE IF EXISTS `tboffice`;
CREATE TABLE `tboffice` (
  `off_ID` varchar(12) NOT NULL COMMENT '管理员ID',
  `off_Name` varchar(16) default NULL COMMENT '姓名',
  `off_Sex` varchar(4) default NULL COMMENT '性别',
  `off_Tel` varchar(15) default NULL COMMENT '电话',
  `off_Email` varchar(30) NOT NULL COMMENT '邮箱',
  PRIMARY KEY  (`off_ID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='教务处管理员表';

-- 
-- 导出表中的数据 `tboffice`
-- 

INSERT INTO `tboffice` VALUES ('admin', 'admin', 'man', '123456', '123456');
INSERT INTO `tboffice` VALUES ('123456', '123456', '1', '123456', '123456');
INSERT INTO `tboffice` VALUES ('11', '11', NULL, NULL, '');
INSERT INTO `tboffice` VALUES ('22', '22', '22', NULL, '');
INSERT INTO `tboffice` VALUES ('1234', '1234', NULL, NULL, '');

-- --------------------------------------------------------

-- 
-- 表的结构 `tbtopic`
-- 

DROP TABLE IF EXISTS `tbtopic`;
CREATE TABLE `tbtopic` (
  `top_ID` varchar(255) NOT NULL COMMENT '课题ID',
  `top_commitID` varchar(12) default NULL COMMENT '提交人ID',
  `top_Name` varchar(100) NOT NULL COMMENT '课题名称',
  `top_Number` int(11) default NULL COMMENT '完成人数',
  `top_Status` varchar(255) default NULL COMMENT '0-未审核 | 1-通过 | 2-未通过',
  `top_Tec` text COMMENT '采用技术',
  `top_Flag` varchar(10) character set ucs2 default NULL COMMENT '标记提交人类型',
  `dept_ID` varchar(255) default NULL COMMENT '所属系部',
  `parent_ID` varchar(255) NOT NULL default '0' COMMENT '父课题ID',
  `completer_ID` varchar(12) default NULL COMMENT '任务人学号',
  PRIMARY KEY  (`top_ID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='课题表';

-- 
-- 导出表中的数据 `tbtopic`
-- 

INSERT INTO `tbtopic` VALUES ('20140807164146', '010', '测试', 1, '1', '放大', '2', '064', '0', NULL);
INSERT INTO `tbtopic` VALUES ('20140924211807', '1106401001', 'springmvc毕业设计', 1, '1', '<p>springmvc课程设计</p>', '1', '064', '0', NULL);
INSERT INTO `tbtopic` VALUES ('20140925083645', '010', '基于android的掌上点餐系统', 3, '1', '<p>基于android的掌上点餐系统 android &nbsp;spring &nbsp;jdbc</p>', '2', '064', '0', NULL);
INSERT INTO `tbtopic` VALUES ('201409250836451', '010', '基于android的掌上点餐系统 后台', 1, '1', '<p>基于android的掌上点餐系统 android &nbsp;spring &nbsp;jdbc</p>', '2', '064', '20140925083645', NULL);
INSERT INTO `tbtopic` VALUES ('201409250836452', '010', '基于android的掌上点餐系统 界面设计', 1, '1', '<p>基于android的掌上点餐系统 android &nbsp;spring &nbsp;jdbc</p>', '2', '064', '20140925083645', NULL);
INSERT INTO `tbtopic` VALUES ('201409250836453', '010', '基于android的掌上点餐系统 移动终端', 1, '1', '<p>基于android的掌上点餐系统 android &nbsp;spring &nbsp;jdbc</p>', '2', '064', '20140925083645', NULL);
INSERT INTO `tbtopic` VALUES ('20140925093020', '010', '基于javaee的论文管理', 4, '0', '<p>基于javaee的论文管理&nbsp;基于javaee的论文管理</p>', '2', '064', '0', NULL);
INSERT INTO `tbtopic` VALUES ('201409250930201', '010', '基于javaee的论文管理', 1, '0', '<p>基于javaee的论文管理&nbsp;基于javaee的论文管理</p>', '2', '064', '20140925093020', NULL);
INSERT INTO `tbtopic` VALUES ('201409250930202', '010', '基于javaee的论文管理 2', 1, '0', '<p>基于javaee的论文管理&nbsp;基于javaee的论文管理</p>', '2', '064', '20140925093020', NULL);
INSERT INTO `tbtopic` VALUES ('201409250930203', '010', '基于javaee的论文管理3', 1, '0', '<p>基于javaee的论文管理&nbsp;基于javaee的论文管理</p>', '2', '064', '20140925093020', NULL);
INSERT INTO `tbtopic` VALUES ('201409250930204', '010', '基于javaee的论文管理 4', 1, '0', '<p>基于javaee的论文管理&nbsp;基于javaee的论文管理</p>', '2', '064', '20140925093020', NULL);

-- --------------------------------------------------------

-- 
-- 表的结构 `teacher`
-- 

DROP TABLE IF EXISTS `teacher`;
CREATE TABLE `teacher` (
  `tea_ID` varchar(12) NOT NULL COMMENT '教师工号',
  `opi_ID` int(11) default NULL COMMENT '开题编号',
  `stt_ID` int(11) default NULL COMMENT '关系编号',
  `stu_stt_ID` int(11) default NULL COMMENT '关系编号',
  `dept_ID` varchar(10) default NULL COMMENT '系部ID',
  `gdi_ID` int(11) default NULL COMMENT '答辩ID',
  `tea_Name` varchar(16) default NULL COMMENT '教师姓名',
  `tea_Sex` varchar(4) default NULL COMMENT '性别',
  `tea_Pos` varchar(20) default NULL COMMENT '职称|0 助教 1 讲师 2 副教授 3 教授',
  `tea_Judge` tinyint(1) default NULL COMMENT '课题审核',
  `tea_Group` tinyint(1) default NULL COMMENT '答辩组长',
  `tea_Tel` varchar(15) default NULL COMMENT '联系电话',
  `tea_Email` varchar(30) NOT NULL COMMENT '电子邮箱',
  PRIMARY KEY  (`tea_ID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='教师信息表';

-- 
-- 导出表中的数据 `teacher`
-- 

INSERT INTO `teacher` VALUES ('06033', NULL, NULL, NULL, '', NULL, '黄隆华', '1', '1', NULL, NULL, '224544444', '1505388138@qq.com');
INSERT INTO `teacher` VALUES ('06034', NULL, NULL, NULL, '', NULL, '411', '1', '1', NULL, NULL, '15111592794', '1505388138@qq.com');
INSERT INTO `teacher` VALUES ('001', NULL, NULL, NULL, '', NULL, '黄泽西', '1', '1', NULL, NULL, '15111592794', 'huangzec@foxmail.com');
INSERT INTO `teacher` VALUES ('002', NULL, NULL, NULL, NULL, NULL, '黄泽西', '1', '0', NULL, NULL, '15874592649', 'xjiujiu@foxmail.com');
INSERT INTO `teacher` VALUES ('079', NULL, NULL, NULL, '064', NULL, '唐波', '1', '1', 0, NULL, '', '1059033846@qq.com');
INSERT INTO `teacher` VALUES ('010', NULL, NULL, NULL, '064', NULL, '黄泽西', '1', '2', 1, NULL, '15874592649', '1505388138@qq.com');
INSERT INTO `teacher` VALUES ('1115403025', NULL, NULL, NULL, '064', NULL, '冯傲雪', '2', '3', 1, NULL, '15874592649', '1505388138@qq.com');
INSERT INTO `teacher` VALUES ('1115403026', NULL, NULL, NULL, '064', NULL, '汪婷婷', '2', '2', 0, NULL, '1555', '1505388138@qq.com');

-- --------------------------------------------------------

-- 
-- 表的结构 `topicapply`
-- 

DROP TABLE IF EXISTS `topicapply`;
CREATE TABLE `topicapply` (
  `id` int(11) NOT NULL auto_increment COMMENT 'id自增',
  `user_id` varchar(255) NOT NULL COMMENT '学生学号',
  `topic_id` varchar(255) NOT NULL COMMENT '课题ID',
  `parent_id` varchar(255) NOT NULL COMMENT '所属系部',
  `open_time` varchar(255) NOT NULL COMMENT '申请开通答辩时间',
  `knowing` text COMMENT '对课题的理解',
  `teacher_idea` text COMMENT '指导老师意见',
  `paper_idea` text COMMENT '论文委员会意见',
  `department_idea` text COMMENT '系部意见',
  `create_time` varchar(255) NOT NULL COMMENT '创建时间|格式：2013-04-10 ',
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 COMMENT='开题答辩申请书' AUTO_INCREMENT=58 ;

-- 
-- 导出表中的数据 `topicapply`
-- 

INSERT INTO `topicapply` VALUES (1, '1106401023', '20140807164166', '064', '2014-09-14', '&lt;p&gt;法萨芬&lt;&#x2F;p&gt;', '&lt;p&gt;法萨芬&lt;&#x2F;p&gt;', '&lt;p&gt;发士大夫&lt;&#x2F;p&gt;', '&lt;p&gt;发的萨芬&lt;&#x2F;p&gt;', '2014-09-14');
INSERT INTO `topicapply` VALUES (2, '1106401023', '20140807164166', '064', '2014-09-14', '&lt;p&gt;我是对课题的理解&amp;nbsp;&amp;nbsp;&amp;nbsp;&amp;nbsp;&lt;&#x2F;p&gt;', '&lt;p&gt;我是指导老师意见&lt;&#x2F;p&gt;', '&lt;p&gt;我是论文管理委员会意见&lt;&#x2F;p&gt;', '&lt;p&gt;我是系部意见&lt;&#x2F;p&gt;', '2014-09-14');
INSERT INTO `topicapply` VALUES (3, '1106401023', '20140807164166', '064', '2014-09-14', '&lt;p&gt;发大发&lt;&#x2F;p&gt;', '&lt;p&gt;发大发&lt;&#x2F;p&gt;', '&lt;p&gt;f阿舒服的的萨菲&lt;&#x2F;p&gt;', '&lt;p&gt;发大水发的萨菲&lt;&#x2F;p&gt;', '2014-09-14');
INSERT INTO `topicapply` VALUES (4, '1106401023', '20140807164166', '064', '2014-09-15', '&lt;p&gt;公告&lt;&#x2F;p&gt;', '&lt;p&gt;改单费&lt;&#x2F;p&gt;', '&lt;p&gt;个电饭锅&lt;&#x2F;p&gt;', '&lt;p&gt;个梵蒂冈&lt;&#x2F;p&gt;', '2014-09-14');
INSERT INTO `topicapply` VALUES (5, '1106401023', '20140807164166', '064', '2014-09-15', '&lt;p&gt;发大发&lt;&#x2F;p&gt;', '&lt;p&gt;访问&lt;&#x2F;p&gt;', '&lt;p&gt;范围广f&lt;&#x2F;p&gt;', '&lt;p&gt;无法方式&lt;&#x2F;p&gt;', '2014-09-14');
INSERT INTO `topicapply` VALUES (6, '1106401001', '20140807164146', '064', '2014-10-10', '&lt;p&gt;课题的理解&lt;&#x2F;p&gt;', '&lt;p&gt;指导老师对我的意见&lt;&#x2F;p&gt;', '&lt;p&gt;付师傅师傅&lt;br&#x2F;&gt;&lt;&#x2F;p&gt;', '&lt;p&gt;发生服务费&lt;br&#x2F;&gt;&lt;&#x2F;p&gt;', '2014-10-02');
INSERT INTO `topicapply` VALUES (7, '1106401001', '20140807164146', '064', '2014-10-17', '&lt;p&gt;的飞洒发大赛的&lt;&#x2F;p&gt;', '&lt;p&gt;放大师傅s&lt;&#x2F;p&gt;', '&lt;p&gt;发送到发送到&lt;&#x2F;p&gt;', '&lt;p&gt;f的萨芬撒地方&lt;&#x2F;p&gt;', '2014-10-02');
INSERT INTO `topicapply` VALUES (8, '1106401001', '20140807164146', '064', '', '', '', '', '', '2014-10-02');
INSERT INTO `topicapply` VALUES (9, '1106401001', '20140807164146', '064', '2014-10-10', '&lt;p&gt;fdsf&amp;nbsp;&lt;&#x2F;p&gt;', '&lt;p&gt;fdsfsd&lt;&#x2F;p&gt;', '&lt;p&gt;fdsfsd f&lt;&#x2F;p&gt;', '&lt;p&gt;dsfdsf&lt;&#x2F;p&gt;', '2014-10-02');
INSERT INTO `topicapply` VALUES (10, '1106401001', '20140807164146', '064', '2014-09-30', '', '', '', '', '2014-10-02');
INSERT INTO `topicapply` VALUES (11, '1106401001', '20140807164146', '064', '', '', '', '', '', '2014-10-09');
INSERT INTO `topicapply` VALUES (12, '1106401001', '20140807164146', '064', '', '', '', '', '', '2014-10-10');
INSERT INTO `topicapply` VALUES (13, '1106401001', '20140807164146', '064', '', '', '', '', '', '2014-10-10');
INSERT INTO `topicapply` VALUES (14, '1106401001', '20140807164146', '064', '', '', '', '', '', '2014-10-10');
INSERT INTO `topicapply` VALUES (15, '1106401023', '', '064', '', '', '', '', '', '2014-10-10');
INSERT INTO `topicapply` VALUES (16, '1106401001', '20140807164146', '064', '', '', '', '', '', '2014-10-10');
INSERT INTO `topicapply` VALUES (17, '1106401001', '20140807164146', '064', '', '', '', '', '', '2014-10-10');
INSERT INTO `topicapply` VALUES (18, '1106401001', '20140807164146', '064', '', '', '', '', '', '2014-10-10');
INSERT INTO `topicapply` VALUES (19, '1106401001', '20140807164146', '064', '', '', '', '', '', '2014-10-10');
INSERT INTO `topicapply` VALUES (20, '1106401001', '20140807164146', '064', '', '', '', '', '', '2014-10-10');
INSERT INTO `topicapply` VALUES (21, '1106401001', '20140807164146', '064', '', '', '', '', '', '2014-10-10');
INSERT INTO `topicapply` VALUES (22, '1106401001', '20140807164146', '064', '', '', '', '', '', '2014-10-10');
INSERT INTO `topicapply` VALUES (23, '1106401001', '20140807164146', '064', '', '', '', '', '', '2014-10-10');
INSERT INTO `topicapply` VALUES (24, '1106401001', '20140807164146', '064', '', '', '', '', '', '2014-10-10');
INSERT INTO `topicapply` VALUES (25, '1106401001', '20140807164146', '064', '', '', '', '', '', '2014-10-10');
INSERT INTO `topicapply` VALUES (26, '1106401001', '20140807164146', '064', '', '', '', '', '', '2014-10-10');
INSERT INTO `topicapply` VALUES (27, '1106401001', '20140807164146', '064', '', '', '', '', '', '2014-10-10');
INSERT INTO `topicapply` VALUES (28, '1106401001', '20140807164146', '064', '', '', '', '', '', '2014-10-10');
INSERT INTO `topicapply` VALUES (29, '1106401001', '20140807164146', '064', '', '', '', '', '', '2014-10-10');
INSERT INTO `topicapply` VALUES (30, '1106401001', '20140807164146', '064', '', '', '', '', '', '2014-10-10');
INSERT INTO `topicapply` VALUES (31, '1106401001', '20140807164146', '064', '', '', '', '', '', '2014-10-10');
INSERT INTO `topicapply` VALUES (32, '1106401002', '201409250836451', '064', '', '', '', '', '', '2014-10-10');
INSERT INTO `topicapply` VALUES (33, '1106401023', '', '064', '', '', '', '', '', '2014-10-10');
INSERT INTO `topicapply` VALUES (34, '1106401023', '', '064', '', '', '', '', '', '2014-10-10');
INSERT INTO `topicapply` VALUES (35, '1106401001', '20140807164146', '064', '', '', '', '', '', '2014-10-10');
INSERT INTO `topicapply` VALUES (36, '1106401001', '20140807164146', '064', '', '', '', '', '', '2014-10-10');
INSERT INTO `topicapply` VALUES (37, '1106401001', '20140807164146', '064', '', '', '', '', '', '2014-10-10');
INSERT INTO `topicapply` VALUES (38, '1106401023', '', '064', '', '', '', '', '', '2014-10-10');
INSERT INTO `topicapply` VALUES (39, '1106401002', '201409250836451', '064', '', '', '', '', '', '2014-10-10');
INSERT INTO `topicapply` VALUES (40, '1106401023', '', '064', '', '', '', '', '', '2014-10-10');
INSERT INTO `topicapply` VALUES (41, '1106401023', '', '064', '', '', '', '', '', '2014-10-10');
INSERT INTO `topicapply` VALUES (42, '1106401023', '', '064', '', '', '', '', '', '2014-10-10');
INSERT INTO `topicapply` VALUES (43, '1106401023', '', '064', '', '', '', '', '', '2014-10-10');
INSERT INTO `topicapply` VALUES (44, '1106401023', '', '064', '', '', '', '', '', '2014-10-10');
INSERT INTO `topicapply` VALUES (45, '1106401023', '', '064', '', '', '', '', '', '2014-10-10');
INSERT INTO `topicapply` VALUES (46, '1106401023', '', '064', '', '', '', '', '', '2014-10-10');
INSERT INTO `topicapply` VALUES (47, '1106401023', '', '064', '', '', '', '', '', '2014-10-10');
INSERT INTO `topicapply` VALUES (48, '1106401023', '', '064', '', '', '', '', '', '2014-10-10');
INSERT INTO `topicapply` VALUES (49, '1106401002', '201409250836451', '064', '', '', '', '', '', '2014-10-10');
INSERT INTO `topicapply` VALUES (50, '1106401023', '', '064', '', '', '', '', '', '2014-10-10');
INSERT INTO `topicapply` VALUES (51, '1106401023', '', '064', '', '', '', '', '', '2014-10-10');
INSERT INTO `topicapply` VALUES (52, '1106401001', '20140807164146', '064', '', '', '', '', '', '2014-10-10');
INSERT INTO `topicapply` VALUES (53, '1106401023', '', '064', '', '', '', '', '', '2014-10-10');
INSERT INTO `topicapply` VALUES (54, '1106401023', '', '064', '', '', '', '', '', '2014-10-10');
INSERT INTO `topicapply` VALUES (55, '1106401023', '', '064', '', '', '', '', '', '2014-10-10');
INSERT INTO `topicapply` VALUES (56, '1106401023', '', '064', '', '', '', '', '', '2014-10-10');
INSERT INTO `topicapply` VALUES (57, '1106401013', '', '064', '', '', '', '', '', '2014-10-10');

-- --------------------------------------------------------

-- 
-- 表的结构 `topicfinish`
-- 

DROP TABLE IF EXISTS `topicfinish`;
CREATE TABLE `topicfinish` (
  `id` int(11) NOT NULL auto_increment COMMENT 'id自增',
  `stu_id` varchar(12) default NULL COMMENT '学号',
  `department_id` varchar(255) NOT NULL COMMENT '所属系部',
  `title` varchar(255) default NULL COMMENT '标题|信息标题',
  `content` text COMMENT '内容|详细内容',
  `create_time` varchar(255) NOT NULL COMMENT '创建时间|格式：2013-04-10 08:09:09',
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 COMMENT='毕业答辩相关材料表' AUTO_INCREMENT=5 ;

-- 
-- 导出表中的数据 `topicfinish`
-- 

INSERT INTO `topicfinish` VALUES (1, '1106401023', '064', '毕业相关材料', '&lt;p style=&quot;line-height: 16px;&quot;&gt;&lt;img src=&quot;http:&#x2F;&#x2F;localhost:8080&#x2F;graduation&#x2F;vendor&#x2F;ueditor&#x2F;dialogs&#x2F;attachment&#x2F;fileTypeImages&#x2F;icon_doc.gif&quot;&#x2F;&gt;&lt;a href=&quot;http:&#x2F;&#x2F;localhost:8080&#x2F;graduation&#x2F;vendor&#x2F;ueditor&#x2F;jsp&#x2F;uploadfiles&#x2F;20140914&#x2F;58221410696792636.doc&quot;&gt;代码规范.doc&lt;&#x2F;a&gt;&lt;&#x2F;p&gt;&lt;p style=&quot;line-height: 16px;&quot;&gt;&lt;img src=&quot;http:&#x2F;&#x2F;localhost:8080&#x2F;graduation&#x2F;vendor&#x2F;ueditor&#x2F;dialogs&#x2F;attachment&#x2F;fileTypeImages&#x2F;icon_doc.gif&quot;&#x2F;&gt;&lt;a href=&quot;http:&#x2F;&#x2F;localhost:8080&#x2F;graduation&#x2F;vendor&#x2F;ueditor&#x2F;jsp&#x2F;uploadfiles&#x2F;20140914&#x2F;41401410696792676.docx&quot;&gt;红橘子信息科技工作室CMS软件开发需求1.0.docx&lt;&#x2F;a&gt;&lt;&#x2F;p&gt;&lt;p style=&quot;line-height: 16px;&quot;&gt;&lt;img src=&quot;http:&#x2F;&#x2F;localhost:8080&#x2F;graduation&#x2F;vendor&#x2F;ueditor&#x2F;dialogs&#x2F;attachment&#x2F;fileTypeImages&#x2F;icon_doc.gif&quot;&#x2F;&gt;&lt;a href=&quot;http:&#x2F;&#x2F;localhost:8080&#x2F;graduation&#x2F;vendor&#x2F;ueditor&#x2F;jsp&#x2F;uploadfiles&#x2F;20140914&#x2F;14051410696792725.doc&quot;&gt;五溪流域民间艺术美术资源库研究与实现项目报告.doc&lt;&#x2F;a&gt;&lt;&#x2F;p&gt;&lt;p&gt;&lt;br&#x2F;&gt;&lt;&#x2F;p&gt;', '2014-09-14 20:13:19');
INSERT INTO `topicfinish` VALUES (2, '1106401002', '064', '毕业答辩相关材料', '&lt;p style=&quot;line-height: 16px;&quot;&gt;&lt;img src=&quot;http:&#x2F;&#x2F;localhost:8080&#x2F;graduation&#x2F;vendor&#x2F;ueditor&#x2F;dialogs&#x2F;attachment&#x2F;fileTypeImages&#x2F;icon_doc.gif&quot;&#x2F;&gt;&lt;a href=&quot;http:&#x2F;&#x2F;localhost:8080&#x2F;graduation&#x2F;vendor&#x2F;ueditor&#x2F;jsp&#x2F;&#x2F;20140915&#x2F;8981410774548491.doc&quot;&gt;2015届毕业设计开题答辩申请表.doc&lt;&#x2F;a&gt;&lt;&#x2F;p&gt;&lt;p style=&quot;line-height: 16px;&quot;&gt;&lt;img src=&quot;http:&#x2F;&#x2F;localhost:8080&#x2F;graduation&#x2F;vendor&#x2F;ueditor&#x2F;dialogs&#x2F;attachment&#x2F;fileTypeImages&#x2F;icon_doc.gif&quot;&#x2F;&gt;&lt;a href=&quot;http:&#x2F;&#x2F;localhost:8080&#x2F;graduation&#x2F;vendor&#x2F;ueditor&#x2F;jsp&#x2F;&#x2F;20140915&#x2F;98321410774548561.doc&quot;&gt;怀化学院本科毕业设计开题报告书.doc&lt;&#x2F;a&gt;&lt;&#x2F;p&gt;&lt;p style=&quot;line-height: 16px;&quot;&gt;&lt;img src=&quot;http:&#x2F;&#x2F;localhost:8080&#x2F;graduation&#x2F;vendor&#x2F;ueditor&#x2F;dialogs&#x2F;attachment&#x2F;fileTypeImages&#x2F;icon_doc.gif&quot;&#x2F;&gt;&lt;a href=&quot;http:&#x2F;&#x2F;localhost:8080&#x2F;graduation&#x2F;vendor&#x2F;ueditor&#x2F;jsp&#x2F;&#x2F;20140915&#x2F;51551410774548594.doc&quot;&gt;怀化学院本科毕业设计任务书.doc&lt;&#x2F;a&gt;&lt;&#x2F;p&gt;&lt;p&gt;&lt;br&#x2F;&gt;&lt;&#x2F;p&gt;', '2014-09-15 17:49:13');
INSERT INTO `topicfinish` VALUES (3, '1106401013', '064', '毕业答辩申请2015', '&lt;p style=&quot;line-height: 16px;&quot;&gt;&lt;img src=&quot;http:&#x2F;&#x2F;localhost:8080&#x2F;graduation&#x2F;vendor&#x2F;ueditor&#x2F;dialogs&#x2F;attachment&#x2F;fileTypeImages&#x2F;icon_doc.gif&quot;&#x2F;&gt;&lt;a href=&quot;http:&#x2F;&#x2F;localhost:8080&#x2F;graduation&#x2F;vendor&#x2F;ueditor&#x2F;jsp&#x2F;20141010&#x2F;78031412948736993.doc&quot;&gt;代码规范.doc&lt;&#x2F;a&gt;&lt;&#x2F;p&gt;&lt;p style=&quot;line-height: 16px;&quot;&gt;&lt;img src=&quot;http:&#x2F;&#x2F;localhost:8080&#x2F;graduation&#x2F;vendor&#x2F;ueditor&#x2F;dialogs&#x2F;attachment&#x2F;fileTypeImages&#x2F;icon_doc.gif&quot;&#x2F;&gt;&lt;a href=&quot;http:&#x2F;&#x2F;localhost:8080&#x2F;graduation&#x2F;vendor&#x2F;ueditor&#x2F;jsp&#x2F;20141010&#x2F;73291412948737070.doc&quot;&gt;五溪流域民间艺术美术资源库研究与实现项目报告.doc&lt;&#x2F;a&gt;&lt;&#x2F;p&gt;&lt;p style=&quot;line-height: 16px;&quot;&gt;&lt;img src=&quot;http:&#x2F;&#x2F;localhost:8080&#x2F;graduation&#x2F;vendor&#x2F;ueditor&#x2F;dialogs&#x2F;attachment&#x2F;fileTypeImages&#x2F;icon_doc.gif&quot;&#x2F;&gt;&lt;a href=&quot;http:&#x2F;&#x2F;localhost:8080&#x2F;graduation&#x2F;vendor&#x2F;ueditor&#x2F;jsp&#x2F;20141010&#x2F;98691412948737468.doc&quot;&gt;系统类图描述.doc&lt;&#x2F;a&gt;&lt;&#x2F;p&gt;&lt;p&gt;&lt;br&#x2F;&gt;&lt;&#x2F;p&gt;', '2014-10-10 21:45:44');
INSERT INTO `topicfinish` VALUES (4, '1106401002', '064', '毕业答辩相关材料 测试', '&lt;p&gt;fdasfwe东风微风&lt;&#x2F;p&gt;', '2014-10-11 16:05:38');

-- --------------------------------------------------------

-- 
-- 表的结构 `topicreport`
-- 

DROP TABLE IF EXISTS `topicreport`;
CREATE TABLE `topicreport` (
  `id` int(11) NOT NULL auto_increment COMMENT 'ID|只能是数字',
  `stu_id` varchar(255) default NULL COMMENT '学号',
  `department_id` varchar(255) NOT NULL COMMENT '所属系部',
  `meaning` text COMMENT '意义、研究动向和见解',
  `content` text COMMENT '课题内容',
  `research` text COMMENT '设计方案、研究方法',
  `deadline` text COMMENT '完成期限和预期进度',
  `referencesl` text COMMENT '参考资料',
  `teacher_view` text COMMENT '指导老师意见',
  `meeting` varchar(255) default NULL COMMENT '开题报告会纪要',
  `judge_view` text COMMENT '答辩组意见',
  `department_view` text COMMENT '系部意见',
  `create_time` varchar(255) NOT NULL COMMENT '创建时间|格式：2013-04-10 08:09:09',
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 COMMENT='开题报告书' AUTO_INCREMENT=10 ;

-- 
-- 导出表中的数据 `topicreport`
-- 

INSERT INTO `topicreport` VALUES (3, '1106401001', '064', '上到山顶', '大多数', '大四的', '大四的', '上到山顶', '大多数', '都上升到', '说的', '都上升到', '2014-10-10 10:46:30');
INSERT INTO `topicreport` VALUES (4, NULL, '064', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '20111');
INSERT INTO `topicreport` VALUES (5, '1106401042', '1106401042', NULL, NULL, NULL, NULL, NULL, NULL, '5', '发送到发送到', NULL, '1106401042');
INSERT INTO `topicreport` VALUES (6, '1106401042', '1106401042', '1106401042', '1106401042', '1106401042', '1106401042', NULL, NULL, NULL, NULL, NULL, '1106401042');
INSERT INTO `topicreport` VALUES (7, '1106401042', '1106401042', '1106401042', '1106401042', '1106401042', '1106401042', NULL, '1106401042', '1106401042', '1106401042', '1106401042', '1106401042');
INSERT INTO `topicreport` VALUES (8, '1106401042', '1106401042', '1106401042', '1106401042', '1106401042', '1106401042', '1106401042', '1106401042', '1106401042', '1106401042', '1106401042', '1106401042');
INSERT INTO `topicreport` VALUES (9, '1106401013', '064', '', '', '', '', '', '', NULL, NULL, NULL, '2014-10-10 20:22:10');

-- --------------------------------------------------------

-- 
-- 表的结构 `user`
-- 

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `userid` int(11) NOT NULL auto_increment COMMENT 'id编号',
  `username` varchar(255) NOT NULL COMMENT '用户账号',
  `password` varchar(255) default NULL COMMENT '登陆密码',
  `permissions` int(11) default NULL COMMENT '用户类型',
  PRIMARY KEY  (`userid`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 COMMENT='用户表' AUTO_INCREMENT=123471 ;

-- 
-- 导出表中的数据 `user`
-- 

INSERT INTO `user` VALUES (1, '123456', '123456', 1);
INSERT INTO `user` VALUES (2, 'admin', 'E020590F0E18CD6053D7AE0E0A507609', 4);
INSERT INTO `user` VALUES (3, '11', '6512BD43D9CAA6E02C990B0A82652DCA', 4);
INSERT INTO `user` VALUES (4, '22', 'B6D767D2F8ED5D21A44B0E5886680CB9', 4);
INSERT INTO `user` VALUES (5, '1234', '81DC9BDB52D04DC20036DBD8313ED055', 4);
INSERT INTO `user` VALUES (6, '1106401042', 'F9A6736C8EC591A6B1632A8D31FD8B29', 3);
INSERT INTO `user` VALUES (7, '1106401001', '35D1C06469FB5E7A9F4E7312FFF1A4BE', 1);
INSERT INTO `user` VALUES (8, '06033', '6398796F364CE2E7005F31BBD08BA210', 3);
INSERT INTO `user` VALUES (9, '1115403025', 'EA20A043C08F5168D4409FF4144F32E2', 2);
INSERT INTO `user` VALUES (10, '010', 'EA20A043C08F5168D4409FF4144F32E2', 2);
INSERT INTO `user` VALUES (11, '1106401002', '8A4013A2506B464D90864FA7DA0B592D', 1);
INSERT INTO `user` VALUES (123468, '1106401023', 'C3CBE9150EF238FFF870D7F9BB07FDFF', 1);
INSERT INTO `user` VALUES (123469, '079', '38B18881FC8D864A177AFE3864C10ABA', 2);
INSERT INTO `user` VALUES (123470, '1106401013', 'C5926A8322EC5B1A2FAF400063C01722', 1);
